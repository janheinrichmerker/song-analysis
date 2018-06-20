package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions.hadoop

import org.apache.hadoop.classification.InterfaceAudience
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.RawComparator
import org.apache.hadoop.mapreduce.*
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat
import java.io.IOException
import kotlin.reflect.KClass

@Throws(IOException::class)
fun jobOf(init: Job.() -> Unit = {}): Job {
    return Job.getInstance()
}

@Throws(IOException::class)
fun jobOf(configuration: Configuration, init: Job.() -> Unit = {}): Job {
    return Job.getInstance(configuration)
}

@Throws(IOException::class)
fun jobOf(configuration: Configuration, jobName: String, init: Job.() -> Unit = {}): Job {
    return Job.getInstance(configuration, jobName)
}

@Throws(IOException::class)
fun jobOf(status: JobStatus, configuration: Configuration, init: Job.() -> Unit = {}): Job {
    return Job.getInstance(status, configuration)
}

@Deprecated(message = "Use `jobOf()`", replaceWith = ReplaceWith("jobOf()"))
@Throws(IOException::class)
fun jobOf(ignored: Cluster, init: Job.() -> Unit = {}): Job {
    return Job.getInstance(ignored)
}

@Deprecated(message = "Use `jobOf()`", replaceWith = ReplaceWith("jobOf(configuration)"))
@Throws(IOException::class)
fun jobOf(ignored: Cluster, configuration: Configuration, init: Job.() -> Unit = {}): Job {
    return Job.getInstance(ignored, configuration)
}

@InterfaceAudience.Private
@Throws(IOException::class)
fun jobOf(ignored: Cluster, status: JobStatus, configuration: Configuration, init: Job.() -> Unit = {}): Job {
    return Job.getInstance(ignored, status, configuration)
}


var Job.inputPaths: Array<Path>
    get() = FileInputFormat.getInputPaths(this)
    set(value) {
        FileInputFormat.setInputPaths(this, value.joinToString { it.name })
    }

var Job.inputPathNames: Array<String>
    get() = inputPaths.map { it.name }.toTypedArray()
    set(value) {
        inputPaths = value.map { Path(it) }.toTypedArray()
    }

var Job.inputPath: Path
    get() = FileInputFormat.getInputPaths(this).first()
    set(value) {
        FileInputFormat.setInputPaths(this, value)
    }

var Job.inputPathName: String
    get() = inputPath.name
    set(value) {
        inputPath = Path(value)
    }

var Job.outputPath: Path?
    get() = FileOutputFormat.getOutputPath(this)
    set(value) {
        FileOutputFormat.setOutputPath(this, value)
    }

var Job.outputPathName: String?
    get() = outputPath?.name
    set(value) {
        outputPath = Path(value)
    }


val Job.mapProgress: Float
    @Throws(IOException::class)
    get() = mapProgress()

val Job.reduceProgress: Float
    @Throws(IOException::class)
    get() = reduceProgress()

val Job.cleanupProgress: Float
    @Throws(IOException::class, InterruptedException::class)
    get() = cleanupProgress()

val Job.setupProgress: Float
    @Throws(IOException::class)
    get() = setupProgress()

@Throws(IOException::class)
fun Job.kill() {
    killJob()
}

var Job.inputFormatKClass: KClass<out InputFormat<*, *>>
    get() = inputFormatClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        inputFormatClass = kClass.java
    }

var Job.outputFormatKClass: KClass<out OutputFormat<*, *>>
    get() = outputFormatClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        outputFormatClass = kClass.java
    }

var Job.mapperKClass: KClass<out Mapper<*, *, *, *>>
    get() = mapperClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        mapperClass = kClass.java
    }

fun Job.setJarByKClass(kClass: KClass<*>) {
    setJarByClass(kClass.java)
}

var Job.combinerKClass: KClass<out Reducer<*, *, *, *>>
    get() = combinerClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        combinerClass = kClass.java
    }

var Job.reducerKClass: KClass<out Reducer<*, *, *, *>>
    get() = reducerClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        reducerClass = kClass.java
    }

var Job.partitionerKClass: KClass<out Partitioner<*, *>>
    get() = partitionerClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        partitionerClass = kClass.java
    }

var Job.mapOutputKeyKClass: KClass<*>
    get() = mapOutputKeyClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        mapOutputKeyClass = kClass.java
    }

var Job.mapOutputValueKClass: KClass<*>
    get() = mapOutputValueClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        mapOutputValueClass = kClass.java
    }

var Job.outputKeyKClass: KClass<*>
    get() = outputKeyClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        outputKeyClass = kClass.java
    }

var Job.outputValueKClass: KClass<*>
    get() = outputValueClass.kotlin
    @Throws(IllegalStateException::class)
    set(kClass) {
        outputValueClass = kClass.java
    }

@Throws(IllegalStateException::class)
fun Job.setCombinerKeyGroupingComparatorKClass(kClass: KClass<out RawComparator<*>>) {
    setCombinerKeyGroupingComparatorClass(kClass.java)
}

@Throws(IllegalStateException::class)
fun Job.setSortComparatorKClass(kClass: KClass<out RawComparator<*>>) {
    setSortComparatorClass(kClass.java)
}

@Throws(IllegalStateException::class)
fun Job.setGroupingComparatorKClass(kClass: KClass<out RawComparator<*>>) {
    setGroupingComparatorClass(kClass.java)
}


@Throws(IOException::class, InterruptedException::class, ClassNotFoundException::class)
fun Job.async() {
    submit()
}

@Throws(IOException::class, InterruptedException::class, ClassNotFoundException::class)
fun Job.await(verbose: Boolean = false): Boolean {
    return waitForCompletion(verbose)
}
