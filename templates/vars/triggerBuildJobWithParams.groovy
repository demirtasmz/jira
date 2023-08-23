def call(Map vars) {
    build job: "${vars.BUILD_JOB}", parameters: [string(name: 'CommitId', value: "${commitid}")], wait: false
    //build job: "frontend-containers/aura-ui-components",  wait: false
}