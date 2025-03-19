# 🍽️ Delicious - Recipe App

**Delicious** is a modern Android application built with **Kotlin** and **Jetpack Compose**. It allows users to browse recipes and view detailed recipe information fetched from a remote API. The app supports offline caching, smooth UI, and clean architecture principles.

---

## ⚙️ Getting Started

### 📋 Prerequisites

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/delicious.git
2. **Open the project** in Android Studio.
3. **Set up API Key and Host**:
   - Obtain your API credentials.
   - Add the following to your local.properties file:
   - Add your token to the file
     ```sh
      API_KEY="your_api_key"
      API_HOST="your_api_host"
     ```
4. **Run the project** on an emulator or physical device.

## 🍳 Features
- **Detailed Recipe View:** View recipe details including prep time, servings, images, video, and description.
- **Offline Mode:** Cached data using Room database allows viewing recipes even without an internet connection.
- **Modern UI:** Built using Jetpack Compose for an interactive interface.
- **Video Playback:** If available, recipe videos can be played directly inside the app.

## 🏛️ Architecture

- **MVVM Pattern**: Ensures separation of concerns, making the codebase scalable and maintainable.
- **Clean Architecture**: Divided into layers for better code organization and testability.
- **Sealed Classes & Enums**: Used for efficient state and error management.

## 🌐 Networking

- **Ktor Client**: Handles API requests and responses efficiently.
- **Timber Logging**: Used for debugging network requests and errors.

## 📊 Testing

- **Unit Testing**: Ensures reliability of ViewModels and repositories.
- **MockK**: Used for mocking dependencies in tests.

## 🛠️ Tech Stack
- **Kotlin**
- **Jetpack Compose**
- **Ktor Client**
- **Room**
- **Koin**
- **Coil**
- **VideoView**
- **JUnit5, MockK, Coroutines Test**

## 🚀 Usage
Launch the app to browse recipes. Tap on a recipe card to view its details, video (if available), and cooking instructions.
