Started by user admin
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/Yuezhuo-plugin-release
[Pipeline] {
[Pipeline] withEnv
[Pipeline] {
[Pipeline] stage
[Pipeline] { (环境检查)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ echo ======== 环境信息 ========
======== 环境信息 ========
+ node -v
+ echo Node.js 版本: v22.13.1
Node.js 版本: v22.13.1
+ yarn -v
+ echo Yarn 版本: 1.22.22
Yarn 版本: 1.22.22
+ svn --version --quiet
+ echo SVN 版本: 1.14.1
SVN 版本: 1.14.1
+ echo 共享工作目录: /mnt/sdb3/yuezhuo-workspace
共享工作目录: /mnt/sdb3/yuezhuo-workspace
+ echo =========================
=========================
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (检查主工程)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/.build_complete
+ echo yes
[Pipeline] echo
主工程已构建，继续...
[Pipeline] sh
+ cat /mnt/sdb3/yuezhuo-workspace/yuezhuo/.build_complete
2026-01-27 08:59:27
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (更新插件仓库)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ mkdir -p /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins
[Pipeline] {
[Pipeline] echo
更新插件仓库...
[Pipeline] checkout
The recommended git tool is: NONE
using credential yuezhuo-git-credentials
 > git rev-parse --resolve-git-dir /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url http://192.168.1.6:9000/innovation/yuezhuo-plugins.git # timeout=10
Fetching upstream changes from http://192.168.1.6:9000/innovation/yuezhuo-plugins.git
 > git --version # timeout=10
 > git --version # 'git version 2.34.1'
using GIT_ASKPASS to set credentials 鸑鷟 git仓库凭证
 > git fetch --tags --force --progress -- http://192.168.1.6:9000/innovation/yuezhuo-plugins.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
Checking out Revision a439ba09bc83b467b67aaf18e87782e9f711ebbd (refs/remotes/origin/master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f a439ba09bc83b467b67aaf18e87782e9f711ebbd # timeout=10
Commit message: "删除构建脚本"
 > git rev-list --no-walk a439ba09bc83b467b67aaf18e87782e9f711ebbd # timeout=10
[Pipeline] sh
+ git rev-parse --short HEAD
[Pipeline] echo
插件仓库 Commit: a439ba0
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (确定打包插件)
[Pipeline] script
[Pipeline] {
[Pipeline] echo
选中插件: plugin-agent-chat
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (拷贝插件到主项目)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ echo 确保插件目录存在...
确保插件目录存在...
+ mkdir -p /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins
[Pipeline] echo
======== 拷贝用户选择的插件 ========
[Pipeline] sh
+ echo 拷贝: plugin-agent-chat
拷贝: plugin-agent-chat
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/plugin-agent-chat
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/plugin-agent-chat /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/
[Pipeline] echo
插件拷贝完成
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (校验插件配置)
[Pipeline] script
[Pipeline] {
[Pipeline] echo
======== 校验插件 package.json 配置（非空校验）========
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/plugin-agent-chat/package.json
+ echo yes
[Pipeline] sh
+ jq -e 
                                        .huaiye != null and
                                        (.huaiye.pluginCode | type == "string" and length > 0) and
                                        (.huaiye.mgmtCategory | type == "string" and length > 0) and
                                        (.huaiye.userCategory | type == "array" and length > 0) and
                                        (.huaiye.func | type == "string" and length > 0) and
                                        (.huaiye.owner | type == "string" and length > 0) and
                                        (.huaiye.lifecycle | type == "string" and length > 0)
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/plugin-agent-chat/package.json
+ echo valid
[Pipeline] echo
✅ plugin-agent-chat: 配置正确
[Pipeline] echo
✅ 所有插件配置校验通过
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (重新安装依赖)
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ echo ======== 重新安装依赖 ========
======== 重新安装依赖 ========
+ yarn install --frozen-lockfile
yarn install v1.22.22
error There are more than one workspace with name "@huaiye/plugin-agent-chat"
info Visit https://yarnpkg.com/en/docs/cli/install for documentation about this command.
+ yarn install
yarn install v1.22.22
error There are more than one workspace with name "@huaiye/plugin-agent-chat"
info Visit https://yarnpkg.com/en/docs/cli/install for documentation about this command.
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (构建打包)
Stage "构建打包" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (归档制品)
Stage "归档制品" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (上传到 SVN)
Stage "上传到 SVN" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (清理旧制品)
Stage "清理旧制品" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] echo

            ========================================
            ❌ 转测归档失败！
            ========================================
            请检查控制台日志获取详细错误信息。
            ========================================
            
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
ERROR: script returned exit code 1
Finished: FAILURE