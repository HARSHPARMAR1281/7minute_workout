# Minutes Workout

An Android application designed to help users track and manage their workout routines efficiently.

## Project Overview

This is an Android application built with Kotlin that provides workout tracking and management functionality. The project includes both the main Android application and utility scripts for handling workout-related assets.

## Project Structure

```
├── app/                    # Main Android application module
│   ├── src/               # Source code
│   │   ├── main/         # Main application code
│   │   │   ├── java/    # Kotlin/Java source files
│   │   │   └── res/     # Android resources
│   └── build.gradle.kts  # App-level build configuration
├── generate_placeholders.py  # Script for generating placeholder images
├── download_images.py        # Script for downloading workout images
└── build.gradle.kts         # Project-level build configuration
```

## Prerequisites

- Android Studio Arctic Fox (2020.3.1) or newer
- JDK 11 or newer
- Android SDK (minimum SDK version as specified in build.gradle)
- Python 3.x (for running utility scripts)

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone [repository-url]
   ```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the application on an emulator or physical device

## Utility Scripts

The project includes two Python utility scripts:

- `generate_placeholders.py`: Generates placeholder images for workout exercises
- `download_images.py`: Downloads and processes workout-related images

To run the utility scripts:
```bash
python generate_placeholders.py
python download_images.py
```

## Building the Project

1. Open the project in Android Studio
2. Wait for the Gradle sync to complete
3. Click on "Run" or press Shift+F10 to build and run the application

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

