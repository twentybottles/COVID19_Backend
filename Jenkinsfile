pipeline {
    agent any
    // 定数や変数を定義する
    environment {
        javaDir = 'src/main/java'
        libsDir = 'build/libs'
        appName = 'COVID19_Backend'
        appVersion = '1.0.0'
    }

    // stagesブロック中に一つ以上のstageを定義する
    stages {
        stage('Prepare environment') {
            // 実際の処理はstepsブロック中に定義する
            steps {
                deleteDir()

                // このJobをトリガーしてきたGithubのプロジェクトをチェックアウト
                checkout scm

                // ジョブ失敗の原因調査用にJenkinsfileとbuild.gradleは最初に保存する
                archiveArtifacts "Jenkinsfile"
                archiveArtifacts "build.gradle"

                // scriptブロックを使うと従来のScripted Pipelinesの記法も使える
                script {
                    // Permission deniedで怒られないために実行権限を付与する
                    if(isUnix()) {
                        sh 'chmod +x gradlew'
                    }
                }
                gradlew 'clean'
            }
        }

        stage('Compile') {
            steps {
                gradlew 'clean build'
            }

            // postブロックでstepsブロックの後に実行される処理が定義できる
            post {
                // alwaysブロックはstepsブロックの処理が失敗しても成功しても必ず実行される
                always {

                    // JavaDoc生成時に実行するとJavaDocの警告も含まれてしまうので
                    // Javaコンパイル時の警告はコンパイル直後に収集する
                    step([

                        // プラグインを実行するときのクラス指定は完全修飾名でなくてもOK
                        $class: 'WarningsPublisher',

                        // Job実行時のコンソールから警告を収集する場合はconsoleParsers、
                        // pmd.xmlなどのファイルから収集する場合はparserConfigurationsを指定する。
                        // なおparserConfigurationsの場合はparserNameのほかにpattern(集計対象ファイルのパス)も指定が必要
                        // パーサ名は下記プロパティファイルに定義されているものを使う
                        //https://github.com/jenkinsci/warnings-plugin/blob/master/src/main/resources/hudson/plugins/warnings/parser/Messages.properties
                        consoleParsers: [
                            [parserName: 'Java Compiler (javac)'],
                        ],
                        canComputeNew: false,
                        canResolveRelativesPaths: false,
                        usePreviousBuildAsReference: true
                    ])
                }
            }
        }

        stage('Deploy') {
            // whenブロックでstageを実行する条件を指定できる
            when {
                // 静的コード解析とテスト失敗時はデプロイしない
                expression {currentBuild.currentResult == 'SUCCESS'}
            }

            steps {
                gradlew 'jar'
                archiveArtifacts "${libsDir}/${appName}-${appVersion}.jar"
                deploy libsDir : libsDir, appName: appName, appVersion: appVersion
            }
        }
    }

    // stagesブロックと同じレベルにpostブロックを定義すると
    // 全てのstage処理が終わった後の処理の定義が可能    
    post {
        always {
            // 最後にワークスペースの中身を削除
            deleteDir()
        }
    }
}


// Gradlewコマンドを実行する
def gradlew(command) {
    if(isUnix()) {
        sh "./gradlew ${command} --stacktrace"
    } else {
        bat "./gradlew.bat ${command} --stacktrace"
    }
}

// デプロイする
// args.warDir warの格納ディレクトリ 
// args.appName アプリ名
// args.appVersion アプリのバージョン
def deploy(Map args) {
    // 秘密鍵のパス ※Tomcatサーバにファイル転送するので事前にJenkinsサーバのどこかに秘密鍵を格納しておく必要がある
    def keyDir = '/Users/riki/.ssh/id_rsa.pub'
    // Tomcatサーバのアドレスとユーザ名
    def webServerAddress = 'ec2-3-20-220-91.us-east-2.compute.amazonaws.com'
    def webServerUser = 'ec2-user'
    def webServer = "${webServerUser}@${webServerAddress}"

    def srcJar = "${args.appName}-${args.appVersion}.jar"
    def destJar = "${args.appName}.jar"

    // ファイル転送してTomcatのwebappsにwarを配置する
    sh "sudo -S scp -i ${keyDir} ./${args.libsDir}/${srcJar} ${webServer}"
    sh "sudo -S ssh -i ${keyDir} ${webServer} \"sudo cp ${srcJar} ${destJar}\""
}