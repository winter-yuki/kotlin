interface In<in T>
interface Out<out T>
interface Inv<T>

abstract class Base {
    abstract fun viaReturn(): Self
    abstract fun viaReturnGeneric(): Out<Self>
    abstract fun viaInputConsumer(consumer: (Self) -> Unit)
}

<!ABSTRACT_CLASS_MEMBER_NOT_IMPLEMENTED!>class Derived<!> : Base() {
    override fun viaReturn(): Self = null!!
    override fun viaReturnGeneric(): Out<Self> = null!!
    // TODO: fix overrides for self
    <!NOTHING_TO_OVERRIDE!>override<!> fun viaInputConsumer(consumer: (Self) -> Unit) = null!!
}
