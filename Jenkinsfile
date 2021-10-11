pipeline {
  agent any
  stages {
    stage('Build everything') {
      parallel {
        stage('Build Config Server') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd config-server && mvn -B package -DskipTests'
          }
        }

        stage('Build API Gateway') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd api-gateway && mvn -B package -DskipTests'
          }
        }

        stage('Build Discovery Server') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd discovery-server && mvn -B package -DskipTests'
          }
        }

        stage('Build Admin Server') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd admin-server && mvn -B package -DskipTests'
          }
        }

        stage('Build Email Service') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd email-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Emotions Service') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd emotions-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Formality Service') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd formality-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Logstash Service') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd logstash-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Stats Service') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
          steps {
            sh 'cd stats-service && mvn -B package -DskipTests'
          }
        }

        stage('Build Users Service') {
          agent {
            docker {
              image 'maven:3.8-openjdk-11'
              args '-v /root/.m2:/root/.m2'
            }

          }
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
        stage('ConfigServer') {
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

        stage('Build API Gateway') {
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

        stage('Build Discovery Server') {
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

        stage('Build Admin Server') {
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

        stage('Build Email Service') {
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

        stage('Build Emotions Service') {
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

        stage('Build Formality Service') {
          agent any
          steps {
            sh '''cd formality-service 
docker build -t formality-service -f Dockerfile-with-maven .'''
            sh '''cd formality-service
docker tag formality-service $registryUri/formality-service:${BUILD_ID}
docker push $registryUri/formality-service:${BUILD_ID}
'''
          }
        }

        stage('Build Logstash Service') {
          agent any
          steps {
            sh '''cd logstash-service 
docker build -t logstash-service -f Dockerfile-with-maven .'''
            sh '''cd logstash-service
docker tag logstash-service $registryUri/logstash-service:${BUILD_ID}
docker push $registryUri/logstash-service:${BUILD_ID}
'''
          }
        }

        stage('Build Stats Service') {
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

        stage('Build Users Service') {
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

    stage('End') {
      steps {
        echo 'Done publishing'
      }
    }

  }
  environment {
    registryUri = '172.18.0.20:5000'
    DOCKER_BUILDKIT = '1'
  }
}