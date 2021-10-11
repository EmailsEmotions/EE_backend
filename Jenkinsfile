pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build Config Server') {
          steps {
            echo 'Building Config Server'
            sh '''cd config-server
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build Discovery Server') {
          steps {
            sh '''cd discovery-server
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build API Gateway') {
          steps {
            sh '''cd api-gateway
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build Admin Server') {
          steps {
            sh '''cd admin-server
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build User Service') {
          steps {
            sh '''cd users-service
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build Emotions Service') {
          steps {
            sh '''cd emotions-service
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build Stats Service') {
          steps {
            sh '''cd stats-service
mvn clean install -Dlicense.skip=true'''
          }
        }

        stage('Build Email Service') {
          steps {
            sh '''cd email-service
mvn clean install -Dlicense.skip=true'''
          }
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