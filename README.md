# Screen Recorder for Android

An open-source Android screen recorder application built with Kotlin, supporting Android 11+ (API 29+).

## Features

- **Screen Recording**: Record your device screen using MediaProjection API
- **Audio Support**: 
  - Internal audio recording (Android 10+)
  - Microphone audio recording
  - No audio option
- **Material Design UI**: Clean and minimal Material Components interface
- **Foreground Service**: Recording runs as a foreground service with notification controls
- **MediaStore Integration**: Videos are automatically saved to Movies/ScreenRecordings folder
- **Proper Permissions**: Runtime permission handling for audio and notifications
- **MP4 Output**: Videos saved in MP4 format with H.264 video codec

## Quick Start

**New to the app?** Check out the [Quick Start Guide](QUICKSTART.md) for step-by-step instructions!

```
1. Install the app
2. Select audio source (Internal/Microphone/None)
3. Tap "Start Recording"
4. Accept permissions
5. Stop when done
6. Find your recording in Gallery or Movies/ScreenRecordings
```

## Documentation

- 📖 [Quick Start Guide](QUICKSTART.md) - Get started in 5 minutes
- 📚 [API Documentation](API.md) - Technical API reference
- 🔧 [Development Guide](DEVELOPMENT.md) - Setup and development
- 🧪 [Testing Guide](TESTING.md) - Testing procedures
- 🏗️ [Architecture](ARCHITECTURE.md) - System design and architecture
- 🔒 [Privacy Policy](PRIVACY.md) - Privacy and security information
- ❓ [FAQ](FAQ.md) - Frequently asked questions
- 🤝 [Contributing](CONTRIBUTING.md) - How to contribute
- 📝 [Changelog](CHANGELOG.md) - Version history

## Requirements

- Android 11+ (API level 29+)
- Permissions:
  - `RECORD_AUDIO` - For microphone audio recording
  - `FOREGROUND_SERVICE` - For foreground service
  - `FOREGROUND_SERVICE_MEDIA_PROJECTION` - For media projection service
  - `POST_NOTIFICATIONS` - For showing notifications (Android 13+)
  - `WAKE_LOCK` - To keep device awake during recording

## Technical Stack

- **Language**: Kotlin
- **Min SDK**: 29 (Android 10)
- **Target SDK**: 34 (Android 14)
- **UI Framework**: Material Components for Android
- **Recording API**: MediaProjection, MediaRecorder
- **Storage**: MediaStore API (scoped storage)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/techdiwas/screenrecorder/
│   │   ├── MainActivity.kt           # Main UI activity
│   │   └── ScreenRecorderService.kt  # Recording service
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml     # Main activity layout
│   │   ├── values/
│   │   │   ├── strings.xml
│   │   │   ├── colors.xml
│   │   │   └── themes.xml
│   │   └── ...
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## Building the Project

1. Clone the repository:
```bash
git clone https://github.com/techdiwas/screen-recorder-.git
cd screen-recorder-
```

2. Build the project:
```bash
./gradlew assembleDebug
```

3. Install on device:
```bash
./gradlew installDebug
```

## Usage

1. Launch the Screen Recorder app
2. Select your preferred audio source:
   - Internal Audio (system sounds)
   - Microphone
   - No Audio
3. Tap "Start Recording"
4. Grant necessary permissions when prompted
5. Accept the screen capture request
6. Recording starts with a persistent notification
7. Tap "Stop Recording" or use the notification to stop
8. Video is automatically saved to Movies/ScreenRecordings

## Video Specifications

- **Format**: MP4 (MPEG-4)
- **Video Codec**: H.264
- **Video Bitrate**: 5 Mbps
- **Frame Rate**: 30 fps
- **Resolution**: Native device resolution
- **Audio Codec**: AAC (when audio enabled)
- **Audio Bitrate**: 128 kbps
- **Audio Sample Rate**: 44.1 kHz

## Screenshots

*Coming soon - screenshots will be added once the app is tested on a physical device*

## Contributing

Contributions are welcome! Please read our [Contributing Guidelines](CONTRIBUTING.md) before submitting pull requests.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Privacy

This app:
- ✅ Stores all data locally on your device
- ✅ Has NO analytics or tracking
- ✅ Has NO advertisements
- ✅ Sends NO data to external servers
- ✅ Is completely open source

Read our full [Privacy Policy](PRIVACY.md) for details.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

- 📋 [Report a Bug](https://github.com/techdiwas/screen-recorder-/issues/new?labels=bug)
- 💡 [Request a Feature](https://github.com/techdiwas/screen-recorder-/issues/new?labels=enhancement)
- ❓ [Ask a Question](https://github.com/techdiwas/screen-recorder-/issues/new?labels=question)

## Acknowledgments

- Built with Android's MediaProjection API
- Uses Material Design Components
- Inspired by the need for a simple, privacy-focused screen recorder

---

**Made with ❤️ by the open source community**