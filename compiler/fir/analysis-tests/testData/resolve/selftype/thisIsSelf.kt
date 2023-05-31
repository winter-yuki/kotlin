// WITH_STDLIB

class A {
    fun f1(): Self {
        return this
    }

    fun f2(): List<Self> {
        return <!RETURN_TYPE_MISMATCH!>listOf(this, this)<!>
    }

    fun f3(): List<Self> {
        return listOf<Self>(<!ARGUMENT_TYPE_MISMATCH!>this<!>, <!ARGUMENT_TYPE_MISMATCH!>this<!>)
    }

    fun f4(): Self {
        return <!RETURN_TYPE_MISMATCH!>A()<!>
    }

    fun f5(): Self = this

    // TODO
    fun f6(): Self = A()
}
