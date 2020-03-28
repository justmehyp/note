package com.hyp

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SayHelloTask extends DefaultTask {
    String username

    {
        group = "SayHello"
        description = "A SayHello Task."
    }

    @TaskAction
    void doAction() {
        println "hello $username"
    }
}
