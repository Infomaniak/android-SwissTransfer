/*
 * Infomaniak SwissTransfer - Android
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
package com.infomaniak.swisstransfer

import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.TransferUi
import com.infomaniak.swisstransfer.dataset.DummyTransfer
import com.infomaniak.swisstransfer.ui.utils.ExpiryStatus
import com.infomaniak.swisstransfer.ui.utils.daysBeforeExpiry
import com.infomaniak.swisstransfer.ui.utils.expiryStatus
import com.infomaniak.swisstransfer.ui.utils.isExpired
import org.junit.Test

class TransferDatesUnitTest {

    @Test
    fun check_expiryStatus_are_correct() {
        assert(TransferUi(DummyTransfer.expired).expiryStatus() == ExpiryStatus.EXPIRED)
        assert(TransferUi(DummyTransfer.expiresToday).expiryStatus() == ExpiryStatus.EXPIRES_TODAY)
        assert(TransferUi(DummyTransfer.expiresTomorrow).expiryStatus() == ExpiryStatus.EXPIRES_TOMORROW)
        assert(TransferUi(DummyTransfer.notExpired).expiryStatus() == ExpiryStatus.NOT_EXPIRED)
    }

    @Test
    fun check_daysBeforeExpiry_are_correct() {
        assert(TransferUi(DummyTransfer.expired).daysBeforeExpiry == -5)
        assert(TransferUi(DummyTransfer.expiresToday).daysBeforeExpiry == 0)
        assert(TransferUi(DummyTransfer.expiresTomorrow).daysBeforeExpiry == 1)
        assert(TransferUi(DummyTransfer.notExpired).daysBeforeExpiry == 5)
    }

    @Test
    fun check_isExpired_are_correct() {
        assert(TransferUi(DummyTransfer.expired).isExpired)
        assert(TransferUi(DummyTransfer.expiresToday).isExpired.not())
        assert(TransferUi(DummyTransfer.expiresTomorrow).isExpired.not())
        assert(TransferUi(DummyTransfer.notExpired).isExpired.not())
        assert(TransferUi(DummyTransfer.downloaded).isExpired)
    }
}
