abstract class A {
    fun a(b: B): Self = <!RETURN_TYPE_MISMATCH!>b.f()<!>
    fun b(b: B): Self? = <!RETURN_TYPE_MISMATCH!>b.g()<!>
    fun c(b: B): Self? = <!RETURN_TYPE_MISMATCH!>b.f()<!>

    fun d(b: B): Self = b.run { <!ARGUMENT_TYPE_MISMATCH!>this.f()<!> }
    fun e(b: B?): Self = b.run { <!ARGUMENT_TYPE_MISMATCH!>this?.f()<!> }
}

class B : A() {
    fun f(): Self = null!!
    fun g(): Self? = null
}
