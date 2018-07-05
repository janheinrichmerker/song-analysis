package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import org.apache.hadoop.conf.Configurable
import org.apache.hadoop.conf.Configuration

var Configurable.configuration: Configuration
    get() = conf
    set(value) {
        conf = value
    }