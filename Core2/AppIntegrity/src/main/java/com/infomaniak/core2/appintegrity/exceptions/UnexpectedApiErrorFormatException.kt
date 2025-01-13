/*
 * Infomaniak SwissTransfer - AppIntegrity
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
 * Thrown when an API call returns an error in an unexpected format that cannot be parsed.
 *
 * This exception indicates that the API response format is different from what was expected,
 * preventing proper parsing of the error details.
 *
 * @param statusCode The HTTP status code returned by the API.
 * @param bodyResponse The raw response body from the API that could not be parsed.
 */
internal class UnexpectedApiErrorFormatException(val statusCode: Int, val bodyResponse: String) : Exception(bodyResponse)
