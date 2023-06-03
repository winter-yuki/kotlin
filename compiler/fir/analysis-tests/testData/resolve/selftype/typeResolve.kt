interface Out<out T>

class A {
    fun f1(): Self = null!!
    fun f2(): Self? = null!!
    fun f3(): Out<Self> = null!!
    fun f4(): Out<Self?> = null!!

    // TODO: prohibit
    class B
    fun B.both1(): Self = null!!
    fun B?.both2(): Self = null!!
}

// TODO: extensions support
fun A.g(): <!NO_THIS!>Self<!> {
    fun A.B.h1(): <!NO_THIS!>Self<!> = null!!
    fun A.B?.h2(): <!NO_THIS!>Self<!> = null!!
    null!!
}
