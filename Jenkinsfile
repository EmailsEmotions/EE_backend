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

    stage('Config Server Analysis') {
      steps {
        sh '''cd config-server
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dlicense.skip=true'''
      }
    }

  }
  environment {
    DOCKER_BUILDKIT = '1'
  }
}