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
[Link2](https://www.youtube.com/watch?v=duO5qgn2DO8)


***
#### Как вырезать фигуру из вьюшки

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
// todo 

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
#### Как запретить активити уничтожаться при повороте экрана?
configChanges
 ```
<activity
android:name=".MyActivity"
android:label="@string/title_my_activity"
android:configChanges="orientation|screenSize|keyboardHidden" />
 ```
Этот флаг сообщает платформе Android, что вы собираетесь вручную обрабатывать изменения ориентации, размера экрана и внешнего вида/исчезновения клавиатуры для этой активити. 
Таким образом, вместо того, чтобы уничтожать и воссоздавать вашу активити, Android просто повернет экран и вызовет один из методов 
жизненного цикла: onConfigurationChanged. Если у вас есть фрагмент, прикрепленный к этой активити, он также получит вызов своего метода onConfigurationChanged. 
Это означает, что будут использоваться одни и те же экземпляры ваших активити и фрагментов, а ваши переменные останутся нетронутыми.
 
***
#### Что такое zRam?


***
#### What is the difference between low memory killer and out of memory killer?
Main difference is how LMK and OOM chooses a process to kill in low memory conditions.

Main reason for introduction of LMK in android was OOM killer sometimes kill high priority process (Like foreground applications) in low memory conditions, on the other hand LMK has interface (oom score value) with activity manager (framework part) which knows priority of processes this results LMK always kill hidden and empty applications before killing foreground and active applications, apart from this some differences are below

***
#### Расскажите про версии garbage collector в Android
1) Dalvik GC: the first GC implementation. It was a conservative, “stop the world” GC. It stops all the threads in the VM and does its work.
2) ART GC (Lollipop & Marshmallow): the major and biggest change. The ART/Dalvik Android Team rewrites the entire GC. This GC was called the “Generational GC” because the objects now have “generations” based on the time they live. Several other big improvements were introduced here, including the way that GC allocates objects.
3) ART GC (Nougat): the ART/Dalvik Android Team rewrites the entire allocation process in assembly code.
4) ART GC (Oreo): an improvement of the ART GC v1. This time, the ART/Dalvik Android Team improves the way that GC does its work by making it concurrent. This was called the “Concurrent Copying Garbage Collector” among a lot of other improvements.
[Link](https://www.youtube.com/watch?v=Cficzcp0ynU)
[Link2](https://proandroiddev.com/collecting-the-garbage-a-brief-history-of-gc-over-android-versions-f7f5583e433c)

***
#### Расскажите подробнее про Dalvik GC
Освобождение памяти проходит в 4 этапа:
1) Сборщик мусора приостанавливает все потоки в системе, чтобы найти все объекты доступные от root. Это требует времени, и за это время ваше приложение ничего не может сделать.
2) Следующее действие происходит параллельно. GC помечает все найденные объекты.  Это означает, что ваше приложение снова работает, но параллелизм приводит к проблеме. Поскольку ваше приложение снова запущено, оно может выделять объекты.
3) Когда происходит новое выделение, GC снова приостанавливает все потоки, чтобы повторить пункт 1.
4) Этап сборки объектов, не помеченных как активных. Происходит параллельно

Dalvik позволил процессу увеличиваться только до 36 МБ в памяти (в зависимости от конкретной конфигурации устройства)

***
##### Расскажите подробнее про ART GC (Lollipop & Marshmallow)
1) Больше нет ограничения по памяти для процесса, ART не имеет ограничений на объем памяти, который может запросить процесс.
2) Алгоритм выделения и освобождения памяти по-прежнему CMS (Concurrent Mark-and-Sweep), но с улучшениями.
Одним из самых больших улучшений в способе размещения объектов сборщиком мусора является замена алгоритма dlmalloc алгоритмом RosAlloc. Эти алгоритмы используются, когда вы создаете новый объект. 
Отличительной особенностью RosAlloc является то, что он поддерживает потоки, а это означает, что он может выполнять распределения, специфичные для конкретного потока.
3) Добавлены *fine-grained* блокировки, благодаря которым GC блокирует гораздо меньше кода. А также делает 1-ю фазу (поиск доступных от корня объектов) алгоритма пометки и очистки параллельной, 
что сокращает время, необходимое для запустите алгоритм маркировки и очистки от ~ 10 мс до ~ 3 мс.
4) Добавлены *GC generations*. *Young*, *Old*, *Permanent*. Первоначально все объекты помещаются  в *Young*, но с течением времени, если прожили достаточно долго переходят в более старые.
Если мы хотим создать объект, а памяти не хватает, GC  в первую очередь освободит память в *Young*, 
и только если там нечего освобождать пойдет в *Old*.

***
##### Расскажите подробнее про ART GC (Nougat)
1) Полностью переписали выделение памяти сборщиком мусора в ассемблерном коде, 
что позволило сделать выделение памяти в 10 раз быстрее,
***
##### Расскажите подробнее про ART GC (Oreo)
1) Опять полностью переписали GC и назвали  его *Concurrent Heap Compaction Collector*.
    1) Оптимизировали дефрагментацию, что позволило оптимизировать работу с памятью на 30% 
    2) В этой версии сборщик мусора делит кучу на «сегменты». Когда потоку требуется память, сборщик мусора предоставляет этому потоку «сегмент» для локального выделения объектов. Таким образом, поток может локально выделять или собирать объекты в этом сегменте, избегая блокировки всей системы для выполнения этой работы.
    3) Взаимовытекающее из предыдущего: при дефрагментации сегмента, если он используется менее 70% или 75%, этот "сегемент" будет собран, а находящиеся там объекты будут перемещены в другой сегмент.

За счет этого, время выделения в 18 раз быстрее, чем у Dalvik, и на 70% быстрее, чем у Nougat.
2) Убрали Generation GC :)

***
#### Расскажите подробнее про GC Андроид 10 (Q)
1) Вернули Generation GC :) 

***
#### Как происходит запуск приложения 
// todo
![Image](app_launch.png)


***
