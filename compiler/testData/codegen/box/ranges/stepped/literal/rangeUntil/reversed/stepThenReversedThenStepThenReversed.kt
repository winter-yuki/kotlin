// Auto-generated by GenerateSteppedRangesCodegenTestData. Do not edit!
// WITH_STDLIB
// !LANGUAGE: +RangeUntilOperator
@file:OptIn(ExperimentalStdlibApi::class)
import kotlin.test.*

fun box(): String {
    val intList = mutableListOf<Int>()
    for (i in ((1..<11 step 2).reversed() step 3).reversed()) {
        intList += i
    }
    assertEquals(listOf(3, 6, 9), intList)

    val longList = mutableListOf<Long>()
    for (i in ((1L..<11L step 2L).reversed() step 3L).reversed()) {
        longList += i
    }
    assertEquals(listOf(3L, 6L, 9L), longList)

    val charList = mutableListOf<Char>()
    for (i in (('a'..<'k' step 2).reversed() step 3).reversed()) {
        charList += i
    }
    assertEquals(listOf('c', 'f', 'i'), charList)

    return "OK"
}