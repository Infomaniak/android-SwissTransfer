/*
 * Infomaniak SwissTransfer - Multiplatform
 * Copyright (C) 2024 Infomaniak Network SA
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.infomaniak.appintegrity.exceptions

/**
 * A sealed class representing various container error exceptions that extend [ApiException].
 * This class is used to handle API errors that occur when creating a container through the API.
 * Each specific error exception is associated with an HTTP status code and a descriptive error message.
 *
 * @constructor Creates a [ContainerErrorsException] with the given status code and error message.
 *
 * @param statusCode The HTTP status code for the error.
 * @param errorMessage A descriptive error message for the error.
 */
sealed class ContainerErrorsException(val statusCode: Int, errorMessage: String) : ApiException(statusCode, errorMessage) {

    /**
     * Exception indicating that email address validation is required.
     * This corresponds to an HTTP 401 Unauthorized status.
     */
    class EmailValidationRequired : ContainerErrorsException(401, "Email address validation required")

    /**
     * Exception indicating that the domain was automatically blocked for security reasons.
     * This corresponds to an HTTP 403 Forbidden status.
     */
    class DomainBlockedException : ContainerErrorsException(403, "The domain was automatically blocked for security reasons")

    /**
     * Exception indicating that the daily transfer limit has been reached.
     * This corresponds to an HTTP 404 Not Found status.
     */
    class DailyTransferLimitReachedException : ContainerErrorsException(404, "Daily transfer limit reached")

    /**
     * Exception indicating that the provided captcha is not valid.
     * This corresponds to an HTTP 422 Unprocessable Entity status.
     */
    class CaptchaNotValidException : ContainerErrorsException(422, "Captcha not valid")

    /**
     * Exception indicating that too many codes have been generated.
     * This corresponds to an HTTP 429 Too Many Requests status.
     */
    class TooManyCodesGeneratedException : ContainerErrorsException(429, "Too many codes generated")

    internal companion object {
        /**
         * Extension function to convert an instance of [UnexpectedApiErrorFormatException] to a more specific exception
         * based on its HTTP status code.
         *
         * This function maps the status codes to specific exceptions as follows:
         * - 401: [EmailValidationRequired]
         * - 403: [DomainBlockedException]
         * - 404: [DailyTransferLimitReachedException]
         * - 422: [CaptchaNotValidException]
         * - 429: [TooManyCodesGeneratedException]
         * - Other status codes: The original [UnexpectedApiErrorFormatException] instance
         *
         * @receiver An instance of [UnexpectedApiErrorFormatException].
         * @return An instance of [Exception] which can be one of the specific exceptions mentioned above,
         * or the original [UnexpectedApiErrorFormatException] if the status code does not match any predefined values.
         */
        fun UnexpectedApiErrorFormatException.toContainerErrorsException(): Exception {
            return when (statusCode) {
                401 -> EmailValidationRequired()
                403 -> DomainBlockedException()
                404 -> DailyTransferLimitReachedException()
                422 -> CaptchaNotValidException()
                429 -> TooManyCodesGeneratedException()
                else -> this
            }
        }
    }
}
