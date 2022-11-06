package coroutines

import kotlinx.coroutines.*
import utils.Activity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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

    private fun start(dispatcher: CoroutineDispatcher) = runBlocking {
        val jobList = mutableListOf<Job>()
        val scope = CoroutineScope(
            dispatcher
        )

        repeat(6) {
            val job = scope.launch {
                println("coroutine $it, start $dispatcher")
                TimeUnit.MILLISECONDS.sleep(100)
                println("coroutine $it, end $dispatcher")
            }
            jobList.add(job)
        }
        jobList.joinAll()
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

fun main() {
    Deferred1()
}


/**3) Fix the code **/
/**3) Исправьте код **/
class Lifecycle1 {
    init {
        MainActivity()
    }

    internal class MainActivity: Activity() {
        private val viewModel = ViewModel()

        override fun onCreate() {
            onClickToSmth {
                lifecycleScope.async { viewModel.callApi() }
            }
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


/**4) Write code with coroutines deadlock **/
/**4) Напишите код который приведет к deadlock **/
