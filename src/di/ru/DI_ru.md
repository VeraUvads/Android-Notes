#### Dagger vs Koin

[Link](https://proandroiddev.com/how-dagger-hilt-and-koin-differ-under-the-hood-c3be1a2959d7)

***

#### ServiceLocator vs DI

***

#### Основные компоненты в DI?

*Module* - это классы, в которые мы выносим код создания объектов. Обычно каждый модуль включает в себя создание
объектов близких по смыслу.

*Component* - это посредник между Activity и модулями. Когда Activity нужен какой-либо объект, она сообщает об этом
компоненту. Компонент знает, какой модуль умеет создавать такой объект, просит модуль создать объект, и передает его в
Activity. При этом компонент может использовать другие модули, чтобы создать всю иерархию объектов, необходимую для
создания искомого объекта.

*Dependencies* -

***

### Dagger

#### Аннотации в Dagger

*@Module* - сообщаем даггеру, что этот класс является модулем, который вносит свой вклад в граф объектов. Модуль
предоставляет зависимости. Все includes последовательно вносятся в граф объектов. Модуль содержит в себе информацию КАК
создать объект.

*@Component* - сообщаем даггеру, что этот класс является компонентом. Компонент — это единица, которая в основном
используется для разрешения зависимостей. Посредник между классом который запрашивает и использует зависимость, и тем
кто его создает. Компонент знает, какой модуль умеет создавать нужный объект, просит модуль создать объект, и передает
его в Activity. При этом компонент может использовать другие модули, чтобы создать всю иерархию объектов, необходимую
для создания искомого объекта.

*@Subcomponent* - сообщаем даггеру, что этот класс является сабкомпонентом. Это вид компонента, производный от
компонента и наследующий зависимости, которые он предоставляет. Позволяет логически разделить и обособить логику.

*@Subcomponent.Builder/@Component.Builder* - помечаем как создать компонент/сабкомпонент

*@Provides* - указывает, что метод является поставщиком объекта.

*@Inject* - помечает, что в свойство должна доставиться зависимость.

*@AssistedInject* - помечает, что не все запрашиваемые параметры есть в графе. Такие параметры мы помечаем как @Assisted

*@Binds* - привязывание одного типа к другому

*@BindsInstance* - помечает метод компонента как привязку экземпляра к некоторому ключу в компоненте.

Example:

 ```Kotlin
class AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
    }
}

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }
}
 ```

*@Reusable* - позволяет эффективнее хранить объекты, не хранит объект все время в памяти (в отличии от *@Singleton*).
Вместо этого временно сохраняет экземпляр в памяти, позволяя не создавать каждый раз новый экземпляр при обращении, но
не гарантирует что объект всегда будет один.

*@Scope* -

***

#### Как работает создаение Scope компонента под капотом?

***

#### Почему Dagger Hilt не стоит использовать для многомодульности
Работает на subcomponents

***

#### Lazy vs Scope?

Lazy - создание и хранение внутри контейнера в котором вызван

Scope - хранилище на уровне компонента который будет жить намного дольше

***

#### В чем минус Subcomponent? Как разделить логику компонента без использования subcomponent?

Subcomponent имеет очень жесткую связь с компонентом, родитель всегда четко должен знать про свои сабкомпоненты. При
генерации кода он вкладывает все класы самкомпонентов внутрь класса компонента. Такой подход не подойдет для
модуляризации.

[//]: # (ПОЧЕМУ? )

Для разделения логики можем использовать два component

1) Когда один модуль и мы видим appComponent из любой точки кода, можем использовать AppComponent в качестве зависимости
   для FeatureComponent

 ```Kotlin
@Component
interface AppComponent

@Component(dependencies = [AppComponent::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder

        fun build(): FeatureComponent
    }
}
 ```

Тогда создание FeatureComponent сводится к

 ```Kotlin
val featureComponent = DaggerFeatureComponent.builder()
    .appComponent(appComponent)
    .build()
 ```

Мы должны передать ему экземпляр компонента AppComponent. Только так FeatureComponent сможет получить доступ к объектам AppComponent. 
Он просто будет просить их у этого компонента.

И вот в этом кроется разница между отношениями компонент-сабкомпонент и компонент-компонент.
Когда мы к компоненту добавляем сабкомпонент, то компонент сам создает реализацию этого сабкомпонента. 
В эту реализацию он передает свои модули. И сабкомпонент сам может в этих модулях найти все, что ему понадобится. 
Т.е. сабкомпоненту неважно, какие объекты прописаны в интерфейсе компонента. 
Сабкомпонент лезет напрямую в модули, минуя компонент.

А вот в случае пары компонент-компонент связь не такая тесная. 
Компоненты создаются отдельно друг от друга. Они общаются на уровне интерфейсов и не имеют доступа к модулям.

2) Когда (к примеру) AppComponent лежит в app модуле, а FeatureComponent в feature модуле, и у нас нет доступа к
   AppComponent.

 ```Kotlin

interface AppComponent : FeatureDeps {

    override fun application(): Application

    @Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}

interface FeatureDeps {
    fun application(): Application
}

@Component(dependencies = [AppComponent::class])
interface FeatureComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appComponent(appComponent: AppComponent): Builder
        fun build(): FeatureComponent
    }
}
 ```



***
