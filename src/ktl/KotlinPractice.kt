package ktl


fun main() {
    threadsDeadLock()
}

fun threadsDeadLock() {
    val firstThread = FirstThread()
    val secondThread = SecondThread()
    firstThread.start()
    secondThread.start()
}

var Lock1 = Any()
var Lock2 = Any()

class FirstThread : Thread() {
    override fun run() {
        synchronized(Lock1) {
            println("Thread 1: Holding lock 1");
            sleep(10)
            println("Thread 1: after sleep");
            synchronized(Lock2) {
                println("Thread 1: Holding lock 1 & 2...");
            }
        }
    }
}

class SecondThread : Thread() {

    override fun run() {
        synchronized(Lock2) {
            println("Thread 2: Holding lock 2");
            sleep(10)
            println("Thread 2: after sleep");
            synchronized(Lock1) {
                println("Thread2: Holding lock 1 & 2...");
            }
        }
    }
}