// example from https://youtrack.jetbrains.com/issue/KT-6494

sealed interface Data {
    data class One(var a: Int) : Data
    data class Two(var a: Int, var b: Int) : Data

    // this shall typecheck
    fun copy(): Self = when(this) {
        is One -> One(a) // no cast needed here, because of smart-cast on `this`
        is Two -> Two(a, b)
        else -> null!!
    }
}

fun test() {
    val a = Data.One(1)
    val b = a.copy() // has type of `A`
}
