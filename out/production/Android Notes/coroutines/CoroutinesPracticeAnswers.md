
#### 1) If we run Dispatcher1 on a quad-core processor, what should we see?
By default, the maximum number of threads used by this dispatcher is equal
to the number of CPU cores.
Coroutines 0,1,2,3 will start immediately. All threads are busy. 
Coroutines 4,5 coroutines will be launched when two the previous one finish their work and release threads.


