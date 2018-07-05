package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import org.gradle.api.Project
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.get
import java.io.File

class Paths internal constructor(project: Project) {

    private val versions = Versions(project)

    private val libraries: File = File(project.rootDir, "lib")
    val hadoopLibrary: File = System.getenv("HADOOP_HOME")?.let { File(it) }
            ?: File(libraries, "hadoop/hadoop-${versions.hadoop}/bin")
    val hdfViewLibrary: File = File(libraries, "hdf/hdf-view/lib")
    val hdfJavaLibrary: File = File(libraries, "hdf/hdf-java/lib/libjhdf5.so")
    val hdfLibrary: File = hdfJavaLibrary

    val jarArchive: File = project
            .tasks["shadowJar"]
            .let { it as Jar }
            .archivePath
}