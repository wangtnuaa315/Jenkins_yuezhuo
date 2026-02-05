# Jenkins 报告方案演示

这里包含了为您准备的 4 种 Jenkins 构建报告方案 Demo。

## 📂 文件列表

1. **[方案一] HTML Publisher (推荐)**
   - 文件: `awesome_report_template.html`
   - 说明: 这是一个独立的 HTML 模板，设计精美。您可以直接下载并在浏览器打开查看效果。
   - 用法: 将此 HTML 生成逻辑嵌入 Jenkinsfile，配合 HTML Publisher 插件使用。

2. **[方案二] Build Summary**
   - 文件: `scheme2_build_summary.groovy`
   - 说明: Groovy 脚本演示。
   - 用法: 用于 `Groovy Postbuild` 插件，能在构建历史侧边栏显示徽章 (Badge) 和摘要。

3. **[方案三] Allure Report**
   - 文件: `scheme3_allure_demo.md`
   - 说明: Markdown 文档。
   - 用法: 介绍了如何在 Jenkinsfile 中配置 Allure，适合需要详细测试用例分析的场景。

4. **[方案四] JSON + 前端渲染**
   - 文件: `scheme4_json_frontend.html`
   - 说明: 一个基于 Vue.js 的单页应用示例。
   - 用法: Jenkins 只负责生成 `report.json`，由这个 HTML 文件动态渲染表格和图表。适合极客或需要高度定制交互的场景。

## 🚀 如何查看

- 直接在文件管理器中双击 `.html` 文件，即可在浏览器中预览 UI 效果。
- 查看 `.groovy` 和 `.md` 文件以了解代码实现。
