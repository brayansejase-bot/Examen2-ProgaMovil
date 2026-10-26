# Kotlin Multiplatform Project - Tareas Colaborativas & Notificaciones (Firebase + RTDB)

This is a Kotlin Multiplatform project targeting Android and iOS, implementing collaborative tasks with Firestore, Realtime Database counters, and local notifications using NotificationManager.

## Integrantes del Grupo
- Brayan Sejas Espinoza
- Jose Alejandro Terrazas Antezana
- Rafael Alejandro Quisbert Paco
- Jorge Pablo Ayaviri Hinojosa
- Jonathan Luizaga Zapata

## Estructura del Proyecto

* [/androidApp](./androidApp) contains the Android application.
* [/shared](./shared/src) is for code that’s shared across your Compose Multiplatform applications.
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - [androidMain](./shared/src/androidMain/kotlin) contains Android-specific implementations (e.g. AndroidNotificationService).
  - [iosMain](./shared/src/iosMain/kotlin) contains iOS-specific implementations.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
