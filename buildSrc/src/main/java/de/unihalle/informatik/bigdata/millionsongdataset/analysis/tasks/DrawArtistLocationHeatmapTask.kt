package de.unihalle.informatik.bigdata.millionsongdataset.analysis.tasks

import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.OutputFile
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getPluginByName
import java.io.File

open class DrawArtistLocationHeatmapTask : JavaExec() {

    init {
        group = "Visualize"
        description = "Draws a heatmap of values based on the artist's location."

        classpath = project
                .convention
                .getPluginByName<JavaPluginConvention>("java")
                .sourceSets["main"]
                .runtimeClasspath
    }

    @InputFile
    lateinit var input: File
    var output: File? = null
    @get:OutputFile
    val outputOrDefault: File
        get() = output ?: File(project.rootDir, "out/images/${input.nameWithoutExtension}.png")

    override fun exec() {
        args = listOf(input.path, outputOrDefault.path)
        main = "${project.group}.DrawArtistValuesLocationHeatmap"
        super.exec()
    }
}