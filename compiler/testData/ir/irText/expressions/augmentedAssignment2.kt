// MUTE_SIGNATURE_COMPARISON_K2: JS_IR
// MUTE_SIGNATURE_COMPARISON_K2: NATIVE
// ^ KT-57818

class A

operator fun A.plusAssign(s: String) {}
operator fun A.minusAssign(s: String) {}
operator fun A.timesAssign(s: String) {}
operator fun A.divAssign(s: String) {}
operator fun A.remAssign(s: String) {}

val p = A()

fun testVariable() {
    val a = A()
    a += "+="
    a -= "-="
    a *= "*="
    a /= "/="
    a %= "*="
}

fun testProperty() {
    p += "+="
    p -= "-="
    p *= "*="
    p /= "/="
    p %= "%="
}
