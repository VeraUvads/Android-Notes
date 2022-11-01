package ktl

import com.sun.org.apache.xpath.internal.operations.Or
import kotlinx.coroutines.*

fun main() {
//    threadsDeadLock()
//    coroutinesDeadlock1()
//    coroutinesDeadlock2()
    variance()
}

fun variance() {
    var fruit = Fruit()
    var orange = Orange()

    fruit = orange
//    orange = fruit // forbidden

    var orangeList = arrayListOf<Orange>()
    var fruitList = arrayListOf<Fruit>()
    var fruitList2 : ArrayList<out Fruit> = orangeList
    var orangeList2 : ArrayList<in Orange> = fruitList
//    fruitList = orangeList // forbidden
//    orangeList = fruitList // forbidden

    doSmth(fruitList)
    doSmth2(orangeList)
    val list = mutableListOf<Orange>()
    list.sortBy { it.hashCode() }
}

fun doSmth(orangeList : ArrayList<in Orange>){
    orangeList.forEach{

    }
   val orange : Any? = orangeList[0] // не даст вернуть Orange, только Any
}

fun doSmth2(orangeList : ArrayList<out Fruit>){
    orangeList.forEach{

    }
//    orangeList.add(Orange())  не даст ничего добавить потому что тип использующий out == Nothing
}

fun coroutinesDeadlock1() {
    var first: Job = Job()
    var second: Job = Job()
    runBlocking() {
        first = launch(start = CoroutineStart.LAZY) {
            println("first before join")
            second.join()
            println("first after join")
        }
        second = launch(start = CoroutineStart.LAZY) {
            println("second before join")
            first.join()
            println("second after join")
        }
        joinAll(first, second)
    }
}


fun coroutinesDeadlock2() {
    var third: Job = Job()
    runBlocking() {
        third = launch() {
            println("third before join")
            third.join()
            println("third after join")
        }
    }
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


open class Fruit

class Orange : Fruit()