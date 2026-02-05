# Yuezhuo Jenkins 任务操作手册

## 概述

本文档描述了基于 Jenkins 的 Yuezhuo 自动化构建流程，包括主工程构建、插件打包、Docker 镜像打包等任务的配置和操作方法。

## 访问信息

| 项目 | 信息 |
|------|------|
| Jenkins 地址 | http://192.168.2.48:18081/ |
| 用户名 | admin |
| 密码 | huaiye2020** |

---

## 任务架构

```
┌─────────────────────────────────────────────────┐
│  yuezhuo-main-build（主工程构建）                  │
│  - 仅在主工程更新时运行                            │
│  - 输出：构建好的主工程环境                         │
└──────────────────────┬────────────────────────────┘
                       │ 依赖
       ┌───────────────┼───────────────┬───────────────┐
       ↓               ↓               ↓               ↓
┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
│ plugin-manual │ │ plugin-auto  │ │plugin-release│ │ docker-build │
│  手动打包     │ │  编译验证    │ │  转测归档    │ │ Docker打包   │
└──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘
```

---

## 共享目录

| 目录 | 说明 |
|------|------|
| `/mnt/sdb3/yuezhuo-workspace/yuezhuo/` | 主工程目录 |
| `/mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/` | 插件仓库目录 |
| `/mnt/sdb3/Jenkins_Yuezhuo/` | 打包产物归档目录 |

---

## 任务列表

| 任务名称 | 脚本文件 | 功能 | 触发方式 |
|----------|----------|------|----------|
| yuezhuo-main-build | `Jenkinsfile.main-build` | 主工程构建 | 手动 |
| yuezhuo-plugin-manual | `Jenkinsfile.plugin.manual-build` | 手动选择插件打包 | 手动 |
| yuezhuo-plugin-auto | `Jenkinsfile.plugin.auto-build` | 自动编译验证 | Poll SCM |
| yuezhuo-plugin-release | `Jenkinsfile.plugin.release-svn` | 打包并归档到 SVN | 手动 |
| yuezhuo-docker-build | `Jenkinsfile.docker-build` | Docker 镜像打包 | 手动 |

---

## 任务详细说明

### 1. yuezhuo-main-build（主工程构建）

**脚本文件**：`Jenkinsfile.main-build`

#### 功能
- 克隆/更新 Yuezhuo 主项目代码
- 克隆/更新插件仓库代码
- 安装依赖并构建主项目
- 生成 `.build_complete` 标记文件

#### 参数

| 参数 | 默认值 | 说明 |
|------|--------|------|
| YUEZHUO_REPO | `http://192.168.1.6:9000/innovation/yuezhuo.git` | 主项目仓库地址 |
| YUEZHUO_BRANCH | `develop` | 主项目分支 |
| FORCE_REBUILD | `false` | 强制重新构建 |
| CLEAN_WORKSPACE | `false` | 清理工作区后重新构建 |
| PLUGINS_REPO | `http://192.168.1.6:9000/innovation/yuezhuo-plugins.git` | 插件仓库地址 |
| PLUGINS_BRANCH | `master` | 插件仓库分支 |

#### 运行时机
- **首次部署**：必须运行
- **主工程代码更新**：需要运行
- **日常插件打包**：不需要运行

> [!IMPORTANT]
> 主工程构建时间较长（约 30-40 分钟），日常插件打包无需重新构建主工程。

---

### 2. yuezhuo-plugin-manual（手动打包）

**脚本文件**：`Jenkinsfile.plugin.manual-build`

#### 功能
- 从复选框手动选择要打包的插件
- 支持多选同时打包多个插件
- 支持一键打包所有插件
- 自动追加时间戳到版本号
- 归档 .tgz 文件到数据盘
- **校验 huaiye 字段**（非空校验）

#### 参数

| 参数 | 默认值 | 说明 |
|------|--------|------|
| SELECTED_PLUGINS | 复选框 | 选择要打包的插件 |
| PLUGINS_REPO | `http://192.168.1.6:9000/innovation/yuezhuo-plugins.git` | 插件仓库地址 |
| PLUGINS_BRANCH | `master` | 插件仓库分支 |
| UPDATE_PLUGINS_REPO | `true` | 是否更新插件仓库 |
| BUILD_ALL | `false` | 打包所有插件 |

---

### 3. yuezhuo-plugin-auto（自动编译验证）

**脚本文件**：`Jenkinsfile.plugin.auto-build`

#### 功能
- 定时检查插件仓库是否有更新
- 自动检测变更的插件
- **只进行编译验证，不打包**
- **编译失败时发送邮件通知**

#### 触发方式

使用 **Poll SCM 轮询方式**：

1. Jenkins 任务配置 → Build Triggers
2. 勾选 **Poll SCM**
3. 日程表填写：`H/5 * * * *`（每 5 分钟检查一次）
4. 保存

> [!NOTE]
> 此任务只做编译验证，不会生成打包产物。如需打包请使用 `yuezhuo-plugin-manual` 任务。

---

### 4. yuezhuo-plugin-release（转测归档）

**脚本文件**：`Jenkinsfile.plugin.release-svn`

#### 功能
- 打包插件后自动上传到 SVN 受控库
- 按年月日（yyyy-MM-dd）创建归档目录
- 用于正式转测版本归档
- **校验 huaiye 字段**（非空校验）

#### 参数

| 参数 | 默认值 | 说明 |
|------|--------|------|
| SELECTED_PLUGINS | 复选框 | 选择要打包的插件 |
| SVN_BASE_URL | `http://192.168.1.6/svn/product_version/...` | SVN 归档路径 |
| BUILD_ALL | `false` | 打包所有插件 |

---

### 5. yuezhuo-docker-build（Docker 镜像打包）

**脚本文件**：`Jenkinsfile.docker-build`

#### 功能
- 将已构建的主工程打包为 Docker 镜像
- 导出为 tar 包：`yuezhuo-<版本号>.tar`
- 支持自定义版本号

#### 参数

| 参数 | 默认值 | 说明 |
|------|--------|------|
| VERSION | `2.0.1` | 版本号，生成 yuezhuo-2.0.1.tar |
| YUEZHUO_BRANCH | `develop` | 主工程分支 |

#### 输出
- Docker 镜像：`yuezhuo:<版本号>`
- Tar 包：`/mnt/sdb3/Jenkins_Yuezhuo/yuezhuo-<版本号>.tar`

> [!WARNING]
> 此任务依赖 `yuezhuo-main-build` 先构建主工程。

---

## 插件 huaiye 字段校验

所有插件打包任务都会对 `package.json` 中的 `huaiye` 字段进行**非空校验**。

### 必需字段

```json
{
  "huaiye": {
    "pluginCode": "HY-AIC-AIM-AGT-001",   // 必填，字符串
    "mgmtCategory": "AIC",                  // 必填，字符串
    "userCategory": ["AIM"],                // 必填，非空数组
    "func": "AGT",                          // 必填，字符串
    "owner": "luchenkai",                   // 必填，字符串
    "lifecycle": "ACTIVE"                   // 必填，字符串
  }
}
```

### 校验规则

| 字段 | 类型 | 规则 |
|------|------|------|
| pluginCode | string | 必须存在且不为空 |
| mgmtCategory | string | 必须存在且不为空 |
| userCategory | array | 必须存在且数组长度 > 0 |
| func | string | 必须存在且不为空 |
| owner | string | 必须存在且不为空 |
| lifecycle | string | 必须存在且不为空 |

> [!CAUTION]
> 校验失败时构建会中止！请确保插件的 `package.json` 配置正确。

---

## 版本号规范

### 格式

```
{原版本号}.{时间戳}
```

### 示例

| package.json 版本 | 打包后版本 | 包名 |
|------------------|-----------|------|
| 1.0.0 | 1.0.0.20260116.173149 | plugin-xxx-1.0.0.20260116.173149.tgz |
| 2.0.0-alpha.43 | 2.0.0-alpha.43.20260116.173149 | plugin-xxx-2.0.0-alpha.43.20260116.173149.tgz |

---

## 操作指南

### 首次配置

1. **创建 Jenkins 凭证**
   - 进入 Jenkins → Manage Jenkins → Credentials
   - 添加 Git 凭证，ID 设为 `yuezhuo-git-credentials`
   - 添加 SVN 凭证，ID 设为 `svn-release-credentials`

2. **安装必要插件**
   - Active Choices（用于动态复选框）
   - Subversion（用于 SVN 上传）
   - Email Extension（用于邮件通知）

3. **创建任务**

   | 任务名 | Pipeline Definition |
   |--------|---------------------|
   | yuezhuo-main-build | Pipeline script（粘贴 Jenkinsfile.main-build 内容） |
   | yuezhuo-plugin-manual | Pipeline script（粘贴 Jenkinsfile.plugin.manual-build 内容） |
   | yuezhuo-plugin-auto | Pipeline script（粘贴 Jenkinsfile.plugin.auto-build 内容） |
   | yuezhuo-plugin-release | Pipeline script（粘贴 Jenkinsfile.plugin.release-svn 内容） |
   | yuezhuo-docker-build | Pipeline script（粘贴 Jenkinsfile.docker-build 内容） |

4. **首次运行主工程构建**
   - 进入 `yuezhuo-main-build`
   - 点击 Build Now
   - 等待构建完成（约 30-40 分钟）

---

### 日常操作

#### 打包单个插件（手动）

1. 进入 `yuezhuo-plugin-manual`
2. 点击 **Build with Parameters**
3. 在复选框中勾选目标插件
4. 点击 **Build**
5. 等待构建完成
6. 在 `/mnt/sdb3/Jenkins_Yuezhuo/` 获取打包产物

#### 打包并归档到 SVN（转测）

1. 进入 `yuezhuo-plugin-release`
2. 点击 **Build with Parameters**
3. 勾选目标插件
4. 确认 SVN 路径正确
5. 点击 **Build**

#### Docker 镜像打包

1. 确保已运行 `yuezhuo-main-build` 构建主工程
2. 进入 `yuezhuo-docker-build`
3. 点击 **Build with Parameters**
4. 填写版本号（如 `2.0.1`）
5. 点击 **Build**
6. 输出文件：`/mnt/sdb3/Jenkins_Yuezhuo/yuezhuo-2.0.1.tar`

---

## 制品管理

### 归档目录结构

```
/mnt/sdb3/Jenkins_Yuezhuo/
├── 20260116_171500_build1/
│   ├── plugin-message-queue-1.0.0.20260116.171500.tgz
│   ├── plugin-video-player-1.0.0.20260116.171500.tgz
│   └── BUILD_INFO.txt
├── 20260116_173000_release_build2/
│   └── ...
├── yuezhuo-2.0.1.tar              # Docker 镜像包
└── ...
```

### 保留策略

- **保留天数**：7 天
- **清理时机**：每次构建完成后自动清理超过 7 天的制品

---

## 常见问题

### Q: 构建失败，提示"主工程尚未构建"？

A: 需要先运行 `yuezhuo-main-build` 任务构建主工程。

### Q: Git 凭证错误？

A: 检查 Jenkins 凭证配置，确保 ID 为 `yuezhuo-git-credentials`。

### Q: 复选框不显示插件列表？

A: 
1. 确保已运行过 `yuezhuo-main-build` 任务
2. 检查 Script Approval 是否有待批准的脚本
3. 确保取消勾选了 Groovy 沙盒

### Q: SVN 上传失败？

A: 检查服务器是否已安装 SVN（`sudo apt-get install subversion`）。

### Q: huaiye 字段校验失败？

A: 检查插件的 `package.json` 文件，确保 `huaiye` 字段包含所有必需属性且值非空。

### Q: 自动编译验证失败后如何查看详情？

A: 
1. 检查 Jenkins 控制台日志
2. 如已配置邮件，检查邮件通知中的详细信息

---

## 文件清单

| 文件 | 说明 |
|------|------|
| `Jenkinsfile.main-build` | 主工程构建 Pipeline 脚本 |
| `Jenkinsfile.plugin.manual-build` | 手动插件打包 Pipeline 脚本 |
| `Jenkinsfile.plugin.auto-build` | 自动编译验证 Pipeline 脚本 |
| `Jenkinsfile.plugin.release-svn` | 转测归档 Pipeline 脚本 |
| `Jenkinsfile.docker-build` | Docker 镜像打包 Pipeline 脚本 |
| `Jenkinsfile.plugin.auto-build.bak` | 自动编译验证脚本备份 |

---

## 维护信息

- **创建时间**：2026-01-16
- **更新时间**：2026-02-02
- **维护团队**：研发部

### 更新记录

| 日期 | 更新内容 |
|------|----------|
| 2026-01-27 | 新增 Docker 镜像打包任务 |
| 2026-01-28 | 自动触发任务改为编译验证，移除打包功能 |
| 2026-01-28 | 新增 huaiye 字段非空校验（使用 jq） |
| 2026-02-02 | 整理操作手册 |
