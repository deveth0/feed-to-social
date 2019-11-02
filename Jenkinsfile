@Library('pipeline-library') pipelineLibrary
@Library('pv-pipeline-library') pvPipelineLibrary
@Library('pipeline-library-config') pipelineLibraryConfig

/*
 * Copyright (c) 2019 dev-eth0.de All rights reserved.
 */

import static de.provision.devops.jenkins.pipeline.utils.ConfigConstants.*
import static io.wcm.devops.jenkins.pipeline.utils.ConfigConstants.*

node() {
  sh("env")
}

Map config = [
    (STAGE_COMPILE): [
        (MAVEN): [
            (MAVEN_GOALS): ["jib:build"]
        ]
    ],
    (PROPERTIES)   : [
        (PROPERTIES_PIPELINE_TRIGGERS): [
            pollSCM('H/5 * * * *')
        ]
    ]
]

routeDefaultJenkinsFile(config)
