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
        sh 'cd config-server && mvn -B -DskipTests clean package'
      }
    }

  }
}