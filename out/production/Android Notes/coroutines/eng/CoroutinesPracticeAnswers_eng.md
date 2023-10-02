## Answers for [coroutines practice](https://github.com/VeraUvads/Android-Notes/blob/398ce6ef0354a023ea39919c581cb9502ea630e0/src/coroutines/eng/CoroutinesTheory_eng.md)

#### 1) If we run Dispatcher1 on a quad-core processor, what should we see?

*Dispatchers.Default* - By default, the maximum number of threads used by this dispatcher is equal to the number of CPU
cores. Coroutines 0,1,2,3 will start immediately. All threads are busy. Coroutines 4,5 coroutines will be launched when
two the previous one finish their work and release threads;

*Dispatchers.IO* - every Job starts immediately;

*newSingleThreadExecutor* - 6 coroutines will come to the dispatcher, which has only one thread. Coroutines had to line
up in a queue and be executed sequentially.

#### 2) What is wrong with Deferred1?

*deferredList.map { it.await() }* not the same with *deferredList.awaitAll()*

The code with *map* will suspend the coroutine on each call and execute all the functions in sequence.
*awaitAll* will do all Deferreds independently.

#### 3) Fix the Lifecycle1.

There are two mistakes.

1) When the activity is destroyed (as example, when we rotate the screen), lifecycle scope will be cancelled. We will
   lose result of our API call. To resolve first problem, we should call our api from *viewModelScope*. It will survive
   after rotation, and we will receive our data.
2) We shouldn't handle network calls on Dispatchers.Default. We have Dispatchers.IO for that goals. Use withContext(
   Dispatchers.IO) for API call.

 ```Kotlin
 class Lifecycle1 {
    internal class MainActivity {

        private fun onCreate() {
            onClick {
                viewModel.callApi()
            }
        }
    }


    internal class ViewModel {
        fun callApi() {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    println("Job started")
                    delay(2000)
                    println("Job finished")
                }
            }
        }
    }
}
 ```

#### 4) Deadlock with coroutine

Many ways, two of them:

 ```Kotlin

fun coroutinesDeadlock1() {
    var first: Job = Job()
    var second: Job = Job()
    runBlocking() {
        first = launch(start = CoroutineStart.LAZY) {
            println("first before join")
            second.join()
            println("first after join")
        }
        second = launch(start = CoroutineStart.LAZY) {
            println("second before join")
            first.join()
            println("second after join")
        }
        joinAll(first, second)
    }
}


fun coroutinesDeadlock2() {
    var third: Job = Job()
    runBlocking() {
        third = launch() {
            println("third before join")
            third.join()
            println("third after join")
        }
    }
}
 ```

5) What will be printed?
    1. Child 1
    2. Error stacktrace

"Child 2" will never be printed, because SupervisorJob works only for child coroutines; Is we want not to cancel our
second Job we should use the supervisor job as shared;

 ```Kotlin
   val sharedJob = SupervisorJob()
   launch {
       val child1 = launch(sharedJob) {
           delay(1000L)
           println("child 1")
           throw RuntimeException()
       }
       val child2 = launch(sharedJob) {
           delay(2000L)
           println("child 2")
       }
       child1.join()
       child2.join()
   }.join()
 ```

or

 ```Kotlin
suspend fun exceptionHandling() = coroutineScope {
    supervisorScope {
        launch {
            delay(1000L) 
            println("child 1")
            throw RuntimeException()
        }
        launch {
            delay(2000L)
            println("child 2")
        }
    }
}
 ```