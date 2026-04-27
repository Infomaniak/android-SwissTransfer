/*
 * Infomaniak SwissTransfer - Android
 * Copyright (C) 2026 Infomaniak Network SA
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
package com.infomaniak.swisstransfer.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

object SwissTransferTransition {
    // Fade simple (actuel)
    val enterTransition = fadeIn()
    val exitTransition = fadeOut()

    // Fade simple rapide
    // val enterTransition = fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMedium))
    // val exitTransition = fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMedium))

    // Slide depuis la droite
    // val enterTransition = slideInHorizontally(initialOffsetX = { it })
    // val exitTransition = slideOutHorizontally(targetOffsetX = { it })

    // Slide depuis le bas
    // val enterTransition = slideInVertically(initialOffsetY = { it })
    // val exitTransition = slideOutVertically(targetOffsetY = { it })

    // Slide depuis la gauche
    // val enterTransition = slideInHorizontally(initialOffsetX = { -it })
    // val exitTransition = slideOutHorizontally(targetOffsetX = { -it })

    // Slide depuis le haut
    // val enterTransition = slideInVertically(initialOffsetY = { -it })
    // val exitTransition = slideOutVertically(targetOffsetY = { -it })

    // Zoom + Fade (enter)
    // val enterTransition = scaleIn(initialScale = 0.8f) + fadeIn()
    // val exitTransition = scaleOut(targetScale = 0.8f) + fadeOut()

    // Slide droite + Fade
    // val enterTransition = slideInHorizontally(initialOffsetX = { it }) + fadeIn()
    // val exitTransition = slideOutHorizontally(targetOffsetX = { it }) + fadeOut()

    // Slide bas + Fade (pour un effet drawer)
    // val enterTransition = slideInVertically(initialOffsetY = { it }) + fadeIn()
    // val exitTransition = slideOutVertically(targetOffsetY = { it }) + fadeOut()

    // Expand + Fade (grossissement)
    // val enterTransition = expandIn(expandFrom = Alignment.Center) + fadeIn()
    // val exitTransition = shrinkOut(shrinkTowards = Alignment.Center) + fadeOut()

    // Expand depuis le coin haut-gauche
    // val enterTransition = expandIn(expandFrom = Alignment.TopStart) + fadeIn()
    // val exitTransition = shrinkOut(shrinkTowards = Alignment.TopStart) + fadeOut()

    // Expand depuis le coin bas-droit
    // val enterTransition = expandIn(expandFrom = Alignment.BottomEnd) + fadeIn()
    // val exitTransition = shrinkOut(shrinkTowards = Alignment.BottomEnd) + fadeOut()

    // Slide droite + Zoom
    // val enterTransition = slideInHorizontally(initialOffsetX = { it }) + scaleIn(initialScale = 0.9f)
    // val exitTransition = slideOutHorizontally(targetOffsetX = { it }) + scaleOut(targetScale = 0.9f)

    // Rotation + Fade
    // val enterTransition = rotateIn(initialRotationDegrees = 45f) + fadeIn()
    // val exitTransition = rotateOut(targetRotationDegrees = 45f) + fadeOut()

    // Clip expand + Slide (effet révélation)
    // val enterTransition = clipInHorizontally(initialWidth = { 0 }) + slideInHorizontally(initialOffsetX = { it })
    // val exitTransition = clipOutHorizontally(targetWidth = { 0 }) + slideOutHorizontally(targetOffsetX = { it })
}
