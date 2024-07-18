# android-SwissTransfer

Send up to 50 GB - Free and without registration - Keep your transfers for up to 30 days.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The `android-SwissTransfer` project integrates
the [SwissTransfer-Multiplatform](https://github.com/Infomaniak/multiplatform-SwissTransfer)  library, offering cross-platform
file transfer capabilities. This project is designed to be simple to use, providing developers with a straightforward way to
implement SwissTransfer functionalities in their Android applications.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You are using a Linux, macOS, or Windows machine.
- You have installed Java Development Kit (JDK) 17 or later.
- You have Android Studio installed.
- You have an active internet connection to download project dependencies.

## Installation

To install `android-SwissTransfer`, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Infomaniak/multiplatform-SwissTransfer.git
   cd android-SwissTransfer
   ```

2. **Open the project in Android Studio:**
    - Launch Android Studio.
    - Select "Open an existing Android Studio project".
    - Navigate to the directory where you cloned the repository and select it.

3. **Sync the project with Gradle:**
    - Once the project is opened, Android Studio will prompt you to sync the project with Gradle files. Click "Sync Now".

## Usage

Here's a basic guide on how to use the `android-SwissTransfer` project:

1. **Adding SwissTransfer-Multiplatform Library:**
   Ensure the `SwissTransfer-Multiplatform` library is included in your `build.gradle` dependencies.

   ```kts
   dependencies {
       implementation("com.github.infomaniak.multiplatform-SwissTransfer:Core:1.0.0")
   }
   ```

2. **Initialize the Library:**
   In your main activity or application class, initialize the library.

   ```kt
    class MainActivity: ComponentActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           setContentView(R.layout.activity_main)

           // Initialize the SwissTransfer library
           SwissTransferInjection()
       }
   }
   ```

## Contributing

If you see a bug or an enhancement point, feel free to create an issue, so that we can discuss it. Once approved, we or you (
depending on the priority of the bug/improvement) will take care of the issue and apply a merge request. Please, don't do a merge
request before creating an issue.

## License

This project is under GPLv3 license. See the LICENSE file for more details.
