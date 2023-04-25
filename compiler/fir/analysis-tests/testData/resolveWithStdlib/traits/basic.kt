// TRAIT_HELPERS

object Traits {
    @Trait
    val int: Int = 0
}

context(Traits)
fun test() {
    plus(42)
}
