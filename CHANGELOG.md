# Changelog

All notable changes to Screen Recorder will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Initial implementation of screen recorder
- MediaProjection-based screen recording
- Audio source selection (Internal/Microphone/None)
- Material Design UI with minimal interface
- Foreground service with notification controls
- MediaStore integration for saving recordings
- MP4 output with H.264 video and AAC audio
- Permission handling for API 29+
- Comprehensive documentation
  - README with features and usage
  - API documentation
  - Development guide
  - Testing guide
  - Architecture overview
  - Privacy policy
  - FAQ
  - Contributing guidelines
  - Quick start guide
- MIT License
- GitHub Actions CI/CD workflow
- .gitignore for Android projects

### Technical Details
- Min SDK: 29 (Android 10)
- Target SDK: 34 (Android 14)
- Language: Kotlin
- UI: Material Components, View Binding
- Video: H.264, 5 Mbps, 30 fps, native resolution
- Audio: AAC, 128 kbps, 44.1 kHz

## [1.0.0] - TBD

### Release Notes
First stable release of Screen Recorder.

**Features:**
- Screen recording with MediaProjection API
- Three audio modes: Internal Audio, Microphone, No Audio
- Simple Material Design interface
- Background recording with foreground service
- Notification with stop action
- Automatic saving to Movies/ScreenRecordings
- Open source under MIT License

**Requirements:**
- Android 10 (API 29) or higher
- Permissions: RECORD_AUDIO (optional), POST_NOTIFICATIONS (optional), Screen capture consent

**Known Limitations:**
- No pause/resume functionality
- Fixed quality settings (5 Mbps, 30 fps)
- Internal audio may not work on all devices
- DRM-protected content appears black in recordings

---

## Version History Format

### [Version] - Date

#### Added
- New features

#### Changed
- Changes to existing functionality

#### Deprecated
- Soon-to-be removed features

#### Removed
- Removed features

#### Fixed
- Bug fixes

#### Security
- Security improvements

---

## Future Versions

### Planned for 1.1.0
- [ ] Pause/Resume recording
- [ ] Quality settings (resolution, bitrate, fps)
- [ ] Recording timer
- [ ] Countdown before recording starts

### Planned for 1.2.0
- [ ] Video trimming/editing
- [ ] Screenshot capture
- [ ] GIF conversion
- [ ] Custom save location

### Under Consideration
- [ ] Face cam overlay
- [ ] Drawing/annotation during recording
- [ ] Cloud upload integration
- [ ] Scheduled recordings
- [ ] Multiple audio sources
- [ ] Game performance overlay

---

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for information on how to contribute to this changelog and the project.

## Links

- [Repository](https://github.com/techdiwas/screen-recorder-)
- [Issues](https://github.com/techdiwas/screen-recorder-/issues)
- [Releases](https://github.com/techdiwas/screen-recorder-/releases)
