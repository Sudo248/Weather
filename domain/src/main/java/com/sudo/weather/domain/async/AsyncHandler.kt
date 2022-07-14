package com.sudo.weather.domain.async

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun <T> asyncHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    scope: CoroutineScope? = null,
    handleException: ((e: Exception) -> Exception)? =  null,
    finally: (() -> Unit)? = null,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    val coroutineContext = scope?.coroutineContext ?: context
    val supervisor = SupervisorJob()

    val blockHandler: suspend CoroutineScope.() -> T = {
        try {
            block()
        } catch (e: Exception) {
            if(!supervisor.isCancelled) supervisor.cancel()
            throw handleException?.invoke(e) ?: e
        }finally {
            finally?.invoke()
        }
    }

    return scope?.async(coroutineContext + supervisor, block = blockHandler)
        ?: CoroutineScope(coroutineContext + supervisor).async(block = blockHandler)
}




