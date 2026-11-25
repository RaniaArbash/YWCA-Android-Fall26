# Assignment 2 Starter Project

Welcome to the Assignment 2 Starter Project for Android Development! This repository serves as the official GitHub template starter project for students.

## Project Overview

This starter project is fully configured and ready to build and run in Android Studio. It includes all required libraries and dependencies for networking, local caching, asynchronous programming, UI development, and image loading. 

**Note:** This is a starter template, not a completed assignment. You must implement the REST API service, JSON response models, local Room database entities, DAOs, database instances, repositories, ViewModels with StateFlow, and Compose screens yourself.

---

## Supported API Choices

You may choose **one** of the following APIs for your assignment:
1. **DummyJSON Products API** (`https://dummyjson.com/`)
2. **Rick and Morty API** (`https://rickandmortyapi.com/api/`)
3. **PokéAPI** (`https://pokeapi.co/api/v2/`)

---

## Required Technologies & Libraries

- **Kotlin** & **Jetpack Compose** (Material 3)
- **Retrofit** & **Gson Converter** (REST API communication & JSON parsing)
- **Room** (Local database storage with KSP compilation)
- **Kotlin Coroutines** & **StateFlow** (Asynchronous programming & reactive UI state)
- **Navigation Compose** (Screen navigation)
- **Coil Compose** (Image loading from URLs)

---

## Getting Started in Android Studio

1. **Clone or Download** this repository.
2. **Open Android Studio** and select **Open** (or import project), then choose the project root folder (`Assignment2Template`).
3. **Sync Gradle**: When prompted, click **Sync Now** to download all required dependencies and configure KSP.
4. **Run the App**: Connect an Android device or start an emulator and click the **Run** (`▶`) button.

---

## Package Structure

Your source code is organized into the following package structure under `com.example.assignment2template`:

```text
com.example.assignment2template
│
├── data
│   ├── remote
│   │   ├── api          # Contains RetrofitInstance.kt (Add your API service interface here)
│   │   └── model        # Add your Retrofit JSON response data classes here
│   │
│   ├── local
│   │   ├── dao          # Add your Room DAO interfaces here
│   │   ├── entity       # Add your Room Entity data classes here
│   │   └── database     # Add your Room Database abstract class here
│   │
│   └── repository       # Add your Repository classes here
│
├── ui
│   ├── screens          # Add your Compose screens here
│   ├── components       # Add reusable Compose UI components here
│   └── theme            # Theme, Color, and Typography definitions (Provided)
│
└── viewmodel            # Add your ViewModels with StateFlow here
```

---

## Assignment Instructions & Checklist

- [ ] **API Selection**: Choose one of the 3 supported APIs and update `RetrofitInstance.kt` with the appropriate base URL if needed.
- [ ] **Network Setup**: Create your Retrofit API interface and data models in `data/remote/`.
- [ ] **Database Setup**: Create Room Entity classes, DAO interfaces, and the Room database in `data/local/`.
- [ ] **Repository**: Implement a repository that fetches data from the API and caches/retrieves it from Room.
- [ ] **ViewModel**: Implement ViewModels exposing `StateFlow` to manage UI state and trigger data loading.
- [ ] **UI Screens**: Build clean Jetpack Compose screens utilizing Navigation Compose and Coil for displaying images.
