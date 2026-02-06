Started by user admin
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/Yuezhuo-plugin-manual
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
Checking out Revision 1246602f85b42af0f1523ba58539ac27a549f52a (refs/remotes/origin/master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 1246602f85b42af0f1523ba58539ac27a549f52a # timeout=10
Commit message: "fix: 修改乱码问题"
 > git rev-list --no-walk 1246602f85b42af0f1523ba58539ac27a549f52a # timeout=10
[Pipeline] sh
+ git rev-parse --short HEAD
[Pipeline] echo
插件仓库 Commit: 1246602
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
+ mkdir -p /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye
[Pipeline] echo
======== 拷贝用户选择的插件 ========
[Pipeline] sh
+ echo 拷贝: plugin-agent-chat
拷贝: plugin-agent-chat
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-agent-chat
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-agent-chat /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
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
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-agent-chat/package.json
+ echo yes
[Pipeline] sh
+ jq -e 
                                        (.name | type == "string" and startswith("@huaiye/")) and
                                        .huaiye != null and
                                        (.huaiye.pluginCode | type == "string" and length > 0) and
                                        (.huaiye.mgmtCategory | type == "string" and length > 0) and
                                        (.huaiye.userCategory | type == "array" and length > 0) and
                                        (.huaiye.func | type == "string" and length > 0) and
                                        (.huaiye.owner | type == "string" and length > 0) and
                                        (.huaiye.lifecycle | type == "string" and length > 0)
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-agent-chat/package.json
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
[1/5] Validating package.json...
[2/5] Resolving packages...
warning Resolution field "dayjs@1.11.13" is incompatible with requested version "dayjs@~1.8.24"
warning Resolution field "nwsapi@2.2.7" is incompatible with requested version "nwsapi@^2.2.12"
warning workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @huaiye/plugin-agent-chat > multer@1.4.5-lts.2: Multer 1.x is impacted by a number of vulnerabilities, which have been patched in 2.x. You should upgrade to the latest 2.x version.
[3/5] Fetching packages...
[4/5] Linking dependencies...
warning " > eslint-plugin-prettier@5.5.4" has unmet peer dependency "prettier@>=3.0.0".
warning " > pretty-quick@3.1.3" has unmet peer dependency "prettier@>=2.0.0".
warning " > @nocobase/client@2.0.0-alpha.43" has unmet peer dependency "react-is@>=18.0.0".
warning " > @huaiye/plugin-agent-chat@2.0.0-alpha.43" has unmet peer dependency "axios@~1.7.0".
warning " > @huaiye/plugin-agent-chat@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @huaiye/plugin-agent-chat@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @huaiye/plugin-deep-research@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @huaiye/plugin-deep-research@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @huaiye/plugin-deep-research@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-action-group@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-action-group@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-action-group@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-block-custom-resource@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-block-custom-resource@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-block-custom-resource@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-block-filter-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-block-filter-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-block-filter-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-block-timeline@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-block-timeline@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-block-timeline@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-collection-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-collection-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-collection-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-custom-details-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-custom-details-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-custom-details-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-custom-form-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-custom-form-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-custom-form-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-custom-table-block-action-group@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-custom-table-block-action-group@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-custom-table-block-action-group@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-custom-table-block-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-custom-table-block-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-custom-table-block-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-custom-table-block-resource@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-custom-table-block-resource@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-custom-table-block-resource@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-custom-table-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-custom-table-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-custom-table-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-data-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-data-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-data-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-field-simple@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-field-simple@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-field-simple@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-nested-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-nested-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-nested-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-nested-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-nested-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-nested-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-simple-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-simple-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-simple-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-simple-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-simple-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-simple-block@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase-example/plugin-simple-popup-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase-example/plugin-simple-popup-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase-example/plugin-simple-popup-action@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/acl@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/cache@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-acl@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-action-bulk-edit@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-bulk-edit@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-bulk-edit@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-action-bulk-update@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-bulk-update@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-bulk-update@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-action-custom-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-custom-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-custom-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-action-duplicate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-duplicate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-duplicate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-action-export@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-action-export@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-export@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-action-export@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-export@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-action-export@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-action-import@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-action-import@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-import@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-action-import@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-import@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-action-import@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-action-print@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-action-print@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-action-print@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-ai@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-ai@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/flow-engine@2.x".
warning " > @nocobase/plugin-ai@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-manager@2.x".
warning " > @nocobase/plugin-ai@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-workflow@2.x".
warning " > @nocobase/plugin-ai@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-ai@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-api-doc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-api-doc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-api-doc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/resourcer@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-api-keys@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-async-task-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-async-task-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-error-handler@2.x".
warning " > @nocobase/plugin-async-task-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-async-task-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-audit-logs@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-audit-logs@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-audit-logs@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-audit-logs@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/auth@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-verification@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-auth-sms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-auth@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-auth@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/auth@2.x".
warning " > @nocobase/plugin-auth@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-auth@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-auth@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-auth@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-backup-restore@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-backup-restore@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-backup-restore@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-backup-restore@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-backup-restore@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-backup-restore@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-block-grid-card@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@1.x".
warning " > @nocobase/plugin-block-grid-card@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@1.x".
warning " > @nocobase/plugin-block-grid-card@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@1.x".
warning " > @nocobase/plugin-block-iframe@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-block-iframe@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-block-iframe@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-block-iframe@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-block-iframe@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-block-list@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@1.x".
warning " > @nocobase/plugin-block-list@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@1.x".
warning " > @nocobase/plugin-block-list@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@1.x".
warning " > @nocobase/plugin-block-markdown@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@1.x".
warning " > @nocobase/plugin-block-markdown@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@1.x".
warning " > @nocobase/plugin-block-markdown@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@1.x".
warning " > @nocobase/plugin-block-reference@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-block-reference@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/flow-engine@2.x".
warning " > @nocobase/plugin-block-reference@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-block-reference@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-block-template@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-block-template@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-ui-schema-storage@2.x".
warning " > @nocobase/plugin-block-template@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-block-template@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-block-workbench@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-block-workbench@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-mobile@2.x".
warning " > @nocobase/plugin-block-workbench@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-block-workbench@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-calendar@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-calendar@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-calendar@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-charts@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-charts@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-charts@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-charts@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-charts@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-localization@2.x".
warning " > @nocobase/plugin-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-collection-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-collection-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-collection-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-collection-tree@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-collection-tree@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-collection-tree@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-error-handler@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-field-sort@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-data-source-main@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-data-source-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-data-source-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-acl@2.x".
warning " > @nocobase/plugin-data-source-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-data-source-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/cache@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-ai@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-main@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-data-visualization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-departments@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-departments@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-departments@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-user-data-sync@2.x".
warning " > @nocobase/plugin-departments@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-departments@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-disable-pm-add@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-disable-pm-add@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-disable-pm-add@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-environment-variables@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-environment-variables@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-environment-variables@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-error-handler@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-error-handler@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-error-handler@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-error-handler@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-error-handler@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-field-attachment-url@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-attachment-url@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-file-manager@2.x".
warning " > @nocobase/plugin-field-attachment-url@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-attachment-url@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-china-region@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-china-region@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-field-china-region@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-china-region@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-formula@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-formula@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-field-formula@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/evaluators@2.x".
warning " > @nocobase/plugin-field-formula@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-formula@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-formula@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-field-m2m-array@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-m2m-array@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-m2m-array@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-markdown-vditor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-markdown-vditor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-markdown-vditor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-main@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-sequence@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/lock-manager@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has unmet peer dependency "lodash@4.17.21".
warning " > @nocobase/plugin-field-sort@2.0.0-alpha.43" has unmet peer dependency "sequelize@^6.26.0".
warning " > @nocobase/plugin-file-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-file-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-file-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-file-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-file-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-file-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-file-previewer-office@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-file-previewer-office@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-file-previewer-office@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-flow-engine@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-flow-engine@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-flow-engine@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-localization@2.x".
warning " > @nocobase/plugin-flow-engine@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-flow-engine@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-flow-engine@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-gantt@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-gantt@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-gantt@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-graph-collection-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-graph-collection-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-graph-collection-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-graph-collection-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-graph-collection-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-hello@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-hello@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-hello@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-kanban@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-kanban@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-kanban@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-license@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-license@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-license@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-locale-tester@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-locale-tester@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-locale-tester@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-localization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/cache@2.x".
warning " > @nocobase/plugin-localization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-localization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-localization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-localization@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-logger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-logger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-logger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-logger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-map@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-map@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-map@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-map@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-map@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-map@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-mobile-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-mobile-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-mobile-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-mobile-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-mobile-client@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-mobile@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-mobile@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-acl@2.x".
warning " > @nocobase/plugin-mobile@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-localization@2.x".
warning " > @nocobase/plugin-mobile@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-mobile@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-mock-collections@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-mock-collections@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-mock-collections@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-error-handler@2.x".
warning " > @nocobase/plugin-mock-collections@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-mock-collections@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-mock-collections@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-multi-app-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-multi-app-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-multi-app-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-multi-app-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-multi-app-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-main@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-error-handler@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-multi-app-manager@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-users@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-multi-app-share-collection@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-notification-email@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-notification-email@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-notification-manager@2.x".
warning " > @nocobase/plugin-notification-email@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-notification-email@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-notification-in-app-message@2.0.0-alpha.43" has unmet peer dependency "@formily/reactive@2.x".
warning " > @nocobase/plugin-notification-in-app-message@2.0.0-alpha.43" has unmet peer dependency "@formily/reactive-react@^2".
warning " > @nocobase/plugin-notification-in-app-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-notification-in-app-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-notification-manager@2.x".
warning " > @nocobase/plugin-notification-in-app-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-notification-in-app-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-notification-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-notification-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-notification-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-notification-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-notification-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-notification-manager@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-notifications@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-notifications@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-notifications@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-notifications@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-notifications@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-public-forms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-public-forms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-client@2.x".
warning " > @nocobase/plugin-public-forms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-ui-schema-storage@2.x".
warning " > @nocobase/plugin-public-forms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-public-forms@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-snapshot-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-snapshot-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-snapshot-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-snapshot-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-snapshot-field@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-system-settings@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-system-settings@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-system-settings@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-system-settings@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-text-copy@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-text-copy@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-text-copy@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-theme-editor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-theme-editor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-theme-editor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-theme-editor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-theme-editor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-theme-editor@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/cache@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-error-handler@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/resourcer@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-ui-schema-storage@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-user-data-sync@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-user-data-sync@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-user-data-sync@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-acl@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-auth@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-system-settings@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-ui-schema-storage@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-user-data-sync@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/resourcer@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-users@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/resourcer@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-verification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-workflow-action-trigger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-action-trigger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-action-trigger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-action-trigger@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-aggregate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-aggregate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-aggregate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-aggregate@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-ui-schema-storage@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-users@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-workflow@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-cc@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-workflow-delay@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-delay@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-delay@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-delay@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/evaluators@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-main@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-dynamic-calculation@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-workflow-json-query@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@1.x".
warning " > @nocobase/plugin-workflow-json-query@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-workflow@1.x".
warning " > @nocobase/plugin-workflow-json-query@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@1.x".
warning " > @nocobase/plugin-workflow-json-query@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@1.x".
warning " > @nocobase/plugin-workflow-json-query@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@1.x".
warning " > @nocobase/plugin-workflow-json-variable-mapping@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@1.x".
warning " > @nocobase/plugin-workflow-json-variable-mapping@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-workflow@1.x".
warning " > @nocobase/plugin-workflow-json-variable-mapping@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@1.x".
warning " > @nocobase/plugin-workflow-json-variable-mapping@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@1.x".
warning " > @nocobase/plugin-workflow-loop@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-loop@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-loop@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/evaluators@2.x".
warning " > @nocobase/plugin-workflow-loop@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-loop@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-mailer@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-mailer@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-mailer@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-mailer@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-main@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-users@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-manual@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-workflow-notification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-notification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-notification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-notification@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-parallel@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-parallel@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-parallel@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-parallel@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-file-manager@2.x".
warning " > @nocobase/plugin-workflow-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-request@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-response-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-response-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-workflow@2.x".
warning " > @nocobase/plugin-workflow-response-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-response-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-response-message@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning " > @nocobase/plugin-workflow-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-sql@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow-test@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow-test@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow-test@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/actions@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/client@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/database@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/evaluators@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/logger@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-data-source-main@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-error-handler@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-mobile@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-users@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/plugin-workflow-test@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/resourcer@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/server@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/test@2.x".
warning " > @nocobase/plugin-workflow@2.0.0-alpha.43" has incorrect peer dependency "@nocobase/utils@2.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > @rsbuild/plugin-babel@1.0.3" has unmet peer dependency "@rsbuild/core@1.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > babel-loader@9.2.1" has unmet peer dependency "webpack@>=5".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > bundle-require@5.1.0" has unmet peer dependency "esbuild@>=0.18".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > esbuild-register@3.6.0" has unmet peer dependency "esbuild@>=0.12 <1".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > postcss-loader@7.3.4" has unmet peer dependency "webpack@^5.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > style-loader@3.3.4" has unmet peer dependency "webpack@^5.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > vite-plugin-css-injected-by-js@3.5.2" has unmet peer dependency "vite@>2.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > vite-plugin-lib-inject-css@1.2.0" has unmet peer dependency "vite@*".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/client > @formily/antd-v5@1.2.3" has unmet peer dependency "react-is@>=16.8.0 || >=17.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/client > @formily/react@2.3.7" has unmet peer dependency "react-is@>=16.8.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/client > @formily/reactive-react@2.3.7" has unmet peer dependency "react-is@>=16.8.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/client > react-image-lightbox@5.1.4" has incorrect peer dependency "react@16.x || 17.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/client > react-image-lightbox@5.1.4" has incorrect peer dependency "react-dom@16.x || 17.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/test > @testing-library/react-hooks@8.0.1" has incorrect peer dependency "react@^16.9.0 || ^17.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/test > @testing-library/user-event@14.5.1" has unmet peer dependency "@testing-library/dom@>=7.21.4".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/test > jsdom-worker@0.3.0" has unmet peer dependency "node-fetch@*".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-ai > @langchain/openai@0.6.13" has incorrect peer dependency "@langchain/core@>=0.3.68 <0.4.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-ai > use-context-selector@2.0.0" has unmet peer dependency "scheduler@>=0.19.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-backup-restore > @koa/multer@3.0.2" has unmet peer dependency "multer@*".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-data-visualization > @ant-design/plots@2.1.14" has unmet peer dependency "lodash-es@^4.17.21".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-workflow-json-query > @nocobase/plugin-workflow-test@1.9.8" has incorrect peer dependency "@nocobase/database@1.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-workflow-json-query > @nocobase/plugin-workflow-test@1.9.8" has incorrect peer dependency "@nocobase/server@1.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-workflow-json-query > @nocobase/plugin-workflow-test@1.9.8" has incorrect peer dependency "@nocobase/test@1.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/build > @rsdoctor/rspack-plugin > @rsdoctor/types@0.4.11" has unmet peer dependency "webpack@5.x".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/client > dumi > raw-loader@4.0.2" has unmet peer dependency "webpack@^4.0.0 || ^5.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-theme-editor > @arvinxu/layout-kit > styled-components@5.3.11" has unmet peer dependency "react-is@>= 16.8.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/bundler-webpack > @umijs/react-refresh-webpack-plugin@0.5.11" has unmet peer dependency "webpack@>=4.43.0 <6.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/bundler-webpack > css-loader@6.7.1" has unmet peer dependency "webpack@^5.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/bundler-webpack > fork-ts-checker-webpack-plugin@8.0.0" has unmet peer dependency "webpack@^5.11.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/lint > @typescript-eslint/eslint-plugin@5.62.0" has incorrect peer dependency "@typescript-eslint/parser@^5.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/lint > stylelint-config-standard@25.0.0" has unmet peer dependency "stylelint@^14.4.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/preset-umi > html-webpack-plugin@5.5.0" has unmet peer dependency "webpack@^5.20.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > @babel/plugin-transform-modules-commonjs@7.23.0" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest@29.7.0" has unmet peer dependency "@babel/core@^7.8.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/lint > stylelint-config-standard > stylelint-config-recommended@7.0.0" has unmet peer dependency "stylelint@^14.4.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest@29.6.3" has unmet peer dependency "@babel/core@^7.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/plugin-theme-editor > @arvinxu/layout-kit > styled-components > babel-plugin-styled-components > @babel/plugin-syntax-jsx@7.23.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax@1.0.1" has unmet peer dependency "@babel/core@^7.0.0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-async-generators@7.8.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-bigint@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-class-properties@7.12.13" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-import-meta@7.10.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-json-strings@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-logical-assignment-operators@7.10.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-nullish-coalescing-operator@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-numeric-separator@7.10.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-object-rest-spread@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-optional-catch-binding@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-optional-chaining@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-cc715536-24c9-4a5c-889d-d1bfa3d2bc0b > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-top-level-await@7.14.5" has unmet peer dependency "@babel/core@^7.0.0-0".
warning Workspaces can only be enabled in private projects.
warning Workspaces can only be enabled in private projects.
warning Workspaces can only be enabled in private projects.
[5/5] Building fresh packages...
$ nocobase postinstall
VERSION:  2.0.0-alpha.43
Done in 23.29s.
+ echo 依赖安装完成
依赖安装完成
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (构建打包)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ echo 清理旧的 tar 制品...
清理旧的 tar 制品...
+ rm -rf storage/tar/@huaiye
+ mkdir -p storage/tar/@huaiye
[Pipeline] echo
======== 开始处理: @huaiye/plugin-agent-chat ========
[Pipeline] sh
+ cat packages/plugins/@huaiye/plugin-agent-chat/package.json
+ grep "version"
+ head -1
+ sed s/.*"version": "\([^"]*\)".*/\1/
[Pipeline] sh
+ cd packages/plugins/@huaiye/plugin-agent-chat
+ cat package.json
+ grep "version"
+ head -1
+ sed s/.*"version": "\([^"]*\)".*/\1/
+ current_version=2.0.0-alpha.43
+ echo 当前版本: 2.0.0-alpha.43
当前版本: 2.0.0-alpha.43
+ new_version=2.0.0-alpha.43.20260205.200030
+ echo 新版本: 2.0.0-alpha.43.20260205.200030
新版本: 2.0.0-alpha.43.20260205.200030
+ sed -i s/"version": "[^"]*"/"version": "2.0.0-alpha.43.20260205.200030"/ package.json
+ grep "version" package.json
  "version": "2.0.0-alpha.43.20260205.200030",
[Pipeline] sh
+ yarn pm create @huaiye/plugin-agent-chat
yarn run v1.22.22
$ nocobase pm create @huaiye/plugin-agent-chat
WAIT: TypeScript compiling...
(node:367777) [DEP0040] DeprecationWarning: The `punycode` module is deprecated. Please use a userland alternative instead.
(Use `node --trace-deprecation ...` to show where the warning was created)
2026-02-05 20:00:43 [debug] create database instance: {"sync":{"alter":{"drop":false},"force":false},"logging":false,"dialect":"postgres","username":"nocobase","database":"nocobase","port":"5432","timezone":"+08:00","tablePrefix":"","underscored":false,"pool":{},"migrator":{"context":{"app":{"appName":"main","name":"main"}}},"logger":{},"rawTimezone":"+08:00"} meta={"databaseInstanceId":"Z6LI1EWZXTj_f6zxWJJ40"} module=database app=main reqId=13fb3708-d32e-4f72-9255-8bda7645eaea dataSourceKey=main
2026-02-05 20:00:43 [info]  add app main into supervisor                 module=application submodule=supervisor method=addApp app=main reqId=13fb3708-d32e-4f72-9255-8bda7645eaea dataSourceKey=main
[@huaiye/plugin-agent-chat] plugin already exists.
2026-02-05 20:00:43 [info]  attempt to add the plugin to the app         module=application app=main reqId=13fb3708-d32e-4f72-9255-8bda7645eaea dataSourceKey=main
2026-02-05 20:00:43 [info]  add plugin [@huaiye/plugin-agent-chat]       meta={"name":"@huaiye/plugin-agent-chat","packageName":"@huaiye/plugin-agent-chat","version":"2.0.0-alpha.43.20260205.200030"} module=application app=main reqId=13fb3708-d32e-4f72-9255-8bda7645eaea dataSourceKey=main
2026-02-05 20:00:43 [info]  [queue] gracefully shutting down...          module=application app=main reqId=13fb3708-d32e-4f72-9255-8bda7645eaea dataSourceKey=main
Done in 10.72s.
[Pipeline] sh
+ yarn build @huaiye/plugin-agent-chat --no-dts
yarn run v1.22.22
$ nocobase build @huaiye/plugin-agent-chat --no-dts
$ tsup
@huaiye/plugin-agent-chat: plugins/@huaiye/plugin-agent-chat build start
@huaiye/plugin-agent-chat: build plugin client
@huaiye/plugin-agent-chat: The build tool will package all dependencies into the dist directory, so you don't need to put them in @nocobase/client, @nocobase/server, axios, multer, react-markdown. Instead, they should be placed in dependencies. For more information, please refer to: devDependencies. https://docs.nocobase.com/development/others/deps
asset [1m[32mindex.js[39m[22m 172 KiB [1m[32m[emitted][39m[22m (name: main)
orphan modules 699 KiB [1m[33m[orphan][39m[22m 177 modules
runtime modules 971 bytes 4 modules
cacheable modules 674 KiB
  modules by path [1m../../../../node_modules/[39m[22m 12.9 KiB
    modules by path [1m../../../../node_modules/style-to-js/[39m[22m 8.59 KiB 4 modules
    modules by path [1m../../../../node_modules/react/[39m[22m 1.05 KiB 2 modules
    [1m../../../../node_modules/extend/index.js[39m[22m 3.24 KiB [1m[33m[built][39m[22m [1m[33m[code generated][39m[22m
  modules by path [1mexternal "@nocobase/[39m[22m 84 bytes
    [1mexternal "@nocobase/client"[39m[22m 42 bytes [1m[33m[built][39m[22m [1m[33m[code generated][39m[22m
    [1mexternal "@nocobase/flow-engine"[39m[22m 42 bytes [1m[33m[built][39m[22m [1m[33m[code generated][39m[22m
  [1m./src/client/index.tsx + 146 modules[39m[22m 661 KiB [1m[33m[code generated][39m[22m
  [1mexternal "react"[39m[22m 42 bytes [1m[33m[built][39m[22m [1m[33m[code generated][39m[22m
  [1mexternal "antd"[39m[22m 42 bytes [1m[33m[built][39m[22m [1m[33m[code generated][39m[22m
  [1mexternal "@ant-design/icons"[39m[22m 42 bytes [1m[33m[built][39m[22m [1m[33m[code generated][39m[22m
Rspack 1.3.2 compiled [1m[32msuccessfully[39m[22m in 1.05 s
@huaiye/plugin-agent-chat: build plugin server source
@huaiye/plugin-agent-chat: The build tool will package all dependencies into the dist directory, so you don't need to put them in @nocobase/client, @nocobase/server, axios, multer, react-markdown. Instead, they should be placed in dependencies. For more information, please refer to: devDependencies. https://docs.nocobase.com/development/others/deps
@huaiye/plugin-agent-chat: delete server files
@huaiye/plugin-agent-chat: build plugin server dependencies
@huaiye/plugin-agent-chat: These packages form-data will be bundled to dist/node_modules. These packages @nocobase/server, axios, @nocobase/utils will be exclude. For more information, please refer to: https://docs.nocobase.com/development/others/deps.
@huaiye/plugin-agent-chat: write external version
Done in 5.27s.
[Pipeline] sh
+ yarn nocobase tar @huaiye/plugin-agent-chat
yarn run v1.22.22
$ nocobase tar @huaiye/plugin-agent-chat
$ tsup
[33mThe CJS build of Vite's Node API is deprecated. See https://vitejs.dev/guide/troubleshooting.html#vite-cjs-node-api-deprecated for more details.[39m
@huaiye/plugin-agent-chat: tar package
Done in 3.16s.
[Pipeline] echo
✅ @huaiye/plugin-agent-chat 打包成功
[Pipeline] }
[Pipeline] // dir
[Pipeline] echo
======== 构建结果 ========
[Pipeline] echo
成功: 1 个
[Pipeline] echo
失败: 0 个
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (归档制品)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ echo ======== 归档制品 ========
======== 归档制品 ========
+ mkdir -p /mnt/sdb3/Jenkins_Yuezhuo/20260205_200056_build17
+ [ -d /mnt/sdb3/yuezhuo-workspace/yuezhuo/storage/tar/@huaiye ]
+ cp /mnt/sdb3/yuezhuo-workspace/yuezhuo/storage/tar/@huaiye/plugin-agent-chat-2.0.0-alpha.43.20260205.200030.tgz /mnt/sdb3/Jenkins_Yuezhuo/20260205_200056_build17/
+ cat
+ echo 制品已归档到: /mnt/sdb3/Jenkins_Yuezhuo/20260205_200056_build17
制品已归档到: /mnt/sdb3/Jenkins_Yuezhuo/20260205_200056_build17
+ ls -la /mnt/sdb3/Jenkins_Yuezhuo/20260205_200056_build17/
total 112
drwxr-xr-x 2 jenkins jenkins  4096  2月  5 20:00 .
drwxr-xr-x 5 jenkins jenkins  4096  2月  5 20:00 ..
-rw-r--r-- 1 jenkins jenkins   178  2月  5 20:00 BUILD_INFO.txt
-rw-r--r-- 1 jenkins jenkins 99337  2月  5 20:00 plugin-agent-chat-2.0.0-alpha.43.20260205.200030.tgz
[Pipeline] archiveArtifacts
Archiving artifacts
‘/mnt/sdb3/yuezhuo-workspace/yuezhuo/storage/tar/@huaiye/*.tgz’ doesn’t match anything: ‘’ exists but not ‘/mnt/sdb3/yuezhuo-workspace/yuezhuo/storage/tar/@huaiye/*.tgz’
No artifacts found that match the file pattern "/mnt/sdb3/yuezhuo-workspace/yuezhuo/storage/tar/@huaiye/*.tgz". Configuration error?
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (清理旧制品)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ echo ======== 清理超过 7 天的旧制品 ========
======== 清理超过 7 天的旧制品 ========
+ find /mnt/sdb3/Jenkins_Yuezhuo -maxdepth 1 -type d -mtime +7 -exec rm -rf {} ;
+ echo 清理完成
清理完成
+ echo 当前制品目录:
当前制品目录:
+ ls -lht /mnt/sdb3/Jenkins_Yuezhuo/
+ head -20
total 3.1G
drwxr-xr-x 2 jenkins jenkins 4.0K  2月  5 20:00 20260205_200056_build17
drwxr-xr-x 2 jenkins jenkins 4.0K  2月  5 19:50 20260205_195055_build16
-rw------- 1 jenkins jenkins 3.1G  2月  4 14:15 yuezhuo-2.0.1.tar
drwxr-xr-x 2 jenkins jenkins 4.0K  2月  3 17:28 20260203_172820_build1
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (生成报告)
[Pipeline] script
[Pipeline] {
[Pipeline] writeFile
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] script
[Pipeline] {
[Pipeline] mail
[Pipeline] echo
邮件发送成功
[Pipeline] }
[Pipeline] // script
[Pipeline] echo

            ========================================
            ❌ 插件打包失败！
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
an exception which occurred:
	in field com.cloudbees.groovy.cps.impl.BlockScopeEnv.locals
	in object com.cloudbees.groovy.cps.impl.BlockScopeEnv@25919274
	in field com.cloudbees.groovy.cps.impl.CallEnv.caller
	in object com.cloudbees.groovy.cps.impl.FunctionCallEnv@65750163
	in field com.cloudbees.groovy.cps.Continuable.e
	in object com.cloudbees.groovy.cps.Continuable@263cdea
	in field org.jenkinsci.plugins.workflow.cps.CpsThread.program
	in object org.jenkinsci.plugins.workflow.cps.CpsThread@22f3c4f2
	in field org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.threads
	in object org.jenkinsci.plugins.workflow.cps.CpsThreadGroup@7edf8850
	in object org.jenkinsci.plugins.workflow.cps.CpsThreadGroup@7edf8850
Also:   org.jenkinsci.plugins.workflow.actions.ErrorAction$ErrorId: 241c019e-893c-4694-9829-ceeaa2f03380
Caused: java.io.NotSerializableException: groovy.json.internal.LazyMap
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:278)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.writeKnownObject(RiverMarshaller.java:735)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:217)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.BlockMarshaller.doWriteObject(BlockMarshaller.java:65)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.BlockMarshaller.writeObject(BlockMarshaller.java:56)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.MarshallerObjectOutputStream.writeObjectOverride(MarshallerObjectOutputStream.java:50)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverObjectOutputStream.writeObjectOverride(RiverObjectOutputStream.java:179)
	at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:346)
	at java.base/java.util.HashMap.internalWriteEntries(HashMap.java:1944)
	at java.base/java.util.HashMap.writeObject(HashMap.java:1497)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.reflect.SerMethods.callWriteObject(SerMethods.java:69)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.reflect.SerializableClass.callWriteObject(SerializableClass.java:231)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1128)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteFields(RiverMarshaller.java:1182)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1140)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteFields(RiverMarshaller.java:1182)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1140)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1119)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteFields(RiverMarshaller.java:1182)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1140)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteFields(RiverMarshaller.java:1182)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1140)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.BlockMarshaller.doWriteObject(BlockMarshaller.java:65)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.BlockMarshaller.writeObject(BlockMarshaller.java:56)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.MarshallerObjectOutputStream.writeObjectOverride(MarshallerObjectOutputStream.java:50)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverObjectOutputStream.writeObjectOverride(RiverObjectOutputStream.java:179)
	at java.base/java.io.ObjectOutputStream.writeObject(ObjectOutputStream.java:346)
	at java.base/java.util.HashMap.internalWriteEntries(HashMap.java:1944)
	at java.base/java.util.HashMap.writeObject(HashMap.java:1497)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.reflect.SerMethods.callWriteObject(SerMethods.java:69)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.reflect.SerializableClass.callWriteObject(SerializableClass.java:231)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1128)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteFields(RiverMarshaller.java:1182)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteSerializableObject(RiverMarshaller.java:1140)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.river.RiverMarshaller.doWriteObject(RiverMarshaller.java:271)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.AbstractObjectOutput.writeObject(AbstractObjectOutput.java:58)
	at PluginClassLoader for workflow-support//org.jboss.marshalling.AbstractMarshaller.writeObject(AbstractMarshaller.java:116)
	at PluginClassLoader for workflow-support//org.jenkinsci.plugins.workflow.support.pickles.serialization.RiverWriter.lambda$writeObject$1(RiverWriter.java:147)
	at PluginClassLoader for script-security//org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.GroovySandbox.runInSandbox(GroovySandbox.java:329)
	at PluginClassLoader for workflow-support//org.jenkinsci.plugins.workflow.support.pickles.serialization.RiverWriter.writeObject(RiverWriter.java:146)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.saveProgram(CpsThreadGroup.java:639)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.saveProgram(CpsThreadGroup.java:613)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.saveProgramIfPossible(CpsThreadGroup.java:596)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.run(CpsThreadGroup.java:520)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup$2.call(CpsThreadGroup.java:372)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup$2.call(CpsThreadGroup.java:302)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsVmExecutorService.lambda$wrap$4(CpsVmExecutorService.java:143)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at hudson.remoting.SingleLaneExecutorService$1.run(SingleLaneExecutorService.java:139)
	at jenkins.util.ContextResettingExecutorService$1.run(ContextResettingExecutorService.java:28)
	at jenkins.security.ImpersonatingExecutorService$1.run(ImpersonatingExecutorService.java:68)
	at jenkins.util.ErrorLoggingExecutorService.lambda$wrap$0(ErrorLoggingExecutorService.java:51)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:539)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsVmExecutorService$1.call(CpsVmExecutorService.java:53)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsVmExecutorService$1.call(CpsVmExecutorService.java:50)
	at org.codehaus.groovy.runtime.GroovyCategorySupport$ThreadCategoryInfo.use(GroovyCategorySupport.java:136)
	at org.codehaus.groovy.runtime.GroovyCategorySupport.use(GroovyCategorySupport.java:275)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsVmExecutorService.lambda$categoryThreadFactory$0(CpsVmExecutorService.java:50)
	at java.base/java.lang.Thread.run(Thread.java:840)
Finished: FAILURE