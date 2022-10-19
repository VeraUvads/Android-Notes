package coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**1) If we run this code on a quad-core processor, what should we see? **/
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
class Deferred1 {
    init {
        start()
    }

    private fun start() = runBlocking() {
        val deffers = mutableListOf<Deferred<String>>()
        repeat(6) {
            val deffer = async(start = CoroutineStart.LAZY) {
                delay(2000)
                "String $it".also {
                    println(it)
                }
            }
            deffers.add(deffer)
        }
        deffers.map { it.await() }
    }

}

fun main() {
    Deferred1()
}


