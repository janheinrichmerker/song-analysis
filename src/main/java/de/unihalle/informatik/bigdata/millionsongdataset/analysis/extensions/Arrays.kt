package de.unihalle.informatik.bigdata.millionsongdataset.analysis.extensions

inline fun <reified ElementType : Any> generateArray(size: Int, crossinline generate: (index: Int) -> ElementType): Array<ElementType> {
    var index = 0
    return generateSequence {
        generate(index++)
    }.take(size).toList().toTypedArray()
}