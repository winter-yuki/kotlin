class In<in T> {
    fun accept(x: T) = null!!
}

class Out<out T> {
    fun produce(): T = null!!
}

class Inv<T> {
    fun id(x: T): T = x
}

abstract class A {
    fun f(): In<Self> = In()
    fun g(): Out<Self> = Out()
    fun h(): Inv<Self> = Inv()
}

class B : A()
