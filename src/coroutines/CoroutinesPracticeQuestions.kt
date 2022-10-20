package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

// Answers here ( https://github.com/VeraUvads/Android-Notes/blob/18127f03c878f8717f844c4809e0308fd192c7b6/src/coroutines/eng/CoroutinesPracticeAnswers_eng.md )
// Ответы по ссылке  ( https://github.com/VeraUvads/Android-Notes/blob/18127f03c878f8717f844c4809e0308fd192c7b6/src/coroutines/ru/CoroutinesPracticeAnswers_ru.md )

/**1) If we run this code on a quad-core processor, what should we see? **/
/**1) Если мы запустим этот код на четырех ядерном процессоре, что интересного мы увидим? **/

class Dispatcher1 {
    init {
        start(Dispatchers.Default)
        start(Dispatchers.IO)
        start(Executors.newSingleThreadExecutor().asCoroutineDispatcher())
    }

    private fun start(dispatcher: CoroutineContext) = runBlocking {
        val jobList = mutableListOf<Job>()
        val scope = CoroutineScope(
            dispatcher
        )

        repeat(6) {
            val job = scope.launch {
                println("coroutine $it, start")
                TimeUnit.MILLISECONDS.sleep(100)
                println("coroutine $it, end")
            }
            jobList.add(job)
        }
        jobList.joinAll()
    }
}

fun main() {
    Dispatcher1()
}

/**2) What is wrong with this code? **/
/**2) Какая ошибка допущена? **/
class Deferred1 {
    init {
        start()
    }

    private fun start() = runBlocking() {
        val deferredList = mutableListOf<Deferred<String>>()
        repeat(6) {
            val deffer = async(start = CoroutineStart.LAZY) {
                delay(2000)
                "String $it".also {
                    println(it)
                }
            }
            deferredList.add(deffer)
        }
        deferredList.map { it.await() }
    }

}

/**3) Fix the code **/
/**3) Исправьте код **/
class Lifecycle1 {
    init {
        MainActivity()
    }


    internal class MainActivity { // trust me, this is real Activity
        init {
            onCreate()
        }

        private val lifecycleScope = CoroutineScope(Dispatchers.Default)
        private val viewModel = ViewModel()

        private fun onCreate() {
            onClick {
                lifecycleScope.async {
                    viewModel.callApi()
                }
            }
        }

        // ignore that fun
        private fun onClick(action: suspend () -> Deferred<Unit>) = runBlocking {
            action().await()
        }
    }


    internal class ViewModel {
        private val viewModelScope = CoroutineScope(Dispatchers.Default)
        suspend fun callApi() {
            println("Job started")
            delay(2000)
            println("Job finished")
        }
    }
}

