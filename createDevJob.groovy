def shellCommand = '''
if [ ! -d "$DIRECTORY" ]; then
    mkdir /var/jenkins_home/jobs/example/workspace/
fi
cp -r ../pipeline-dev/* /var/jenkins_home/jobs/example/workspace/
'''


job("local-pipeline-manager") {
    customWorkspace("/pipeline-dev")
    steps {
        dsl {
            text(readFileFromWorkspace('devJobDefinition.groovy'))
            removeAction('DELETE')
        }
        shell(shellCommand)
    }
    publishers {
        downstream('devPipeline', 'STABLE')
    }
}
