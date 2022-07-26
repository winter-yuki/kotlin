abstract class S {
    fun p(): Self = this
    fun q(): Self? = null
}

class D : S() {
    fun a(): Self = this
    fun b(): Self? = this
    fun c(): Self? = this
}

fun test() {
    val d: D = D()
    val d1: D = d.a()
    val d2: D = d.p()
    val d3: D? = d.b()
    val d4: D? = d.q()

//    val d5: D = d.p().a().q()

//    b.a().b()
//    b.b()?.c()
//    b.a().p().q()?.a()
//    b.q()?.a()
}
