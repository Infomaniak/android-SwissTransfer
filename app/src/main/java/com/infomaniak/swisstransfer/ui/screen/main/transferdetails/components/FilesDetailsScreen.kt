import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.infomaniak.multiplatform_swisstransfer.common.interfaces.ui.FileUi
import com.infomaniak.swisstransfer.ui.components.FileItemList
import com.infomaniak.swisstransfer.ui.components.SwissTransferTopAppBar
import com.infomaniak.swisstransfer.ui.components.TopAppBarButtons
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.main.transferdetails.TransferDetailsViewModel
import com.infomaniak.swisstransfer.ui.screen.newtransfer.FilesSize
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun FilesDetailsScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    transferDetailsViewModel: TransferDetailsViewModel = hiltViewModel<TransferDetailsViewModel>(),
    folderUuid: String,
    navigateToFolder: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    navigateBack: () -> Unit,
    close: (() -> Unit),
) {
    // If we don't have a folderUuid, it means we have to load files from importedFiles in ImportFilesViewModel
    val files by transferDetailsViewModel.filesInFolder.collectAsStateWithLifecycle()

    if (files?.isEmpty() == true) navigateBack()

    LaunchedEffect(folderUuid) {
        transferDetailsViewModel.loadFiles(folderUuid)
    }

    files?.let {
        SwissTransferScaffold(
            topBar = {
                SwissTransferTopAppBar(
                    navigationIcon = { TopAppBarButtons.Back(onClick = navigateBack) },
                    actions = { TopAppBarButtons.Close(close) },
                )
            },
        ) {
            Column {
                FilesDetailsScreen(
                    paddingValues = paddingValues,
                    files = it,
                    navigateToFolder = navigateToFolder,
                    withFileSize = withFilesSize,
                    withSpaceLeft = withSpaceLeft,
                )
            }
        }
    }
}

@Composable
fun FilesDetailsScreen(
    paddingValues: PaddingValues,
    files: List<FileUi>,
    navigateToFolder: ((String) -> Unit)? = null,
    withFileSize: Boolean,
    withSpaceLeft: Boolean,
    onFileRemoved: ((uuid: String) -> Unit)? = null,
) {
    Column(modifier = Modifier.padding(paddingValues)) {
        FilesSize(files, withFilesSize = withFileSize, withSpaceLeft)
        FileItemList(
            modifier = Modifier.padding(horizontal = Margin.Medium),
            files = files,
            isRemoveButtonVisible = onFileRemoved != null,
            isCheckboxVisible = { false },
            isUidChecked = { false },
            setUidCheckStatus = { _, _ -> },
            onRemoveUid = { onFileRemoved?.invoke(it) },
            onClick = { navigateToFolder?.invoke(it) }
        )
    }
}

fun getOnFileRemoveCallback(
    importFilesViewModel: ImportFilesViewModel,
    withFileDelete: Boolean,
): ((String) -> Unit)? {
    return if (withFileDelete) {
        { importFilesViewModel.removeFileByUid(it) }
    } else null
}

@PreviewAllWindows
@Composable
private fun Preview(@PreviewParameter(FileUiListPreviewParameter::class) files: List<FileUi>) {
    SwissTransferTheme {
        Surface {
            FilesDetailsScreen(
                paddingValues = PaddingValues(0.dp),
                files = files,
                navigateToFolder = {},
                withFileSize = true,
                withSpaceLeft = true,
                onFileRemoved = {},
            )
        }
    }
}
