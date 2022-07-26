abstract class Q {
    fun a(): Self = null!!
    fun b(): Self? = null!!

    fun xx(p: P): Q = p.x()
    fun yy(p: P): Q? = p.y()
    fun zz(p: P): Q? = p.x()
}

class P : Q() {
    fun aa(): P = a()
    fun bb(): P? = b()
    fun cc(): P? = a()
    fun dd(): Q = a()
    fun ee(): Q? = b()
    fun ff(): Q? = a()

    fun x(): Self = null!!
    fun y(): Self? = null!!
}
