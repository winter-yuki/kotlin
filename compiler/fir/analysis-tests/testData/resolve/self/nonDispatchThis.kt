// WITH_STDLIB

class A {
    fun f(): Self = <!DEBUG_INFO_EXPRESSION_TYPE("")!>apply {}<!>
    fun g(): Self = run { <!DEBUG_INFO_EXPRESSION_TYPE("")!>f()<!> }
}
