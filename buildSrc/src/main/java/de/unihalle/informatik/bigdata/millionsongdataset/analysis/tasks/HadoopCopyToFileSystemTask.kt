package de.unihalle.informatik.bigdata.millionsongdataset.analysis.tasks

import org.gradle.api.tasks.InputFiles
import java.io.File

open class HadoopCopyToFileSystemTask : HadoopTask() {

    init {
        description = "Copies a local file to the Hadoop file system."
    }

    @InputFiles
    lateinit var localPath: File
    @InputFiles
    lateinit var remotePath: File

    override val command = "fs"
    override val arguments: List<String>
        get() = listOf(
                "-copyFromLocal",
                localPath.path,
                remotePath.path
        )
}