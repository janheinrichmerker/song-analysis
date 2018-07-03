package de.unihalle.informatik.bigdata.millionsongdataset.analysis.hadoop

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop.configuration
import org.apache.hadoop.conf.Configurable
import org.apache.hadoop.conf.Configured
import org.apache.hadoop.util.Tool

class SelectableTool(
        private val selectTool: (toolName: String) -> Tool
) : Tool, Configurable by Configured() {

    override fun run(arguments: Array<String>?): Int {
        if (arguments == null) {
            throw IllegalArgumentException("Missing program arguments.")
        }
        if (arguments.isEmpty() || arguments[0].isEmpty()) {
            throw IllegalArgumentException("Missing analysis tool name.")
        }
        val tool = selectTool(arguments[0])
        tool.configuration = configuration
        return tool.run(arguments.drop(1).toTypedArray())
    }
}