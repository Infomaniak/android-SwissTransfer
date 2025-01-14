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

package com.infomaniak.core2.compose.basics

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.*
import splitties.collections.forEachByIndex
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

@Composable
inline fun <reified T> rememberCallableState(): CallableState<T> {
    return remember { CallableState() }
}

@Stable
class CallableState<T> @PublishedApi internal constructor(
    @PublishedApi
    internal val awaiters: SnapshotStateList<Continuation<T>>,
    private val isOfUnitType: Boolean
) : (T) -> Unit, () -> Unit {

    companion object {
        inline operator fun <reified T> invoke(): CallableState<T> {
            return CallableState<T>(mutableStateListOf(), isOfUnitType = T::class == Unit::class)
        }
    }

    suspend fun awaitOneCall(): T = suspendCancellableCoroutine { continuation ->
        awaiters.add(continuation)
        continuation.invokeOnCancellation { awaiters.remove(continuation) }
    }

    override fun invoke() {
        require(isOfUnitType)
        @Suppress("UNCHECKED_CAST")
        call(Unit as T)
    }

    override fun invoke(t: T) {
        call(t)
    }

    fun call(newValue: T): Boolean {
        val list = Snapshot.withMutableSnapshot {
            val result = awaiters.toList()
            awaiters.clear()
            result
        }
        list.forEachByIndex { it.resume(newValue) }
        return list.isNotEmpty()
    }

    inline val awaitersCount: Int get() = awaiters.size
    inline val isAwaitingCall: Boolean get() = awaiters.isNotEmpty()
}

inline fun CallableState<Unit>.call(): Boolean = call(Unit)
