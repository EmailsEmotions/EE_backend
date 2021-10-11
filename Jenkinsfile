pipeline {
  agent any
  stages {
    stage('Clone repository') {
      steps {
        git(url: 'https://github.com/EmailsEmotions/EE_backend', branch: 'develop')
      }
    }

    stage('Build') {
      steps {
        sh '''cd config-server
docker build -f Dockerfile-with-maven .'''
      }
    }

  }
  environment {
    DOCKER_BUILDKIT = '1'
  }
}