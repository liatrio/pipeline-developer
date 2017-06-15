job("local-pipeline-manager") {
    customWorkspace("/pipeline-dev")
    steps {
        dsl {
            text(readFileFromWorkspace('devJobDefinition.groovy'))
            removeAction('DELETE')
        }
        shell('cp -r ../pipeline-dev/* /var/jenkins_home/jobs/example/workspace/')
    }
    publishers {
        downstream('devPipeline', 'STABLE')
    }
}
