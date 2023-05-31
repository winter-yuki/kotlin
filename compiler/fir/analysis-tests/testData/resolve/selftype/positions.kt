interface In<in T>
interface Out<out T>
interface Inv<T>

class C {
    fun invalid1(): In<Self> = null!!

    fun invalid2(x: Self) {
        null!!
    }

    fun invalid3(produce: () -> Self) {
        null!!
    }

    fun invalid4(): Inv<Self> = null!!

    fun invalid5(xs: Inv<Self>) {
        null!!
    }
}
