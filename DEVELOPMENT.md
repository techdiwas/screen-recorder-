# Development Guide

## Prerequisites

- Android Studio Arctic Fox (2020.3.1) or newer
- JDK 11 or newer
- Android SDK with API 29+ (Android 10+)
- A physical Android device with API 29+ for testing (emulator may not support MediaProjection properly)

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone https://github.com/techdiwas/screen-recorder-.git
   cd screen-recorder-
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - File → Open → Select the project root directory
   - Wait for Gradle sync to complete

3. **Build the project:**
   - Build → Make Project (or Ctrl+F9 / Cmd+F9)
   - Resolve any dependency issues if prompted

4. **Run on device:**
   - Connect an Android device with API 29+ via USB
   - Enable USB debugging on the device
   - Run → Run 'app' (or Shift+F10 / Ctrl+R)

## Project Architecture

### Core Components

#### MainActivity.kt
The main entry point of the application. Handles:
- UI interactions and audio source selection
- Runtime permission requests (RECORD_AUDIO, POST_NOTIFICATIONS)
- MediaProjection intent launching
- Service lifecycle management

#### ScreenRecorderService.kt
A foreground service that manages screen recording. Features:
- MediaProjection setup for screen capture
- MediaRecorder configuration for video/audio encoding
- VirtualDisplay creation for screen mirroring
- Notification management for foreground service
- MediaStore integration for saving recordings

### Key APIs Used

1. **MediaProjection API**
   - Captures screen content
   - Requires user consent via system dialog
   - API Level 21+ (Android 5.0+)

2. **MediaRecorder API**
   - Encodes video and audio
   - Outputs MP4 container format
   - H.264 video codec, AAC audio codec

3. **MediaStore API**
   - Scoped storage compliant (Android 10+)
   - Saves to Movies/ScreenRecordings
   - No WRITE_EXTERNAL_STORAGE permission needed

4. **Foreground Service**
   - Required for long-running recording
   - Shows persistent notification
   - FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION (Android 14+)

## Testing

### Manual Testing Checklist

1. **Basic Recording:**
   - [ ] Start recording with no audio
   - [ ] Stop recording
   - [ ] Verify video saved to Movies/ScreenRecordings
   - [ ] Play video and verify screen content captured

2. **Audio Recording:**
   - [ ] Record with microphone audio
   - [ ] Record with internal audio (Android 10+)
   - [ ] Verify audio is present in recordings

3. **Permissions:**
   - [ ] Deny audio permission → app should still record video
   - [ ] Deny notification permission → check if notification still works
   - [ ] Grant all permissions → verify full functionality

4. **Edge Cases:**
   - [ ] Start recording, minimize app, return → verify still recording
   - [ ] Stop via notification action
   - [ ] Rotate device during recording
   - [ ] Low storage scenario
   - [ ] Interrupted by phone call

### Known Limitations

1. **Internal Audio Recording:**
   - Only available on Android 10+ (API 29+)
   - Some devices may not support it
   - DRM-protected content (e.g., Netflix) cannot be captured

2. **Performance:**
   - High-resolution recordings may impact device performance
   - Battery consumption increases during recording

3. **Permissions:**
   - Microphone permission only required when recording mic audio
   - Notification permission required on Android 13+ for notifications

## Troubleshooting

### Build Issues

**Problem:** Gradle sync fails
- **Solution:** Ensure you have JDK 11+ and Android SDK installed
- Check `local.properties` has correct SDK path

**Problem:** Compilation errors
- **Solution:** Clean project (Build → Clean Project)
- Invalidate caches (File → Invalidate Caches / Restart)

### Runtime Issues

**Problem:** Recording doesn't start
- **Solution:** Check if MediaProjection permission was granted
- Verify foreground service can be started
- Check Logcat for error messages

**Problem:** No audio in recording
- **Solution:** Verify microphone permission granted
- Check if device supports internal audio capture
- Some ROMs may restrict audio capture

**Problem:** Video not saved
- **Solution:** Check storage space available
- Verify MediaStore permissions
- Check app has not been force-stopped

## Code Style

- Follow Kotlin coding conventions
- Use meaningful variable and function names
- Add comments for complex logic
- Keep functions small and focused
- Use nullable types appropriately

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

Open source - MIT License (or specify your license)
