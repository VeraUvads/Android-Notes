# Android-Notes

### [Coroutines](src/coroutines/CoroutinesQuestions.md)

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
11) What is the difference between **deferreds.map { it.await() }** and **deferreds.awaitAll()**.
12) What is **CoroutineStart**? Which types do you know?
13) How to cancel coroutine? What is ensureActive?
14) How to put custom data to *CoroutineContext*?

### [Common](src/common_android/CommonAndroid.md)

1) Is there a limit on the size of the bundle?
2) What is the Binder transaction?

### [Compose](src/compose/ComposeQuestions.md)
1) What is recomposition?
2) *remember*, *remember(key)*, *rememberSaveable*, *remember{derivedStateOf()}* difference
3) What is **SideEffect** for?
4) Effect types
5) State types
6) What is the **@Stable** annotation for?


