abstract class A {
    fun p(): Self = this
    fun q(): Self? = null
}

class B : A() {
    fun a(): Self = p()
    fun b(): Self? = q()
    fun c(): Self? = p()
}
