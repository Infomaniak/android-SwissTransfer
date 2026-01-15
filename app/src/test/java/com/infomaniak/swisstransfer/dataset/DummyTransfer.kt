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
package com.infomaniak.swisstransfer.dataset

import com.infomaniak.core.common.utils.endOfTheDay
import com.infomaniak.core.common.utils.startOfTheDay
import com.infomaniak.core.common.utils.tomorrow
import com.infomaniak.multiplatform_swisstransfer.common.ext.toDateFromSeconds
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.transfers.Container
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.transfers.File
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.transfers.Transfer
import com.infomaniak.multiplatform_swisstransfer.common.models.TransferStatus
import com.infomaniak.multiplatform_swisstransfer.common.utils.DateUtils.SECONDS_IN_A_DAY
import kotlin.time.Clock
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime

object DummyTransfer {

    @OptIn(ExperimentalTime::class)
    private val nowSeconds get() = Clock.System.now().epochSeconds
    private val nowDate = nowSeconds.toDateFromSeconds()

    private val container1 = object : Container {
        override var uuid: String = "transfer1Container"
        override var duration: Long = 0L
        override var createdDateTimestamp: Long = 0L
        override var expiredDateTimestamp: Long = 0L
        override var numberOfFiles: Int = 0
        override var message: String? = null
        override var needPassword: Boolean = false
        override var language: String = "language"
        override var sizeUploaded: Long = 0L
        override var deletedDateTimestamp: Long? = null
        override var swiftVersion: Int = 0
        override var downloadLimit: Int = 20
        override var source: String = "source"
        override var files: List<File> = listOf(
            createDummyFile(containerUUID = uuid, fileName = "cat no no.gif", mimeType = "image/gif"),
            createDummyFile(containerUUID = uuid, fileName = "cat vibe.gif", mimeType = "image/gif"),
        )
    }

    val container2 = object : Container by container1 {
        override val uuid: String = "transfer2container"
    }

    val container3 = object : Container by container1 {
        override val uuid: String = "transfer3container"
    }

    val container4 = object : Container by container1 {
        override val uuid: String = "transfer4container"
    }

    val container5 = object : Container by container1 {
        override val uuid: String = "transfer5container"
    }

    val transfer1 = object : Transfer {
        override var linkUUID: String = "transferLinkUUID1"
        override var containerUUID: String = "containerUUID"
        override var downloadCounterCredit: Int = 12
        override var createdDateTimestamp: Long = 0L
        override var expiredDateTimestamp: Long = nowSeconds - 5L * SECONDS_IN_A_DAY
        override var hasBeenDownloadedOneTime: Boolean = false
        override var isMailSent: Boolean = true
        override var downloadHost: String = "url"
        override var container: Container = this@DummyTransfer.container1
        override val transferStatus: TransferStatus = TransferStatus.READY
    }

    val transfer2 = object : Transfer by transfer1 {
        override val linkUUID: String = "transfer2"
        override val containerUUID: String = "container2"
        override var expiredDateTimestamp: Long = nowDate.endOfTheDay().time / 1_000L
        override val container: Container = container2
        override val transferStatus: TransferStatus = TransferStatus.WAIT_VIRUS_CHECK
    }

    val transfer3 = object : Transfer by transfer1 {
        override val linkUUID: String = "transfer3"
        override val containerUUID: String = "container3"
        override var expiredDateTimestamp: Long = nowDate.tomorrow().startOfTheDay().time / 1_000L
        override val container: Container = container3
        override val transferStatus: TransferStatus = TransferStatus.WAIT_VIRUS_CHECK
    }

    val transfer4 = object : Transfer by transfer1 {
        override val linkUUID: String = "transfer4"
        override val containerUUID: String = "container4"
        override var expiredDateTimestamp: Long = nowSeconds + 5L * SECONDS_IN_A_DAY
        override val container: Container = container4
        override val transferStatus: TransferStatus = TransferStatus.WAIT_VIRUS_CHECK
    }

    val transfer5 = object : Transfer by transfer1 {
        override val linkUUID: String = "transfer5"
        override val containerUUID: String = "container5"
        override var downloadCounterCredit: Int = 0
        override var expiredDateTimestamp: Long = nowSeconds + 10L * SECONDS_IN_A_DAY
        override val container: Container = container5
        override val transferStatus: TransferStatus = TransferStatus.WAIT_VIRUS_CHECK
    }

    val expired: Transfer
    val expiresToday: Transfer
    val expiresTomorrow: Transfer
    val notExpired: Transfer
    val downloaded: Transfer

    init {
        expired = transfer1
        expiresToday = transfer2
        expiresTomorrow = transfer3
        notExpired = transfer4
        downloaded = transfer5
    }

    val transfers = listOf(transfer1, transfer2, transfer3, transfer4, transfer5)

    private fun createDummyFile(
        containerUUID: String,
        fileName: String,
        mimeType: String? = null,
    ): File = object : File {
        override val containerUUID: String = containerUUID
        override val uuid: String = "$fileName|whatever"
        override val fileName: String = fileName
        override val fileSizeInBytes: Long = 10_000_000L
        override val downloadCounter: Int = 0
        @OptIn(ExperimentalTime::class)
        override val createdDateTimestamp: Long = (Clock.System.now() - 1.hours).epochSeconds
        @OptIn(ExperimentalTime::class)
        override val expiredDateTimestamp: Long = (Clock.System.now() + 7.days).epochSeconds
        override val eVirus: String = ""
        override val deletedDate: String? = null
        override val mimeType: String? = mimeType
        override val receivedSizeInBytes: Long = fileSizeInBytes
        override val path: String? = null
        override val thumbnailPath: String? = null
    }
}
