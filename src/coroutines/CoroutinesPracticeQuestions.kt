package coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

// Answers here ( https://github.com/VeraUvads/Android-Notes/blob/18127f03c878f8717f844c4809e0308fd192c7b6/src/coroutines/eng/CoroutinesPracticeAnswers_eng.md )
// Ответы по ссылке  ( https://github.com/VeraUvads/Android-Notes/blob/18127f03c878f8717f844c4809e0308fd192c7b6/src/coroutines/ru/CoroutinesPracticeAnswers_ru.md )

/**1) If we run this code on a quad-core processor, what should we see? **/
/**1) Если мы запустим этот код на четырех ядерном процессоре, что интересного мы увидим? **/

class Dispatcher1 {
    init {
        start(Dispatchers.Default)
        start(Dispatchers.IO)
    }

    private fun start(coroutineContext: CoroutineContext) = runBlocking() {
        repeat(6) {
            launch(coroutineContext) {
                println("coroutine $it, start")
                delay(100)
                println("coroutine $it, end")
            }
        }
    }
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

/**3) What is wrong with this code? **/
/**3) Какая ошибка допущена? **/
class Lifecycle1 {
    init {
        MainActivity()
    }


    internal class MainActivity { // trust me, this is real Activity
        private val lifecycleScope = CoroutineScope(Job())
        private val viewModel = ViewModel()

        init {
            onCreate()
        }

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
        suspend fun callApi() {
            println("Job started")
            delay(2000)
            println("Job finished")
        }
    }

}


fun main() {
    Lifecycle1()
}