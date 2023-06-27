// WITH_STDLIB
// TRAIT_HELPERS

object Traits {
    @Trait
    val int: Int = 0
}

fun box(): String {
    with(Traits) {
        val res = plus(42)
        return if (res == 42)  "OK" else "Fail: ${res}"
    }
}
