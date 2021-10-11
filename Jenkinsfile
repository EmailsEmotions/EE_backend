pipeline {
  agent {
    docker {
      image 'maven:3.8.1-adoptopenjdk-11'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build everything') {
      parallel {
        stage('Build Config Server') {
          steps {
            sh 'cd config-server && mvn -B package -DskipTests'
          }
        }

        stage('Build API Gateway') {
          steps {
            sh 'cd api-gateway && mvn -B package -DskipTests'
          }
        }

        stage('Build Discovery Server') {
          steps {
            sh 'cd discovery-server && mvn -B package -DskipTests'
          }
        }

        stage('Build Admin Server') {
          steps {
            sh 'cd admin-server && mvn -B package -DskipTests'
          }
        }

        stage('Build Email Service') {
          steps {
            sh 'cd email-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Emotions Service') {
          steps {
            sh 'cd emotions-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Stats Service') {
          steps {
            sh 'cd stats-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Users Service') {
          steps {
            sh 'cd users-service && mvn -B package -DskipTests'
          }
        }

      }
    }

    stage('Confirm publish to registry') {
      steps {
        echo 'Registry IP: $registryUri'
        echo 'Publishing images'
      }
    }

    stage('Publish to registry') {
      parallel {
        stage('Config Server') {
          agent any
          steps {
            sh '''cd config-server 
docker build -t config-server -f Dockerfile-with-maven .'''
            sh '''cd config-server
docker tag config-server $registryUri/config-server:${BUILD_ID}
docker push $registryUri/config-server:${BUILD_ID}
'''
          }
        }

        stage('API Gateway') {
          agent any
          steps {
            sh '''cd api-gateway 
docker build -t api-gateway -f Dockerfile-with-maven .'''
            sh '''cd api-gateway
docker tag api-gateway $registryUri/api-gateway:${BUILD_ID}
docker push $registryUri/api-gateway:${BUILD_ID}
'''
          }
        }

        stage('Discovery Server') {
          agent any
          steps {
            sh '''cd discovery-server 
docker build -t discovery-server -f Dockerfile-with-maven .'''
            sh '''cd discovery-server
docker tag discovery-server $registryUri/discovery-server:${BUILD_ID}
docker push $registryUri/discovery-server:${BUILD_ID}
'''
          }
        }

        stage('Admin Server') {
          agent any
          steps {
            sh '''cd admin-server 
docker build -t admin-server -f Dockerfile-with-maven .'''
            sh '''cd admin-server
docker tag admin-server $registryUri/admin-server:${BUILD_ID}
docker push $registryUri/admin-server:${BUILD_ID}
'''
          }
        }

        stage('Email Service') {
          agent any
          steps {
            sh '''cd email-service 
docker build -t email-service -f Dockerfile-with-maven .'''
            sh '''cd email-service
docker tag email-service $registryUri/email-service:${BUILD_ID}
docker push $registryUri/email-service:${BUILD_ID}
'''
          }
        }

        stage('Emotions Service') {
          agent any
          steps {
            sh '''cd emotions-service 
docker build -t emotions-service -f Dockerfile-with-maven .'''
            sh '''cd emotions-service
docker tag emotions-service $registryUri/emotions-service:${BUILD_ID}
docker push $registryUri/emotions-service:${BUILD_ID}
'''
          }
        }

        stage('Stats Service') {
          agent any
          steps {
            sh '''cd stats-service 
docker build -t stats-service -f Dockerfile-with-maven .'''
            sh '''cd stats-service
docker tag stats-service $registryUri/stats-service:${BUILD_ID}
docker push $registryUri/stats-service:${BUILD_ID}
'''
          }
        }

        stage('Users Service') {
          agent any
          steps {
            sh '''cd users-service 
docker build -t users-service -f Dockerfile-with-maven .'''
            sh '''cd users-service
docker tag users-service $registryUri/users-service:${BUILD_ID}
docker push $registryUri/users-service:${BUILD_ID}
'''
          }
        }

      }
    }

    stage('Finish') {
      steps {
        echo 'Finished publishing'
      }
    }

  }
  environment {
    registryUri = '172.18.0.20:5000'
    DOCKER_BUILDKIT = '1'
  }
}