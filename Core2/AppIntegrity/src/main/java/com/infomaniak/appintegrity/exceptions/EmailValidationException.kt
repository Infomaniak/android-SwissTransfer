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
 * A sealed class representing exceptions related to email validation that extend [ApiException].
 * This class is used to handle specific errors that occur during the email validation process.
 *
 * @property statusCode The HTTP status code associated with the error.
 * @constructor Creates an [EmailValidationException] with the given status code.
 *
 * @param statusCode The HTTP status code for the error.
 */
sealed class EmailValidationException(statusCode: Int) : ApiException(statusCode, "") {

    /**
     * Exception indicating that the provided password is invalid during email validation.
     * This corresponds to an HTTP 401 Unauthorized status.
     */
    class InvalidPasswordException : EmailValidationException(401)

    internal companion object {
        /**
         * Extension function to convert an instance of [UnexpectedApiErrorFormatException] to a specific
         * [EmailValidationException] based on its HTTP status code.
         *
         * This function maps the status codes to specific exceptions as follows:
         * - 401: [InvalidPasswordException]
         * - Other status codes: The original [UnexpectedApiErrorFormatException] instance
         *
         * @receiver An instance of [UnexpectedApiErrorFormatException].
         * @return An instance of [EmailValidationException] which can be [InvalidPasswordException]
         * or the original [UnexpectedApiErrorFormatException] if the status code does not match any predefined values.
         */
        fun UnexpectedApiErrorFormatException.toEmailValidationException() = when (statusCode) {
            401 -> InvalidPasswordException()
            else -> this
        }
    }
}
