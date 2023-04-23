// !LANGUAGE: +ContextReceivers
// WITH_STDLIB

//import kotlin.annotation.Trait

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.BINARY)
annotation class Trait

object Traits {
    @Trait
    val int = 0
}

context(Traits)
fun test() {
    plus(42)
}
