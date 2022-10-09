package coroutines

/*
1) Coroutine exceptions handling

2) How coroutines state machine will divide this code?

    launch {
        val param = buildParam()

        doSmth1(param) // suspend function

        toast("First task completed")

        doSmth2() // suspend function

        toast("Second task completed")
    }

3) How continuation.resume set parameters to next suspend fun?

* */