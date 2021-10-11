pipeline {
  agent any
  stages {
    stage('Publish') {
      agent any
      steps {
        echo 'Publishing image'
        sh '''cd config-server
docker build -t config-server -f Dockerfile-with-maven .
docker tag config-server localhost:5000/config-server
docker push 172.18.0.20:5000/config-server
'''
      }
    }

  }
  environment {
    registryCredentialSet = 'dockerhub-bjencz'
    registryUri = 'http://172.18.0.20:5000'
    dockerInstance = ''
  }
}