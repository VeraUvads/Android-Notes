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

#### 3) Разница между *0 until 10*, *0..10* and *0..<10*



#### 4) Что такое inline/noinline/crossinline? Какие плюсы от использования?
Когда мы не можем использовать inline?

