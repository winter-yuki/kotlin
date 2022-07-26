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
    override fun a(): C = null!!
    override fun b(): C? = null!!
    override fun c(): C = null!!

    override fun f(): Self = null!!
    override fun g(): Self? = null!!
    override fun h(): Self = null!!

    override fun x(): C = null!!
    override fun y(): C? = null
    override fun z(): C = null!!
}
