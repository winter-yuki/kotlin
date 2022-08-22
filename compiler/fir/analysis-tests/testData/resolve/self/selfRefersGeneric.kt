// USE_STDLIB

open class Lazy<out T>(val computation: () -> T) {
    fun copy(): Self = Lazy { computation().apply {} }
}

open class LazyNumber<out T : Number>(computation: () -> T) : Lazy<T>(computation) {
    fun shortify(): Self = LazyNumber { computation().shortValue() }
}

class LazyInt(computation: () -> Int) : LazyNumber<Int>(computation) {
    fun add(n: Int): Self = LazyInt { computation() + n }
}

fun test() {
    LazyInt { 42 }
        .copy()
        .shortify()
        .add(13)
        .computation()
}
