package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.Paths
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.Versions
import org.gradle.api.Project

val Project.versions
    get() = Versions(this)
val Project.paths
    get() = Paths(this)