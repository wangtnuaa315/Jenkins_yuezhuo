# Yuezhuo 代码质量检查模块使用指南

## 📖 概述

`quality-check.groovy` 是一个 Jenkins 共享库，提供统一的代码质量检查功能。

**文件路径**: `d:/PythonProject/Jenkins/Yuezhuo/quality-check.groovy`

---

## 🔍 检查项说明

| 检查项 | 说明 | 输出文件 |
|--------|------|----------|
| **ESLint** | 代码规范、语法错误、代码风格 | `eslint-report.json` |
| **单元测试** | 验证功能正确性 | `junit.xml` |
| **代码覆盖率** | 测试覆盖代码的百分比 | `cobertura-coverage.xml` |
| **依赖安全扫描** | 检查依赖包是否有已知漏洞 | 控制台输出 |

### 圈复杂度 (Cyclomatic Complexity)

圈复杂度衡量代码的复杂程度，计算方式为：**独立路径数量 = 判断节点数 + 1**

| 复杂度值 | 含义 | 建议 |
|---------|------|------|
| 1-10 | ✅ 简单，易维护 | 保持 |
| 11-20 | ⚠️ 中等复杂 | 考虑重构 |
| 21-50 | ❌ 高复杂度 | 必须拆分 |
| >50 | 🚫 不可测试 | 立即重构 |

**`max=10` 表示：当函数复杂度超过 10 时，ESLint 会发出警告。**

启用方式：在 `.eslintrc.json` 中添加：
```json
{ "rules": { "complexity": ["warn", { "max": 10 }] } }
```

---

## 🚀 使用方法

### 方式一：执行所有检查

```groovy
stage('代码质量检查') {
    steps {
        script {
            def qualityChecks = load 'quality-check.groovy'
            qualityChecks.runAllQualityChecks(env.YUEZHUO_DIR)
        }
    }
    post {
        always {
            script {
                def qualityChecks = load 'quality-check.groovy'
                qualityChecks.publishReports(env.YUEZHUO_DIR)
            }
        }
    }
}
```

### 方式二：单独执行某项检查

```groovy
script {
    def qualityChecks = load 'quality-check.groovy'
    
    qualityChecks.runLint(env.YUEZHUO_DIR)         // 仅 ESLint
    qualityChecks.runTests(env.YUEZHUO_DIR)        // 仅单元测试
    qualityChecks.runCoverage(env.YUEZHUO_DIR)     // 仅覆盖率
    qualityChecks.runSecurityAudit(env.YUEZHUO_DIR) // 仅安全扫描
}
```

---

## 🔌 所需 Jenkins 插件

| 插件名称 | 用途 | 必需 |
|---------|------|------|
| Warnings Next Generation | ESLint 报告展示 | ✅ |
| JUnit | 测试报告展示 | ✅ (内置) |
| Coverage 或 Cobertura | 覆盖率报告 | ✅ |

---

## 📊 报告方案建议

推荐使用 **Allure Report** 或 **自定义 HTML 报告**，可以将所有检查结果整合为一份精美报告。

如需生成 HTML 报告，请参考 `report_demos/` 目录中的模板。
