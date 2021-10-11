pipeline {
  agent {
    docker {
      image 'maven:3.8-openjdk-11'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'cd config-server && mvn -B -DskipTests'
      }
    }

    stage('Test') {
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }

      }
      steps {
        sh 'cd config-server && mvn test'
      }
    }

    stage('Static Code Analysis') {
      steps {
        withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins-sonar') {
          sh 'cd config-server && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        }

      }
    }

  }
}