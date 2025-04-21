def getTimeStamp() {
    def date = new Date()
    return date.format('yyyyMMddHHmmss')
}

pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    environment {
        DOCKER_IMAGE_VERSION = '1.0.0'
        DOCKER_HUB_USERNAME = 'brahim2023'
        DOCKER_HUB_PASSWORD = 'Lifeisgoodbrahim@@'
        DOCKER_COMPOSE_FILE = 'docker-compose.yml'
        SONAR_HOST_URL = 'http://192.168.186.128:9001'
        SONAR_TOKEN = 'sqa_c3bb610452c05f15d1882a711b34657c8f2bfdd3'
    }

    stages {


           stage('SonarQube Analysis') {
            steps {
                script {
                    ["login-service"].each { project ->
                        echo "Processing project: ${project}"
                        def projectKey = "${project}-${getTimeStamp()}"
                        dir(project) {
                            sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=true'
                            sh "mvn sonar:sonar -Dsonar.token=${env.SONAR_TOKEN} -Dsonar.projectKey=${projectKey} -Dsonar.host.url=${env.SONAR_HOST_URL}"
                        }
                    }
                }
            }
        }

        stage('Build Maven Project') {
            steps {
                dir('product-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Upload Artifact to Nexus') {
            steps {
                script {
                    def version = "1.0-SNAPSHOT"
                    def projectName = "product-service"

                    dir('product-service') {
                        nexusArtifactUploader(
                            nexusVersion: 'nexus3',
                            protocol: 'http',
                            nexusUrl: '192.168.186.128:8081',
                            groupId: 'org.tokkom',
                            version: version,
                            repository: 'maven-snapshots',
                            credentialsId: 'nexus-credentials',
                            artifacts: [
                                [
                                    artifactId: projectName,
                                    classifier: '',
                                    file: "target/${projectName}-${version}.jar",
                                    type: 'jar'
                                ]
                            ]
                        )
                    }
                }
            }
        }
      stage('Build Docker Images') {
            steps {
                script {
                    def services = [
                        "discovery-service", "gateway-service", "product-service",
                        "formation-service", "order-service", "notification-service",
                        "login-service", "contact-service"
                    ]
                    services.each { serviceName ->
                        dir(serviceName) {
                            sh "docker build -t ${serviceName}:${DOCKER_IMAGE_VERSION} ."
                        }
                    }
                }
            }
        }

        stage('Deploy Microservices') {
            steps {
                script {
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} down"
                    sh "docker compose -f ${DOCKER_COMPOSE_FILE} up -d"
                }
            }
        }

        stage('Push Docker Images to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                        sh "docker login -u ${DOCKER_HUB_USERNAME} -p ${DOCKER_HUB_PASSWORD}"
                        def services = [
                            "discovery-service", "gateway-service", "product-service",
                            "formation-service", "order-service", "notification-service",
                            "login-service", "contact-service"
                        ]
                        services.each { serviceName ->
                            def localTag = "${serviceName}:${DOCKER_IMAGE_VERSION}"
                            def remoteTag = "${DOCKER_HUB_USERNAME}/${serviceName}:${DOCKER_IMAGE_VERSION}"
                            sh "docker tag ${localTag} ${remoteTag}"
                            sh "docker push ${remoteTag}"
                        }
                    }
                }
            }
        }

    }
}
