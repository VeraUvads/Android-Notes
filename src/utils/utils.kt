package utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

abstract class Activity{
    protected val lifecycleScope = CoroutineScope(Dispatchers.Default)
    init { onCreate() }

    abstract fun onCreate()

    protected fun onClickToSmth(action: suspend () -> Deferred<Unit>) = runBlocking {
        action().await()
    }
}