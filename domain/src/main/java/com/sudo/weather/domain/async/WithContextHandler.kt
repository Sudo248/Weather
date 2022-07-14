package com.sudo.weather.domain.async

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public suspend fun <T> withContextHandler(
    context: CoroutineContext = EmptyCoroutineContext,
    scope: CoroutineScope? = null,
    handleException: ((e: Exception) -> Exception)? = null,
    finally: (() -> Unit)? = null,
    block: suspend CoroutineScope.() -> T
): T {
    val coroutineContext = scope?.coroutineContext ?: context

    return withContext(coroutineContext) {
        try {
            block()
        } catch (e: Exception) {
            throw handleException?.invoke(e) ?: e
        }finally {
            finally?.invoke()
        }
    }
}