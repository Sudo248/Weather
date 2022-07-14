package com.sudo.weather.domain.async

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun <T> asyncTask(
    context: CoroutineContext = EmptyCoroutineContext,
    scope: CoroutineScope? = null,
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return scope?.async( block = block)
        ?: CoroutineScope(context).async(block = block)
}