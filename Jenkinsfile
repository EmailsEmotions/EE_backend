pipeline {
  agent any
  stages {
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
    registryUri = 'http://172.18.0.20:5000'
    dockerInstance = ''
  }
}