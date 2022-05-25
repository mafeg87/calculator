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
                sh "./gradlew jacocoTestReport"
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
        stage('Sonarqube') {
            environment {
               scannerHome = tool 'SonarQubeScanner'
            }
            steps {
               withSonarQubeEnv('sonarqube') {
                  sh "${scannerHome}/bin/sonar-scanner"
                }
                timeout(time: 10, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
            }
       }

        
    }

    node {
            stage('SCM') {
            checkout scm
        }
        stage('SonarQube Analysis') {
             withSonarQubeEnv() {
             sh "./gradlew sonarqube"
           }
        }
    }

}