abstract class A {
    fun a(b: B): Self = b.f()
    fun b(b: B): Self? = b.g()
    fun c(b: B): Self? = b.f()
}

class B : A() {
    fun f(): Self = this
    fun g(): Self? = null
}
