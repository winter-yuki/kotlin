// LANGUAGE: +GenericInlineClassParameter
// IGNORE_BACKEND: JVM
// TARGET_BACKEND: JVM
// WITH_STDLIB

@JvmInline
value class AAAA<T : Any>(val x: List<T>)

class A {
    fun equalsChecks1(x: AAAA<List<Int>>) {}
}

fun box(): String {
    val paramTypes = A::class.java.methods.find { it.name.contains("equalsChecks1") }!!.genericParameterTypes.toList().toString()
    if (paramTypes != "[java.util.List<? extends java.util.List<java.lang.Integer>>]") return "FAIL: $paramTypes"
    return "OK"
}
