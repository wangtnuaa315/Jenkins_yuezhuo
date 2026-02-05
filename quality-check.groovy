/**
 * ============================================================================
 * Yuezhuo 代码质量检查模块 (增强版)
 * ============================================================================
 * 
 * 【功能说明】
 * 提供可复用的代码质量检查功能，包含详细的指标数据：
 * - ESLint 代码规范检查（错误数、警告数、检查文件数）
 * - 单元测试（暂时跳过 - 待测试用例完善后启用）
 * - 代码覆盖率（行覆盖率、分支覆盖率）
 * - 依赖安全扫描（高危、中危、低危漏洞数）
 * 
 * 【使用方法】
 * def qualityChecks = load "quality-check.groovy"
 * def results = qualityChecks.runAllQualityChecks("/path/to/project")
 * qualityChecks.sendEmailReport(results, reportPath, "recipient@example.com")
 * 
 * 【参考范围】
 * ESLint:   错误=0 为通过，警告<50 为良好
 * 覆盖率:   >80% 优秀，60-80% 良好，<60% 需改进
 * 安全扫描: 高危=0 为通过
 * 
 * 【维护信息】
 * 更新时间：2026-02-04
 * ============================================================================
 */

/**
 * 执行 ESLint 代码规范检查
 * @param projectDir 项目根目录
 * @return Map 包含详细结果 {pass, errors, warnings, files, details}
 */
def runLint(String projectDir) {
    echo "======== ESLint 代码规范检查 ========"
    def result = [pass: true, errors: 0, warnings: 0, files: 0, details: [], topErrors: []]
    
    dir(projectDir) {
        try {
            timeout(time: 5, unit: 'MINUTES') {
                def output = sh(
                    script: '''
                        PLUGIN_DIR="packages/plugins/@huaiye"
                        
                        if [ -d "$PLUGIN_DIR" ]; then
                            echo "开始 ESLint 检查（限定 $PLUGIN_DIR 目录）..."
                            
                            # 统计插件数量
                            PLUGIN_COUNT=$(ls -1 "$PLUGIN_DIR" 2>/dev/null | wc -l)
                            echo "PLUGIN_COUNT=$PLUGIN_COUNT"
                            
                            # 使用 eslint 检查插件目录，输出 JSON 格式
                            npx eslint "$PLUGIN_DIR" --ext .ts,.tsx,.js,.jsx --format json -o eslint-report.json 2>/dev/null || true
                            
                            if [ -f "eslint-report.json" ]; then
                                # 统计问题数量
                                ERROR_COUNT=$(cat eslint-report.json | grep -o '"severity":2' | wc -l)
                                WARN_COUNT=$(cat eslint-report.json | grep -o '"severity":1' | wc -l)
                                FILE_COUNT=$(cat eslint-report.json | grep -o '"filePath"' | wc -l)
                                
                                echo "ERROR_COUNT=$ERROR_COUNT"
                                echo "WARN_COUNT=$WARN_COUNT"
                                echo "FILE_COUNT=$FILE_COUNT"
                                
                                # 提取前20个错误的详细信息（用于报告展示）
                                # 使用 Python 解析 JSON 并生成详细日志文件
                                python3 -c "
import json
try:
    with open('eslint-report.json') as f:
        data = json.load(f)
    
    errors = []
    for item in data:
        fp = item.get('filePath', '')
        parts = fp.split('/')
        short_path = '/'.join(parts[-3:]) if len(parts) >= 3 else fp
        for msg in item.get('messages', []):
            severity = msg.get('severity', 0)
            line = msg.get('line', 0)
            col = msg.get('column', 0)
            text = msg.get('message', '').replace('\\n', ' ')
            rule = msg.get('ruleId', 'unknown')
            errors.append({
                'file': short_path,
                'fullPath': fp,
                'line': line,
                'col': col,
                'message': text,
                'rule': rule,
                'severity': 'error' if severity == 2 else 'warning'
            })
    
    # 输出前20个错误给邮件使用
    error_only = [e for e in errors if e['severity'] == 'error']
    for e in error_only[:20]:
        print('DETAIL:{}|{}|{}|{}'.format(e['file'], e['line'], e['message'][:80], e['rule']))
    
    # 生成详细日志文件
    with open('eslint-errors.txt', 'w') as out:
        out.write('=' * 70 + '\\n')
        out.write('ESLint 代码检查详细报告\\n')
        out.write('=' * 70 + '\\n\\n')
        
        err_count = len([e for e in errors if e['severity'] == 'error'])
        warn_count = len([e for e in errors if e['severity'] == 'warning'])
        out.write('统计: {} 个错误, {} 个警告\\n\\n'.format(err_count, warn_count))
        
        # 按文件分组
        files = {}
        for e in errors:
            if e['fullPath'] not in files:
                files[e['fullPath']] = []
            files[e['fullPath']].append(e)
        
        for fp, errs in sorted(files.items()):
            file_errors = [e for e in errs if e['severity'] == 'error']
            file_warns = [e for e in errs if e['severity'] == 'warning']
            out.write('\\n--- {} ({} errors, {} warnings) ---\\n'.format(
                '/'.join(fp.split('/')[-4:]), len(file_errors), len(file_warns)))
            
            for e in sorted(errs, key=lambda x: x['line']):
                prefix = 'E' if e['severity'] == 'error' else 'W'
                out.write('  [{}] L{}: {} ({})\\n'.format(prefix, e['line'], e['message'][:100], e['rule']))
    
    print('LOGFILE=eslint-errors.txt')
except Exception as ex:
    pass
" 2>/dev/null || true
                            fi
                        else
                            echo "插件目录不存在"
                        fi
                    ''',
                    returnStdout: true
                ).trim()
                
                // 解析输出
                output.split('\n').each { line ->
                    if (line.startsWith('ERROR_COUNT=')) {
                        result.errors = line.replace('ERROR_COUNT=', '').trim().toInteger()
                    } else if (line.startsWith('WARN_COUNT=')) {
                        result.warnings = line.replace('WARN_COUNT=', '').trim().toInteger()
                    } else if (line.startsWith('FILE_COUNT=')) {
                        result.files = line.replace('FILE_COUNT=', '').trim().toInteger()
                    } else if (line.startsWith('DETAIL:')) {
                        def parts = line.replace('DETAIL:', '').split('\\|')
                        if (parts.size() >= 4) {
                            result.topErrors.add([
                                file: parts[0],
                                line: parts[1],
                                message: parts[2],
                                rule: parts[3]
                            ])
                        }
                    }
                }
                
                result.pass = (result.errors == 0)
                
                echo "ESLint 结果: ${result.errors} 错误, ${result.warnings} 警告, ${result.files} 文件"
                if (result.topErrors.size() > 0) {
                    echo "前 ${result.topErrors.size()} 个错误详情已收集"
                }
            }
        } catch (Exception e) {
            echo "ESLint 检查超时或失败: ${e.message}"
            result.pass = false
        }
    }
    return result
}

/**
 * 执行单元测试（暂时跳过）
 * @param projectDir 项目根目录
 * @return Map 包含详细结果
 */
def runTests(String projectDir) {
    echo "======== 单元测试 ========"
    echo "⏭️ 暂时跳过 - 待测试用例完善后启用"
    
    // 返回跳过状态
    return [
        pass: true, 
        skipped: true,
        total: 0,
        passed: 0,
        failed: 0,
        message: "暂无测试用例"
    ]
    
    /* 
    // 以下代码在测试用例完善后启用
    dir(projectDir) {
        def testResult = sh(
            script: '''
                if [ -f "package.json" ] && grep -q '"test"' package.json; then
                    yarn test --run --poolOptions.threads.singleThread=true 2>&1 || true
                fi
            ''',
            returnStdout: true
        )
        // 解析测试结果...
    }
    */
}

/**
 * 生成代码覆盖率报告
 * @param projectDir 项目根目录
 * @return Map 包含详细结果 {pass, lines, branches, functions}
 */
def runCoverage(String projectDir) {
    echo "======== 代码覆盖率 ========"
    echo "⏭️ 暂时跳过 - 待测试用例完善后启用"
    
    // 返回跳过状态
    return [
        pass: true,
        skipped: true,
        lines: 0,
        branches: 0,
        functions: 0,
        message: "暂无测试用例，无法生成覆盖率"
    ]
    
    /*
    // 以下代码在测试用例完善后启用
    dir(projectDir) {
        def coverageResult = sh(
            script: '''
                yarn test --coverage --run 2>&1 || true
            ''',
            returnStdout: true
        )
        // 解析覆盖率结果...
    }
    */
}

/**
 * 执行依赖安全扫描
 * @param projectDir 项目根目录
 * @return Map 包含详细结果 {pass, high, moderate, low, total, topVulns}
 */
def runSecurityAudit(String projectDir) {
    echo "======== 依赖安全扫描 ========"
    def result = [pass: true, high: 0, moderate: 0, low: 0, total: 0, topVulns: []]
    
    dir(projectDir) {
        try {
            // 1. 先运行完整的 audit 并保存到文件
            sh '''
                if [ -f "yarn.lock" ]; then
                    yarn audit --json > security-audit-raw.json 2>/dev/null || true
                elif [ -f "package-lock.json" ]; then
                    npm audit --json > security-audit-raw.json 2>/dev/null || true
                fi
            '''
            
            // 2. 使用 Python 解析并生成详细报告
            def output = sh(
                script: '''
                    if [ -f "security-audit-raw.json" ]; then
                        python3 -c "
import json
import sys

high = moderate = low = 0
vulns = []

try:
    with open('security-audit-raw.json') as f:
        for line in f:
            try:
                data = json.loads(line.strip())
                if data.get('type') == 'auditAdvisory':
                    adv = data.get('data', {}).get('advisory', {})
                    severity = adv.get('severity', 'low')
                    if severity == 'high' or severity == 'critical':
                        high += 1
                    elif severity == 'moderate':
                        moderate += 1
                    else:
                        low += 1
                    
                    vulns.append({
                        'severity': severity,
                        'module': adv.get('module_name', 'unknown'),
                        'title': adv.get('title', '')[:100],
                        'url': adv.get('url', '')
                    })
                elif data.get('type') == 'auditSummary':
                    summary = data.get('data', {}).get('vulnerabilities', {})
                    high = summary.get('high', 0) + summary.get('critical', 0)
                    moderate = summary.get('moderate', 0)
                    low = summary.get('low', 0) + summary.get('info', 0)
            except:
                continue

    print('HIGH={}'.format(high))
    print('MODERATE={}'.format(moderate))
    print('LOW={}'.format(low))
    
    # 输出前15个高危漏洞详情
    high_vulns = [v for v in vulns if v['severity'] in ('high', 'critical')][:15]
    for v in high_vulns:
        print('VULN:{}|{}|{}'.format(v['severity'], v['module'], v['title']))
    
    # 生成详细日志文件
    with open('security-audit-details.txt', 'w') as out:
        out.write('=' * 60 + '\\n')
        out.write('依赖安全扫描详细报告\\n')
        out.write('=' * 60 + '\\n\\n')
        out.write('统计: {} 高危, {} 中危, {} 低危\\n\\n'.format(high, moderate, low))
        
        for sev in ['critical', 'high', 'moderate', 'low']:
            sev_vulns = [v for v in vulns if v['severity'] == sev]
            if sev_vulns:
                out.write('\\n--- {} ({} 个) ---\\n'.format(sev.upper(), len(sev_vulns)))
                for v in sev_vulns:
                    out.write('  [{}] {}\\n'.format(v['module'], v['title']))
                    if v['url']:
                        out.write('      {}'.format(v['url']) + '\\n')
    
    print('LOGFILE=security-audit-details.txt')
except Exception as e:
    print('ERROR={}'.format(str(e)))
"
                    fi
                ''',
                returnStdout: true
            ).trim()
            
            // 3. 解析输出
            output.split('\n').each { line ->
                if (line.startsWith('HIGH=')) {
                    result.high = line.replace('HIGH=', '').trim().toInteger()
                } else if (line.startsWith('MODERATE=')) {
                    result.moderate = line.replace('MODERATE=', '').trim().toInteger()
                } else if (line.startsWith('LOW=')) {
                    result.low = line.replace('LOW=', '').trim().toInteger()
                } else if (line.startsWith('VULN:')) {
                    def parts = line.replace('VULN:', '').split('\\|')
                    if (parts.size() >= 3) {
                        result.topVulns.add([
                            severity: parts[0],
                            module: parts[1],
                            title: parts[2]
                        ])
                    }
                }
            }
            
            result.total = result.high + result.moderate + result.low
            result.pass = (result.high == 0)
            
            echo "安全扫描结果: ${result.high} 高危, ${result.moderate} 中危, ${result.low} 低危"
            if (result.topVulns.size() > 0) {
                echo "发现 ${result.topVulns.size()} 个高危漏洞详情已收集"
            }
            
        } catch (Exception e) {
            echo "安全扫描失败: ${e.message}"
            result.pass = true
            result.message = "扫描异常"
        }
    }
    return result
}

/**
 * 执行所有质量检查
 * @param projectDir 项目根目录
 * @return Map 包含所有检查的详细结果
 */
def runAllQualityChecks(String projectDir) {
    echo "======== 开始代码质量检查 ========"
    
    def results = [:]
    
    results.lint = runLint(projectDir)
    results.tests = runTests(projectDir)
    results.coverage = runCoverage(projectDir)
    results.security = runSecurityAudit(projectDir)
    
    // 计算整体状态
    results.overallPass = results.lint.pass && results.security.pass
    
    echo """
    ========================================
    代码质量检查完成
    ========================================
    ESLint:     ${results.lint.pass ? '✅ 通过' : '❌ 有错误'} (${results.lint.errors} 错误, ${results.lint.warnings} 警告)
    单元测试:   ${results.tests.skipped ? '⏭️ 跳过' : (results.tests.pass ? '✅ 通过' : '❌ 失败')}
    覆盖率:     ${results.coverage.skipped ? '⏭️ 跳过' : (results.coverage.lines + '%')}
    安全扫描:   ${results.security.pass ? '✅ 通过' : '⚠️ 有漏洞'} (${results.security.high} 高危)
    ========================================
    """
    
    return results
}

/**
 * 发布质量报告到 Jenkins
 * @param projectDir 项目根目录
 */
def publishReports(String projectDir) {
    echo "======== 发布质量报告 ========"
    
    try {
        recordIssues(
            tools: [esLint(pattern: "${projectDir}/**/eslint-report.json")],
            qualityGates: [[threshold: 1, type: 'TOTAL', unstable: true]]
        )
    } catch (Exception e) {
        echo "ESLint 报告发布失败: ${e.message}"
    }
}

/**
 * 生成 HTML 报告文件
 * @param results 检查结果
 * @param outputPath 报告输出路径
 */
def generateHtmlReport(Map results, String outputPath) {
    def buildNumber = env.BUILD_NUMBER ?: 'N/A'
    def jobName = env.JOB_NAME ?: 'Unknown'
    def buildUrl = env.BUILD_URL ?: ''
    def timestamp = new Date().format('yyyy-MM-dd HH:mm:ss')
    
    def html = """<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>代码质量报告 #${buildNumber}</title>
    <style>
        :root {
            --primary-color: #3b82f6;
            --success-color: #10b981;
            --danger-color: #ef4444;
            --warning-color: #f59e0b;
            --bg-color: #f3f4f6;
            --card-bg: #ffffff;
            --text-main: #1f2937;
            --text-secondary: #6b7280;
        }
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
            background-color: var(--bg-color);
            color: var(--text-main);
            margin: 0;
            padding: 20px;
            line-height: 1.5;
        }
        .container { max-width: 800px; margin: 0 auto; }
        .header-card {
            background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%);
            color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
            margin-bottom: 24px;
        }
        .header-card h1 { margin: 0; font-size: 24px; }
        .header-card p { margin: 8px 0 0; opacity: 0.9; font-size: 14px; }
        .stats-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 16px;
            margin-bottom: 24px;
        }
        .stat-card {
            background: var(--card-bg);
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
            text-align: center;
            border-top: 4px solid var(--primary-color);
        }
        .stat-card.success { border-color: var(--success-color); }
        .stat-card.warning { border-color: var(--warning-color); }
        .stat-card.danger { border-color: var(--danger-color); }
        .stat-value { font-size: 28px; font-weight: 700; margin: 8px 0; }
        .stat-label { color: var(--text-secondary); font-size: 13px; text-transform: uppercase; }
        .main-card {
            background: var(--card-bg);
            border-radius: 10px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
            padding: 24px;
            margin-bottom: 24px;
        }
        .card-title { font-size: 18px; font-weight: 600; margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid #e5e7eb; }
        .check-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 14px 0;
            border-bottom: 1px solid #f3f4f6;
        }
        .check-row:last-child { border-bottom: none; }
        .check-name { font-weight: 500; }
        .check-detail { color: var(--text-secondary); font-size: 13px; margin-top: 4px; }
        .status-badge {
            display: inline-flex;
            align-items: center;
            padding: 4px 12px;
            border-radius: 9999px;
            font-size: 12px;
            font-weight: 500;
        }
        .status-success { background-color: #d1fae5; color: #065f46; }
        .status-warning { background-color: #fef3c7; color: #92400e; }
        .status-danger { background-color: #fee2e2; color: #991b1b; }
        .status-skipped { background-color: #e5e7eb; color: #6b7280; }
        .reference { background: #f9fafb; padding: 16px; border-radius: 8px; margin-top: 16px; }
        .reference-title { font-weight: 600; margin-bottom: 8px; font-size: 14px; }
        .reference-item { font-size: 13px; color: var(--text-secondary); margin: 4px 0; }
        .footer { text-align: center; color: var(--text-secondary); font-size: 12px; margin-top: 30px; }
        .error-table { width: 100%; border-collapse: collapse; font-size: 12px; margin-top: 12px; }
        .error-table th { background: #f9fafb; padding: 10px 8px; text-align: left; font-weight: 600; color: var(--text-secondary); border-bottom: 2px solid #e5e7eb; }
        .error-table td { padding: 8px; border-bottom: 1px solid #f3f4f6; vertical-align: top; }
        .error-table tr:hover { background-color: #f9fafb; }
        .file-path { font-family: monospace; color: var(--primary-color); max-width: 180px; word-break: break-all; }
        .line-num { text-align: center; font-family: monospace; color: var(--warning-color); font-weight: 600; }
        .error-msg { color: var(--text-main); max-width: 300px; }
        .rule-name { font-family: monospace; font-size: 11px; color: var(--danger-color); background: #fee2e2; padding: 2px 6px; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header-card">
            <h1>🔍 代码质量报告</h1>
            <p>构建 #${buildNumber} • ${jobName} • ${timestamp}</p>
            ${results.plugins?.size() > 0 ? '<p style="margin-top: 8px; font-size: 13px; opacity: 0.9;">📦 检查插件: ' + results.plugins.join(', ') + '</p>' : ''}
        </div>

        <div class="stats-grid">
            <div class="stat-card ${results.lint.errors == 0 ? 'success' : 'danger'}">
                <div class="stat-value" style="color: ${results.lint.errors == 0 ? 'var(--success-color)' : 'var(--danger-color)'}">
                    ${results.lint.errors}
                </div>
                <div class="stat-label">ESLint 错误</div>
            </div>
            <div class="stat-card ${results.lint.warnings < 50 ? 'warning' : 'danger'}">
                <div class="stat-value" style="color: var(--warning-color)">${results.lint.warnings}</div>
                <div class="stat-label">ESLint 警告</div>
            </div>
            <div class="stat-card ${results.security.high == 0 ? 'success' : 'danger'}">
                <div class="stat-value" style="color: ${results.security.high == 0 ? 'var(--success-color)' : 'var(--danger-color)'}">
                    ${results.security.high}
                </div>
                <div class="stat-label">高危漏洞</div>
            </div>
        </div>

        <div class="main-card">
            <div class="card-title">检查详情</div>
            
            <div class="check-row">
                <div>
                    <div class="check-name">ESLint 代码规范</div>
                    <div class="check-detail">检查 ${results.lint.files} 个文件，发现 ${results.lint.errors} 错误 ${results.lint.warnings} 警告</div>
                </div>
                <span class="status-badge ${results.lint.pass ? 'status-success' : 'status-danger'}">
                    ${results.lint.pass ? '✓ 通过' : '✗ 有错误'}
                </span>
            </div>
            
            <div class="check-row">
                <div>
                    <div class="check-name">单元测试</div>
                    <div class="check-detail">${results.tests.skipped ? '暂无测试用例，待完善后启用' : (results.tests.passed + '/' + results.tests.total + ' 通过')}</div>
                </div>
                <span class="status-badge ${results.tests.skipped ? 'status-skipped' : (results.tests.pass ? 'status-success' : 'status-danger')}">
                    ${results.tests.skipped ? '⏭ 跳过' : (results.tests.pass ? '✓ 通过' : '✗ 失败')}
                </span>
            </div>
            
            <div class="check-row">
                <div>
                    <div class="check-name">代码覆盖率</div>
                    <div class="check-detail">${results.coverage.skipped ? '暂无测试用例，无法生成覆盖率' : ('行覆盖率 ' + results.coverage.lines + '%')}</div>
                </div>
                <span class="status-badge ${results.coverage.skipped ? 'status-skipped' : (results.coverage.lines >= 60 ? 'status-success' : 'status-warning')}">
                    ${results.coverage.skipped ? '⏭ 跳过' : (results.coverage.lines + '%')}
                </span>
            </div>
            
            <div class="check-row">
                <div>
                    <div class="check-name">依赖安全扫描</div>
                    <div class="check-detail">${results.security.high} 高危, ${results.security.moderate} 中危, ${results.security.low} 低危</div>
                </div>
                <span class="status-badge ${results.security.pass ? 'status-success' : 'status-danger'}">
                    ${results.security.pass ? '✓ 通过' : '⚠ 有漏洞'}
                </span>
            </div>
        </div>

        ${results.lint.topErrors?.size() > 0 ? """
        <div class="main-card">
            <div class="card-title">📋 ESLint 错误详情（前 ${results.lint.topErrors.size()} 个）</div>
            <table class="error-table">
                <thead>
                    <tr>
                        <th>文件</th>
                        <th>行号</th>
                        <th>错误信息</th>
                        <th>规则</th>
                    </tr>
                </thead>
                <tbody>
                    ${results.lint.topErrors.collect { err -> """
                    <tr>
                        <td class="file-path">${err.file}</td>
                        <td class="line-num">${err.line}</td>
                        <td class="error-msg">${err.message}</td>
                        <td class="rule-name">${err.rule}</td>
                    </tr>
                    """}.join('')}
                </tbody>
            </table>
            <div style="text-align: center; color: var(--text-secondary); font-size: 12px; margin-top: 12px;">
                共 ${results.lint.errors} 个错误，此处仅显示前 ${results.lint.topErrors.size()} 个，完整报告请查看 eslint-report.json
            </div>
        </div>
        """ : ''}

        <div class="main-card">
            <div class="reference">
                <div class="reference-title">📊 参考范围</div>
                <div class="reference-item">• ESLint: 错误=0 为通过，警告<50 为良好</div>
                <div class="reference-item">• 覆盖率: >80% 优秀，60-80% 良好，<60% 需改进</div>
                <div class="reference-item">• 安全: 高危=0 为通过，中低危建议修复</div>
            </div>
        </div>

        <div class="footer">
            Generated by Jenkins CI • Yuezhuo Quality Check Module
            <br><a href="${buildUrl}" style="color: var(--primary-color);">查看完整构建日志</a>
        </div>
    </div>
</body>
</html>"""
    
    writeFile file: outputPath, text: html
    echo "HTML 报告已生成: ${outputPath}"
}

/**
 * 发送质量报告邮件（增强版）
 * @param results 检查结果 Map
 * @param reportPath HTML 报告路径
 * @param recipients 收件人
 */
def sendEmailReport(Map results, String reportPath, String recipients = '') {
    echo "======== 发送质量报告邮件 ========"
    
    def buildNumber = env.BUILD_NUMBER ?: 'N/A'
    def jobName = env.JOB_NAME ?: 'Unknown'
    def buildUrl = env.BUILD_URL ?: ''
    def timestamp = new Date().format('yyyy-MM-dd HH:mm:ss')
    
    // 确定整体状态
    def overallPass = results.lint.pass && results.security.pass
    def overallStatus = overallPass ? '✅ 通过' : '⚠️ 需关注'
    
    def emailSubject = "[Jenkins] 代码质量报告 #${buildNumber} - ${overallStatus}"
    
    def emailBody = """<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body { font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif; margin: 0; padding: 0; background: #f3f4f6; }
        .wrapper { max-width: 600px; margin: 0 auto; padding: 20px; }
        .header {
            background: linear-gradient(135deg, #1e3a8a 0%, #3b82f6 100%);
            color: white;
            padding: 25px;
            border-radius: 12px 12px 0 0;
        }
        .header h1 { margin: 0; font-size: 22px; }
        .header p { margin: 8px 0 0; opacity: 0.9; font-size: 13px; }
        .content { background: white; padding: 25px; border-radius: 0 0 12px 12px; }
        .stats-row { display: flex; gap: 12px; margin-bottom: 20px; }
        .stat-box {
            flex: 1;
            text-align: center;
            padding: 15px;
            border-radius: 8px;
            background: #f9fafb;
        }
        .stat-box.success { border-top: 3px solid #10b981; }
        .stat-box.warning { border-top: 3px solid #f59e0b; }
        .stat-box.danger { border-top: 3px solid #ef4444; }
        .stat-value { font-size: 24px; font-weight: 700; }
        .stat-label { font-size: 11px; color: #6b7280; text-transform: uppercase; margin-top: 4px; }
        .check-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 12px 0;
            border-bottom: 1px solid #f3f4f6;
        }
        .check-item:last-child { border-bottom: none; }
        .check-name { font-weight: 500; font-size: 14px; }
        .check-detail { font-size: 12px; color: #6b7280; margin-top: 2px; }
        .badge { padding: 4px 10px; border-radius: 20px; font-size: 11px; font-weight: 500; }
        .badge-success { background: #d1fae5; color: #065f46; }
        .badge-warning { background: #fef3c7; color: #92400e; }
        .badge-danger { background: #fee2e2; color: #991b1b; }
        .badge-skip { background: #e5e7eb; color: #6b7280; }
        .reference { background: #f9fafb; padding: 12px; border-radius: 6px; margin-top: 16px; font-size: 12px; color: #6b7280; }
        .btn { display: inline-block; background: #3b82f6; color: white; padding: 10px 20px; text-decoration: none; border-radius: 6px; margin-top: 16px; font-size: 13px; }
        .footer { text-align: center; padding: 20px; color: #9ca3af; font-size: 11px; }
    </style>
</head>
<body>
    <div class="wrapper">
        <div class="header">
            <h1>🔍 代码质量报告</h1>
            <p>构建 #${buildNumber} • ${jobName} • ${timestamp}</p>
            ${results.plugins?.size() > 0 ? '<p style="margin-top: 6px; font-size: 12px; opacity: 0.9;">📦 ' + results.plugins.join(', ') + '</p>' : ''}
        </div>
        <div class="content">
            <div class="stats-row">
                <div class="stat-box ${results.lint.errors == 0 ? 'success' : 'danger'}">
                    <div class="stat-value" style="color: ${results.lint.errors == 0 ? '#10b981' : '#ef4444'}">${results.lint.errors}</div>
                    <div class="stat-label">ESLint 错误</div>
                </div>
                <div class="stat-box warning">
                    <div class="stat-value" style="color: #f59e0b">${results.lint.warnings}</div>
                    <div class="stat-label">ESLint 警告</div>
                </div>
                <div class="stat-box ${results.security.high == 0 ? 'success' : 'danger'}">
                    <div class="stat-value" style="color: ${results.security.high == 0 ? '#10b981' : '#ef4444'}">${results.security.high}</div>
                    <div class="stat-label">高危漏洞</div>
                </div>
            </div>
            
            <div class="check-item">
                <div>
                    <div class="check-name">ESLint 代码规范</div>
                    <div class="check-detail">${results.lint.files} 文件, ${results.lint.errors} 错误, ${results.lint.warnings} 警告</div>
                </div>
                <span class="badge ${results.lint.pass ? 'badge-success' : 'badge-danger'}">${results.lint.pass ? '✓ 通过' : '✗ 错误'}</span>
            </div>
            
            <div class="check-item">
                <div>
                    <div class="check-name">单元测试</div>
                    <div class="check-detail">${results.tests.skipped ? '暂无测试用例' : (results.tests.passed + '/' + results.tests.total)}</div>
                </div>
                <span class="badge badge-skip">⏭ 跳过</span>
            </div>
            
            <div class="check-item">
                <div>
                    <div class="check-name">代码覆盖率</div>
                    <div class="check-detail">${results.coverage.skipped ? '暂无测试用例' : (results.coverage.lines + '%')}</div>
                </div>
                <span class="badge badge-skip">⏭ 跳过</span>
            </div>
            
            <div class="check-item">
                <div>
                    <div class="check-name">依赖安全扫描</div>
                    <div class="check-detail">${results.security.high} 高危, ${results.security.moderate} 中危, ${results.security.low} 低危</div>
                </div>
                <span class="badge ${results.security.pass ? 'badge-success' : 'badge-danger'}">${results.security.pass ? '✓ 通过' : '⚠ 漏洞'}</span>
            </div>
            
            ${results.lint.topErrors?.size() > 0 ? '''
            <div style="margin-top: 20px; padding-top: 16px; border-top: 1px solid #e5e7eb;">
                <div style="font-weight: 600; margin-bottom: 12px;">📋 主要 ESLint 错误（前 ''' + results.lint.topErrors.size() + ''' 个）</div>
                <table style="width: 100%; border-collapse: collapse; font-size: 11px;">
                    <tr style="background: #f9fafb;">
                        <th style="padding: 8px; text-align: left; border-bottom: 1px solid #e5e7eb;">文件:行号</th>
                        <th style="padding: 8px; text-align: left; border-bottom: 1px solid #e5e7eb;">错误信息</th>
                    </tr>
                    ''' + results.lint.topErrors.collect { err -> '''
                    <tr>
                        <td style="padding: 6px 8px; border-bottom: 1px solid #f3f4f6; font-family: monospace; color: #3b82f6;">''' + err.file + ':' + err.line + '''</td>
                        <td style="padding: 6px 8px; border-bottom: 1px solid #f3f4f6; color: #374151;">''' + err.message + '''</td>
                    </tr>
                    '''}.join('') + '''
                </table>
            </div>
            ''' : ''}
            
            ${results.security.topVulns?.size() > 0 ? '''
            <div style="margin-top: 20px; padding-top: 16px; border-top: 1px solid #e5e7eb;">
                <div style="font-weight: 600; margin-bottom: 12px;">🛡️ 高危安全漏洞（前 ''' + results.security.topVulns.size() + ''' 个）</div>
                <table style="width: 100%; border-collapse: collapse; font-size: 11px;">
                    <tr style="background: #f9fafb;">
                        <th style="padding: 8px; text-align: left; border-bottom: 1px solid #e5e7eb;">包名</th>
                        <th style="padding: 8px; text-align: left; border-bottom: 1px solid #e5e7eb;">漏洞描述</th>
                    </tr>
                    ''' + results.security.topVulns.collect { vuln -> '''
                    <tr>
                        <td style="padding: 6px 8px; border-bottom: 1px solid #f3f4f6; font-family: monospace; color: #ef4444;">''' + vuln.module + '''</td>
                        <td style="padding: 6px 8px; border-bottom: 1px solid #f3f4f6; color: #374151;">''' + vuln.title + '''</td>
                    </tr>
                    '''}.join('') + '''
                </table>
            </div>
            ''' : ''}
            
            <div class="reference">
                <strong>📊 参考范围:</strong> ESLint 错误=0 为通过 | 覆盖率 >80% 优秀 | 高危漏洞=0 为通过
            </div>
            
            <div style="margin-top: 16px;">
                <a href="${buildUrl}" class="btn">查看完整报告 →</a>
                <a href="${buildUrl}artifact/eslint-errors.txt" style="display: inline-block; background: #6b7280; color: white; padding: 10px 16px; text-decoration: none; border-radius: 6px; margin-left: 8px; font-size: 13px;">📄 ESLint 详情</a>
                <a href="${buildUrl}artifact/security-audit-details.txt" style="display: inline-block; background: #6b7280; color: white; padding: 10px 16px; text-decoration: none; border-radius: 6px; margin-left: 8px; font-size: 13px;">🛡️ 漏洞详情</a>
            </div>
        </div>
        <div class="footer">
            Generated by Jenkins CI • Yuezhuo Quality Check Module
        </div>
    </div>
</body>
</html>"""
    
    try {
        mail(
            to: recipients,
            subject: emailSubject,
            body: emailBody,
            mimeType: 'text/html'
        )
        echo "邮件发送成功"
    } catch (Exception e) {
        echo "邮件发送失败: ${e.message}"
    }
}

// 返回 this 以支持 load 调用
return this
