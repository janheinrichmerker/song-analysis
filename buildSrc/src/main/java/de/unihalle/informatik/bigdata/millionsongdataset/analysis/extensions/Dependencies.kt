package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.Versions
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.kotlin


fun DependencyHandler.kotlin(module: String, versions: Versions): Any = kotlin(module, versions.kotlin)
fun DependencyHandler.hadoop(module: String, version: String? = null): Any = create(
        group = "org.apache.hadoop",
        name = "hadoop-${module.removePrefix("hadoop-")}",
        version = version
)

fun DependencyHandler.hadoop(module: String, versions: Versions): Any = hadoop(module, versions.hadoop)