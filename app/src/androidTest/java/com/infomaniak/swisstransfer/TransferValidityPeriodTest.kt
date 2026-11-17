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
package com.infomaniak.swisstransfer

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.isSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.infomaniak.core.ui.compose.preview.previewparameter.dummyUserOf
import com.infomaniak.swisstransfer.ui.LocalUser
import com.infomaniak.swisstransfer.ui.screen.main.settings.ValidityPeriodOption
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.EmailTextFieldCallbacks
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesScreen
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.PickFilesViewModel.CanSendStatus
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.TransferOptionState
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.TransferOptionsCallbacks
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferOptionType
import com.infomaniak.swisstransfer.ui.screen.newtransfer.pickfiles.components.TransferTypeUi
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.GetSetCallbacks
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransferValidityPeriodTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private var selectedPeriod by mutableStateOf<ValidityPeriodOption?>(null)
    private val sentPeriods = mutableListOf<ValidityPeriodOption?>()

    @Test
    fun sendingWithoutDuration_requiresSelectionThenSends() {
        showPickFiles()

        composeRule.onNodeWithText(context.getString(R.string.buttonSelect)).performScrollTo().assertIsDisplayed()
        clickSend()

        composeRule.onNodeWithText(context.getString(R.string.recommendedDurationsDescription)).assertIsDisplayed()
        composeRule.onAllNodes(isSelected() and hasAnyAncestor(isDialog())).assertCountEquals(0)
        composeRule.runOnIdle { assertEquals(emptyList<ValidityPeriodOption?>(), sentPeriods) }

        selectDays(1)

        composeRule.runOnIdle { assertEquals(listOf(ValidityPeriodOption.ONE), sentPeriods) }
    }

    @Test
    fun durationSelectedInOptions_doesNotSendUntilSendIsPressed() {
        showPickFiles()

        composeRule.onNodeWithText(context.getString(R.string.settingsOptionValidityPeriod)).performScrollTo().performClick()
        selectDays(3)

        composeRule.runOnIdle {
            assertEquals(ValidityPeriodOption.THREE, selectedPeriod)
            assertEquals(emptyList<ValidityPeriodOption?>(), sentPeriods)
        }
        clickSend()

        composeRule.runOnIdle { assertEquals(listOf(ValidityPeriodOption.THREE), sentPeriods) }
        composeRule.onNodeWithText(context.getString(R.string.newTransferValidityPeriodTitle)).assertDoesNotExist()
    }

    @Test
    fun dismissingDurationPicker_doesNotSendOrSelectDuration() {
        showPickFiles()
        clickSend()

        composeRule.onAllNodes(SemanticsMatcher.keyIsDefined(SemanticsActions.Dismiss))[0]
            .performSemanticsAction(SemanticsActions.Dismiss) { it() }

        composeRule.onNodeWithText(context.getString(R.string.newTransferValidityPeriodTitle)).assertDoesNotExist()
        composeRule.runOnIdle {
            assertNull(selectedPeriod)
            assertEquals(emptyList<ValidityPeriodOption?>(), sentPeriods)
        }
        clickSend()
        composeRule.onNodeWithText(context.getString(R.string.newTransferValidityPeriodTitle)).assertIsDisplayed()
    }

    @Test
    fun thirtyDaysForSignedInUser_requiresConfirmationAndCanBeCancelled() {
        showPickFiles(isSignedIn = true)
        clickSend()
        selectDays(30)

        composeRule.onNodeWithText(context.getString(R.string.transferAvailabilityAlertDescription)).assertIsDisplayed()
        composeRule.runOnIdle {
            assertNull(selectedPeriod)
            assertEquals(emptyList<ValidityPeriodOption?>(), sentPeriods)
        }
        composeRule.onNodeWithText(context.getString(com.infomaniak.core.common.R.string.buttonCancel)).performClick()
        composeRule.runOnIdle {
            assertNull(selectedPeriod)
            assertEquals(emptyList<ValidityPeriodOption?>(), sentPeriods)
        }

        selectDays(30)
        composeRule.onNode(
            hasText(context.getString(R.string.transferSendButton)) and hasAnyAncestor(isDialog())
        ).performClick()

        composeRule.runOnIdle { assertEquals(listOf(ValidityPeriodOption.THIRTY), sentPeriods) }
    }

    @Test
    fun thirtyDaysForGuest_sendsWithoutAvailabilityConfirmation() {
        showPickFiles()
        clickSend()
        selectDays(30)

        composeRule.runOnIdle { assertEquals(listOf(ValidityPeriodOption.THIRTY), sentPeriods) }
        composeRule.onNodeWithText(context.getString(R.string.transferAvailabilityAlertTitle)).assertDoesNotExist()
    }

    private fun clickSend() {
        composeRule.onNodeWithText(context.getString(R.string.transferSendButton)).performClick()
    }

    private fun selectDays(days: Int) {
        composeRule.onNodeWithText(context.resources.getQuantityString(R.plurals.settingsValidityPeriodValue, days, days))
            .performScrollTo()
            .performClick()
    }

    private fun showPickFiles(isSignedIn: Boolean = false) {
        composeRule.setContent {
            CompositionLocalProvider(LocalUser provides if (isSignedIn) dummyUserOf(1, "Test", "User") else null) {
                SwissTransferTheme {
                    PickFilesScreen(
                        files = { emptyList() },
                        canSendStatus = { CanSendStatus.Yes },
                        onTransferTitleChange = {},
                        emailTextFieldCallbacks = EmailTextFieldCallbacks(
                            transferAuthorEmail = GetSetCallbacks(get = { "" }, set = {}),
                            isAuthorEmailInvalid = { false },
                            recipientEmail = GetSetCallbacks(get = { "" }, set = {}),
                            isRecipientEmailInvalid = { false },
                            validatedRecipientsEmails = GetSetCallbacks(get = { emptySet() }, set = {}),
                        ),
                        transferMessageCallbacks = GetSetCallbacks(get = { "" }, set = {}),
                        selectedTransferType = GetSetCallbacks(get = { TransferTypeUi.Link }, set = {}),
                        transferOptionsCallbacks = TransferOptionsCallbacks(
                            transferOptionsStates = {
                                listOf(TransferOptionState(TransferOptionType.VALIDITY_DURATION) { selectedPeriod })
                            },
                            onTransferOptionValueSelected = { selectedPeriod = it as ValidityPeriodOption },
                            password = GetSetCallbacks(get = { "" }, set = {}),
                            isPasswordValid = { true },
                        ),
                        pickFiles = {},
                        selectContact = {},
                        exitNewTransfer = {},
                        isAwaitingSend = { true },
                        onSendButtonClick = { sentPeriods.add(selectedPeriod) },
                        snackbarHostState = remember { SnackbarHostState() },
                        navigateToFilesDetails = {},
                    )
                }
            }
        }
    }
}
