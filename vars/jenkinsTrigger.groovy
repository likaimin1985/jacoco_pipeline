def call(){
    workspace= "${env.JENKINS_HOME}/pipeline_script_ec"
    //ws()人为规定工作空间，用于存放pipeline源码
    ws("$workspace"){
        //拉取pipeline源码
        git credentialsId: 'c5579d06-b37e-4410-948c-0588ff764679', url: 'https://github.com/likaimin1985/jacoco_pipeline.git'
        def node_name = env.NODE_NAME
        load "Jenkinsfile"

    }

}