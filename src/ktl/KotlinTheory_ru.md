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

*0 until 10* == *0..<10* (новый синтаксис, появился в kotlin 1.8.0)

*0..10* берет интервал включая правый край, *0..<10* исключая

#### 4) Что такое inline/noinline/crossinline? Какие плюсы от использования? Почему не использовать их постоянно? Когда мы не можем использовать inline? Что такое non-local-return?

[Link](youtube.com/watch?v=shTrR_O6TaA)

#### 5) Что такое reified? В чем плюс использования с inline?

Помогает избавиться от рефлексии. Inline подставляет вместо дженерика нужный нам класс

#### 6) Сколько параметров в конструкторе может иметь inline class? Почему?

Только один параметр. Во время компиляции на место инициализации класса заменит инициализацию этого параметра, и
перенесет код из inline в тот где используется его реализация.

#### 7) Контравариантность, ковариантность, инвариантность

Представим что у нас есть два класса

 ```Kotlin
open class Fruit
class Orange : Fruit()

fun variance() {
    var fruit = Fruit()
    var orange = Orange()

    fruit = orange   // если мы попытаемся записать orange во fruit мы сможем это сделать
//    orange = fruit // fruit в orange уже нельзя (потому что orange является fruit, а fruit не является orange) 

//    А как поведет себя список? 

    var orangeList: ArrayList<Orange> = arrayListOf<Orange>()
    var fruitList: ArrayList<Fruit> = arrayListOf<Fruit>()
//    Мы не сможем присвоить переменную как список другого типа.
//    Это называется ИНВАРИАНТНОСТЬ
//    fruitList = orangeList // forbidden
//    orangeList = fruitList // forbidden
    
}
```

Если мы хотим иметь список в котором можем содержать всех родителей Orange мы можем использовать *ArrayList< in Orange>*
. Это называется КОНТРВАРИАНТНОСТЬ

```Kotlin
doSmth(fruitList)

fun doSmth(orangeList: ArrayList<in Orange>) {
    orangeList.forEach {

    }
    val orange: Any? = orangeList[0] // не даст вернуть Orange, только Any
}
```

Если мы хотим иметь список в котором можем содержать всех наследлников Fruit мы можем использовать ArrayList<out Fruit>.
Это называется КОВАРИАНТНОСТЬ

```Kotlin
doSmth2(orangeList)

fun doSmth2(orangeList: ArrayList<out Fruit>) {
    orangeList.forEach {

    }
//    orangeList.add(Orange())  не даст ничего добавить потому что тип использующий out == Nothing
}

```

Так же *in* и *out* могут быть использованы для указания того, какие типы должны быть возвращающими, а какие принимающие

 ```Kotlin
class Example<in T, out V> {
    fun check(t: T) {}
    fun check(): V? {
        return null
    }
//    fun check2(v: V){ // так нельзя, тип V должен использовать для возврата значения
//
//    }
}
 ```

#### 8) Разница между Nothing, Unit и Any

#### 9) Можно ли наследоваться от data class? Почему?

#### 10) Что такое делегаты? 

Выражение после by является делегатом, потому что геттеры и сеттеры, делегированы его методам getValue() и setValue(). 
Делегаты свойств не должны реализовывать интерфейс, но они должны предоставлять getValue() функцию (и setValue() для vars).