def shellCommand = '''
if [ ! -d /var/jenkins_home/jobs/devPipeline/ ]; then
    mkdir /var/jenkins_home/jobs/devPipeline/
    mkdir /var/jenkins_home/jobs/devPipeline/workspace/
fi
rm -r /var/jenkins_home/jobs/devPipeline/workspace/*
cp -r ../pipeline-dev/* /var/jenkins_home/jobs/devPipeline/workspace/
'''


job("pipeline-updater") {
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
