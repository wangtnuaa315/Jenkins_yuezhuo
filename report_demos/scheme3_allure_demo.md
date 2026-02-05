# 方案三：Allure Report 配置指南

## 1. 效果预览
Allure 提供了一个独立的、交互式的 Web 仪表盘，包含：
- **总览页**：展示用例通过率、趋势图。
- **Behaviors**：按功能模块分组查看结果。
- **Graphs**：耗时分布、重试次数等图表。

## 2. Jenkinsfile 配置 demo

```groovy
pipeline {
    agent any
    
    stages {
        stage('编译验证') {
            steps {
                script {
                    // 在编译过程中生成 Allure 兼容的 XML/JSON 结果
                    // 如果是 Java/Maven 项目，使用 allure-maven 插件
                    // 如果是 Node.js (本案)，可以使用 mocha-allure-reporter 或 jest-allure
                    // 这里假设生成了 results 目录
                    sh "yarn test --reporter allure-mocha --reporter-options resultsDir=allure-results"
                }
            }
        }
    }
    
    post {
        always {
            // 生成并发布 Allure 报告
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']] // 指定结果目录
            ])
        }
    }
}
```

## 3. Node.js 项目适配
在你的插件项目中，如果使用 Jest 或 Mocha 测试，需要安装对应 reporter：
`yarn add -D allure-mocha` 或 `yarn add -D jest-allure`

## 4. 优缺点
- ✅ **优点**：界面最专业，交互性最好，支持详细的测试步骤回溯。
- ❌ **缺点**：需要额外安装 Jenkins Allure 插件 + 命令行工具；主要针对"测试用例"设计，如果是纯"编译检查"，需要伪造测试结果文件才能展示得好。
