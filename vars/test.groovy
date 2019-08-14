def cal(){
  pipeline{
    agent any

    stages{

      stage("test"){
        steps{
          sh "mvn test"
        }
      }

      stage("build"){
        steps{
          sh "mvn package"
        }
      }
    }
    post{
      always{
        echo "im done"
      }
      success{
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }
  }
}
