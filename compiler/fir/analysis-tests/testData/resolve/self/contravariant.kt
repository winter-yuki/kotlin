abstract class A {
    fun p(): Self = null!!
    fun q(): Self? = null
}

class B : A() {
    fun a(): Self = p().d()
    fun b(): Self? = q()?.d()
    fun c(): Self? = p().d()

    fun d(): Self = this.p().a()
    fun e(): Self? = this.q()?.a()
    fun f(): Self? = this.p().a()

    fun g(): Self = <!RETURN_TYPE_MISMATCH!>super.p().p()<!>
    fun h(): Self? = <!RETURN_TYPE_MISMATCH!>super.q()?.p()<!>
    fun i(): Self? = <!RETURN_TYPE_MISMATCH!>super.p().p()<!>

    fun j(b: Boolean): Self = if (b) this else p()
}
