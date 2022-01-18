// LANGUAGE: -JvmInlineValueClasses, +GenericInlineClassParameter, +SealedInlineClasses
// IGNORE_BACKEND: JVM

interface I {
    fun str(i: Int): String = "K"
}

sealed inline class IC: I

inline class ICString(val s: String): IC() {
    override fun str(i: Int): String = s
}

object ICO: IC()

fun toString(ic: IC): String = ic.str(0)

fun box(): String = toString(ICString("O")) + toString(ICO)