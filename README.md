# Notification Tracker

An Android app that fires local notifications at 8 AM, 10 AM, and 12 PM daily and logs each with timestamp and battery level.

## Features

- Local notifications scheduled at exact times using AlarmManager
- Notifications persist across device reboots
- Logs each notification entry with time, date, and battery percentage
- Paginated list using Paging 3 and Room
- Built with Jetpack Compose

## Tech Stack

- Kotlin
- Jetpack Compose
- AlarmManager
- Room Database
- Paging 3
- Clean Architecture (Repository pattern + Use Cases)
- Manual dependency injection via AppContainer

## Architecture

The project follows Clean Architecture principles with three layers:

- **Presentation** — Compose UI, ViewModel
- **Domain** — Use cases, repository interface
- **Data** — Room database, repository implementation, alarm scheduling

## Setup

Clone the repo and open in Android Studio. Grant notification and exact alarm permissions on first launch.
