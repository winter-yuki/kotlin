// WITH_STDLIB
// TRAIT_HELPERS

context(TraitBunch)
fun runInContext(): String {
    if (1 sadd 2 != 3) return "Fail: ${1 sadd 2}"
    if ("a" sadd "b" != "ab") return "Fail: ${"a" sadd "b"}"
    // TODO remove <Int>
    if (concat<Int>(1, 2, 3) != 6) return "Fail: ${concat<Int>(1, 2, 3)}"
    if (concat("a", "b", "c") != "abc") return "Fail: ${concat<String>("a", "b", "c")}"
    return "OK"
}

fun box(): String = with(TraitBunch) {
    runInContext()
}
