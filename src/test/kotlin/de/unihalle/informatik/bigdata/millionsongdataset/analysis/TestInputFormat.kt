package de.unihalle.informatik.bigdata.millionsongdataset.analysis

import de.unihalle.informatik.bigdata.millionsongdataset.analysis.mapreduce.reader.hdf5.Hdf5SongFileInputFormat
import de.unihalle.informatik.bigdata.millionsongdataset.analysis.model.Song
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.BlockLocation
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapreduce.InputFormat
import org.apache.hadoop.mapreduce.TaskAttemptID
import org.apache.hadoop.mapreduce.lib.input.FileSplit
import org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl
import org.apache.hadoop.util.ReflectionUtils
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

object TestInputFormat {

    @JvmStatic
    fun main(arguments: Array<String>) {
        testInputFormatCanBeInitialized()
    }

    fun getResource(name: String): File {
        val clazz = javaClass
        val path = "/${clazz.`package`.name.replace('.', '/')}/$name"
        println(path)
        val url = clazz.getResource(path)
        println(url)
        val file = File(url.toURI())
        println(file)
        return file
    }

    @Test
    fun testInputFormatCanBeInitialized() {
        val configuration = Configuration(false)
        configuration["fs.default.name"] = "file:///"

        val file = getResource("TRADUDB128F42A61F9.h5")
        val path = Path(file.path)

        val split = FileSplit(path, 0, file.length(), null)
        println(split)


        val fileSystem = path.getFileSystem(Configuration())
        val blockLocations: Array<BlockLocation> = fileSystem.getFileBlockLocations(path, 0, file.length())
        println(blockLocations.map { "Offset: ${it.offset}; Length: ${it.length}; Hosts: ${it.hosts.joinToString()}; Cached hosts: ${it.cachedHosts.joinToString()}" })

        val inputFormat: InputFormat<Text, Song> = ReflectionUtils.newInstance(Hdf5SongFileInputFormat::class.java, configuration)
        val context = TaskAttemptContextImpl(configuration, TaskAttemptID())
        val reader = inputFormat.createRecordReader(split, context)
        assertNotNull("No record reader was created.", reader)

        reader.initialize(split, context)

        val nextKeyValue = reader.nextKeyValue()
        assertTrue("Couldn't load first key-value pair.", nextKeyValue)

        val key = reader.currentKey
        assertNotNull("Key was empty.", key)

        val value = reader.currentValue
        assertNotNull("Value was empty.", value)

        println("$key: \n$value")
    }
}