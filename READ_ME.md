# 🚀 F1 Mobile App: Modern Android Development Showcase

This is a single-module Android application demonstrating a robust, maintainable architecture using **Jetpack Compose** for UI and **Ktor** for networking. The project is designed to fetch and display Formula 1 data, focusing on clean separation of concerns.

---

## 🎯 Architecture & Technologies

The project strictly follows the **MVVM (Model-View-ViewModel)** architectural pattern and utilizes a modern stack:

| Concept | Technology Used | Rationale/Benefit Demonstrated |
| :--- | :--- | :--- |
| **UI Layer** | **Jetpack Compose** | Declarative, reactive UI development and modern Android toolkit adoption. |
| **Networking** | **Ktor Client** | Lightweight, multiplatform HTTP client for asynchronous data fetching (`ApiService.kt`). |
| **Dependency Injection** | **Hilt** | Compile-time dependency management, ensuring testability and providing app-wide singletons (`AppModule.kt`). |
| **State Management** | **Kotlin Flows** (`StateFlow`) | Efficient, thread-safe state communication from the `HomeViewModel` to the `HomeScreen` Composable. |
| **Navigation** | **Compose Navigation** | Structured and explicit handling of screen transitions and the back stack (`MainActivity.kt`, `NavigationGraph.kt`). |

---

## 📂 Key Components for Review

The structure is optimized for clarity and maintainability:

* **`data/remote/ApiService.kt`**: Contains all Ktor-based suspend functions for remote API calls (e.g., `fetchDrivers()`, `fetchSchedules()`).
* **`data/repository/HomeRepository.kt`**: The single source of truth for the Home screen data, abstracting the remote layer.
* **`ui/screens/home_screen/HomeViewModel.kt`**: The core logic component. Manages API calls, handles exceptions, and exposes `UiState.Loading`/`Success`/`Error` via `StateFlow`.
* **`ui/screens/home_screen/HomeScreen.kt`**: The primary UI composable, responsible for reacting to `HomeViewModel` states and rendering complex elements like the **Horizontal Pager** and the upcoming race countdown.
* **`MainActivity.kt`**: Handles the main application setup, including Hilt, and the persistent **Bottom Navigation Bar** structure.

---

## 🏃 Getting Started

1.  Clone the repository.
2.  Open in **Android Studio**.
3.  Build and Run on an emulator or device.

---

**Developed by: Amit Padhar**