def install(){
  stage("mvn install"){
    sh "mvn install"
  }
}
