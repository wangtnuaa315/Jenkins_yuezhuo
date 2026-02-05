Started by user admin
Obtained Jenkinsfile.plugin.manual-quality from git http://192.168.1.6:9000/innovation/yuezhuo-plugins.git
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in /var/lib/jenkins/workspace/Yuezhuo-plugin-manual-QualityCheck
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Declarative: Checkout SCM)
[Pipeline] checkout
The recommended git tool is: NONE
using credential yuezhuo-git-credentials
 > git rev-parse --resolve-git-dir /var/lib/jenkins/workspace/Yuezhuo-plugin-manual-QualityCheck/.git # timeout=10
Fetching changes from the remote Git repository
 > git config remote.origin.url http://192.168.1.6:9000/innovation/yuezhuo-plugins.git # timeout=10
Fetching upstream changes from http://192.168.1.6:9000/innovation/yuezhuo-plugins.git
 > git --version # timeout=10
 > git --version # 'git version 2.34.1'
using GIT_ASKPASS to set credentials 鸑鷟 git仓库凭证
 > git fetch --tags --force --progress -- http://192.168.1.6:9000/innovation/yuezhuo-plugins.git +refs/heads/*:refs/remotes/origin/* # timeout=10
 > git rev-parse refs/remotes/origin/master^{commit} # timeout=10
Checking out Revision 9b8dd2adf7ab894c51728181393f04ee1e18f3de (refs/remotes/origin/master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 9b8dd2adf7ab894c51728181393f04ee1e18f3de # timeout=10
Commit message: "fix: 修改ci构建脚本"
 > git rev-list --no-walk c41f8622e701380e09135cb80c716ae94a7b2250 # timeout=10
[Pipeline] }
[Pipeline] // stage
[Pipeline] withEnv
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
[Pipeline] { (代码质量检查)
[Pipeline] script
[Pipeline] {
[Pipeline] echo
======== 开始代码质量检查 ========
[Pipeline] load
[Pipeline] { (/var/lib/jenkins/workspace/Yuezhuo-plugin-manual-QualityCheck/quality-check.groovy)
[Pipeline] }
[Pipeline] // load
[Pipeline] echo
======== 开始代码质量检查 ========
[Pipeline] echo
======== ESLint 代码规范检查 ========
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] timeout
Timeout set to expire in 5 min 0 sec
[Pipeline] {
[Pipeline] sh
+ [ -f package.json ]
+ grep -q "lint" package.json
+ PLUGIN_DIR=packages/plugins/@huaiye
+ [ -d packages/plugins/@huaiye ]
+ echo 开始 ESLint 检查（限定 packages/plugins/@huaiye 目录）...
开始 ESLint 检查（限定 packages/plugins/@huaiye 目录）...
+ ls -1 packages/plugins/@huaiye
+ wc -l
+ PLUGIN_COUNT=3
+ echo 发现 3 个插件
发现 3 个插件
+ npx eslint packages/plugins/@huaiye --ext .ts,.tsx,.js,.jsx --format json -o eslint-report.json
+ true
+ [ -f eslint-report.json ]
+ cat eslint-report.json
+ grep -o "severity":2
+ wc -l
+ ERROR_COUNT=636
+ cat eslint-report.json
+ grep -o "severity":1
+ wc -l
+ WARN_COUNT=4
+ echo ESLint 结果: 636 错误, 4 警告
ESLint 结果: 636 错误, 4 警告
+ echo ESLint 报告已生成: eslint-report.json
ESLint 报告已生成: eslint-report.json
[Pipeline] }
[Pipeline] // timeout
[Pipeline] }
[Pipeline] // dir
[Pipeline] echo
======== 单元测试 ========
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ [ -f package.json ]
+ grep -q "test" package.json
+ yarn test --ci --reporters=default --reporters=jest-junit
yarn run v1.22.22
$ nocobase test --ci --reporters=default --reporters=jest-junit
process.env.TEST_ENV server-side [
  '--max_old_space_size=14096',
  './node_modules/vitest/vitest.mjs',
  '--ci',
  '--reporters=default',
  '--reporters=jest-junit',
  '--run',
  '--poolOptions.threads.singleThread=true'
]
file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:403
          throw new CACError(`Unknown option \`${name.length > 1 ? `--${name}` : `-${name}`}\``);
                ^

CACError: Unknown option `--ci`
    at Command.checkUnknownOptions (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:403:17)
    at CAC.runMatchedCommand (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:603:13)
    at CAC.parse (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:544:12)
    at file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/cli.js:9:13
    at ModuleJob.run (node:internal/modules/esm/module_job:271:25)
    at async onImport.tracePromise.__proto__ (node:internal/modules/esm/loader:547:26)
    at async main (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/cli-wrapper.js:45:5)

Node.js v22.13.1
/mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/execa/lib/error.js:60
		error = new Error(message);
		        ^

Error: Command failed with exit code 1: node --max_old_space_size=14096 ./node_modules/vitest/vitest.mjs --ci --reporters=default --reporters=jest-junit --run --poolOptions.threads.singleThread=true
    at makeError (/mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/execa/lib/error.js:60:11)
    at handlePromise (/mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/execa/index.js:118:26)
    at process.processTicksAndRejections (node:internal/process/task_queues:105:5)
    at async Command.<anonymous> (/mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/core/cli/src/commands/test.js:81:9) {
  shortMessage: 'Command failed with exit code 1: node --max_old_space_size=14096 ./node_modules/vitest/vitest.mjs --ci --reporters=default --reporters=jest-junit --run --poolOptions.threads.singleThread=true',
  command: 'node --max_old_space_size=14096 ./node_modules/vitest/vitest.mjs --ci --reporters=default --reporters=jest-junit --run --poolOptions.threads.singleThread=true',
  escapedCommand: 'node "--max_old_space_size=14096" "./node_modules/vitest/vitest.mjs" --ci "--reporters=default" "--reporters=jest-junit" --run "--poolOptions.threads.singleThread=true"',
  exitCode: 1,
  signal: undefined,
  signalDescription: undefined,
  stdout: undefined,
  stderr: undefined,
  failed: true,
  timedOut: false,
  isCanceled: false,
  killed: false
}

Node.js v22.13.1
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
+ true
+ echo 测试报告已生成
测试报告已生成
[Pipeline] }
[Pipeline] // dir
[Pipeline] echo
======== 代码覆盖率 ========
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ [ -f package.json ]
+ grep -q "test" package.json
+ yarn test --coverage --coverageReporters=cobertura --coverageReporters=text
yarn run v1.22.22
$ nocobase test --coverage --coverageReporters=cobertura --coverageReporters=text
process.env.TEST_ENV server-side [
  '--max_old_space_size=14096',
  './node_modules/vitest/vitest.mjs',
  '--coverage',
  '--coverageReporters=cobertura',
  '--coverageReporters=text',
  '--run',
  '--poolOptions.threads.singleThread=true'
]
file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:403
          throw new CACError(`Unknown option \`${name.length > 1 ? `--${name}` : `-${name}`}\``);
                ^

CACError: Unknown option `--coverageReporters`
    at Command.checkUnknownOptions (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:403:17)
    at CAC.runMatchedCommand (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:603:13)
    at CAC.parse (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/vendor/cac.8mXc9Oj6.js:544:12)
    at file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/cli.js:9:13
    at ModuleJob.run (node:internal/modules/esm/module_job:271:25)
    at async onImport.tracePromise.__proto__ (node:internal/modules/esm/loader:547:26)
    at async main (file:///mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/vitest/dist/cli-wrapper.js:45:5)

Node.js v22.13.1
/mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/execa/lib/error.js:60
		error = new Error(message);
		        ^

Error: Command failed with exit code 1: node --max_old_space_size=14096 ./node_modules/vitest/vitest.mjs --coverage --coverageReporters=cobertura --coverageReporters=text --run --poolOptions.threads.singleThread=true
    at makeError (/mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/execa/lib/error.js:60:11)
    at handlePromise (/mnt/sdb3/yuezhuo-workspace/yuezhuo/node_modules/execa/index.js:118:26)
    at process.processTicksAndRejections (node:internal/process/task_queues:105:5)
    at async Command.<anonymous> (/mnt/sdb3/yuezhuo-workspace/yuezhuo/packages/core/cli/src/commands/test.js:81:9) {
  shortMessage: 'Command failed with exit code 1: node --max_old_space_size=14096 ./node_modules/vitest/vitest.mjs --coverage --coverageReporters=cobertura --coverageReporters=text --run --poolOptions.threads.singleThread=true',
  command: 'node --max_old_space_size=14096 ./node_modules/vitest/vitest.mjs --coverage --coverageReporters=cobertura --coverageReporters=text --run --poolOptions.threads.singleThread=true',
  escapedCommand: 'node "--max_old_space_size=14096" "./node_modules/vitest/vitest.mjs" --coverage "--coverageReporters=cobertura" "--coverageReporters=text" --run "--poolOptions.threads.singleThread=true"',
  exitCode: 1,
  signal: undefined,
  signalDescription: undefined,
  stdout: undefined,
  stderr: undefined,
  failed: true,
  timedOut: false,
  isCanceled: false,
  killed: false
}

Node.js v22.13.1
error Command failed with exit code 1.
info Visit https://yarnpkg.com/en/docs/cli/run for documentation about this command.
+ true
+ echo 覆盖率报告已生成: coverage/cobertura-coverage.xml
覆盖率报告已生成: coverage/cobertura-coverage.xml
[Pipeline] }
[Pipeline] // dir
[Pipeline] echo
======== 依赖安全扫描 ========
[Pipeline] dir
Running in /mnt/sdb3/yuezhuo-workspace/yuezhuo
[Pipeline] {
[Pipeline] sh
+ npm audit --audit-level=high
npm error code ENOLOCK
npm error audit This command requires an existing lockfile.
npm error audit Try creating one first with: npm i --package-lock-only
npm error audit Original error: loadVirtual requires existing shrinkwrap file
npm error A complete log of this run can be found in: /var/lib/jenkins/.npm/_logs/2026-02-04T02_40_08_171Z-debug-0.log
+ true
[Pipeline] }
[Pipeline] // dir
[Pipeline] echo

    ========================================
    代码质量检查完成
    ========================================
    ESLint:     ✅ 通过
    单元测试:   ✅ 通过
    覆盖率:     ✅ 已生成
    安全扫描:   ✅ 通过
    ========================================
    
[Pipeline] echo
======== 生成 HTML 质量报告 ========
[Pipeline] writeFile
[Pipeline] echo
HTML 报告已生成: /var/lib/jenkins/workspace/Yuezhuo-plugin-manual-QualityCheck/quality-report.html
[Pipeline] echo
======== 代码质量检查完成 ========
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
Checking out Revision 9b8dd2adf7ab894c51728181393f04ee1e18f3de (refs/remotes/origin/master)
 > git config core.sparsecheckout # timeout=10
 > git checkout -f 9b8dd2adf7ab894c51728181393f04ee1e18f3de # timeout=10
Commit message: "fix: 修改ci构建脚本"
[Pipeline] sh
+ git rev-parse --short HEAD
[Pipeline] echo
插件仓库 Commit: 9b8dd2a
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
warning workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @huaiye/plugin-agent-chat > multer@1.4.5-lts.2: Multer 1.x is impacted by a number of vulnerabilities, which have been patched in 2.x. You should upgrade to the latest 2.x version.
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
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > @rsbuild/plugin-babel@1.0.3" has unmet peer dependency "@rsbuild/core@1.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > babel-loader@9.2.1" has unmet peer dependency "webpack@>=5".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > bundle-require@5.1.0" has unmet peer dependency "esbuild@>=0.18".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > esbuild-register@3.6.0" has unmet peer dependency "esbuild@>=0.12 <1".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > postcss-loader@7.3.4" has unmet peer dependency "webpack@^5.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > style-loader@3.3.4" has unmet peer dependency "webpack@^5.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > vite-plugin-css-injected-by-js@3.5.2" has unmet peer dependency "vite@>2.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > vite-plugin-lib-inject-css@1.2.0" has unmet peer dependency "vite@*".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/client > @formily/antd-v5@1.2.3" has unmet peer dependency "react-is@>=16.8.0 || >=17.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/client > @formily/react@2.3.7" has unmet peer dependency "react-is@>=16.8.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/client > @formily/reactive-react@2.3.7" has unmet peer dependency "react-is@>=16.8.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/client > react-image-lightbox@5.1.4" has incorrect peer dependency "react@16.x || 17.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/client > react-image-lightbox@5.1.4" has incorrect peer dependency "react-dom@16.x || 17.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/test > @testing-library/react-hooks@8.0.1" has incorrect peer dependency "react@^16.9.0 || ^17.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/test > @testing-library/user-event@14.5.1" has unmet peer dependency "@testing-library/dom@>=7.21.4".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/test > jsdom-worker@0.3.0" has unmet peer dependency "node-fetch@*".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-ai > @langchain/openai@0.6.13" has incorrect peer dependency "@langchain/core@>=0.3.68 <0.4.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-ai > use-context-selector@2.0.0" has unmet peer dependency "scheduler@>=0.19.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-backup-restore > @koa/multer@3.0.2" has unmet peer dependency "multer@*".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-data-visualization > @ant-design/plots@2.1.14" has unmet peer dependency "lodash-es@^4.17.21".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-workflow-json-query > @nocobase/plugin-workflow-test@1.9.8" has incorrect peer dependency "@nocobase/database@1.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-workflow-json-query > @nocobase/plugin-workflow-test@1.9.8" has incorrect peer dependency "@nocobase/server@1.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-workflow-json-query > @nocobase/plugin-workflow-test@1.9.8" has incorrect peer dependency "@nocobase/test@1.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/build > @rsdoctor/rspack-plugin > @rsdoctor/types@0.4.11" has unmet peer dependency "webpack@5.x".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/client > dumi > raw-loader@4.0.2" has unmet peer dependency "webpack@^4.0.0 || ^5.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-theme-editor > @arvinxu/layout-kit > styled-components@5.3.11" has unmet peer dependency "react-is@>= 16.8.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/bundler-webpack > @umijs/react-refresh-webpack-plugin@0.5.11" has unmet peer dependency "webpack@>=4.43.0 <6.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/bundler-webpack > css-loader@6.7.1" has unmet peer dependency "webpack@^5.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/bundler-webpack > fork-ts-checker-webpack-plugin@8.0.0" has unmet peer dependency "webpack@^5.11.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/lint > @typescript-eslint/eslint-plugin@5.62.0" has incorrect peer dependency "@typescript-eslint/parser@^5.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/lint > stylelint-config-standard@25.0.0" has unmet peer dependency "stylelint@^14.4.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/preset-umi > html-webpack-plugin@5.5.0" has unmet peer dependency "webpack@^5.20.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > @babel/plugin-transform-modules-commonjs@7.23.0" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest@29.7.0" has unmet peer dependency "@babel/core@^7.8.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/lint > stylelint-config-standard > stylelint-config-recommended@7.0.0" has unmet peer dependency "stylelint@^14.4.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest@29.6.3" has unmet peer dependency "@babel/core@^7.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/plugin-theme-editor > @arvinxu/layout-kit > styled-components > babel-plugin-styled-components > @babel/plugin-syntax-jsx@7.23.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax@1.0.1" has unmet peer dependency "@babel/core@^7.0.0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-async-generators@7.8.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-bigint@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-class-properties@7.12.13" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-import-meta@7.10.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-json-strings@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-logical-assignment-operators@7.10.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-nullish-coalescing-operator@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-numeric-separator@7.10.4" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-object-rest-spread@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-optional-catch-binding@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-optional-chaining@7.8.3" has unmet peer dependency "@babel/core@^7.0.0-0".
warning "workspace-aggregator-8de8cd6d-6305-48e7-89ba-c115cd1e401f > @nocobase/devtools > umi > @umijs/test > babel-jest > babel-preset-jest > babel-preset-current-node-syntax > @babel/plugin-syntax-top-level-await@7.14.5" has unmet peer dependency "@babel/core@^7.0.0-0".
warning Workspaces can only be enabled in private projects.
warning Workspaces can only be enabled in private projects.
warning Workspaces can only be enabled in private projects.
[5/5] Building fresh packages...
$ nocobase postinstall
VERSION:  2.0.0-alpha.43
Done in 23.58s.
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
+ cd packages/plugins/@huaiye/plugin-agent-chat
+ cat package.json
+ grep "version"
+ head -1
+ sed s/.*"version": "\([^"]*\)".*/\1/
+ current_version=2.0.0-alpha.43
+ echo 当前版本: 2.0.0-alpha.43
当前版本: 2.0.0-alpha.43
+ new_version=2.0.0-alpha.43.20260204.104046
+ echo 新版本: 2.0.0-alpha.43.20260204.104046
新版本: 2.0.0-alpha.43.20260204.104046
+ sed -i s/"version": "[^"]*"/"version": "2.0.0-alpha.43.20260204.104046"/ package.json
+ grep "version" package.json
  "version": "2.0.0-alpha.43.20260204.104046",
[Pipeline] sh
+ yarn pm create @huaiye/plugin-agent-chat
yarn run v1.22.22
$ nocobase pm create @huaiye/plugin-agent-chat
WAIT: TypeScript compiling...
(node:1180052) [DEP0040] DeprecationWarning: The `punycode` module is deprecated. Please use a userland alternative instead.
(Use `node --trace-deprecation ...` to show where the warning was created)
2026-02-04 10:40:58 [debug] create database instance: {"sync":{"alter":{"drop":false},"force":false},"logging":false,"dialect":"postgres","username":"nocobase","database":"nocobase","port":"5432","timezone":"+08:00","tablePrefix":"","underscored":false,"pool":{},"migrator":{"context":{"app":{"appName":"main","name":"main"}}},"logger":{},"rawTimezone":"+08:00"} meta={"databaseInstanceId":"wrpVs_F4QBty5E5LuApvF"} module=database app=main reqId=2e7d05f8-f7bd-4d03-9d53-d3b7887effae dataSourceKey=main
2026-02-04 10:40:59 [info]  add app main into supervisor                 module=application submodule=supervisor method=addApp app=main reqId=2e7d05f8-f7bd-4d03-9d53-d3b7887effae dataSourceKey=main
[@huaiye/plugin-agent-chat] plugin already exists.
2026-02-04 10:40:59 [info]  attempt to add the plugin to the app         module=application app=main reqId=2e7d05f8-f7bd-4d03-9d53-d3b7887effae dataSourceKey=main
2026-02-04 10:40:59 [info]  add plugin [@huaiye/plugin-agent-chat]       meta={"name":"@huaiye/plugin-agent-chat","packageName":"@huaiye/plugin-agent-chat","version":"2.0.0-alpha.43.20260204.104046"} module=application app=main reqId=2e7d05f8-f7bd-4d03-9d53-d3b7887effae dataSourceKey=main
2026-02-04 10:40:59 [info]  [queue] gracefully shutting down...          module=application app=main reqId=2e7d05f8-f7bd-4d03-9d53-d3b7887effae dataSourceKey=main
Done in 11.03s.
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
Rspack 1.3.2 compiled [1m[32msuccessfully[39m[22m in 1.13 s
@huaiye/plugin-agent-chat: build plugin server source
@huaiye/plugin-agent-chat: The build tool will package all dependencies into the dist directory, so you don't need to put them in @nocobase/client, @nocobase/server, axios, multer, react-markdown. Instead, they should be placed in dependencies. For more information, please refer to: devDependencies. https://docs.nocobase.com/development/others/deps
@huaiye/plugin-agent-chat: delete server files
@huaiye/plugin-agent-chat: build plugin server dependencies
@huaiye/plugin-agent-chat: These packages form-data will be bundled to dist/node_modules. These packages @nocobase/server, axios, @nocobase/utils will be exclude. For more information, please refer to: https://docs.nocobase.com/development/others/deps.
@huaiye/plugin-agent-chat: write external version
Done in 5.44s.
[Pipeline] sh
+ yarn nocobase tar @huaiye/plugin-agent-chat
yarn run v1.22.22
$ nocobase tar @huaiye/plugin-agent-chat
$ tsup
[33mThe CJS build of Vite's Node API is deprecated. See https://vitejs.dev/guide/troubleshooting.html#vite-cjs-node-api-deprecated for more details.[39m
@huaiye/plugin-agent-chat: tar package
Done in 3.30s.
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
[Pipeline] { (Declarative: Post Actions)
[Pipeline] script
[Pipeline] {
[Pipeline] }
[Pipeline] // script
Error when executing always post condition:
Also:   org.jenkinsci.plugins.workflow.actions.ErrorAction$ErrorId: f60cbbc2-22e2-42bd-afe5-2e7f7b3e23bb
java.lang.NoSuchMethodError: No such DSL method 'publishHTML' found among steps [GitLabMergeRequestLabelExists, acceptGitLabMR, addGitLabMRComment, archive, bat, build, catchError, checkout, deleteDir, dir, echo, emailext, emailextrecipients, envVarsForTool, error, fileExists, findBuildScans, getContext, git, gitlabBuilds, gitlabCommitStatus, input, isUnix, junit, library, libraryResource, load, mail, milestone, node, parallel, powershell, properties, publishChecks, publishIssues, pwd, pwsh, readFile, readScmFile, readTrusted, recordCoverage, recordIssues, resolveScm, retry, scanForIssues, script, sh, sleep, stage, stash, step, timeout, timestamps, tm, tool, unarchive, unstable, unstash, updateGitlabCommitStatus, validateDeclarativePipeline, waitForBuild, waitUntil, warnError, withChecks, withContext, withCredentials, withEnv, withGradle, wrap, writeFile, ws] or symbols [CrossCoreEmbeddedStudioParser, GitUsernamePassword, PVSStudio, activeChoice, activeChoiceHtml, acuCobol, agent, ajc, all, allBranchesSame, allOf, always, analysisParser, androidLintParser, ansibleLint, ant, antFromApache, antOutcome, antTarget, any, anyOf, apiToken, apiTokenProperty, aquaScanner, architecture, archiveArtifacts, armCc, artifactManager, assembla, authorInChangelog, authorizationMatrix, axivion, axivionSuite, batchFile, bitbucket, bitbucketServer, bluepearl, booleanParam, brakeman, branch, brokenBuildSuspects, brokenTestsSuspects, browser, buckminster, buildButton, buildDiscarder, buildDiscarders, buildRetention, buildSingleRevisionOnly, buildUser, buildingTag, builtInNode, cadence, cargo, caseInsensitive, caseSensitive, ccm, certificate, cgit, changeRequest, changelog, changelogBase, changelogToBranch, changeset, checkStyle, checkoutOption, checkoutToSubdirectory, choice, choiceParam, clair, clang, clangAnalyzer, clangTidy, cleanAfterCheckout, cleanBeforeCheckout, cleanWs, clock, cloneOption, cmake, codeAnalysis, codeChecker, codeGeneratorParser, codeNarc, command, computerRetentionCheckInterval, consoleUrlProvider, contributor, coolflux, coverage, coverageTotalsColumn, cpd, cppCheck, cppLint, cps, credentials, cron, crumb, cssLint, culprits, dark, darkSystem, dart, default, defaultDisplayUrlProvider, defaultFolderConfiguration, defaultView, demand, detekt, developers, diabC, disableConcurrentBuilds, disableRestartFromStage, disableResume, discoverOtherRefs, discoverOtherRefsTrait, discoverReferenceBuild, diskSpace, diskSpaceMonitor, docFx, dockerLint, downstream, doxygen, drMemory, dscanner, dumb, dupFinder, durabilityHint, eclipse, email-ext, embeddedEngineerParser, envVars, envVarsFilter, environment, equals, erlc, errorProne, esLint, excludeCategory, excludeFile, excludeMessage, excludeModule, excludeNamespace, excludePackage, excludeType, experimentalFlags, expression, extendedEmailPublisher, file, fileParam, filePath, findBugs, fingerprint, fingerprints, firstBuildChangelog, fisheye, flake8, flawfinder, flexSdk, frameOptions, freeStyle, freeStyleJob, fromScm, fromSource, fxcop, gcc, gcc3, gcc4, gendarme, ghsMulti, git, gitBranchDiscovery, gitHooks, gitHubBranchDiscovery, gitHubBranchHeadAuthority, gitHubExcludeArchivedRepositories, gitHubExcludeForkedRepositories, gitHubExcludePrivateRepositories, gitHubExcludePublicRepositories, gitHubForkDiscovery, gitHubIgnoreDraftPullRequestFilter, gitHubPullRequestDiscovery, gitHubSshCheckout, gitHubTagDiscovery, gitHubTopicsFilter, gitHubTrustContributors, gitHubTrustEveryone, gitHubTrustNobody, gitHubTrustPermissions, gitLab, gitLabConnection, gitList, gitSCM, gitSource, gitTagDiscovery, gitTool, gitUsernamePassword, gitWeb, gitblit, github, githubProjectProperty, githubPush, gitiles, gitlab, gitlabApiToken, gnat, gnuFortran, goLint, goVet, gogs, gradle, groovyScript, group, grype, hadoLint, headRegexFilter, headWildcardFilter, hyperlink, hyperlinkToModels, iar, iarCstat, ibLinter, ideaInspection, ignoreOnPush, inbound, includeCategory, includeFile, includeMessage, includeModule, includeNamespace, includePackage, includeType, infer, inferOwner, inferRepository, inheriting, inheritingGlobal, installSource, intel, invalids, isRestartedRun, issueTotalsColumn, issues, java, javaDoc, jcReport, jdk, jgit, jgitapache, jnlp, jobBuildDiscarder, jobName, jsHint, jsLint, junitParser, junitTestResultStorage, kiln, klocWork, kotlin, ktLint, label, lastDuration, lastFailure, lastGrantedAuthorities, lastStable, lastSuccess, legacy, legacySCM, lfs, list, local, localBranch, localBranchTrait, locale, location, logRotator, loggedInUsersCanDoAnything, mailer, masterBuild, maven, maven3Mojos, mavenConsole, mavenErrors, mavenGlobalConfig, mavenMojos, mavenWarnings, metrowerksCodeWarrior, mineRepository, modelsim, modernSCM, msBuild, multiBranchProjectDisplayNaming, multibranch, myPy, myView, nagFortran, namedBranchesDifferent, node, nodeProperties, nonInheriting, none, nonresumable, not, npmAudit, oelintAdv, organizationFolder, otDockerLint, overrideIndexTriggers, owaspDependencyCheck, paneStatus, parallelsAlwaysFailFast, parameters, password, pattern, pcLint, pep8, perBuildTag, perforce, perlCritic, permanent, phabricator, php, phpCodeSniffer, phpStan, pipeline, pipelineGraphView, pipelineTriggers, pit, plainText, plugin, pmdParser, pollSCM, polyspaceParser, prefast, preserveStashes, previous, prism, projectNamingStrategy, protoLint, proxy, pruneStaleBranch, pruneStaleTag, pruneTags, puppetLint, pyDocStyle, pyLint, qacSourceCodeAnalyser, qtTranslation, queueItemAuthenticator, quietPeriod, rateLimit, rateLimitBuilds, reactiveChoice, recipients, recordCoverage, recordIssues, redmine, refSpecs, remoteName, requestor, resharperInspectCode, resourceRoot, responseTime, retainOnlyVariables, revApi, rfLint, rhodeCode, robocopy, ruboCop, run, runParam, sSHLauncher, sarif, scala, schedule, scmGit, scmRetryCount, scriptApproval, scriptApprovalLink, scriptlerScript, search, security, shell, simian, simpleBuildDiscarder, simulinkCheckParser, skipDefaultCheckout, skipStagesAfterUnstable, slave, sonarQube, sourceRegexFilter, sourceWildcardFilter, sparseCheckout, sparseCheckoutPaths, specificRepositories, sphinxBuild, spotBugs, ssh, sshUserPrivateKey, standard, status, string, stringParam, styleCop, styleLint, submodule, submoduleOption, sunC, suppressAutomaticTriggering, suppressFolderAutomaticTriggering, swapSpace, swiftLint, tag, tagList, taskScanner, taskingVx, teamFoundation, teamSlugFilter, text, textParam, themeManager, tiCss, timestamper, timestamperConfig, timezone, tmpSpace, tnsdl, toolLocation, triggeredBy, trivy, tsLint, unsecured, untrusted, upstream, upstreamDevelopers, user, userIdentity, userLocale, userOrGroup, userSeed, usernameColonPassword, usernamePassword, vale, valgrind, veracodePipelineScanner, viewgit, viewsTabBar, warnings, warningsParsers, weather, withAnt, xlc, xmlLint, yamlLint, yoctoScanner, yuiCompressor, zip, zptLint] or globals [currentBuild, env, params, pipeline, scm]
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.DSL.invokeMethod(DSL.java:217)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsScript.invokeMethod(CpsScript.java:124)
	at jdk.internal.reflect.GeneratedMethodAccessor584.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:98)
	at groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)
	at groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1225)
	at groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1034)
	at org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:41)
	at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)
	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:116)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker$1.call(Checker.java:180)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.GroovyInterceptor.onMethodCall(GroovyInterceptor.java:23)
	at PluginClassLoader for script-security//org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.SandboxInterceptor.onMethodCall(SandboxInterceptor.java:163)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker$1.call(Checker.java:178)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker.checkedCall(Checker.java:182)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker.checkedCall(Checker.java:152)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker.checkedCall(Checker.java:152)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.sandbox.SandboxInvoker.methodCall(SandboxInvoker.java:17)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.LoggingInvoker.methodCall(LoggingInvoker.java:120)
	at WorkflowScript.run(WorkflowScript:341)
	at ___cps.transform___(Native Method)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationGroup.methodCall(ContinuationGroup.java:107)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.FunctionCallBlock$ContinuationImpl.dispatchOrArg(FunctionCallBlock.java:118)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.FunctionCallBlock$ContinuationImpl.fixArg(FunctionCallBlock.java:87)
	at jdk.internal.reflect.GeneratedMethodAccessor418.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:71)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.dispatch(CollectionLiteralBlock.java:54)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.item(CollectionLiteralBlock.java:45)
	at jdk.internal.reflect.GeneratedMethodAccessor421.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:71)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.dispatch(CollectionLiteralBlock.java:54)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.item(CollectionLiteralBlock.java:45)
	at jdk.internal.reflect.GeneratedMethodAccessor421.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:71)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ConstantBlock.eval(ConstantBlock.java:21)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.Next.step(Next.java:84)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.Continuable.run0(Continuable.java:142)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.SandboxContinuable.access$001(SandboxContinuable.java:17)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.SandboxContinuable.run0(SandboxContinuable.java:48)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThread.runNextChunk(CpsThread.java:188)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.run(CpsThreadGroup.java:464)
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

[Pipeline] echo

            ========================================
            ❌ 任务执行失败！
            ========================================
            请检查控制台日志获取详细错误信息。
            ========================================
            
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // withEnv
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Also:   org.jenkinsci.plugins.workflow.actions.ErrorAction$ErrorId: f60cbbc2-22e2-42bd-afe5-2e7f7b3e23bb
java.lang.NoSuchMethodError: No such DSL method 'publishHTML' found among steps [GitLabMergeRequestLabelExists, acceptGitLabMR, addGitLabMRComment, archive, bat, build, catchError, checkout, deleteDir, dir, echo, emailext, emailextrecipients, envVarsForTool, error, fileExists, findBuildScans, getContext, git, gitlabBuilds, gitlabCommitStatus, input, isUnix, junit, library, libraryResource, load, mail, milestone, node, parallel, powershell, properties, publishChecks, publishIssues, pwd, pwsh, readFile, readScmFile, readTrusted, recordCoverage, recordIssues, resolveScm, retry, scanForIssues, script, sh, sleep, stage, stash, step, timeout, timestamps, tm, tool, unarchive, unstable, unstash, updateGitlabCommitStatus, validateDeclarativePipeline, waitForBuild, waitUntil, warnError, withChecks, withContext, withCredentials, withEnv, withGradle, wrap, writeFile, ws] or symbols [CrossCoreEmbeddedStudioParser, GitUsernamePassword, PVSStudio, activeChoice, activeChoiceHtml, acuCobol, agent, ajc, all, allBranchesSame, allOf, always, analysisParser, androidLintParser, ansibleLint, ant, antFromApache, antOutcome, antTarget, any, anyOf, apiToken, apiTokenProperty, aquaScanner, architecture, archiveArtifacts, armCc, artifactManager, assembla, authorInChangelog, authorizationMatrix, axivion, axivionSuite, batchFile, bitbucket, bitbucketServer, bluepearl, booleanParam, brakeman, branch, brokenBuildSuspects, brokenTestsSuspects, browser, buckminster, buildButton, buildDiscarder, buildDiscarders, buildRetention, buildSingleRevisionOnly, buildUser, buildingTag, builtInNode, cadence, cargo, caseInsensitive, caseSensitive, ccm, certificate, cgit, changeRequest, changelog, changelogBase, changelogToBranch, changeset, checkStyle, checkoutOption, checkoutToSubdirectory, choice, choiceParam, clair, clang, clangAnalyzer, clangTidy, cleanAfterCheckout, cleanBeforeCheckout, cleanWs, clock, cloneOption, cmake, codeAnalysis, codeChecker, codeGeneratorParser, codeNarc, command, computerRetentionCheckInterval, consoleUrlProvider, contributor, coolflux, coverage, coverageTotalsColumn, cpd, cppCheck, cppLint, cps, credentials, cron, crumb, cssLint, culprits, dark, darkSystem, dart, default, defaultDisplayUrlProvider, defaultFolderConfiguration, defaultView, demand, detekt, developers, diabC, disableConcurrentBuilds, disableRestartFromStage, disableResume, discoverOtherRefs, discoverOtherRefsTrait, discoverReferenceBuild, diskSpace, diskSpaceMonitor, docFx, dockerLint, downstream, doxygen, drMemory, dscanner, dumb, dupFinder, durabilityHint, eclipse, email-ext, embeddedEngineerParser, envVars, envVarsFilter, environment, equals, erlc, errorProne, esLint, excludeCategory, excludeFile, excludeMessage, excludeModule, excludeNamespace, excludePackage, excludeType, experimentalFlags, expression, extendedEmailPublisher, file, fileParam, filePath, findBugs, fingerprint, fingerprints, firstBuildChangelog, fisheye, flake8, flawfinder, flexSdk, frameOptions, freeStyle, freeStyleJob, fromScm, fromSource, fxcop, gcc, gcc3, gcc4, gendarme, ghsMulti, git, gitBranchDiscovery, gitHooks, gitHubBranchDiscovery, gitHubBranchHeadAuthority, gitHubExcludeArchivedRepositories, gitHubExcludeForkedRepositories, gitHubExcludePrivateRepositories, gitHubExcludePublicRepositories, gitHubForkDiscovery, gitHubIgnoreDraftPullRequestFilter, gitHubPullRequestDiscovery, gitHubSshCheckout, gitHubTagDiscovery, gitHubTopicsFilter, gitHubTrustContributors, gitHubTrustEveryone, gitHubTrustNobody, gitHubTrustPermissions, gitLab, gitLabConnection, gitList, gitSCM, gitSource, gitTagDiscovery, gitTool, gitUsernamePassword, gitWeb, gitblit, github, githubProjectProperty, githubPush, gitiles, gitlab, gitlabApiToken, gnat, gnuFortran, goLint, goVet, gogs, gradle, groovyScript, group, grype, hadoLint, headRegexFilter, headWildcardFilter, hyperlink, hyperlinkToModels, iar, iarCstat, ibLinter, ideaInspection, ignoreOnPush, inbound, includeCategory, includeFile, includeMessage, includeModule, includeNamespace, includePackage, includeType, infer, inferOwner, inferRepository, inheriting, inheritingGlobal, installSource, intel, invalids, isRestartedRun, issueTotalsColumn, issues, java, javaDoc, jcReport, jdk, jgit, jgitapache, jnlp, jobBuildDiscarder, jobName, jsHint, jsLint, junitParser, junitTestResultStorage, kiln, klocWork, kotlin, ktLint, label, lastDuration, lastFailure, lastGrantedAuthorities, lastStable, lastSuccess, legacy, legacySCM, lfs, list, local, localBranch, localBranchTrait, locale, location, logRotator, loggedInUsersCanDoAnything, mailer, masterBuild, maven, maven3Mojos, mavenConsole, mavenErrors, mavenGlobalConfig, mavenMojos, mavenWarnings, metrowerksCodeWarrior, mineRepository, modelsim, modernSCM, msBuild, multiBranchProjectDisplayNaming, multibranch, myPy, myView, nagFortran, namedBranchesDifferent, node, nodeProperties, nonInheriting, none, nonresumable, not, npmAudit, oelintAdv, organizationFolder, otDockerLint, overrideIndexTriggers, owaspDependencyCheck, paneStatus, parallelsAlwaysFailFast, parameters, password, pattern, pcLint, pep8, perBuildTag, perforce, perlCritic, permanent, phabricator, php, phpCodeSniffer, phpStan, pipeline, pipelineGraphView, pipelineTriggers, pit, plainText, plugin, pmdParser, pollSCM, polyspaceParser, prefast, preserveStashes, previous, prism, projectNamingStrategy, protoLint, proxy, pruneStaleBranch, pruneStaleTag, pruneTags, puppetLint, pyDocStyle, pyLint, qacSourceCodeAnalyser, qtTranslation, queueItemAuthenticator, quietPeriod, rateLimit, rateLimitBuilds, reactiveChoice, recipients, recordCoverage, recordIssues, redmine, refSpecs, remoteName, requestor, resharperInspectCode, resourceRoot, responseTime, retainOnlyVariables, revApi, rfLint, rhodeCode, robocopy, ruboCop, run, runParam, sSHLauncher, sarif, scala, schedule, scmGit, scmRetryCount, scriptApproval, scriptApprovalLink, scriptlerScript, search, security, shell, simian, simpleBuildDiscarder, simulinkCheckParser, skipDefaultCheckout, skipStagesAfterUnstable, slave, sonarQube, sourceRegexFilter, sourceWildcardFilter, sparseCheckout, sparseCheckoutPaths, specificRepositories, sphinxBuild, spotBugs, ssh, sshUserPrivateKey, standard, status, string, stringParam, styleCop, styleLint, submodule, submoduleOption, sunC, suppressAutomaticTriggering, suppressFolderAutomaticTriggering, swapSpace, swiftLint, tag, tagList, taskScanner, taskingVx, teamFoundation, teamSlugFilter, text, textParam, themeManager, tiCss, timestamper, timestamperConfig, timezone, tmpSpace, tnsdl, toolLocation, triggeredBy, trivy, tsLint, unsecured, untrusted, upstream, upstreamDevelopers, user, userIdentity, userLocale, userOrGroup, userSeed, usernameColonPassword, usernamePassword, vale, valgrind, veracodePipelineScanner, viewgit, viewsTabBar, warnings, warningsParsers, weather, withAnt, xlc, xmlLint, yamlLint, yoctoScanner, yuiCompressor, zip, zptLint] or globals [currentBuild, env, params, pipeline, scm]
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.DSL.invokeMethod(DSL.java:217)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsScript.invokeMethod(CpsScript.java:124)
	at jdk.internal.reflect.GeneratedMethodAccessor584.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:98)
	at groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)
	at groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1225)
	at groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1034)
	at org.codehaus.groovy.runtime.callsite.PogoMetaClassSite.call(PogoMetaClassSite.java:41)
	at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:47)
	at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:116)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker$1.call(Checker.java:180)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.GroovyInterceptor.onMethodCall(GroovyInterceptor.java:23)
	at PluginClassLoader for script-security//org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.SandboxInterceptor.onMethodCall(SandboxInterceptor.java:163)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker$1.call(Checker.java:178)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker.checkedCall(Checker.java:182)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker.checkedCall(Checker.java:152)
	at PluginClassLoader for script-security//org.kohsuke.groovy.sandbox.impl.Checker.checkedCall(Checker.java:152)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.sandbox.SandboxInvoker.methodCall(SandboxInvoker.java:17)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.LoggingInvoker.methodCall(LoggingInvoker.java:120)
	at WorkflowScript.run(WorkflowScript:341)
	at ___cps.transform___(Native Method)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationGroup.methodCall(ContinuationGroup.java:107)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.FunctionCallBlock$ContinuationImpl.dispatchOrArg(FunctionCallBlock.java:118)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.FunctionCallBlock$ContinuationImpl.fixArg(FunctionCallBlock.java:87)
	at jdk.internal.reflect.GeneratedMethodAccessor418.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:71)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.dispatch(CollectionLiteralBlock.java:54)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.item(CollectionLiteralBlock.java:45)
	at jdk.internal.reflect.GeneratedMethodAccessor421.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:71)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.dispatch(CollectionLiteralBlock.java:54)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.CollectionLiteralBlock$ContinuationImpl.item(CollectionLiteralBlock.java:45)
	at jdk.internal.reflect.GeneratedMethodAccessor421.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:569)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ContinuationPtr$ContinuationImpl.receive(ContinuationPtr.java:71)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.impl.ConstantBlock.eval(ConstantBlock.java:21)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.Next.step(Next.java:84)
	at PluginClassLoader for workflow-cps//com.cloudbees.groovy.cps.Continuable.run0(Continuable.java:142)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.SandboxContinuable.access$001(SandboxContinuable.java:17)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.SandboxContinuable.run0(SandboxContinuable.java:48)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThread.runNextChunk(CpsThread.java:188)
	at PluginClassLoader for workflow-cps//org.jenkinsci.plugins.workflow.cps.CpsThreadGroup.run(CpsThreadGroup.java:464)
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
