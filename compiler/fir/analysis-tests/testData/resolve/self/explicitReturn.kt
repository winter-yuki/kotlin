abstract class A {
    fun f(): Self = null!!
    fun g(): Self? = null!!
}

class B: A() {
    fun x(i: Int): Self = null!!
    fun y(): Self? = null!!
}
