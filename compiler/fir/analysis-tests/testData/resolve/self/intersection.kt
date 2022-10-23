// INFERENCE_HELPERS

interface A {
    fun f(): Self
}

interface B : A {
    fun g(): Self
}

interface C : A {
    fun h(): Self
}

interface D : B, C

interface E : B, C

fun test(d: D, e: E) {
    val bc = if (true) d else e
    val b1: B = bc.f().h()
    val c2: C = bc.f().g()

    val Sbc = if (true) d.f() else e
    val b2: B = Sbc.h()
    val c2: C = Sbc.g()

    val SbSc = select(d.f(), e.f())
    val b3: B = SbSc.h()
    val c3: C = SbSc.g()
}
