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
[Pipeline] sh
+ ls -1 /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/+ 
sort
[Pipeline] echo
打包所有插件: 19 个
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
[Pipeline] sh
+ echo 拷贝: plugin-ai-knowledge-base
拷贝: plugin-ai-knowledge-base
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-ai-knowledge-base /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-deep-research
拷贝: plugin-deep-research
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-deep-research /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-device-tree-selector
拷贝: plugin-device-tree-selector
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-device-tree-selector /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-integration-workflow
拷贝: plugin-integration-workflow
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-integration-workflow /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-knowledge-graph
拷贝: plugin-knowledge-graph
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-knowledge-graph
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-knowledge-graph /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-llm-chat
拷贝: plugin-llm-chat
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-llm-chat
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-llm-chat /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-media-server-config
拷贝: plugin-media-server-config
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-media-server-config /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-message-queue
拷贝: plugin-message-queue
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-message-queue /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-pdf-viewer-ocr
拷贝: plugin-pdf-viewer-ocr
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-pdf-viewer-ocr /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-player
拷贝: plugin-player
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-player
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-player /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-rag
拷贝: plugin-rag
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-rag
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-rag /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-sie-comm-hub
拷贝: plugin-sie-comm-hub
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-sie-comm-hub /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-speech-service-center
拷贝: plugin-speech-service-center
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-speech-service-center /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-speech-to-text
拷贝: plugin-speech-to-text
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-speech-to-text /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-video-capture
拷贝: plugin-video-capture
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-video-capture /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-video-meeting
拷贝: plugin-video-meeting
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-video-meeting /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-video-player
拷贝: plugin-video-player
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-video-player /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
[Pipeline] sh
+ echo 拷贝: plugin-video-talk
拷贝: plugin-video-talk
+ rm -rf /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk
+ cp -r /mnt/sdb3/yuezhuo-workspace/yuezhuo-plugins/@huaiye/plugin-video-talk /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/
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
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-ai-knowledge-base/package.json
+ echo yes
[Pipeline] echo
❌ plugin-ai-knowledge-base: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-deep-research/package.json
+ echo yes
[Pipeline] echo
❌ plugin-deep-research: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-device-tree-selector/package.json
+ echo yes
[Pipeline] echo
❌ plugin-device-tree-selector: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-integration-workflow/package.json
+ echo yes
[Pipeline] echo
❌ plugin-integration-workflow: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-knowledge-graph/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-knowledge-graph/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-knowledge-graph/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-knowledge-graph/package.json
+ echo no
[Pipeline] echo
❌ plugin-knowledge-graph: 缺少 huaiye 字段
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-llm-chat/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-llm-chat/package.json
+ echo valid
[Pipeline] echo
✅ plugin-llm-chat: 配置正确
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-media-server-config/package.json
+ echo yes
[Pipeline] echo
❌ plugin-media-server-config: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-message-queue/package.json
+ echo yes
[Pipeline] echo
❌ plugin-message-queue: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-pdf-viewer-ocr/package.json
+ echo yes
[Pipeline] echo
❌ plugin-pdf-viewer-ocr: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-player/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-player/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-player/package.json
+ echo no
[Pipeline] echo
❌ plugin-player: 缺少 huaiye 字段
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-rag/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-rag/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-rag/package.json
+ echo no
[Pipeline] sh
+ jq -r .name // "(未设置)" /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-rag/package.json
[Pipeline] echo
❌ plugin-rag: name 字段必须以 @huaiye/ 开头，当前值: @nocobase/plugin-rag
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-sie-comm-hub/package.json
+ echo yes
[Pipeline] echo
❌ plugin-sie-comm-hub: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-service-center/package.json
+ echo yes
[Pipeline] echo
❌ plugin-speech-service-center: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-speech-to-text/package.json
+ echo yes
[Pipeline] echo
❌ plugin-speech-to-text: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-capture/package.json
+ echo yes
[Pipeline] echo
❌ plugin-video-capture: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-meeting/package.json
+ echo yes
[Pipeline] echo
❌ plugin-video-meeting: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-player/package.json
+ echo yes
[Pipeline] echo
❌ plugin-video-player: 字段缺失或为空: userCategory
[Pipeline] sh
+ test -f /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
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
                                     /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo invalid
[Pipeline] sh
+ jq -e .name | type == "string" and startswith("@huaiye/") /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye != null /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.pluginCode | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.mgmtCategory | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.userCategory | type == "array" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo no
[Pipeline] sh
+ jq -e .huaiye.func | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.owner | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] sh
+ jq -e .huaiye.lifecycle | type == "string" and length > 0 /mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/plugins/@huaiye/plugin-video-talk/package.json
+ echo yes
[Pipeline] echo
❌ plugin-video-talk: 字段缺失或为空: userCategory
[Pipeline] echo

                        ========================================
                        ❌ 以下插件配置不完整：
                        plugin-ai-knowledge-base (字段缺失或为空: userCategory)
plugin-deep-research (字段缺失或为空: userCategory)
plugin-device-tree-selector (字段缺失或为空: userCategory)
plugin-integration-workflow (字段缺失或为空: userCategory)
plugin-knowledge-graph (缺少 huaiye 字段)
plugin-media-server-config (字段缺失或为空: userCategory)
plugin-message-queue (字段缺失或为空: userCategory)
plugin-pdf-viewer-ocr (字段缺失或为空: userCategory)
plugin-player (缺少 huaiye 字段)
plugin-rag (name 字段必须以 @huaiye/ 开头，当前值: @nocobase/plugin-rag)
plugin-sie-comm-hub (字段缺失或为空: userCategory)
plugin-speech-service-center (字段缺失或为空: userCategory)
plugin-speech-to-text (字段缺失或为空: userCategory)
plugin-video-capture (字段缺失或为空: userCategory)
plugin-video-meeting (字段缺失或为空: userCategory)
plugin-video-player (字段缺失或为空: userCategory)
plugin-video-talk (字段缺失或为空: userCategory)
                        ========================================
                        
[Pipeline] error
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (重新安装依赖)
Stage "重新安装依赖" skipped due to earlier failure(s)
[Pipeline] getContext
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
[Pipeline] { (清理旧制品)
Stage "清理旧制品" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (生成报告)
Stage "生成报告" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] script
[Pipeline] {
[Pipeline] mail
[Pipeline] echo
Email sent successfully
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
ERROR: 插件配置校验失败，请完善 package.json 中的 huaiye 字段
Finished: FAILURE