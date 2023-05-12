// TRAIT_HELPERS

context(TraitBunch)
fun testContext() {
    1 sadd 2
    "a" sadd "b"

    // TODO fix checkContextReceivers
    concat<Int>(1, 2, 3)
    concat("a", "b", "C")
}

context(TraitBunch)
fun testLabel() {
    with(this@intMonoid) {
        concat(1, 2, 3)
    }
}

fun testReceiver() {
    with(TraitBunch) {
        1 sadd 2
        "a" sadd "b"

        // TODO fix checkContextReceivers
        concat<Int>(1, 2, 3)
        concat("a", "b", "C")
    }
}

fun TraitBunch.withReceiver() {
    1 sadd 2
    "a" sadd "b"

    // TODO fix checkContextReceivers
    concat<Int>(1, 2, 3)
    concat("a", "b", "C")
}
