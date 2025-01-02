/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2024-2025 Infomaniak Network SA
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

@file:Suppress("nothing_to_inline")

package com.infomaniak.core2.compose.core

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.*
import splitties.collections.forEachByIndex
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

@Composable
inline fun <T> rememberCallableState(): CallableState<T> {
    return remember { CallableState() }
}

@Stable //TODO: Report Compose compiler crash when this is made an inline value class.
class CallableState<T> private constructor(
    @PublishedApi
    internal val awaiters: SnapshotStateList<Continuation<T>>
) {

    constructor() : this(mutableStateListOf())

    suspend fun awaitOneCall(): T = suspendCancellableCoroutine { c ->
        awaiters.add(c)
        c.invokeOnCancellation { awaiters.remove(c) }
    }

    operator fun invoke(newValue: T): Boolean {
        val list = Snapshot.withMutableSnapshot {
            val result = awaiters.toList()
            awaiters.clear()
            result
        }
        list.forEachByIndex { it.resume(newValue) }
        return list.isNotEmpty()
    }

    inline val awaitersCount: Int get() = awaiters.size
    inline val awaitsCall: Boolean get() = awaiters.isNotEmpty()
}

@Suppress("nothing_to_inline")
inline operator fun CallableState<Unit>.invoke(): Boolean = invoke(Unit)

@Suppress("nothing_to_inline")
inline fun CallableState<Unit>.asCallable(): () -> Unit = { invoke() }
