#### Is there a limit on the size of the bundle?

When used to pass information between Android components the bundle is serialized into a binder transaction.
The Binder transaction buffer has a limited fixed size, currently 1MB, which is shared by all transactions in progress for the process. 
Since this limit is at the process level rather than at the per activity level, these transactions 
include all binder transactions in the app such as onSaveInstanceState, startActivity and any interaction with the system. 
When the size limit is exceeded, a TransactionTooLargeException is thrown.

For the specific case of savedInstanceState, the amount of data should be kept small because 
the system process needs to hold on to the provided data for as long as the user can ever navigate back to that activity 
(even if the activity's process is killed). Recommended size less than 50k.

***
#### What is the Binder transaction?
Binder is the main IPC/RPC (Inter-Process Communication) system in Android. 
It allows applications to communicate with each other, and it is the base of several important mechanisms in the Android environment. 
For instance, Android services are built on top of Binder.
Message exchanged with Binder are called binder transactions, they can transport simple data 
such as integers but also process more complex structures like file descriptors, 
memory buffers or weak/strong references on objects.

Все происходит через байндер

***
#### Как данные могут передаваться в обход Binder

***
#### What processor does Android use?
[Link](https://habr.com/ru/post/140459/)

***
#### Why we don't use JVM on Android? 
[Link](https://towardsdatascience.com/jvm-vs-dvm-b257229d18a2)


***
#### Как вырезать фигуру из вьюшки

***
#### Dagger/Hilt vs Koin
[Link](https://proandroiddev.com/how-dagger-hilt-and-koin-differ-under-the-hood-c3be1a2959d7)

***
#### ServiceLocator vs DI

***
#### What is *Zygote* ?

***
#### Difference between *Dalvik* and *ART* ? What is Profile-Guided Compilation?
Dalvik - JIT
ART- AOT
JIT - takes less RAM, but runtime is much slower
AOT - takes a lot of RAM, but runtime works is 20 time more efficient
Profile-Guided Compilation - JIT, but if application is frequently uses AOT
[Link](https://www.youtube.com/watch?v=0J1bm585UCc)


***
#### Самая ранняя точка входа в приложение? 
Content provider ??

***
#### Отличия контекстов
 
***
#### Приоритеты процессов 

***
#### Мы обновили приложение, хранили Serializable и Parcelable. Добавили новое поле, как поддержать изменение?
Parcelable: переопределить writeToParcel
Serializable: переопределить serialVersionUID -> Позволит выбросить ошибку

Можно сделать ручную сериализацию:
Для Serializable : writeObject - readObject.
Можно использовать Externalizable - принцип тот же что и у Parcelable, работает значительно быстрее чем Serializable, 
но медленее чем Parcelable.

[Link](https://www.youtube.com/watch?v=tko54cjc79U)

***
#### Жизненный цикл view. Когда при invalidate() не вызовется onDraw(). Всегда ли отработает requestLayout()? 
![img.png](viewLifecycle.png)

***
#### Как избежать повторной синхронизации если предыдущая еще не закончилась? (например: пользователь зашел на экран, сразу же вышел и быстро вернулся обратно)
идемпотентность

***
#### Разница между commit и commitAllowStateLoss


***
#### Как реализовать viewModel с нуля? Как не создать ее дважды? Когда создавать, когда уничтожать? 


***
#### Как реализовать mvi?



***
#### Основные компоненты в DI?

***
#### Как работает создаение Scope компонента под капотом?



***
#### Когда луче использовать svg, png, webp
если больше 200*200 - svg проседает в отрисовке раз в 10.
при конвентрировани png в webp мы почти не теряем в качестве, но размер сильно уменьшается

***
#### Различия в работе glide, picasso, koil
 

***
#### Для чего нужны PrecomputedTextView, SpannableTextView
 

***
#### Для чего нужен recycler view pool 

***
#### Отличие LongPolling от WebSocket
[Link](https://ably.com/blog/websockets-vs-long-polling#:~:text=Long%20polling%20is%20more%20resource,hops%20between%20servers%20and%20devices.)

 
***
#### Как реализовать кэширование?

 
***
#### Как андроид под капотом отрисовывает интерфейс?


***
#### Как реализовать кэширование?

 


