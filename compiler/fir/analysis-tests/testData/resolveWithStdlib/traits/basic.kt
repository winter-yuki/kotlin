// TRAIT_HELPERS

object Traits {
    @Trait
    val int: Int = 0
}

context(Traits)
fun testContext() {
    plus(42)
}

context(Traits)
fun testLabel() {
    with(this@int) {
        plus(42)
    }
}

fun testReceiver() {
    with(Traits) {
        plus(42)
    }
}
