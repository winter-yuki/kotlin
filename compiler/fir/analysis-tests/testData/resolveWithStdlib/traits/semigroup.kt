@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class Trait

interface Semigroup<T> {
    infix fun T.sadd(other: T): T
}

interface Monoid<T> : Semigroup<T> {
    val empty: T
}

context(Monoid<T>)
fun <T> concat(vararg xs: T): T = xs.fold(empty) { acc, t -> acc sadd t }

object IntAddMonoid : Monoid<Int> {
    override val empty: Int = 0
    override fun Int.sadd(other: Int): Int = this + other
}

object StringMonoid : Monoid<String> {
    override val empty: String = ""
    override fun String.sadd(other: String): String = plus(other)
}

object TraitBunch {
    @Trait
    val intMonoid: Monoid<Int> = IntAddMonoid

    @Trait
    val stringMonoid: Monoid<String> = StringMonoid
}

context(TraitBunch)
fun test() {
    1 sadd 2
    "a" sadd "b"

    concat<Int>(1, 2, 3)
    concat<String>("a", "b", "C")
}
