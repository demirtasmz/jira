def call(Map vars) {
    git branch: vars.PROJECT_BRANCH, credentialsId: 'demirtasmz', url: vars.PROJECT_GITURL
    //GIT_COMMIT_TAG = sh(returnStdout: true, script: "git rev-parse --short=8 HEAD").trim()
}