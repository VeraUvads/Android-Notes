package kotlin

val a: () -> Unit = {}

val b: () -> Unit = object : () -> Unit {
    override fun invoke() {}
}

val c: () -> Unit = ::test

val d: () -> Unit = fun() {}

fun test() = Unit