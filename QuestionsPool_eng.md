# Android-Notes

### [Common](src/common_android/CommonAndroid_eng.md)

1) What is Android Software Stack. What are the major components of the Android platform?
2) Why Android OS uses DVM instead of JVM? Why Android depreciated DVM and started to use ART? 
3) What is App Sandbox?
4) Android build process
5) Inter process communication (IPC)
6) How is the application launched?
7) What is process ranking?
8) How does a garbage collector work? Which garbage collector used in Android?
9) Does increasing threads increase performance?
10) What is the difference between _process_ and _thread_? 
11) What does thread scheduling depend on?
12) What are Handler, Looper, MessageQueue for?


### [Coroutines Theory](src/coroutines/eng/CoroutinesTheory_eng.md)

1) Difference between a **coroutine** and a **thread**?
2) What are the main components of coroutines? Describe their role
3) How the main components of coroutines depend on each other
4) Coroutine exceptions handling
5) Why is it forbidden to catch *CancellationException*?
6) What is suspension point? How the coroutines state machine divides code?
7) How continuation.resume() set parameters to next continuation? TODO
8) Difference between **async** and **launch**
9) Job types
10) *Join*, *JoinAll*, *Await*, *AwaitAll*
11) What is **CoroutineStart**? Which types do you know?
12) How to cancel coroutine? What is ensureActive?
13) How to put custom data to *CoroutineContext*?
##### [CoroutinesPracticeQuestions](src/coroutines/CoroutinesPracticeQuestions.kt)
##### [CoroutinesPracticeAnswers](src/coroutines/eng/CoroutinesPracticeAnswers_eng.md)


### [Compose](src/compose/ru/ComposeTheory_ru.md)
1) What is recomposition?
2) *remember*, *remember(key)*, *rememberSaveable*, *remember{derivedStateOf()}* difference
3) What is **SideEffect** for?
4) Effect types
5) State types
6) What is the **@Stable** annotation for?

### [Kotlin](src/compose/ru/ComposeTheory_ru.md)
1) Is it possible to inherit from data class? Why?
2) What is *inline/noinline/crossinline*? What are the benefits of using? Why aren't they used all the time? 
3) When can't we use *inline*? What is a non-local return?
4) What is *reified*? What is the advantage of using with inline?
5) How many parameters in a constructor can an *inline class* have? Why?
6) Contravariance, covariance, invariance
7) Difference between Nothing, Unit and Any
8) What are *delegates*?
9) What does _typealias_ compile to?





