# Movie Compose Project

This project is a movie application built using Jetpack Compose, Kotlin, and various Android libraries. It showcases the use of modern Android development practices, including MVVM architecture, dependency injection with Koin, and asynchronous data handling with Kotlin Coroutines and Flow.

## Features

- Display a list of now showing and popular movies.
- View detailed information about a selected movie.
- Save movies to a bookmark list.
- View cast information for each movie.
- Play movie trailers.

## Architecture

The project follows the MVVM (Model-View-ViewModel) architecture pattern, which helps in separating the concerns of the application and makes the codebase more maintainable and testable.

## Libraries and Tools

- **Jetpack Compose**: Android’s modern toolkit for building native UI.
- **Kotlin**: Programming language used for the entire codebase.
- **Koin**: Dependency injection framework.
- **Kotlin Coroutines**: For asynchronous programming.
- **Kotlin Flow**: For handling streams of data asynchronously.
- **Coil**: Image loading library for Android.
- **Navigation Component**: For handling navigation within the app.
- **Retrofit**: For network requests.
- **Kotlinx Serialization**: For JSON parsing.

## Project Structure

- `data`: Contains data models, network data sources, and repository implementations.
- `domain`: Contains use cases and entity models.
- `presentation`: Contains ViewModels, Composables, and UI-related classes.
- `ui`: Contains theme and other UI-related resources.

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later.
- Kotlin 1.5 or later.

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/eng-marwa/movie-compose-project.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the project on an emulator or a physical device.

## Usage

- Browse the list of now showing and popular movies.
- Click on a movie to view its details.
- Save movies to your bookmark list by clicking the bookmark icon.
- View the cast of the movie and play trailers.

## Acknowledgements

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Koin](https://insert-koin.io/)
- [Coil](https://coil-kt.github.io/coil/)
- [Retrofit](https://square.github.io/retrofit/)
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)

## Screenshots

![Screenshot 1](screenshots/screenshot1.png, width="200")
![Screenshot 2](screenshots/screenshot2.png, width="200")