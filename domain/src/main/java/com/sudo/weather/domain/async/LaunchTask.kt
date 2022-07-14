package com.sudo.weather.domain.async

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public fun launchTask(
    context: CoroutineContext = EmptyCoroutineContext,
    scope: CoroutineScope? = null,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return scope?.launch(block = block)
        ?: CoroutineScope(context).launch(block = block)
}