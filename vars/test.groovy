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
          script{
            parallel(
              run: {
                sh "mvn spring-boot:run"
              },
              test: {
                sh "sleep 30"
                jsonText = sh(script:"curl -s -X GET http://localhost:9000", returnStdout:true)
                println jsonText
                json = readJSON text: jsonText
                println json
                if (json[0].firstName == "lokesh"){
                  currentBuild.result = 'SUCCESS'
                } else {
                  currentBuild.result = 'FAILURE'
                }
              }
            )
          }
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
