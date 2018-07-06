package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class Versions internal constructor(private val project: Project) {
    val kotlin by plugin { "1.2.50" }
    val hadoop = "3.1.0"
    val shadow by plugin { "2.0.1" }
    val jUnit = "4.12"
    val jUnitPlatform by plugin("junit.platform") { "1.0.0" }
    val spek = "1.1.5"

    private class PluginVersion(private val module: String? = null, private val defaultVersion: () -> String) : ReadOnlyProperty<Versions, String> {
        override fun getValue(thisRef: Versions, property: KProperty<*>): String {
            val module = (this.module ?: property.name).toLowerCase()
            return thisRef.project
                    .buildscript
                    .configurations["classpath"]
                    .resolvedConfiguration
                    .firstLevelModuleDependencies
                    .find { module in it.moduleName.toLowerCase() }
                    ?.moduleVersion
                    ?: defaultVersion()
        }
    }

    private fun plugin(module: String? = null, defaultVersion: () -> String) = PluginVersion(module, defaultVersion)
}