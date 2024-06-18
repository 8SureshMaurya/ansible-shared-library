// Jenkinsfile
@Library('ansible-shared-library') _

pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                // Clone repository or any initial setup
                echo 'Cloning repository...'
            }
        }

        stage('User Approval') {
            steps {
                script {
                    // Call deployKafka function from shared library vars/
                    deployKafka([
                        SLACK_CHANNEL_NAME: "${params.SLACK_CHANNEL_NAME}",
                        ENVIRONMENT: "${params.ENVIRONMENT}",
                        CODE_BASE_PATH: "${params.CODE_BASE_PATH}",
                        ACTION_MESSAGE: "${params.ACTION_MESSAGE}",
                        KEEP_APPROVAL_STAGE: "${params.KEEP_APPROVAL_STAGE}"
                    ])
                }
            }
        }

        stage('Playbook Execution') {
            steps {
                // Playbook execution steps (if not already handled in deployKafka function)
                echo 'Executing Ansible playbook...'
            }
        }

        stage('Notification') {
            steps {
                // Notification steps (if not already handled in deployKafka function)
                echo 'Sending deployment notification...'
]            }
        }
    }

    // Optionally, define parameters for input from Jenkins job configuration
    parameters {
        string(name: 'SLACK_CHANNEL_NAME', defaultValue: 'build-status', description: 'Slack channel name for notifications')
        string(name: 'ENVIRONMENT', defaultValue: 'prod', description: 'Environment to deploy Kafka (e.g., dev, test, prod)')
        // Add more parameters as needed
    }
}
