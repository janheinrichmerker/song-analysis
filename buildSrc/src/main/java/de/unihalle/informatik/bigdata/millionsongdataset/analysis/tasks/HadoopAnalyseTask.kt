package de.unihalle.informatik.bigdata.millionsongdataset.analysis.tasks

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.paths
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.process.ExecResult
import java.io.File

open class HadoopAnalyseTask : HadoopTask() {

    init {
        description = "Runs a Hadoop analysis."
    }

    @Input
    lateinit var tool: String

    private val paths = project.paths

    @InputFile
    var jarArchivePath: File = paths.jarArchive
    @InputDirectory
    lateinit var inputPath: File
    @OutputDirectory
    lateinit var outputPath: File

    init {
        outputs.upToDateWhen { false }
    }

    override val command: String = "jar"
    override val arguments: List<String>
        get() = listOf(
                jarArchivePath.path,
                "-Dmapreduce.map.java.opts=-Dhdf.hdf5lib.H5.hdf5lib=${paths.hdfLibrary}",
                "-Dmapreduce.reduce.java.opts=-Dhdf.hdf5lib.H5.hdf5lib=${paths.hdfLibrary}",
                "-Dmapreduce.map.java.opts=-Xmx2048m",
                "-Dmapreduce.reduce.java.opts=-Xmx2048m",
                "-Dhdf.hdf5lib.H5.hdf5lib=${paths.hdfLibrary}",
                "-Dhadoop.root.logger=WARN,DRFA",
                tool,
                inputPath.path,
                outputPath.path
        )

    private fun cleanOutputDir() {
        println("Cleaning output path: ${outputPath.path}")
        outputPath.deleteRecursively()
        outputPath.delete()
        if (outputPath.exists()) {
            println("Output path could not be cleaned.")
        }
    }

    override fun run(): ExecResult {
        cleanOutputDir()
        return super.run()
    }
}