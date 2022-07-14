package com.sudo.weather.domain.common

/**
 * Describe the reason for the failure
 *
 * [message] description reason for failure
 * */
sealed class Failure(override val message: String) : Exception(message) {
    /**
     * Default error
     * */
    data class DefaultError(override val message: String = "Default error") : Failure(message)

    /**
     * Failure permission: require permission
     * */

    data class RequirePermission(override val message: String = "Required permission") : Failure(message)

    /**
     * Failure socket: no internet connection.
     * */
    data class NoInternetConnection(override val message: String = "No internet connection") :
        Failure(message)

    /**
     * Api Format Exception
     * */
    data class ApiFormatException(override val message: String = "Api format exception") :
        Failure(message)

    /**
     * Unable to process the data
     * */
    data class UnableToProcess(override val message: String = "Unable to process the data") :
        Failure(message)

    /**
     * Unexpected error occurred
     *
     * When unknown exactly reason caused Failed
     * */
    data class UnexpectedError(override val message: String = "Unexpected error occurred") :
        Failure(message)

    /**
     * ApiFailure is Failure when client network call.
     * */
//    data class ApiFailure(val statusCode: Int, override val message: String): Failure(message)

    sealed class ApiFailure(open val statusCode: Int, override val message: String) :
        Failure(message) {

        companion object {
            fun getInstance(statusCode: Int, message: String? = null): ApiFailure {
                return when (statusCode) {
                    400 -> if (message != null) BadRequest(message = message) else BadRequest()
                    401 -> if (message != null) UnauthorisedRequest(message = message) else UnauthorisedRequest()
                    404 -> if (message != null) NotFound(message = message) else NotFound()
                    405 -> if (message != null) MethodNotAllowed(message = message) else MethodNotAllowed()
                    406 -> if (message != null) NotAcceptable(message = message) else NotAcceptable()
                    408 -> if (message != null) RequestTimeout(message = message) else RequestTimeout()
                    409 -> if (message != null) Conflict(message = message) else Conflict()
                    499 -> if (message != null) RequestCancelled(message = message) else RequestCancelled()
                    500 -> if (message != null) InternalServerError(message = message) else InternalServerError()
                    501 -> if (message != null) NotImplemented(message = message) else NotImplemented()
                    503 -> if (message != null) ServiceUnavailable(message = message) else ServiceUnavailable()
                    else -> Unknown(statusCode = statusCode, message = message ?: "Unknown")
                }
            }
        }

        /**
         * [499 Client Closed Request Used]
         *
         * when the client has close the request before the server could send a response.
         *
         * [444 No Response Used]
         *
         * To indicate that the server has returned no information to the client and closed the connection.
         *
         * */
        data class RequestCancelled(
            override val statusCode: Int = 499,
            override val message: String = "Request cancelled"
        ) : ApiFailure(statusCode, message)

        /**
         *  [401 Unauthorized]
         *
         * Response status code indicates that the client request has not been completed because it lacks valid authentication credentials for the requested resource.
         * */
        data class UnauthorisedRequest(
            override val statusCode: Int = 401,
            override val message: String = "Unauthorised request"
        ) : ApiFailure(statusCode, message)

        /**
         * [400 Bad Request]
         *
         * Response status code indicates that the server cannot or will not process the request
         * due to something that is perceived to be a client error.
         * */
        data class BadRequest(
            override val statusCode: Int = 400,
            override val message: String = "Bad request"
        ) : ApiFailure(statusCode, message)

        /**
         * [404 Not Found]
         *
         * The server has not found anything matching the Request-URI.
         *
         * No indication is given of whether the condition is temporary or permanent
         * */
        data class NotFound(
            override val statusCode: Int = 404,
            override val message: String = "Not found"
        ) : ApiFailure(statusCode, message)

        /**
         * [405 Method Not Allowed]
         *
         * Response status code indicates that the server knows
         * the request method, but the target resource doesn't support this method.
         * */
        data class MethodNotAllowed(
            override val statusCode: Int = 405,
            override val message: String = "Method Not Allowed"
        ) : ApiFailure(statusCode, message)

        /**
         * [406 Not Acceptable]
         *
         * Client error response code indicates that the server cannot
         * produce a response matching the list of acceptable values
         * defined in the request's proactive content negotiation headers,
         * and that the server is unwilling to supply a default representation.
         * */
        data class NotAcceptable(
            override val statusCode: Int = 406,
            override val message: String = "Not Acceptable"
        ) : ApiFailure(statusCode, message)

        /**
         * [408 Request Timeout]
         *
         * Response status code means that the server would like
         * to shut down this unused connection. It is sent on an
         * idle connection by some servers, even without any previous
         * request by the client.
         * */
        data class RequestTimeout(
            override val statusCode: Int = 408,
            override val message: String = "Connection request timeout"
        ) : ApiFailure(statusCode, message)

        /**
         * [409 Conflict]
         *
         * Response status code indicates a request conflict
         * with the current state of the target resource.
         * */
        data class Conflict(
            override val statusCode: Int = 409,
            override val message: String = "Error due to a conflict"
        ) : ApiFailure(statusCode, message)

        /**
         * [500 Internal Server Error]
         *
         * Server error response code indicates that the server
         * encountered an unexpected condition that prevented
         * it from fulfilling the request.
         * */
        data class InternalServerError(
            override val statusCode: Int = 500,
            override val message: String = "Internal Server Error"
        ) : ApiFailure(statusCode, message)

        /**
         * [501 Not Implemented]
         *
         * Server error response code means that the server
         * does not support the functionality required to fulfill
         * the request.
         * */
        data class NotImplemented(
            override val statusCode: Int = 501,
            override val message: String = "Not Implemented"
        ) : ApiFailure(statusCode, message)

        /**
         * [503 Service Unavailable]
         *
         * Server error response code indicates that
         * the server is not ready to handle the request.
         * */
        data class ServiceUnavailable(
            override val statusCode: Int = 503,
            override val message: String = "Service Unavailable"
        ) : ApiFailure(statusCode, message)

        /**
         * Unknown api exception
         * */
        data class Unknown(
            override val statusCode: Int,
            override val message: String
        ) : ApiFailure(statusCode, message)

    }
}


