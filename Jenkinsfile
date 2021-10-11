pipeline {
  agent any
  stages {
    stage('Publish') {
      agent any
      steps {
        sh 'echo $registryUri'
        echo 'Publishing image'
        sh '''cd config-server
docker build -t config-server -f Dockerfile-with-maven .
'''
        sh '''cd config-server
docker tag config-server 172.18.0.20:5000/config-server:${BUILD_ID}
docker push 172.18.0.20:5000/config-server:${BUILD_ID}
'''
        echo 'Published to registry'
      }
    }

  }
  environment {
    registryCredentialSet = 'dockerhub-bjencz'
    registryUri = 'http://172.18.0.20:5000'
    dockerInstance = ''
    DOCKER_BUILDKIT = '1'
  }
}