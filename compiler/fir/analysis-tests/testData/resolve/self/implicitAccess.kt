// WITH_STDLIB

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
    val d = D()
    val d1: D = d.run { a().p() }
    val d2: D = d.run { p().a() }
    val d3: D? = d.run { q()?.p() }
}

fun D.test() {
    val d1: D? = p().a().q()
    val d2: D? = a().q()?.c()
}
