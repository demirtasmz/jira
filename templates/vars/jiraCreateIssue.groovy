def call(Map config=[:]) {
  def rawBody = libraryResource 'jira/createIssue.json'
  def binding = [
    key: "${config.key}",
    parent: "${config.parent}",
    label1: "${config.label1}",
    label2: "${config.label2}",
    label3: "${config.label3}",
    summary: "${config.summary}",
    description: "${config.description}",
    serviceName: "${config.serviceName}",
    imageTag: "${config.imageTag}",
    issueTypeName: "${config.issueTypeName}"
  ]
  def render = renderTemplate(rawBody,binding) 
  sh('(curl -D- -u $JIRA_CREDENTIALS -X POST --data "'+render+'" -H "Content-Type: application/json" $JIRA_URL/rest/api/2/issue -o output.json)')
  response= sh(script: 'echo $(cat output.json)', returnStdout: true).trim()
  def jsonObj = readJSON text: "$response"
  //sh"""
  //curl --location --request POST "$JIRA_URL/rest/api/3/issue/${jsonObj.key}/attachments" -u $JIRA_CREDENTIALS  -H "X-Atlassian-Token: no-check" --form "file=@"description.txt""
  //"""
}
