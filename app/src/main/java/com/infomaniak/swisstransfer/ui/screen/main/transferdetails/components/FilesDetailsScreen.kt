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
import com.infomaniak.swisstransfer.ui.components.TopAppBarButton
import com.infomaniak.swisstransfer.ui.previewparameter.FileUiListPreviewParameter
import com.infomaniak.swisstransfer.ui.screen.main.components.SwissTransferScaffold
import com.infomaniak.swisstransfer.ui.screen.newtransfer.FilesSize
import com.infomaniak.swisstransfer.ui.screen.newtransfer.ImportFilesViewModel
import com.infomaniak.swisstransfer.ui.theme.Margin
import com.infomaniak.swisstransfer.ui.theme.SwissTransferTheme
import com.infomaniak.swisstransfer.ui.utils.PreviewAllWindows

@Composable
fun FilesDetailsScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    importFilesViewModel: ImportFilesViewModel = hiltViewModel<ImportFilesViewModel>(),
    folderUuid: String? = null,
    navigateToDetails: (String) -> Unit,
    withFilesSize: Boolean,
    withSpaceLeft: Boolean,
    withFileDelete: Boolean,
    navigateBack: () -> Unit,
    close: (() -> Unit),
) {
    // If we don't have a folderUuid, it means we have to load files from importedFiles in ImportFilesViewModel
    val files by importFilesViewModel.files.collectAsStateWithLifecycle()

    if (files?.isEmpty() == true) navigateBack()

    LaunchedEffect(folderUuid) {
        importFilesViewModel.loadFiles(folderUuid)
    }

    files?.let {
        SwissTransferScaffold(
            topBar = {
                SwissTransferTopAppBar(
                    navigationMenu = TopAppBarButton.backButton(navigateBack),
                    actionMenus = arrayOf(TopAppBarButton.closeButton { close() }),
                )
            },
        ) {
            Column {
                FilesDetailsScreen(
                    paddingValues = paddingValues,
                    files = it,
                    navigateToDetails = navigateToDetails,
                    withFileSize = withFilesSize,
                    withSpaceLeft = withSpaceLeft,
                    onFileRemoved = getOnFileRemoveCallback(importFilesViewModel, withFileDelete),
                )
            }
        }
    }
}

@Composable
fun FilesDetailsScreen(
    paddingValues: PaddingValues,
    files: List<FileUi>,
    navigateToDetails: (String) -> Unit,
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
            onClick = {
                navigateToDetails(it)
            }
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
                navigateToDetails = {},
                withFileSize = true,
                withSpaceLeft = true,
                onFileRemoved = {},
            )
        }
    }
}
