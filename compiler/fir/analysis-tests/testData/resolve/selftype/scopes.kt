open class A {
    fun self1(): Self {
        return this
    }

    fun self2(): Self {
        return <!RETURN_TYPE_MISMATCH!>this<!>
    }

    fun self3(): Self {
        return this.self4()
    }

    fun self4(): Self {
        return <!RETURN_TYPE_MISMATCH!>self3()<!>
    }
}

class B : A() {
    fun bOnly() {}

    fun derived1(): Self {
        return this.self1()
    }

    fun derived2(): Self {
        return <!RETURN_TYPE_MISMATCH!>self2()<!>
    }
}

fun test(a: A): A {
    val x: A = a.self1()
    B().self2().bOnly()
    return a
}
