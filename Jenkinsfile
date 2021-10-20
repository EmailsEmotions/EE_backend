pipeline {
  agent any
  stages {
    stage('Checkout GitHub') {
      steps {
        checkout scm
      }
    }

    stage('Build Config Server (and init cache)') {
      steps {
        withMaven() {
          sh 'cd config-server && mvn -B package -DskipTests'
        }

      }
    }

    stage('Build everything') {
      parallel {
        stage('Build API Gateway') {
          steps {
            withMaven() {
              sh 'cd api-gateway && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Discovery Server') {
          steps {
            withMaven() {
              sh 'cd discovery-server && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Admin Server') {
          steps {
            withMaven() {
              sh 'cd admin-server && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Email Service') {
          steps {
            withMaven() {
              sh 'cd email-service && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Emotions Service') {
          steps {
            withMaven() {
              sh 'cd emotions-service && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Stats Service') {
          steps {
            withMaven() {
              sh 'cd stats-service && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Users Service') {
          steps {
            withMaven() {
              sh 'cd users-service && mvn -B package -DskipTests'
            }

          }
        }

        stage('Build Formality Service') {
          steps {
            withMaven() {
              sh 'cd formality-service && mvn -B package -DskipTests'
            }

          }
        }

      }
    }

    stage('Test All') {
      parallel {
        stage('Test Config Server') {
          steps {
            withMaven() {
              sh 'cd config-server && mvn test'
            }

          }
        }

        stage('Test API Gateway') {
          steps {
            withMaven() {
              sh 'cd api-gateway && mvn test'
            }

          }
        }

        stage('Test Email Service') {
          steps {
            withMaven() {
              sh 'cd email-service && mvn test'
            }

          }
        }

        stage('Test Emotions Service') {
          steps {
            withMaven() {
              sh 'cd emotions-service && mvn test'
            }

          }
        }

        stage('Test Stats Service') {
          steps {
            withMaven() {
              sh 'cd stats-service && mvn test'
            }

          }
        }

        stage('Test Users Service') {
          steps {
            withMaven() {
              sh 'cd users-service && mvn test'
            }

          }
        }

        stage('Test Formality Service') {
          steps {
            withMaven() {
              sh 'cd formality-service && mvn test'
            }

          }
        }

      }
    }

    stage('Static Code Analysis') {
      parallel {
        stage('Config Server') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd config-server && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('API Gateway') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd api-gateway && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Admin Server') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd admin-server && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Discovery Server') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd discovery-server && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Email Service') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd email-service && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Emotions Service') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd emotions-service && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Formality Service') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd formality-service && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Stats Service') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd stats-service && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

          }
        }

        stage('Users Service') {
          steps {
            withSonarQubeEnv(installationName: 'SonarQube', credentialsId: 'jenkins') {
              sh 'cd users-service && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
            }

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

    stage('Create ready to go images') {
      parallel {
        stage('Config Server') {
          steps {
            sh 'cd config-server && docker build -t config-server -f Dockerfile .'
          }
        }

        stage('API Gateway') {
          steps {
            sh 'cd api-gateway && docker build -t api-gateway -f Dockerfile .'
          }
        }

        stage('Discovery Server') {
          steps {
            sh 'cd discovery-server && docker build -t discovery-server -f Dockerfile .'
          }
        }

        stage('Admin Server') {
          steps {
            sh 'cd admin-server && docker build -t admin-server -f Dockerfile .'
          }
        }

        stage('Email Service') {
          steps {
            sh 'cd email-service && docker build -t email-service -f Dockerfile .'
          }
        }

        stage('Emotions Service') {
          steps {
            sh 'cd emotions-service && docker build -t emotions-service -f Dockerfile .'
          }
        }

        stage('Stats Service') {
          steps {
            sh 'cd stats-service && docker build -t stats-service -f Dockerfile .'
          }
        }

        stage('Users Service') {
          steps {
            sh 'cd users-service && docker build -t users-service -f Dockerfile .'
          }
        }

      }
    }

    stage('Pushing images') {
      parallel {
        stage('Config Server') {
          steps {
            sh '''cd config-server
docker tag config-server $registryUri/config-server:${BUILD_ID}
docker push $registryUri/config-server:${BUILD_ID}
'''
          }
        }

        stage('API Gateway') {
          steps {
            sh '''cd api-gateway
docker tag api-gateway $registryUri/api-gateway:${BUILD_ID}
docker push $registryUri/api-gateway:${BUILD_ID}
'''
          }
        }

        stage('Discovery Server') {
          steps {
            sh '''cd discovery-server
docker tag discovery-server $registryUri/discovery-server:${BUILD_ID}
docker push $registryUri/discovery-server:${BUILD_ID}
'''
          }
        }

        stage('Admin Server') {
          steps {
            sh '''cd admin-server
docker tag admin-server $registryUri/admin-server:${BUILD_ID}
docker push $registryUri/admin-server:${BUILD_ID}
'''
          }
        }

        stage('Email Service') {
          steps {
            sh '''cd email-service
docker tag email-service $registryUri/email-service:${BUILD_ID}
docker push $registryUri/email-service:${BUILD_ID}
'''
          }
        }

        stage('Emotions Service') {
          steps {
            sh '''cd emotions-service
docker tag emotions-service $registryUri/emotions-service:${BUILD_ID}
docker push $registryUri/emotions-service:${BUILD_ID}
'''
          }
        }

        stage('Stats Service') {
          steps {
            sh '''cd stats-service
docker tag stats-service $registryUri/stats-service:${BUILD_ID}
docker push $registryUri/stats-service:${BUILD_ID}
'''
          }
        }

        stage('Users Service') {
          steps {
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
  tools {
    maven '3.8.3'
  }
  environment {
    registryUri = '172.18.0.20:5000'
  }
  options {
    skipDefaultCheckout()
  }
}