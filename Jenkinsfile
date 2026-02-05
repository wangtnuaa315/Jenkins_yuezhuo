// NocoBase 插件自动化打包 Pipeline
// 需要先克隆主项目并构建，然后链接插件仓库进行打包

pipeline {
    agent any

    parameters {
        string(
            name: 'NOCOBASE_REPO',
            defaultValue: 'http://192.168.1.6:9000/innovation/yuezhuo.git',
            description: 'NocoBase 主项目仓库地址'
        )
        string(
            name: 'NOCOBASE_BRANCH',
            defaultValue: 'develop',
            description: 'NocoBase 主项目分支'
        )
        string(
            name: 'PLUGINS_REPO',
            defaultValue: 'http://192.168.1.6:9000/innovation/yuezhuo-plugins.git',
            description: '插件仓库地址'
        )
        string(
            name: 'PLUGINS_BRANCH',
            defaultValue: 'master',
            description: '插件仓库分支'
        )
        string(
            name: 'PLUGIN_NAME',
            defaultValue: 'plugin-message-queue',
            description: '插件名称（不含 @huaiye/ 前缀），留空或填 ALL 表示打包所有插件'
        )
        booleanParam(
            name: 'REBUILD_NOCOBASE',
            defaultValue: false,
            description: '是否重新构建 NocoBase 主项目（首次构建或依赖更新时勾选）'
        )
        booleanParam(
            name: 'CLEAN_WORKSPACE',
            defaultValue: false,
            description: '构建前清理工作区（会重新克隆所有代码）'
        )
    }

    environment {
        // 制品存储目录
        ARTIFACTS_DIR = '/mnt/sdb3/Jenkins_Yuezhuo'
        // Git 凭证 ID
        GIT_CREDENTIALS = 'yuezhuo-git-credentials'
        // 工作目录
        NOCOBASE_DIR = "${WORKSPACE}/nocobase"
        PLUGINS_DIR = "${WORKSPACE}/nocobase-plugins"
    }

    stages {
        stage('环境检查') {
            steps {
                script {
                    sh '''
                        echo "======== 环境信息 ========"
                        echo "Node.js 版本: $(node -v)"
                        echo "Yarn 版本: $(yarn -v)"
                        echo "工作目录: $(pwd)"
                        echo "========================="
                    '''
                }
            }
        }

        stage('清理工作区') {
            when {
                expression { params.CLEAN_WORKSPACE == true }
            }
            steps {
                sh '''
                    echo "======== 清理工作区 ========"
                    rm -rf ${WORKSPACE}/nocobase
                    rm -rf ${WORKSPACE}/nocobase-plugins
                    echo "清理完成"
                '''
            }
        }

        stage('拉取 NocoBase 主项目') {
            steps {
                script {
                    dir('nocobase') {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: "*/${params.NOCOBASE_BRANCH}"]],
                            extensions: [],
                            userRemoteConfigs: [[
                                url: params.NOCOBASE_REPO,
                                credentialsId: env.GIT_CREDENTIALS
                            ]]
                        ])
                        
                        env.NOCOBASE_COMMIT = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                        echo "NocoBase Commit: ${env.NOCOBASE_COMMIT}"
                    }
                }
            }
        }

        stage('拉取插件仓库') {
            steps {
                script {
                    dir('nocobase-plugins') {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: "*/${params.PLUGINS_BRANCH}"]],
                            extensions: [],
                            userRemoteConfigs: [[
                                url: params.PLUGINS_REPO,
                                credentialsId: env.GIT_CREDENTIALS
                            ]]
                        ])
                        
                        env.PLUGINS_COMMIT = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                        echo "Plugins Commit: ${env.PLUGINS_COMMIT}"
                    }
                }
            }
        }

        stage('安装主项目依赖') {
            steps {
                dir('nocobase') {
                    sh '''
                        echo "======== 配置 npm 镜像 ========"
                        yarn config set registry https://registry.npmmirror.com/
                        yarn config set disable-self-update-check true
                        
                        echo "======== 安装 NocoBase 依赖 ========"
                        yarn install --frozen-lockfile || yarn install
                        echo "依赖安装完成"
                    '''
                }
            }
        }

        stage('构建主项目') {
            when {
                anyOf {
                    expression { params.REBUILD_NOCOBASE == true }
                    expression { params.CLEAN_WORKSPACE == true }
                    expression { 
                        // 首次构建检测
                        return !fileExists("${env.NOCOBASE_DIR}/packages/core/client/lib")
                    }
                }
            }
            steps {
                dir('nocobase') {
                    sh '''
                        echo "======== 构建 NocoBase 主项目 ========"
                        yarn build
                        echo "主项目构建完成"
                    '''
                }
            }
        }

        stage('拷贝插件到主项目') {
            steps {
                script {
                    sh '''
                        echo "======== 拷贝插件到主项目 ========"
                        PLUGINS_TARGET="${WORKSPACE}/nocobase/packages/plugins"
                        
                        # 确保目标目录存在
                        mkdir -p "$PLUGINS_TARGET"
                        
                        # 删除旧的插件目录（如果存在）
                        rm -rf "$PLUGINS_TARGET/@huaiye"
                        
                        # 拷贝整个 @huaiye 目录
                        cp -r "${WORKSPACE}/nocobase-plugins/@huaiye" "$PLUGINS_TARGET/"
                        
                        echo "插件拷贝完成"
                        ls -la "$PLUGINS_TARGET/@huaiye/"
                    '''
                }
            }
        }

        stage('重新安装依赖') {
            steps {
                dir('nocobase') {
                    sh '''
                        echo "======== 重新安装依赖（识别新链接的插件）========"
                        yarn install --frozen-lockfile || yarn install
                        echo "依赖安装完成"
                    '''
                }
            }
        }

        stage('发现插件') {
            steps {
                script {
                    def pluginName = params.PLUGIN_NAME.trim()
                    
                    if (pluginName == '' || pluginName.toUpperCase() == 'ALL') {
                        // 自动发现所有插件
                        def plugins = sh(
                            script: '''
                                ls -1 ${WORKSPACE}/nocobase-plugins/@huaiye/ 2>/dev/null | sort
                            ''',
                            returnStdout: true
                        ).trim()
                        
                        if (plugins) {
                            env.PLUGIN_LIST = plugins
                            echo "发现 ${plugins.split('\n').size()} 个插件:\n${plugins}"
                        } else {
                            error "未找到任何插件目录"
                        }
                    } else {
                        env.PLUGIN_LIST = pluginName
                        echo "指定打包插件: ${pluginName}"
                    }
                }
            }
        }

        stage('构建打包') {
            steps {
                script {
                    def plugins = env.PLUGIN_LIST.split('\n')
                    def successCount = 0
                    def failedPlugins = []
                    def buildTimestamp = new Date().format('yyyyMMdd.HHmmss')
                    
                    dir('nocobase') {
                        for (plugin in plugins) {
                            plugin = plugin.trim()
                            if (!plugin) continue
                            
                            def fullName = "@huaiye/${plugin}"
                            def pluginPath = "packages/plugins/@huaiye/${plugin}"
                            echo "======== 开始处理: ${fullName} ========"
                            
                            try {
                                // 0. 修改版本号（追加时间戳）
                                sh """
                                    cd ${pluginPath}
                                    
                                    # 获取当前版本号
                                    current_version=\$(cat package.json | grep '"version"' | head -1 | sed 's/.*"version": "\\([^"]*\\)".*/\\1/')
                                    echo "当前版本: \$current_version"
                                    
                                    # 追加时间戳
                                    new_version="\${current_version}.${buildTimestamp}"
                                    echo "新版本: \$new_version"
                                    
                                    # 修改 package.json
                                    sed -i "s/\\"version\\": \\"[^\\"]*\\"/\\"version\\": \\"\$new_version\\"/" package.json
                                    
                                    # 验证修改
                                    grep '"version"' package.json
                                """
                                
                                // 1. 创建/注册插件
                                sh "yarn pm create ${fullName} || true"
                                
                                // 2. 构建
                                sh "yarn build ${fullName} --no-dts"
                                
                                // 3. 打包
                                sh "yarn nocobase tar ${fullName}"
                                
                                successCount++
                                echo "✅ ${fullName} 打包成功"
                            } catch (Exception e) {
                                failedPlugins.add(plugin)
                                echo "❌ ${fullName} 打包失败: ${e.message}"
                            }
                        }
                    }
                    
                    env.SUCCESS_COUNT = successCount.toString()
                    env.FAILED_PLUGINS = failedPlugins.join(',')
                    
                    echo "======== 构建结果 ========"
                    echo "成功: ${successCount} 个"
                    echo "失败: ${failedPlugins.size()} 个"
                    if (failedPlugins.size() > 0) {
                        echo "失败列表: ${failedPlugins.join(', ')}"
                    }
                }
            }
        }

        stage('归档制品') {
            steps {
                script {
                    def timestamp = new Date().format('yyyyMMdd_HHmmss')
                    def archiveDir = "${env.ARTIFACTS_DIR}/${timestamp}_build${env.BUILD_NUMBER}"
                    
                    sh """
                        echo "======== 归档制品 ========"
                        
                        mkdir -p ${archiveDir}
                        
                        # 复制 .tgz 文件到归档目录
                        if [ -d "${env.NOCOBASE_DIR}/storage/tar/@huaiye" ]; then
                            cp ${env.NOCOBASE_DIR}/storage/tar/@huaiye/*.tgz ${archiveDir}/ 2>/dev/null || true
                        fi
                        
                        # 创建构建信息文件
                        cat > ${archiveDir}/BUILD_INFO.txt << EOF
Build Number: ${env.BUILD_NUMBER}
NocoBase Commit: ${env.NOCOBASE_COMMIT}
Plugins Commit: ${env.PLUGINS_COMMIT}
NocoBase Branch: ${params.NOCOBASE_BRANCH}
Plugins Branch: ${params.PLUGINS_BRANCH}
Build Time: ${timestamp}
Success Count: ${env.SUCCESS_COUNT}
Failed Plugins: ${env.FAILED_PLUGINS}
EOF
                        
                        echo "制品已归档到: ${archiveDir}"
                        ls -la ${archiveDir}/ || echo "归档目录为空"
                    """
                    
                    // Jenkins 归档
                    archiveArtifacts artifacts: 'nocobase/storage/tar/*.tgz', allowEmptyArchive: true
                }
            }
        }

        stage('清理旧制品') {
            steps {
                script {
                    sh """
                        echo "======== 清理超过 7 天的旧制品 ========"
                        find ${env.ARTIFACTS_DIR} -maxdepth 1 -type d -mtime +7 -exec rm -rf {} \\; 2>/dev/null || true
                        echo "清理完成"
                        
                        echo "当前制品目录:"
                        ls -lht ${env.ARTIFACTS_DIR}/ | head -20
                    """
                }
            }
        }
    }

    post {
        success {
            echo """
            ========================================
            ✅ 构建成功！
            ========================================
            构建号: ${env.BUILD_NUMBER}
            NocoBase Commit: ${env.NOCOBASE_COMMIT}
            Plugins Commit: ${env.PLUGINS_COMMIT}
            成功打包: ${env.SUCCESS_COUNT} 个插件
            制品目录: ${env.ARTIFACTS_DIR}
            ========================================
            """
        }
        failure {
            echo """
            ========================================
            ❌ 构建失败！
            ========================================
            请检查控制台日志获取详细错误信息。
            ========================================
            """
        }
    }
}
