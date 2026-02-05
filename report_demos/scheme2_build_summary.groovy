// 方案二：Build Summary (Groovy Postbuild) 演示脚本
// 将此脚本放入 Jenkinsfile 的 post -> always -> script 中运行
// 或者配置在 "Groovy Postbuild" 插件中

import hudson.model.*

def manager = manager // Jenkins 注入的 manager 对象

// 1. 添加精简的徽章 (Badge)
if (manager.build.result == Result.SUCCESS) {
    manager.addShortText("✅", "black", "white", "0px", "white")
} else {
    manager.addShortText("❌", "black", "white", "0px", "white")
}

// 2. 添加详细的摘要信息 (Summary)
// 支持 HTML 格式
def successCount = manager.envVars['SUCCESS_COUNT'] ?: '0'
def failedCount = manager.envVars['FAILED_COUNT'] ?: '0'
def failedPlugins = manager.envVars['FAILED_PLUGINS'] ?: 'None'

def summary = manager.createSummary("graph.png") // 使用内置图标
summary.appendText("<h3>🔌 插件编译统计</h3>", false)
summary.appendText("<ul style='list-style: none; padding: 0;'>", false)
summary.appendText("<li>✅ 成功: <b>${successCount}</b></li>", false)

if (failedCount.toInteger() > 0) {
    summary.appendText("<li>❌ 失败: <b style='color: red;'>${failedCount}</b></li>", false)
    summary.appendText("<li>⚠️ 失败列表: ${failedPlugins}</li>", false)
} else {
    summary.appendText("<li>🎉 全部通过</li>", false)
}
summary.appendText("</ul>", false)

// 3. 动态修改构建描述
def currentDesc = manager.build.description ?: ""
manager.build.description = "${currentDesc} [Plugins: ${successCount}/${Integer.parseInt(successCount) + Integer.parseInt(failedCount)}]"
