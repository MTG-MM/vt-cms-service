pipeline {
    agent any
    environment {
        BRANCH_NAME = "${GIT_BRANCH.split("/")[1]}"
        DOCKER_HUB_URL="lambro2510"
        RESPOSITORY="lambro2510"
        DOCKER_HUB_TOKEN=credentials("docker_hub_token")
        NAME="mos-cms-service"
        PORT="8900"
    }
    stages {
        stage('Build') {
            steps {
                script {
                  sh "sudo docker login | echo ${RESPOSITORY} | echo ${DOCKER_HUB_TOKEN}"
                    sh "sudo docker build -t lambro2510/${NAME}:${BUILD_NUMBER} ."
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                  if(BRANCH_NAME == 'master'){
                     sh "sudo docker run --name ${NAME}:${BUILD_NUMBER} -d -p ${PORT}:80 ${RESPOSITORY}/${NAME}:${BUILD_NUMBER}"
                  }

                  try{
                    sh "sudo docker stop ${NAME}"
                  } catch(Exception e) {
                     echo "No running container found with the name ${NAME}."
                  }

                  sh "docker rename ${NAME}:${BUILD_NUMBER} ${NAME}"
                  try{
                    sh 'sudo docker container prune -f'
                    sh 'sudo docker image prune -f'
                  } catch(Exception e) {
                     echo "remove trash image, container"
                  }
               }
            }
        }

    }
}
