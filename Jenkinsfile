import groovy.json.*
pipeline{
    agent{
        node{
            label "${node_name}"
        }
    }
    tools{
        //在各个slave中已将各个maven 统一为这个名字，因此可以在各个node中通用
        maven 'mvn3.6'
    }
    options{
        timestamps()
    }


    stages{
        stage('params acquisition'){
            steps{
                script{
                    //获取当前时间作为pipeline创建时间
                    create_time = new Date()
                    create_timestamp = create_time.getTime()
                }
            }
        }
        //TODO:调试完成后将写死的数值恢复成自动获取@lingquan
        stage('clone') {
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'c5579d06-b37e-4410-948c-0588ff764679', url: 'https://github.com/likaimin1985/jacoco_demo.git']]])
                script{
                     sh label: '', script: '''cd $JOB_NAME
                     sh label: '', script: 'mvn clean compile'                }
            }


        }

        stage('DumpFromServer'){
            steps {
                script{
                    echo 'This is a DumpFromServer step'
                    sh label: '', script: 'mvn org.jacoco:jacoco-maven-plugin:0.8.5:dump -Djacoco.address=192.1.1.101 -Djacoco.port=6300'
                }
            }
        }

        stage('jacoco'){
            steps{
                jacoco()
            }
        }
    }
    //TODO:清除工作空间
        post{
            always{
                script {
                     //清除工作空间
                     cleanWs(
                         cleanWhenAborted: true,
                         cleanWhenFailure: true,
                         cleanWhenNotBuilt: true,
                         cleanWhenSuccess: true,
                         cleanWhenUnstable: true,
                         cleanupMatrixParent: true,
                         disableDeferredWipeout: true,
                         deleteDirs: true
                     )

                }
            }
        }

}