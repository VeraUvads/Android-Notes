#### Difference between a "coroutine" and a "thread"?

1) Coroutines are a form of sequential processing: only one is executing at any given time. Threads are a form of
   concurrent processing: multiple threads may be executing at any given time.
2) The operating system switches running threads according to its scheduler, which is an algorithm in the operating
   system kernel. With coroutines, the programmer and programming language determine when to switch coroutines
3) Coroutines can provide a very high level of concurrency with very little overhead. Generally in a threaded
   environment you have at most 30-50 threads before the amount of overhead wasted actually scheduling these threads
   (by the system scheduler) significantly cuts into the amount of time the threads actually do useful work.
4) Coroutines run within a single thread or pool of threads.

***

#### What are the main components of coroutines? Describe their role

*Job, Context, Dispatcher и CoroutineScope*

*Job* - Stores the state of the coroutine: active / canceled / completed. These states change as the coroutine runs. But
we also use coroutine state products on their own: to cancel the coroutine or start deferring the coroutine. Jobs can be
arranged into parent-child hierarchies where cancellation of a parent leads to immediate cancellation of all its
children recursively.

*Context*

*Dispatcher*

*CoroutineScope* - By convention, should contain an instance of a job to enforce structured concurrency.
***

#### Job types

***

#### Coroutine exceptions handling

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

#### Why is it forbidden to catch CancellationException?

***

#### How coroutines state machine will divide this code?

 ```Kotlin
launch {
    val param = buildParam()
    doSmthSuspend1(param)
    toast("First task completed")
    doSmthSuspend2()
    toast("Second task completed")
}
```

***

#### How continuation.resume() set parameters to next suspend fun?

 ```Kotlin
launch {
    val param = buildParam()
    val firstResult = doSmthSuspend1(param)
    doSmthSuspend2(firstResult)
}
```
