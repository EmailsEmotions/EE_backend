pipeline {
  agent any
  stages {
    stage('Clone repository') {
      parallel {
        stage('Clone repository') {
          steps {
            git(url: 'https://github.com/EmailsEmotions/EE_backend', branch: 'develop')
          }
        }

        stage('SonarQube') {
          steps {
            withSonarQubeEnv 'SonarQube'
          }
        }

      }
    }

    stage('Build') {
      parallel {
        stage('Build Config Server') {
          steps {
            sh '''cd config-server
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build Discovery Server') {
          steps {
            sh '''cd discovery-server
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build API Gateway') {
          steps {
            sh '''cd api-gateway
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build Admin Server') {
          steps {
            sh '''cd admin-server
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build User Service') {
          steps {
            sh '''cd users-service
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build Formality Service') {
          steps {
            sh '''cd formality-service
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build Emotions Service') {
          steps {
            sh '''cd emotions-service
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build Stats Service') {
          steps {
            sh '''cd stats-service
docker build -f Dockerfile-with-maven .'''
          }
        }

        stage('Build Email Service') {
          steps {
            sh '''cd email-service
docker build -f Dockerfile-with-maven .'''
          }
        }

      }
    }

    stage('error') {
      steps {
        echo 'Build Completed'
      }
    }

  }
  environment {
    DOCKER_BUILDKIT = '1'
  }
}