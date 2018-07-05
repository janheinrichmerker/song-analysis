package de.unihalle.informatik.bigdata.millionsongdataset.analysis.tasks

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.paths
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecResult
import java.io.File

abstract class HadoopTask : DefaultTask() {

    init {
        group = "Hadoop"
        description = "Executes a Hadoop command."
    }

    abstract val command: String
    open val arguments: List<String> = mutableListOf()

    open val workingDirectory: File? = null
    open val environment: Map<String, String> = mutableMapOf()

    @Input
    open val hadoopPath = project.paths.hadoopLibrary

    private val hadoopExecutable: String
        get() = "$hadoopPath/hadoop"
    private val hadoopArguments: List<String>
        get() = listOf(command) + arguments

    @TaskAction
    open fun run(): ExecResult {
        return project.exec {
            if (workingDirectory != null) {
                workingDir = workingDirectory
            }
            executable = hadoopExecutable
            args = hadoopArguments
            println("Executing Hadoop: ${commandLine.joinToString(" ")}")
        }
    }
}