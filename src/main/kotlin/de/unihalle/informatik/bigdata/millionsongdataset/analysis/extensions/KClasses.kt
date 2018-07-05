package de.unihalle.informatik.bigdata.songs.extensions

import org.apache.hadoop.util.ClassUtil
import java.io.IOException
import kotlin.reflect.KClass


/**
 * Find a jar that contains a class of the same name, if any.
 * It will return a jar file, even if that is not the first thing
 * on the class path that has a class with the same name.
 *
 * @return A jar file that contains the class, or `null`.
 *
 * @throws IOException
 */
val KClass<*>.containingJar: String?
    get() {
        return ClassUtil.findContainingJar(this.java)
    }