open class A {
    fun newA(): Self {
        return <!RETURN_TYPE_MISMATCH!>A()<!>
    }
}

class B : A() {
    fun newB(): Self {
        return B()
    }
}
