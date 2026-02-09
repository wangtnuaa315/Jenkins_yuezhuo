Started by user admin
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/Yuezhuo-docker-build-QualityCheck
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
+ hostname
+ echo Node: ps
Node: ps
+ node -v
+ echo Node.js: v22.13.1
Node.js: v22.13.1
+ yarn -v
+ echo Yarn: 1.22.22
Yarn: 1.22.22
+ echo 分支: develop
分支: develop
+ echo =========================
=========================
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (检查代码仓库)
[Pipeline] script
[Pipeline] {
[Pipeline] sh
+ test -d /mnt/sdb3/yuezhuo-workspace/yuezhuo/.git
+ echo yes
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] checkout
The recommended git tool is: NONE
using credential yuezhuo-git-credentials
 > git rev-parse --resolve-git-dir /mnt/sdb3/yuezhuo-workspace/yuezhuo/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url http://192.168.1.6:9000/innovation/yuezhuo.git # timeout=10
Fetching upstream changes from http://192.168.1.6:9000/innovation/yuezhuo.git
 > git --version # timeout=10
 > git --version # 'git version 2.34.1'
using GIT_ASKPASS to set credentials 鸑鷟 git仓库凭证
 > git fetch --tags --force --progress -- http://192.168.1.6:9000/innovation/yuezhuo.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/develop^{commit} # timeout=10
Checking out Revision f47e7da1362b6cb10b52d74b788c5b87b4eaaa83 (refs/remotes/origin/develop)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f f47e7da1362b6cb10b52d74b788c5b87b4eaaa83 # timeout=10
Commit message: "Merge branch 'zhangyong-dev' into 'develop'"
 > git rev-list --no-walk f47e7da1362b6cb10b52d74b788c5b87b4eaaa83 # timeout=10
[Pipeline] }
[Pipeline] // dir
[Pipeline] echo
代码仓库已更新到 develop 分支
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (安装依赖)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ test -d node_modules
+ echo yes
[Pipeline] echo
node_modules 已存在，跳过安装
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (获取代码信息)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ git rev-parse HEAD
[Pipeline] sh
+ git rev-parse --short HEAD
[Pipeline] sh
+ git branch --show-current
[Pipeline] echo

                        ========================================
                        代码信息：
                        - 分支: 
                        - Commit: f47e7da136
                        ========================================
                        
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (ESLint 检查)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ timeout 300 yarn eslint packages/*/src --ext .ts,.tsx,.js,.jsx --ignore-pattern node_modules --ignore-pattern dist --ignore-pattern *.d.ts --format stylish
+ true
+ echo ======================================================================
+ echo ESLint 代码检查详细报告
+ echo ======================================================================
+ echo 
+ grep -c  error  /var/lib/jenkins/workspace/Yuezhuo-docker-build-QualityCheck/eslint-output.txt
+ echo 0
+ ERROR_COUNT=0
0
+ grep -c  warning  /var/lib/jenkins/workspace/Yuezhuo-docker-build-QualityCheck/eslint-output.txt
+ echo 0
+ WARN_COUNT=0
0
+ echo 统计: 0
0 个错误, 0
0 个警告
+ echo 
+ head -200 /var/lib/jenkins/workspace/Yuezhuo-docker-build-QualityCheck/eslint-output.txt
[Pipeline] }
[Pipeline] // dir
[Pipeline] sh
+ grep -oE [0-9]+ errors? /var/lib/jenkins/workspace/Yuezhuo-docker-build-QualityCheck/eslint-output.txt
+ + headgrep -oE -1 [0-9]+

+ echo 0
[Pipeline] sh
+ grep -oE [0-9]+ warnings? /var/lib/jenkins/workspace/Yuezhuo-docker-build-QualityCheck/eslint-output.txt
+ head -1
+ grep -oE [0-9]+
+ echo 0
[Pipeline] echo

                    ========================================
                    ESLint 检查结果: success
                    - 错误: 0
                    - 警告: 0
                    - 耗时: 4.507s
                    ========================================
                    
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (TypeScript 类型检查)
[Pipeline] script
[Pipeline] {
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ set +e
+ yarn tsc --noEmit
+ true
+ set -e
+ python3
  File "<stdin>", line 13
    for line in content.split('
                              ^
SyntaxError: unterminated string literal (detected at line 13)
[Pipeline] }
[Pipeline] // dir
[Pipeline] }
[Pipeline] // script
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (依赖安全审计)
Stage "依赖安全审计" skipped due to earlier failure(s)
[Pipeline] getContext
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (归档报告)
Stage "归档报告" skipped due to earlier failure(s)
[Pipeline] getContext
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
            ❌ 代码质量检查失败！
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