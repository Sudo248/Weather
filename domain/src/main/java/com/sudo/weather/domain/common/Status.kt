package com.sudo.weather.domain.common

/**
 * Describe the process of retrieving data from datasource(local or remote)
 *
 * It also describes the state of the screen
 *
 *  [Idle]: nothing, it also is init state
 *
 *  [Loading]: process of retrieving data is not complete
 *
 *  [Success]: process of retrieving data complete and return data
 *
 *  [Error]: process of retrieving data complete and return error
 */

sealed class Status<out S>{
    object Idle : Status<Nothing>()
    object Loading : Status<Nothing>()
    data class Success<out T> (val data: T) : Status<T>()
    data class Error<out Ex : Exception>(val error: Ex) : Status<Nothing>()

    /**
     * Returns `true` if the object is of the [Loading] type, which means
     *
     * `data` is fetching from remote or database.
     */
    val isLoading: Boolean
        get() = this is Loading

    /**
     * Returns `true` if the object is of the [Success] type, which means
     *
     * `data` will return a valid result.
    */
    val isSuccess: Boolean
        get() = this is Success<S>

    /**
     * Returns `true` if the object is of the [Error] type, which means
     *
     * `error` will return a valid result.
     */
    val isError: Boolean
        get() = this is Error<Exception>

    fun data(): S? = if(isSuccess) (this as Success<S>).data else null

    fun requireData(): S = (this as Success<S>).data

    fun <Ex : Exception> error(): Ex? = if(isError) (this as Error<Ex>).error else null

    override fun toString(): String {
        return when(this){
            is Success -> "Success(${this.data})"
            is Error<Exception> -> "Error: ${this.error}"
            is Loading -> "Loading"
            else -> "Idle"
        }
    }

}
