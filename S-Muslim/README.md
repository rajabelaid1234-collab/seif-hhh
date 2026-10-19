# S-Muslim — Android starter

This folder contains the first native Android project for S-Muslim.

## Build an APK on GitHub (phone only)

1. Open the repository's **Actions** tab.
2. Select **Build S-Muslim APK**.
3. Open the latest run and wait for it to finish.
4. Under **Artifacts**, download **S-Muslim-debug-apk**.
5. Extract the ZIP and install `app-debug.apk` on your Android phone.

The workflow builds a debug APK using GitHub-hosted runners. No Android Studio is needed on your phone.

## Current scope

Version 0.1.0 is only the starter UI. Quran text/audio offline storage, verified source content, and focus-mode app blocking/recitation matching are not implemented yet. Those will be added separately and tested before being described as working.

Minimum Android version currently configured: Android 6.0 (API 23). Blocking other apps reliably is not supported uniformly across all Android versions and may require user-enabled system permissions or dedicated-device configuration.
