pipeline {
    agent any
    stages {
        stage ("Checkout") {
            steps {
                git url: 'https://github.com/adamfrance/calculator.git', branch : 'master'
            }
        }
        stage("Compile") {
            steps {
                sh "./gradlew compileJava"
            }
        }
        stage ("Unit test") {
            steps {
                sh "./gradlew test"
            }
        }
        stage("code coverage"){
            steps {
                sh "./gradlew jococoTestReport"
                publishHTML (target :[
                    reportDir : 'build/reports/jacoco/test/html',
                    reportFiles : 'index.html',
                    reportName : "Jacoco Report"
                ])
                sh "./gradlew jacocoTestCoverageVerification"
            }
        }
        stage ("Static code analysis"){
            steps {
                sh "./gradlew checkstyleMain"
                publishHTML (target :[
                    reportDir : 'build/reports/checkstyle/',
                    reportFiles : 'main.html',
                    reportName : "Checkstyle Report"
                ])
            }
        }
    }
}