class A {
    fun f(): Self = this
    fun g(): Self? = this
    fun h(): Self? = null

    fun x(): Self = f()
    fun y(): Self? = f()
    fun z(): Self? = g()
}
