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
        sh 'docker build -f config-server/Dockerfile-with-maven .'
      }
    }

  }
}