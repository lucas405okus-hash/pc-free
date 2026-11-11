# CASH PC — Android prototype

This is the first Android application scaffold for the CASH PC project.

## Current status
- Native Android app shell (Kotlin).
- Android Storage Access Framework ISO/image picker; no broad storage permission.
- Basic VM status screen.
- **QEMU is not integrated yet. This APK does not boot Windows or ReactOS.** The start button intentionally reports that limitation rather than pretending a VM started.

## Build
Open `cash-pc` in Android Studio with JDK 17 and Android SDK 35, or run `gradle assembleDebug` from this directory using Gradle 8.9+.

## Planned engineering stages
1. Build and install the Android shell on ARM64.
2. Integrate a maintained QEMU Android build or build QEMU from source for `arm64-v8a`.
3. Add aarch64 guest first for performance testing; x86/x86_64 guests require CPU emulation and may be very slow.
4. Add disk image creation, boot configuration, touch mouse, keyboard, and VM lifecycle handling.
5. Test ReactOS first. Windows installation media must be obtained lawfully by the user; this app does not bundle Windows.

## Important compatibility note
The target phone uses an ARM64 SoC. Running an x86 Windows guest requires instruction translation and is not equivalent to native Windows. Roblox Studio performance and compatibility are not guaranteed.

## Licensing
The Android shell in this directory is original project code. QEMU and any future third-party components must retain their respective licenses and notices. No Microsoft Windows binaries are included.
