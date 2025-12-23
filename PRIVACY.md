# Security & Privacy Policy

## Overview

Screen Recorder is an open-source Android application designed with privacy and security as core principles. This document outlines our approach to handling sensitive data and permissions.

## Data Collection

### What We DO NOT Collect

- **No user data transmission**: This app does NOT send any data to external servers
- **No analytics**: We do not track usage, crashes, or user behavior
- **No advertisements**: No ad networks or tracking SDKs
- **No cloud storage**: All recordings stay on your device
- **No user accounts**: No login, registration, or user profiles

### What Stays on Your Device

- **Screen recordings**: Saved locally to Movies/ScreenRecordings
- **App preferences**: Audio source selection (stored in app memory only)
- **Temporary files**: Created during recording, deleted after saving to MediaStore

## Permissions

### Required Permissions and Their Purpose

#### 1. RECORD_AUDIO (Dangerous Permission)
- **When requested**: Only when "Microphone" audio source is selected
- **Purpose**: Capture microphone audio during screen recording
- **Can be denied**: Yes - app will record video without audio
- **Runtime permission**: Yes - requested before starting recording

#### 2. FOREGROUND_SERVICE (Normal Permission)
- **Purpose**: Keep recording service running in foreground
- **Required for**: Continuous recording while app is in background
- **Declared in**: AndroidManifest.xml
- **Runtime permission**: No - granted at install time

#### 3. FOREGROUND_SERVICE_MEDIA_PROJECTION (Normal Permission)
- **Purpose**: Specify foreground service type (Android 14+)
- **Required for**: Media projection service compliance
- **Declared in**: AndroidManifest.xml
- **Runtime permission**: No - granted at install time

#### 4. POST_NOTIFICATIONS (Dangerous Permission, Android 13+)
- **When requested**: At first recording attempt on Android 13+
- **Purpose**: Show recording status notification
- **Can be denied**: Yes - notification may not show (recording still works)
- **Runtime permission**: Yes - requested before starting recording

#### 5. WAKE_LOCK (Normal Permission)
- **Purpose**: Keep device awake during recording (implicit)
- **Required for**: Prevent device sleep during recording
- **Declared in**: AndroidManifest.xml
- **Runtime permission**: No - granted at install time

### MediaProjection Permission

**Special System Permission:**
- Requested via system dialog (not a manifest permission)
- Cannot be persisted - must be granted each recording session
- User explicitly agrees to screen capture
- Completely controlled by Android system
- Cannot be bypassed or automated

## Data Storage

### Local Storage Only

All recordings are stored locally on your device:

**Primary Location:**
```
/storage/emulated/0/Movies/ScreenRecordings/
```

**Temporary Location (during recording):**
```
/data/data/com.techdiwas.screenrecorder/files/Movies/ScreenRecordings/
```

**Access:**
- Files accessible through device gallery
- Files accessible through file managers
- No remote access
- No cloud sync
- No automatic uploads

### MediaStore API

We use Android's MediaStore API for storage:
- **Scoped Storage**: Compliant with Android 10+ requirements
- **No broad storage access**: We don't request WRITE_EXTERNAL_STORAGE
- **App-specific storage**: Temporary files in app's directory
- **User-accessible**: Final videos in shared Movies directory
- **Deletable**: User can delete via gallery or file manager

## Security Measures

### Code Security

1. **Open Source**: All code is publicly available for review
2. **No Obfuscation**: Code is transparent (ProGuard disabled in debug)
3. **No Network Code**: Zero network operations
4. **No External Dependencies**: Minimal third-party libraries
5. **Memory Safety**: Proper resource cleanup and leak prevention

### Runtime Security

1. **Permission Checks**: Runtime verification before accessing sensitive APIs
2. **Graceful Degradation**: App works with minimal permissions
3. **Safe Defaults**: "No Audio" selected by default
4. **User Control**: User chooses audio source explicitly

### Service Security

1. **Foreground Service**: Visible to user via notification
2. **User-Initiated**: Cannot start recording without user action
3. **Explicit Stop**: User can stop recording anytime
4. **Process Isolation**: Runs in app's sandboxed process

## Privacy by Design

### Principles

1. **Data Minimization**: Only capture what user explicitly requests
2. **Purpose Limitation**: Permissions used only for stated purpose
3. **Transparency**: Clear indication when recording is active
4. **User Control**: Full control over when recording starts/stops
5. **Local Processing**: All operations performed on-device

### No Data Sharing

- **No third-party access**: No SDKs with data collection
- **No cross-app data**: No sharing with other apps
- **No backup to cloud**: Android backup includes only preferences
- **No telemetry**: No usage statistics collected

## Compliance

### Android Policies

- **Google Play Policies**: Compliant with Play Store requirements
- **Scoped Storage**: Follows Android 10+ storage guidelines
- **Background Restrictions**: Proper foreground service usage
- **Permission Best Practices**: Runtime permissions with explanations

### DRM & Content Protection

**Limitation**: Some content cannot be recorded:
- DRM-protected media (Netflix, Prime Video, etc.)
- Secure surfaces (banking apps, password fields)
- This is enforced by Android system, not the app

## User Rights

### Your Control

You have complete control over:
1. **When to record**: App never records without explicit action
2. **What to record**: System handles screen capture permission
3. **Audio source**: Choose internal, microphone, or no audio
4. **Storage**: Delete recordings anytime from gallery
5. **Permissions**: Revoke permissions in Android settings

### Deleting Data

To completely remove app data:

1. **Delete Recordings**:
   - Open gallery or file manager
   - Navigate to Movies/ScreenRecordings
   - Delete files manually

2. **Clear App Data**:
   - Settings → Apps → Screen Recorder
   - Storage → Clear Data

3. **Uninstall App**:
   - Removes app and app-specific data
   - Recordings in Movies folder remain (user files)

## Third-Party Libraries

### Dependencies

The app uses only official Android/Google libraries:

1. **androidx.core:core-ktx**: Android KTX extensions (Google)
2. **androidx.appcompat:appcompat**: App compatibility (Google)
3. **com.google.android.material:material**: Material Design (Google)
4. **androidx.constraintlayout:constraintlayout**: Layout library (Google)
5. **androidx.lifecycle:lifecycle-service**: Lifecycle components (Google)

**All libraries**: Open source, from Google's AndroidX project

**No analytics libraries**: No Firebase, no Crashlytics, no tracking

## Security Updates

### Reporting Vulnerabilities

If you discover a security vulnerability:

1. **Do not** post publicly
2. Email: [Your contact email] or create private GitHub security advisory
3. Include: Description, steps to reproduce, potential impact
4. We will respond within 48 hours

### Update Policy

- Security fixes: Released as soon as possible
- Dependency updates: Regular monitoring of AndroidX updates
- Android version support: Target latest stable API level

## Children's Privacy

This app does not target children and:
- Collects no personal information
- Has no age restrictions
- Contains no ads or in-app purchases
- Is safe for all ages (content depends on what user records)

## Changes to This Policy

- Policy version: 1.0
- Last updated: December 2024
- Changes: Will be posted in this file and app releases
- Notification: Update notes will mention privacy policy changes

## Contact

For privacy concerns or questions:
- GitHub Issues: https://github.com/techdiwas/screen-recorder-/issues
- Repository: https://github.com/techdiwas/screen-recorder-

## Transparency

This is an open-source project:
- **Source code**: Publicly available on GitHub
- **Build process**: Reproducible from source
- **No hidden features**: Everything is in the code
- **Community auditable**: Anyone can review the code

## Summary

**What Screen Recorder Does:**
- Records your screen when you explicitly start it
- Saves recordings to your device's Movies folder
- Uses microphone if you select that option

**What Screen Recorder Does NOT Do:**
- Send data to any server
- Track your usage
- Access files outside of recordings it creates
- Run in background without notification
- Record without your explicit action

---

**Your privacy is paramount. All screen recordings stay on your device, under your control.**
