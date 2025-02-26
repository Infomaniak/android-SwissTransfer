# Copy-free upload TODO

## Screens

- Merge `UploadProgressScreen`, `UploadErrorScreen`, and possibly `UploadSuccessScreen`.
  - Maybe also merge `ValidateUserEmailScreen` and have it send a retry signal.

## Other stuff

- Extract stuff from `UploadForegroundService`'s companion object.
  - Add a retry signal thing.
- Add error/failure states to `UploadState`
  - Do we need to create separate hierarchies for terminal and non-terminal errors?
- Surface errors and progress from `TransferUploader`
- Rename `closActivity` parameters.
