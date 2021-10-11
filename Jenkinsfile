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
        sh 'cd config-server && mvn -B package -DskipTests'
      }
    }

    stage('Test') {
      steps {
        sh 'cd config-server'
      }
    }

    stage('Static Code Analysis') {
      steps {
        withSonarQubeEnv('SonarQube') {
          sh 'cd config-server && mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        }

      }
    }

    stage('Publish') {
      agent any
      steps {
        echo 'Publishing image'
        script {
          def dockerfile = 'Dockerfile-with-maven'
          configServer = docker.build('config-server', "-f ${dockerfile} ./config-server")
        }

        script {
          docker.withRegistry(registryUri) {
            configServer.push("${env.BUILD_NUMBER}")
            configServer.push("latest")
          }
        }

      }
    }

  }
  environment {
    registryCredentialSet = 'dockerhub-bjencz'
    registryUri = 'http://172.18.0.6:5000'
    dockerInstance = ''
  }
}