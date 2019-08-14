def call(){
  pipeline{
    agent any

    stages{
/*
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
*/      
      stage("run"){
        steps{
          parallel(
            a: {
              sh "mvn spring-boot:run"
            },
            b: {
              sh "sleep 10"
              sh "curl localhost:9000"
            }
          )
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
