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

## License

Open source - feel free to use and modify for your projects.

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.