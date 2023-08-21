def call(Map vars) {
    sh('(curl -g -u $JIRA_CREDENTIALS $JIRA_URL/rest/api/2/search?jql=cf[10071]~$CommitId -o output.json)')
    //sh('(curl -u $JIRA_CREDENTIALS $JIRA_URL/rest/api/2/search?jql=cf[10071]~${vars.COMMIT_ID} -o output.json)')
    response= sh(script: 'echo $(cat output.json)', returnStdout: true).trim()
    def jsonObj = readJSON text: "$response"
    ISSUE_KEY = "${jsonObj.issues[0].key}"
    echo "$ISSUE_KEY"

}