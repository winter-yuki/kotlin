// TRAIT_HELPERS

context(TraitBunch)
fun test() {
    1 sadd 2
    "a" sadd "b"

    // TODO fix checkContextReceivers
    concat<Int>(1, 2, 3)
    concat("a", "b", "C")
}
