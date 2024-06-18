// vars/deployKafka.groovy
def call(Map config) {
    def slackChannel = config.SLACK_CHANNEL_NAME ?: 'build-status'
    def environment = config.ENVIRONMENT ?: 'prod'
    def codeBasePath = config.CODE_BASE_PATH ?: 'env/prod'
    def actionMessage = config.ACTION_MESSAGE ?: 'My notification'
    def keepApprovalStage = config.KEEP_APPROVAL_STAGE ?: true

    echo "Deploying Kafka to environment: ${environment}"

    // Implement your Ansible playbook execution here
    ansiblePlaybook(
        playbook: "${env.WORKSPACE}/ansible/playbook.yml",
        inventory: "${env.WORKSPACE}/ansible/inventory",
        extras: "-e kafka_env=${environment}"
    )

    // Send notification to Slack or other channels
    slackSend(channel: slackChannel, color: 'good', message: actionMessage)

    // Optionally, handle user approval logic here if needed
    if (keepApprovalStage) {
        input message: 'Approve deployment?', ok: 'Proceed'
    }
}
