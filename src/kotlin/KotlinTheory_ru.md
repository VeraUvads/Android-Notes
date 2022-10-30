#### 1) Разница между *class* и *data class*,  *data object* и *object*

#### 2) Способы реализовать функциональный тип?

 ```Kotlin
val a: () -> Unit = {}

val b: () -> Unit = object : () -> Unit {
    override fun invoke() {}
}

val c: () -> Unit = ::foo

val d: () -> Unit = fun() {}

fun foo() = Unit
 ```