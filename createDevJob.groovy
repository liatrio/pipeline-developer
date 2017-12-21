def shellCommand = '''
if [ ! -d /var/jenkins_home/jobs/devPipeline/ ]; then
    mkdir /var/jenkins_home/jobs/devPipeline/
    mkdir /var/jenkins_home/jobs/devPipeline/workspace/
fi
if [ -e /var/jenkins_home/jobs/devPipeline/workspace/* ]; then
    rm -r /var/jenkins_home/jobs/devPipeline/workspace/*
fi
if [[ ! -z "$(ls -A)" ]] ; then
    cp -r * /var/jenkins_home/jobs/devPipeline/workspace/
fi
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
