/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2025 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.screen.newtransfer.importfiles

import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks

data class EmailTextFieldCallbacks(
    val transferAuthorEmail: GetSetCallbacks<String>,
    val isAuthorEmailInvalid: () -> Boolean,
    val recipientEmail: GetSetCallbacks<String>,
    val isRecipientEmailInvalid: () -> Boolean,
    val validatedRecipientsEmails: GetSetCallbacks<Set<String>>
) {

    fun checkEmailError(isAuthor: Boolean): Boolean {
        return if (isAuthor) {
            isAuthorEmailInvalid() && transferAuthorEmail.get().isNotEmpty()
        } else {
            isRecipientEmailInvalid() && recipientEmail.get().isNotEmpty()
        }
    }
}
