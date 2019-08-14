def call(){
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
      
      stage("run"){
        steps{
          sh "mvn spring-boot:run"
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
