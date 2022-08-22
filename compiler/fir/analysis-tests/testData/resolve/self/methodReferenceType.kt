class A {
    fun f(): Self = null!!
}

fun test() {
    val a = A()
    val fa: () -> A = a::f
    val f: (A) -> A = A::f
}
