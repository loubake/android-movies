# android-movies

This is a work in progress project that's part of a personal study. The goal is to consolidate the usage of some Android technologies.


## The app

The app shows a grid of movies requested from *The Movie DB's* API.

In order to run the project, an API Key from *The Movie DB* site is needed.

A file called _**keys.properties**_ must be created on root folder (same level as _**settings.gradle.kts**_) and its content should be as follows: 

```
TheMovieDbApiKey="[YOUR-API-KEY-HERE]"
```


## Technologies used

Language: Kotlin.

Clean Architecture with MVVM design pattern.

Koin for dependency injection.

Kotlin Coroutines for handling async operations.


## Next steps

- Dagger and Hilt for dependency injection.
- Kotlin Flow
- Pagination
- Jetpack Compose
- Navigation Component
