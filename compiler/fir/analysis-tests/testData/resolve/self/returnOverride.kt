abstract class A {
    abstract fun a(): Self
    abstract fun b(): Self?
    abstract fun c(): Self?
}

abstract class B : A() {
    abstract fun f(): Self
    abstract fun g(): Self?
    abstract fun h(): Self?

    abstract fun x(): Self
    abstract fun y(): Self?
    abstract fun z(): Self?
}

class C : B() {
    override fun a(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>C<!> = null!!
    override fun b(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>C?<!> = null!!
    override fun c(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>C<!> = null!!

    override fun f(): Self = null!!
    override fun g(): Self? = null!!
    override fun h(): Self = null!!

    override fun x(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>C<!> = null!!
    override fun y(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>C?<!> = null
    override fun z(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>C<!> = null!!
}
