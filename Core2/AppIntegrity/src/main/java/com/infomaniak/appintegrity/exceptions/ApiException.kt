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
 * Thrown when an API call fails due to an error identified by a specific error code.
 *
 * This exception is used to represent errors returned by an API, with an associated error code
 * and message describing the problem.
 *
 * @param errorCode The specific error code returned by the API.
 * @param errorMessage The detailed error message explaining the cause of the failure.
 */
open class ApiException(val errorCode: String, errorMessage: String) : Exception(errorMessage)
