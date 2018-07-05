import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.*
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.tasks.HadoopAnalyseTask
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.kotlin
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.versions
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.paths
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.tasks.HadoopCopyToFileSystemTask

plugins {
    kotlin("jvm") version "1.2.50"
    application
    java
    id("com.github.johnrengelman.shadow") version "2.0.1"
    id("org.junit.platform.gradle.plugin") version "1.0.0"
    // shadow version "2.0.1"
    // jUnit version "1.0.0"
}

val versions = project.versions
val paths = project.paths

repositories {
    mavenCentral()
    maven("http://dl.bintray.com/jetbrains/spek")
}

dependencies {
    compile(fileTree(paths.hdfJavaLibrary))
    compile(fileTree(paths.hdfViewLibrary))
    compile(kotlin("stdlib-jdk8", versions))
    compile(hadoop("common", versions))
    compile(hadoop("mapreduce-client-core", versions))
    compile(group = "junit", name = "junit", version = versions.jUnit)
    testCompile(kotlin("reflect", versions))
    testCompile(group = "org.jetbrains.spek", name = "spek-api", version = versions.spek) {
        exclude("org.jetbrains.kotlin")
    }
    testRuntime(group = "org.jetbrains.spek", name = "spek-junit-platform-engine", version = versions.spek) {
        exclude("org.jetbrains.kotlin")
    }
}

configurations.compile.apply {
    exclude(group = "org.slf4j")
    exclude(group = "log4j")
}

group = "de.unihalle.informatik.bigdata.millionsongdataset.analysis"
version = "0.1.0"

application {
    mainClassName = "${project.group}.Analyse"
}

//test {
//    // Pass the HDF5 native library path to all Java tasks
//    systemProperty("hdf.hdf5lib.H5.hdf5lib", hdfLibraryPath)
//}

tasks {
    val shadowJar by getting(ShadowJar::class) {
        manifest {
            attributes["Main-Class"] = application.mainClassName
        }
    }

    val copyHdfLibraryToHadoopFileSystem by creating(HadoopCopyToFileSystemTask::class) {
        localPath = paths.hdfLibrary
        remotePath = paths.hdfLibrary
    }

    val analyseGenreWordCount by creating(HadoopAnalyseTask::class) {
        tool = "analyse-genre-word-count"
        inputPath = File(project.rootDir, "data/songs/B")
        outputPath = File(project.rootDir, "out/analytics")
    }
    analyseGenreWordCount.dependsOn(shadowJar)

    val analyseArtistHotttness by creating(HadoopAnalyseTask::class) {
        tool = "analyse-artist-hotttness"
        inputPath = File(project.rootDir, "data/songs/B")
        outputPath = File(project.rootDir, "out/analytics")
    }
    analyseArtistHotttness.dependsOn(shadowJar)

    val analyseArtistDanceability by creating(HadoopAnalyseTask::class) {
        tool = "analyse-artist-danceability"
        inputPath = File(project.rootDir, "data/songs/B")
        outputPath = File(project.rootDir, "out/analytics")
    }
    analyseArtistDanceability.dependsOn(shadowJar)

    val analyseYearDominantGenre by creating(HadoopAnalyseTask::class) {
        tool = "analyse-year-dominant-genre"
        inputPath = File(project.rootDir, "data/songs/B")
        outputPath = File(project.rootDir, "out/analytics")
    }
    analyseYearDominantGenre.dependsOn(shadowJar)

    val analyseYearGenres by creating(HadoopAnalyseTask::class) {
        tool = "analyse-year-genres"
        inputPath = File(project.rootDir, "data/songs/B")
        outputPath = File(project.rootDir, "out/analytics")
    }
    analyseYearGenres.dependsOn(shadowJar)

    val drawSongHotttnessArtistLocationHeatmap by creating(JavaExec::class) {
        group = "Visualize"
        description = "Draws a heatmap of values based on the artist's location."

        val input = File(project.rootDir, "results/average-song-hotttness-by-artist-B.tsv")
        val output = File(project.rootDir, "out/images/$name.png")

        classpath = java.sourceSets["main"].runtimeClasspath
        main = "${project.group}.DrawArtistValuesLocationHeatmap"
        args = listOf(input.path, output.path)

        outputs.file(output)
    }

}
