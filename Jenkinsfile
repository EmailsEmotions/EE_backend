pipeline {
  agent any
  stages {
    stage('Publish') {
      agent any
      steps {
        sh 'echo "Registry URL: " $registryUri'
        echo 'Publishing image'
        sh '''cd config-server
docker build -t config-server -f Dockerfile-with-maven .
'''
        sh '''cd config-server
 docker tag config-server $registryUri/config-server:${BUILD_ID}
 docker push $registryUri/config-server:${BUILD_ID}
'''
        echo 'Published to registry'
      }
    }

  }
  environment {
    registryUri = '172.18.0.20:5000'
    DOCKER_BUILDKIT = '1'
  }
}