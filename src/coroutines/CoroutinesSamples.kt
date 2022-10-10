package coroutines

import java.util.concurrent.CancellationException

// Beauty way for coroutine exceptions handling

val Throwable.isCancelException: Boolean
    get() = this is CancellationException || this is CancellationException

@SinceKotlin("1.3")
inline fun <T> Result<T>.onFailureSafe(
    action: (exception: Throwable) -> Unit
): Result<T> {
    exceptionOrNull()?.let { if (it.isCancelException) throw it else action(it) }
    return this
}
