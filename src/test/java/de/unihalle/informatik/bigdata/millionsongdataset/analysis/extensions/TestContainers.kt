package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

import org.jetbrains.spek.api.dsl.TestBody
import org.jetbrains.spek.api.dsl.TestContainer

fun <R> TestContainer.it(description: String, body: TestBody.() -> R): R {
    var returnValue: R? = null
    test("it $description") {
        returnValue = body()
    }
    return returnValue!!
}