# Android-Notes (in  progress)

RUS

### [Список вопросов](QuestionsPool_ru.md)
Список вопросов по темам о разработке на андроид, корутинам и compose.

### Kotlin
1) Разница между *class* и *data class*,  *data object* и *object*
2) Способы реализовать функциональный тип
3) Разница между *0 until 10*, *0..10* and *0..<10*
4) Что такое inline/noinline/crossinline? Какие плюсы от использования? Почему не использовать их постоянно? Когда мы не можем использовать inline? Что такое non-local-return?
5) Что такое reified? В чем плюс использования с inline?
6) Сколько параметров в конструкторе может иметь inline class? Почему?
7) Контравариантность, ковариантность, инвариантность
8) Разница между Nothing, Unit и Any
9) Можно ли наследоваться от data class? Почему?
##### [Ответы по теории Kotlin](src/ktl/KotlinTheory_ru.md)


### Coroutines

1) Разница между корутинами тредами
2) Какие основные составляющие компоненты корутин вы знаете. Опишите их роль
3) Как основные компоненты корутин зависят друг от друга
4) Расскажите про обработку исключений
5) Что такое suspension points?
6) Разница между async и launch
7) Виды Job
8) Join, JoinAll, Await, AwaitAll
9) В чем разница между deferreds.map { it.await() } and deferreds.awaitAll()
10) Что такое CoroutineStart? Какие типы бывают?
11) Что такое ensureActive? 
12) Как поместить дополнительные данные в CoroutineContext?
13) Напишите код который приведет к deadlock
14) Как отменяются скоупы при выбросе ошибки в дочернем скоупе? 
15) Что такое Flow? Когда мы должны его использовать?
16) Что такое CoroutineDispatcher? В каких случаях какой использовать?

##### [Ответы по теории Coroutines](src/coroutines/ru/CoroutinesTheory_ru.md)
##### [Вопросы по практике Coroutines](src/coroutines/CoroutinesPracticeQuestions.kt)
##### [Ответы по практике Coroutines](src/coroutines/eng/CoroutinesPracticeAnswers_eng.md)

### Compose
1) Что такое рекомпозиция
2) remember, remember(key), rememberSaveable, remember { derivedStateOf() } различия
3) Что такое Side-Effect?
4) Какие виды Side-Effect бывают?
5) Что такое Snapshot Policy? 
6) Переиспользует ли LazyColumn элементы по аналогии с RecyclerView?
7) Сохранит ли _by remember {}_ свое значение при повороте экрана?
8) Что значит поднятие состояния (state hoisting)?
9) Способы сохранения состояния при смене конфигурации 
10) Жизненный цикл composable
11) Можно ли передавать viewModel в дочерние composable функции?
12) Как избежать вызова рекомпозиции всех элементов списка при добавлении одного нового элемента?
13) За что отвечает аннотация @Stable
14) Как добавить отступы между элементами списка?
##### [Ответы по теории Compose](src/compose/eng/ComposeTheory_eng.md)

### Android
1) Лимит на размер bundle?
2) Что такое Binder транзакция?
3) Как данные могут передаваться в обход Binder
4) Какой процессор использует Android?
5) Зачем нужен Dalvik/Art вместо JVM?
6) Разница между Dalvik и Art
7) Самая ранняя точка входа в приложение?
8) Отличия контекстов
9) Приоритеты процессов
10) Мы обновили приложение, хранили Serializable и Parcelable. Добавили новое поле, как поддержать изменение?
11) Жизненный цикл view. Когда при invalidate() не вызовется onDraw(). Всегда ли отработает requestLayout()?
12) Когда луче использовать svg, png, webp
13) Различия в работе glide, picasso, koil
14) Отличие LongPolling от WebSocket
15) Как андроид под капотом отрисовывает интерфейс?
16) Как запретить активити уничтожаться при повороте экрана?
17) Разница между low memory killer и out of memory killer?
18) Расскажите про версии garbage collector в Android
19) Как происходит запуск приложения
20) Что такое Zygote?
21) Разница между targetSDK и compileSdk 
22) Как происходит компиляция приложения
23) Что такое процесс в Android
24) Что такое App Sandbox 
25) Может ли BroadcastReceiver быть запущен без объявления в манифесте? 
26) Виды сервисов 
27) Отличие IntentService, Service, JobIntentService, JobService
28) За что отвечают Content resolver и Content Provider
29) Что такое PendingIntent? 
30) Если создать два Pending Intent отличные только по данным помещенным в data, с какой ошибкой можно столкнуться?
31) Когда можно сохранять state чтобы гарантированно восстановить его даже в случае если андроид убьёт приложение?
32) Какие launch mode существуют?
##### [Ответы Android](src/common_android/eng/CommonAndroid_eng.md)


### DI
1) Dagger/Hilt vs Koin
2) ServiceLocator vs DI 
3) Основные компоненты в DI
###### Dagger
1) Аннотации в Dagger
2) Как работает создаение Scope компонента под капотом?
3) Почему Hilt не стоит использовать для многомодульности
4) Lazy vs Scope?
5) В чем минус Subcomponent? Как разделить логику компонента без использования subcomponent?

##### [Ответы DI](src/di/ru/DI_ru.md)

### Multithreading

1) В чем отличие потока от процесса
2) Какую функцию выполняет Handler?

##### [Ответы Multithreading](src/multithreading/ru/Multithreading_ru.md)
***

ENG

### [QuestionsPool](QuestionsPool_eng.md)
Full questions list for Coroutines, Compose, Common Android topics
`

### Coroutines
##### [CoroutinesTheory](src/coroutines/eng/CoroutinesTheory_eng.md)
##### [CoroutinesPracticeQuestions](src/coroutines/CoroutinesPracticeQuestions.kt)
##### [CoroutinesPracticeAnswers](src/coroutines/eng/CoroutinesPracticeAnswers_eng.md)

### Compose
##### [ComposeTheory](src/compose/eng/ComposeTheory_eng.md)

### Android
##### [AndroidTheory](src/common_android/eng/CommonAndroid_eng.md)

****
