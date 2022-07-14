package com.sudo.weather.domain.async

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun launchHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    scope: CoroutineScope? = null,
    handleException: ((e: Exception) -> Exception)? =  null,
    finally: (() -> Unit)? = null,
    block: suspend CoroutineScope.() -> Unit
): Job {
    val coroutineContext = scope?.coroutineContext ?: context
    val supervisor = SupervisorJob()
    val blockHandler: suspend CoroutineScope.() -> Unit = {
        try {
            block()
        } catch (e: Exception) {
            if(!supervisor.isCancelled) supervisor.cancel()
            throw handleException?.invoke(e) ?: e
        }finally {
            finally?.invoke()
        }
    }

    return scope?.launch(coroutineContext + supervisor, block = blockHandler)
        ?: CoroutineScope(coroutineContext + supervisor).launch(block = blockHandler)
}