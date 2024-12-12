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
package com.infomaniak.core2.appintegrity.exceptions

/**
 * Represents an unknown exception that can occur during the execution of the application.
 *
 * This exception is used to encapsulate unexpected or unknown errors that are not covered
 * by other specific exception types.
 *
 * @property message The detailed message describing the error.
 * @property cause The underlying cause of this exception, if any.
 *
 * @constructor Creates an instance of `UnknownException` with a detailed error message and an optional cause.
 *
 * @param cause The underlying exception that caused this exception.
 */
internal class UnknownException(cause: Throwable) : Exception(cause) {
    override val message: String = cause.message ?: cause.toString()
}
