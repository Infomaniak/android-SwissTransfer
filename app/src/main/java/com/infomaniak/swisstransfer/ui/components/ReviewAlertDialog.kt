package com.infomaniak.swisstransfer.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.infomaniak.swisstransfer.R
import com.infomaniak.core.R as RCore

@Composable
fun ReviewAlertDialog(onUserWantsToReview: () -> Unit, onUserWantsToGiveFeedback: () -> Unit, onDismiss: () -> Unit) {
    SwissTransferAlertDialog(
        title = stringResource(RCore.string.reviewAlertTitle, stringResource(R.string.appName)),
        positiveButton = {
            SmallButton(
                style = ButtonType.Tertiary,
                title = stringResource(RCore.string.buttonYes),
                onClick = onUserWantsToReview,
            )
        },
        negativeButton = {
            SmallButton(
                style = ButtonType.Tertiary,
                title = stringResource(RCore.string.buttonNo),
                onClick = onUserWantsToGiveFeedback,
            )
        },
        onDismiss = onDismiss,
    )
}
