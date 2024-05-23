# NewsApp

NewsApp is a Android application that provides users with the latest news articles from various sources using the NewsAPI.

<img width=300 src="https://github.com/Italord0/news-app/assets/32901063/5db0f173-821b-4a13-b877-cac4b1c56efe" >

## Features

- Browse top headlines and articles from multiple sources.

## Technologies Used

- **MVVM Architecture:** Separation of concerns for a clean and maintainable codebase.
- **Jetpack Compose:** Modern UI toolkit for building native Android UI.
- **Retrofit:** Type-safe HTTP client for making network requests.
- **Moshi:** JSON library for parsing network responses into Kotlin objects.
- **Koin:** Lightweight dependency injection framework for Kotlin.

## Setup

1. Clone the repository:

`git clone https://github.com/Italord0/NewsApp.git`

2. Open the project in Android Studio.

3. Obtain an API key from [NewsAPI](https://newsapi.org/) and replace `"api_key"` in `local.properties`:

`api_key=YOUR_API_KEY`

4. Build and run the app on an emulator or physical device.

## Flavors

The NewsApp project supports the following flavors:

- **bbc (default):** Default flavor that provides news articles from BBC.
- **abc:** Flavor that provides news articles from ABC News.
- **theVerge:** Flavor that provides news articles from The Verge.
- **wallStreetJournal:** Flavor that provides news articles from The Wall Street Journal.

To build a specific flavor, use the following command:

`./gradlew assembleFlavorName`
