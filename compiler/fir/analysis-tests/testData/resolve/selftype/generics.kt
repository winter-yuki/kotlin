// WITH_STDLIB

interface PCollection<E> {
    fun add(elem: E): Self
    fun selfs(): List<Self>
}

interface PList<E> : PCollection<E> {
    fun listSpecific()
}

fun testReturn(xs: PList<Int>) {
    val newXs: PList<Int> = xs.add(42)
    newXs.listSpecific()
    xs.selfs().forEach { it.listSpecific() }
}

interface BaseButton {
    fun onClick(observer: (Self) -> Unit)
}

interface CheckBoxButton : BaseButton {
    fun isChecked(): Boolean
}

fun testParams(button: CheckBoxButton) {
    button.onClick { <!DEBUG_INFO_EXPRESSION_TYPE("CheckBoxButton")!>it<!>.isChecked() }
}
