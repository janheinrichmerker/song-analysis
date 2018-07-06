package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec


val PluginDependenciesSpec.shadow: PluginDependencySpec
    get() = id("com.github.johnrengelman.shadow")

val PluginDependenciesSpec.jUnit: PluginDependencySpec
    get() = id("org.junit.platform.gradle.plugin")

val PluginDependenciesSpec.junit: PluginDependencySpec
    get() = jUnit