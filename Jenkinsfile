pipeline {
  agent any
  stages {
    stage('Build Config Server') {
      steps {
        withMaven(jdk: 'Java', maven: 'maven') {
          echo 'Building Config Server'
          sh '''cd config-server &&
mvn clean install -Dmaven.test.skip=true'''
        }

      }
    }

    stage('Build Completed') {
      steps {
        echo 'Build Completed'
      }
    }

    stage('SonarV2') {
      steps {
        withMaven(jdk: 'Java', maven: 'maven') {
          withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins-sonar') {
            sh 'cd config-server && mvn clean package sonar:sonar -Dlicense.skip=true -Dmaven.test.skip=true'
          }

        }

      }
    }

  }
  environment {
    DOCKER_BUILDKIT = '1'
  }
}