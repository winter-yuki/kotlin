// WITH_STDLIB

abstract class A {
    abstract fun f(): Self
    fun g(): Self = this
}

class B : A() {
    override fun f(): Self = this
}

fun Any.test() {
    require(this is B)
    val b: B = f().g().f()
}
