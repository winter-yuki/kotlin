fun f(x: Int): <!NO_THIS!>Self<!> = null!!
fun g(x: <!NO_THIS!>Self<!>): <!NO_THIS!>Self?<!> = null!!

fun <!NO_THIS!>Self<!>.h(): Int = 42

context(<!NO_THIS!>Self<!>)
fun contexted() = Unit
