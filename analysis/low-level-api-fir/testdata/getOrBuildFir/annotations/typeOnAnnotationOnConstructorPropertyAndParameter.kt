// LOOK_UP_FOR_ELEMENT_OF_TYPE: org.jetbrains.kotlin.psi.KtAnnotationEntry

class ResolveMe(
    var addCommaWarning: <expr>@Anno</expr> Boolean = false,
    second: @Anno Boolean = false,
) : A() {

}

open class A

@Target(AnnotationTarget.TYPE)
annotation class Anno