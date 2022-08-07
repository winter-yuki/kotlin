abstract class S {
    fun p(): Self = null!!
    fun q(): Self? = null
}

class D : S() {
    fun a(): Self = null!!
    fun b(): Self? = null!!
    fun c(): Self? = null!!
}

fun test() {
    val d: D = D()
    val d1: D = d.a()
    val d2: D = d.p()
    val d3: D? = d.b()
    val d4: D? = d.q()

    val d5: D? = d.p().a().q()
    val d6: D? = d.a().q()?.c()
}
