def call(Map config=[:]) {
  def rawBody = libraryResource 'jira/transitionIssue.json'
  def binding = [
    transitionId: "${config.transitionId}"    
  ]
  // get issue key with custom field commitid
  sh('(curl -g -u $JIRA_CREDENTIALS $JIRA_URL/rest/api/2/search?jql=cf[10071]~$commitid -o output.json)')
  response= sh(script: 'echo $(cat output.json)', returnStdout: true).trim()
  def jsonObj = readJSON text: "$response"
  def render = renderTemplate(rawBody,binding) 
  sh"""
  curl --location --request POST "$JIRA_URL/rest/api/latest/issue/${jsonObj.issues[0].key}/transitions" -u $JIRA_CREDENTIALS  -H "Content-Type: application/json" --data "${render}"
  """
}