interface I<T> {
    fun i(t: T): Int
}

class C<<expr>T</expr>>(val x: Int): I<T> {
    companion object {
        val K: Int = 58
    }

    fun test(): Int {
        return 45 * K
    }

    fun count(xs: List<T>): Int {
        return xs.size
    }

    override fun i(t: T): Int {
        return test() + t.hashCode()
    }

    inner class B() {

    }
}