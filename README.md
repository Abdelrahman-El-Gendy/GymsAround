# GymsAroundApp

## Overview

**GymsAround** is a modern Android application built with Jetpack Compose that helps users discover nearby gyms. The app features a clean UI with a list of gyms and detailed views for each location, including favorite functionality.

## Features

- **Gym List**: Displays a scrollable list of nearby gyms  
- **Gym Details**: Shows detailed information about a selected gym  
- **Favorite Toggle**: Users can mark/unmark gyms as favorites  
- **Navigation**: Deep linking support for gym details  
- **Loading States**: Proper handling of loading and error states  
- **Accessibility**: Semantic descriptions for screen readers  

## Technologies Used

- **Kotlin**: Primary programming language  
- **Jetpack Compose**: Modern declarative UI toolkit  
- **Hilt**: Dependency injection framework  
- **ViewModel**: For UI-related data management  
- **Navigation Compose**: For in-app navigation  
- **Coroutines**: For asynchronous operations  

## Project Structure
<h3>Project Structure</h3>

<pre>
com.example.gymsaround/
├── Gyms/
│   ├── data/                          <span style="color: gray;"># Data layer components</span>
│   ├── domain/                        <span style="color: gray;"># Business logic and models</span>
│   └── presentation/
│       ├── details/                   <span style="color: gray;"># Gym details screen components</span>
│       ├── gymslist/                  <span style="color: gray;"># Gyms list screen components</span>
│       └── SemanticsDescription.kt    <span style="color: gray;"># Accessibility constants</span>
├── di/                                <span style="color: gray;"># Dependency injection setup</span>
├── ui/                                <span style="color: gray;"># Theme and styling</span>
└── GymsApplication.kt                 <span style="color: gray;"># Application class</span>
</pre>





## Screens

### 1. Gyms List Screen
- Displays a list of gyms with name, address, and favorite status  
- Each item is clickable to navigate to details  
- Loading indicator and error handling  
- Favorite toggle functionality  

### 2. Gym Details Screen
- Shows detailed information about a selected gym  
- Displays open/closed status with color coding  
- Supports deep linking with URL pattern:  
  `https://www.gymsaround.com/details/{gym_id}`

## Architecture

The app follows a **clean architecture** approach with clear separation of concerns:

### Presentation Layer
- Composable functions for UI  
- ViewModels for state management  

### Domain Layer
- Use cases: `GetInitialGymsUseCase`, `ToggleFavouriteStateUseCase`  
- Business models: `Gym` data class  

### Data Layer
- Repositories: `GymsScreenRepository`, `GymsDetailsRepository`  
- Data sources: remote/local  

## Key Components

- `GymsViewModel`: Manages state for the gyms list screen  
- `GymsDetailsViewModel`: Manages state for the details screen  
- `GymsScreen`: Main composable for the gyms list  
- `GymDetailsScreen`: Composable for gym details  
- `GetInitialGymsUseCase`: Business logic for loading gyms  
- `ToggleFavouriteStateUseCase`: Business logic for favorite toggling  

## Getting Started

### Prerequisites

- Android Studio (latest version)  
- Android SDK (API level 24+)  
- Kotlin 1.8+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/GymsAroundApp.git
