// FILE: ObjectPattern.java

import org.jetbrains.annotations.NotNull;

public abstract class ObjectPattern<T, Self1 extends ObjectPattern<T, Self1>> {
    protected ObjectPattern(@NotNull Class<T> aClass) {

    }

    public static class Capture<T> extends ObjectPattern<T,Capture<T>> {
        public Capture(@NotNull Class<T> aClass) {
            super(aClass);
        }
    }
}

// FILE: UastPatterns.kt

interface UElement

interface UExpression : UElement

interface UReferenceExpression : UExpression

fun injectionHostOrReferenceExpression(): UExpressionPattern.Capture<UExpression> =
    uExpression().filter { it is UReferenceExpression }

fun uExpression(): UExpressionPattern.Capture<UExpression> = expressionCapture(UExpression::class.java)

fun <T : UExpression> expressionCapture(clazz: Class<T>): UExpressionPattern.Capture<T> = UExpressionPattern.Capture(clazz)

open class UElementPattern<T : UElement, Self1 : UElementPattern<T, Self1>>(clazz: Class<T>) : ObjectPattern<T, Self1>(clazz) {
    fun filter(filter: (T) -> Boolean): Self1 = this <!UNCHECKED_CAST!>as Self1<!>
}

open class UExpressionPattern<T : UExpression, Self1 : UExpressionPattern<T, Self1>>(clazz: Class<T>) : UElementPattern<T, Self1>(clazz) {
    open class Capture<T : UExpression>(clazz: Class<T>) : UExpressionPattern<T, Capture<T>>(clazz)
}
