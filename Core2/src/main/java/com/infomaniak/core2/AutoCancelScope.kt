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
@file:OptIn(ExperimentalSplittiesApi::class)

package com.infomaniak.core2

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import splitties.coroutines.raceOf
import splitties.experimental.ExperimentalSplittiesApi

/**
 * Executes the passed [block], and once it's done running, automatically cancels the passed
 * [CoroutineScope] after it, so any child coroutines get cancelled.
 *
 * Typical use cases:
 * - just be able to use `this` as a `scope` for `Flow`'s `shareIn` and `stateIn` operators
 * - launch "side" coroutines that live only while the block is not finished
 */
suspend fun <R> autoCancelScope(block: suspend CoroutineScope.() -> R): R {
    val value = CompletableDeferred<R>()
    return raceOf({
        value.complete(block())
        awaitCancellation() // We don't use the value of block straightaway because it'd never make it,
        // since the underlying implementation of raceOf uses async blocks, which would wait for children,
        // defeating the purpose of our behavior that automatically cancels the scope when the block is done
        // executing.
    }, {
        value.await()
    })
}
