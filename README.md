# pipeline-developer

This is a jenkins server for developing pipelines locally without requiring git commits. 

## Usage

#### To use included jenkins container
1. Change the first half of line 7 from `../spring-petclinic` to the relative or absolute path of the pipeline directory to be worked on.
2. Run `docker-compose up`
3. Run the pipeline-updater job at `http://localhost:18080/job/pipeline-updater/`
4. Your pipeline should have run at `http://localhost:18080/job/devPipeline/`

#### To use a different jenkins setup
1. Create a freestyle job.
2. Have it pull down this repo at `https://github.com/liatrio/pipeline-developer`
3. Add a "Process Job DSL Script" step and have it look on the filesystem for `createDevJob.groovy`
4. Add a volume to the jenkins container in the docker-compose that references your project so that jenkins can copy those files in. 
