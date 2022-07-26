abstract class A<T> {
    fun f(): Self = null!!
    fun g(): Self? = null!!

    fun h(): Self = null!!
    fun i(): Self? = null

    open fun j(): Self = null!!
    open fun k(): Self = null!!

    fun foo(): T = null!!
}

class B<T> : A<T>() {
    fun h2(): Self = h()
    fun i2(): Self? = i()

    override fun j(): Self = null!!
    override fun k(): Self = null!!
}

fun test(b: B<String>) {
    b.f().foo().length // TODO string rewrap set type in substitution scope
}
