// USE_STDLIB

abstract class Lazy<T>(val computation: () -> T) {
    abstract protected fun create(computation: () -> T): Self
    fun copy(): Self = create { computation() }
}

abstract class LazyNumber<T : Number>(computation: () -> T) : Lazy<T>(computation) {
    fun shortify(): Self = create { computation().toShort() }
}

class LazyInt(computation: () -> Int) : LazyNumber<Int>(computation) {
    override fun create(computation: () -> Int): Self = LazyInt(computation)
    fun add(n: Int): Self = create { computation() + n }
}

fun test() {
    LazyInt { 42 }
        .copy()
        .shortify()
        .add(13)
        .computation()
}
