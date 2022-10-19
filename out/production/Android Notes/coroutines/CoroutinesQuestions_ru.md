#### Разница между корутинами тредами

1) Корутины — это форма последовательной обработки: в любой момент времени выполняется только одна. Треды представляют
   собой форму параллельной обработки: несколько потоков могут выполняться в любой момент времени.
2) Операционная система переключает запущенные потоки в соответствии со своим планировщиком, который управляется
   алгоритмом в ядре ОС. С корутинами программист и язык программирования определяют, когда переключать работу.
3) Корутины могут обеспечить очень высокий уровень параллелизма с очень небольшими накладными расходами. Как правило, в
   многопоточной среде у вас есть не более 30-50 потоков, прежде чем количество накладных расходов, потраченных впустую
   на фактическое планирование этих потоков
   (системным планировщиком), значительно сократит количество времени, в течение которого потоки действительно выполняют
   полезную работу.
4) Корутины выполняются в одном потоке или пуле потоков.

***

#### Какие основные составляющие компоненты корутин вы знаете. Опишите их роль

*Job, Context, Dispatcher и CoroutineScope*

*Job* - Хранит состояние корутины: активно/отменено/завершено. Эти состояния меняются по мере выполнения корутины. Мы
также можем менять состояния корутины самостоятельно: чтобы отменить корутину или начать создать отложенную корутины. *
Job* может быть организована в иерархии родитель-потомок, где отмена родителя приводит к немедленной отмене всех его
детей рекурсивно.

*CoroutineContext* - коллекция уникальных значений Key-Element. По контракту должен содержать экземпляр Job для
обеспечения структурированного параллелизма.

[//]: # (TODO)
*Dispatcher* -

*CoroutineScope* -

*Continuation* -

***

#### Как основные компоненты корутин зависят друг от друга

[//]: # (TODO)

***

#### Обработка исключений корутин

a)

 ```Kotlin
 try {
    doSmthSuspend()
} catch (exception: Exception) {
    if (exception is CancellationException) {
        throw exception
    }
}
 ```

b)

 ```Kotlin
val handler = CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
}

launch(handler) {
    throw AssertionError()
}

CoroutineExceptionHandler got java.lang.AssertionError
```

***

#### Почему нельзя перехватывать CancellationException?

[//]: # (TODO)

***

#### Что такое suspension points? Как стейт машина корутин разделит этот код?

[//]: # (TODO)

[//]: # (Suspension points are points in code which either end your program early &#40;mostly bad paths in programs&#41;, or which start some work on the side, in another routine which is suspended, ultimately notifying you of the end result, and allowing you to continue where you left off.)

 ```Kotlin
launch {
    doSmthSuspend1()
    toast("First task completed")
    doSmthSuspend2()
    toast("Second task completed")
}


```

1. doSmthSuspend1() и все до
2. все после doSmthSuspend1() и до doSmthSuspend2()
3. все после doSmthSuspend2()

***

#### Как continue.resume() передает параметры следующему блоку стейт машины?

[//]: # (TODO поправить ошибку в логике)

 ```Kotlin
launch {
    val param = buildParam()
    val firstResult = doSmthSuspend1(param)
    doSmthSuspend2(firstResult)
}
```

continuation.resume(param)

*param* кастуется к нужному параметру

 ```
   Object invokeSuspend(Object result) {
        switch (label) {
            case 0: {
                label = 1;
                result = buildParam();
                if (result == COROUTINE_SUSPENDED) return COROUTINE_SUSPENDED;
            }
            case 1: {
                param = (String) result;
                if (result == COROUTINE_SUSPENDED) return COROUTINE_SUSPENDED;
            }
        }
    }
```

***

#### Разница между async и launch

*Launch* -Создает и запукает корутину, возвращает Job и не заботится о результате.

*Async* - Создает и запускает корутину, и возвращает ее будущий результат через реализацию Deferred. Результат можно
получить через *Deferred.await()*. Приостанавливает нынешний тред в точке вызова *await()* и ждет завершение выполнения
корутины.

***

#### Виды Job

[//]: # (TODO)

*Job* -

*Deffered* -

*SupervisorJob* -

***

#### Join, JoinAll, Await, AwaitAll

*Join* - Приостанавливает текущую корутины до завершения Job.

*JoinAll* - Приостанавливает текущую корутину до тех пор, пока все Job не будут выполнены. Этот метод семантически
эквивалентен:

 ```Kotlin
jobs.forEach { it.join() }
 ```

*Await* - расширение класса Deferred. Ожидает выполнения без блокировки. Возвращает результат корутины.

*AwaitAll* - расширение класса Deferred. Ожидает выполнения всех Deffered. Continuation.resume вызывается либо со
списком значений, когда все Deffered работы завершены, или с первым брошенным исключением (включая отмену).

 ```Kotlin
val results: List<String> = listOf<Deferred<String>>().awaitAll()
 ```

***

#### В чем разница между deferreds.map { it.await() } and deferreds.awaitAll().

*AwaitAll* это **НЕ** эквивалент для

 ```Kotlin
deferreds.map { it.await() } // it is NOT AwaitAll
```

*awaitAll* - вернет ошибку если хоть одна из *Deffered* выбросит исключение.

*этот код* - выполнит все корутины, даже если было выброшено исключение в одной из *Deffered*;

***

#### Что такое CoroutineStart? Какие типы бывают?

 ```Kotlin
fun CoroutineScope.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job
 ```

*CoroutineStart* определяет параметры запуска для билдера корутины.

*DEFAULT* - создает и запускает корутину немедленно;

*ATOMIC* - атомарно (без возможности отмены) планирует сопрограмму для выполнения;

*LAZY* - запускает сопрограмму, только когда к ней обратятся;

*UNDISPATCHED* - немедленно выполняет сопрограмму до ее первой точки приостановки в текущем потоке.

***

#### Как отменить корутину? Что такое ensureActive?

Мы можем отменить job,  и должны убедиться, что текущий scope активен, используя *isActive* или *ensureActive()*

ensureActive() - Если job больше не активна, бросает CancellationException. Этот метод является заменой
следующего кода, но с более точным исключением:

 ```Kotlin
 if (!isActive) {
    throw CancellationException()
}
 ```

 ```Kotlin
val job = CoroutineScope.launch {
    ensureActive()
    doSmth()
}
job.cancel()
 ```

***

#### Как поместить дополнительные данные в CoroutineContext?

В CoroutineContext мы можем поместить классы реализующие CoroutineContext.Element. AbstractCoroutineContextElement - абстрактный класс
для реализации CoroutineContext.Element.

 ```Kotlin
data class SharedData(
    val sharedInfo: Long,
) : AbstractCoroutineContextElement(UserData) {
    companion object Key : CoroutineContext.Key<UserData>
}

//then scope could be created as
val scope = CoroutineScope(Job() + Dispatchers.Default + SharedData("I have a secret for you"))
 ```
