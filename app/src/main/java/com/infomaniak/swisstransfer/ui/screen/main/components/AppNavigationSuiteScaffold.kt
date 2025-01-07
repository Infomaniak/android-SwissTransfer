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
package com.infomaniak.swisstransfer.ui.screen.main.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuite
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldLayout
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.swisstransfer.ui.NewTransferActivity
import com.infomaniak.swisstransfer.ui.components.BrandTopAppBar
import com.infomaniak.swisstransfer.ui.components.LargeButton
import com.infomaniak.swisstransfer.ui.images.AppImages.AppIcons
import com.infomaniak.swisstransfer.ui.images.icons.Add
import com.infomaniak.swisstransfer.ui.navigation.MainNavigation
import com.infomaniak.swisstransfer.ui.navigation.NavigationItem
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.launchActivity

/**
 * Layout for a [NavigationSuiteScaffold]'s content. This function wraps the [content] and places
 * the [NavigationSuite] component according to the given [layoutType].
 *
 * @param layoutType The current [NavigationSuiteType]. It determines whether
 * the app will use a bottom navigation bar ([NavigationSuiteType.NavigationBar])
 * or a navigation rail ([NavigationSuiteType.NavigationRail]).
 *
 * @param navigationItems The navigation items to be displayed.
 *
 * @param currentDestination The current selected item in the navigation suite. It represents
 * the active navigation item that the user is currently on, which will be highlighted or selected
 * in the navigation UI.
 *
 * @param navigateToSelectedItem A lambda function that handles the navigation logic when a user
 * selects a different item in the navigation suite. It receives the selected [MainNavigation]
 * item and performs the necessary navigation.
 *
 * @param content The content of your screen
 */
@Composable
fun AppNavigationSuiteScaffold(
    layoutType: NavigationSuiteType,
    navigationItems: List<NavigationItem>,
    currentDestination: MainNavigation,
    navigateToSelectedItem: (MainNavigation) -> Unit,
    content: @Composable () -> Unit,
) {
    Surface(color = SwissTransferTheme.materialColors.background) {
        NavigationSuiteScaffoldLayout(
            navigationSuite = {
                when (layoutType) {
                    NavigationSuiteType.None -> Unit
                    NavigationSuiteType.NavigationBar -> {
                        AppNavigationBar(navigationItems, currentDestination, navigateToSelectedItem)
                    }
                    else -> {
                        AppNavigationRail(navigationItems, currentDestination, navigateToSelectedItem)
                    }
                }
            },
            layoutType = layoutType,
        ) {
            Box(
                modifier = Modifier.consumeWindowInsets(
                    when (layoutType) {
                        NavigationSuiteType.NavigationBar -> NavigationBarDefaults.windowInsets.only(WindowInsetsSides.Bottom)
                        NavigationSuiteType.NavigationRail -> NavigationRailDefaults.windowInsets.only(WindowInsetsSides.Start)
                        NavigationSuiteType.NavigationDrawer -> DrawerDefaults.windowInsets.only(WindowInsetsSides.Start)
                        else -> WindowInsets(0, 0, 0, 0)
                    },
                ),
            ) {
                content()
            }
        }
    }
}

@Composable
private fun AppNavigationBar(
    navigationItems: List<NavigationItem>,
    currentDestination: MainNavigation,
    onClick: (MainNavigation) -> Unit,
) {
    NavigationBar(containerColor = SwissTransferTheme.colors.navigationItemBackground) {
        navigationItems.forEach { navigationItem ->
            NavigationBarItem(
                icon = { NavigationIcon(true, navigationItem) },
                label = { NavigationLabel(navigationItem) },
                selected = currentDestination::class == navigationItem.destination::class,
                onClick = { onClick(navigationItem.destination) },
            )
        }
    }
}

@Composable
private fun AppNavigationRail(
    navigationItems: List<NavigationItem>,
    currentDestination: MainNavigation,
    onClick: (MainNavigation) -> Unit,
) {
    val context = LocalContext.current
    PermanentDrawerSheet(
        drawerContainerColor = SwissTransferTheme.colors.navigationItemBackground,
        windowInsets = DrawerDefaults.windowInsets.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom),
    ) {
        BrandTopAppBar()
        Column(modifier = Modifier.padding(horizontal = Margin.Mini, vertical = Margin.Medium)) {
            navigationItems.forEachIndexed { index, navigationItem ->
                NavigationDrawerItem(
                    icon = { NavigationIcon(false, navigationItem) },
                    label = { NavigationLabel(navigationItem) },
                    selected = currentDestination::class == navigationItem.destination::class,
                    onClick = { onClick(navigationItem.destination) },
                )
                if (index != navigationItems.lastIndex) {
                    Spacer(modifier = Modifier.height(Margin.Micro))
                }
            }

            Spacer(modifier = Modifier.weight(1.0f))
            LargeButton(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.contentDescriptionCreateNewTransferButton),
                onClick = { context.launchActivity(NewTransferActivity::class) },
                imageVector = AppIcons.Add
            )
        }
    }
}

@Composable
private fun NavigationIcon(isNavigationBar: Boolean, navigationItem: NavigationItem) {
    val contentDescription = if (isNavigationBar) null else stringResource(navigationItem.label)
    Icon(navigationItem.icon, contentDescription)
}

@Composable
private fun NavigationLabel(navigationItem: NavigationItem) {
    Text(text = stringResource(navigationItem.label))
}
