# Frequently Asked Questions (FAQ)

## General Questions

### Q: What is Screen Recorder?
**A:** Screen Recorder is an open-source Android application that allows you to record your device's screen with optional audio (internal or microphone). It's designed for Android 10+ devices.

### Q: Is it really free and open source?
**A:** Yes! The entire source code is available on GitHub under an open-source license. There are no ads, no in-app purchases, and no hidden costs.

### Q: Does it work on my device?
**A:** The app requires Android 10 (API 29) or newer. Most devices from 2019 onwards support this version.

## Installation & Setup

### Q: How do I install the app?
**A:** 
1. Download the APK from the releases page
2. Enable "Install from Unknown Sources" if needed
3. Install the APK
4. Grant permissions when requested

Or build from source using Android Studio.

### Q: What permissions does it need?
**A:** 
- **Screen Capture**: System permission, requested each time
- **Record Audio**: Only if you want microphone audio
- **Notifications**: To show recording status (Android 13+)
- **Foreground Service**: To keep recording in background

### Q: Can I deny some permissions?
**A:** Yes! You can deny:
- Audio permission → records video only
- Notification permission → still records, but notification may not show

You cannot deny screen capture permission - it's required for recording.

## Usage

### Q: How do I start recording?
**A:**
1. Open Screen Recorder app
2. Select audio source (Internal/Microphone/None)
3. Tap "Start Recording"
4. Accept screen capture permission
5. Recording begins

### Q: How do I stop recording?
**A:** Two ways:
1. Open the app and tap "Stop Recording"
2. Tap "Stop" in the notification

### Q: Where are recordings saved?
**A:** Recordings are saved to:
```
/storage/emulated/0/Movies/ScreenRecordings/
```
You can access them through your device's gallery or file manager.

### Q: What format are the recordings?
**A:**
- **Container**: MP4
- **Video Codec**: H.264
- **Audio Codec**: AAC (if audio enabled)
- **Quality**: 5 Mbps bitrate, 30 fps

### Q: Can I record without audio?
**A:** Yes! Select "No Audio" before starting recording.

### Q: What's the difference between Internal Audio and Microphone?
**A:**
- **Internal Audio**: Records system sounds (music, games, videos)
- **Microphone**: Records your voice and ambient sounds
- **No Audio**: Video only, silent recording

### Q: Can I record phone calls?
**A:** No. Recording phone calls is restricted by Android for privacy reasons.

## Audio Recording

### Q: Internal audio recording doesn't work. Why?
**A:** Several possible reasons:
1. **Device limitation**: Some devices don't support internal audio capture
2. **Android version**: Requires Android 10+
3. **ROM restriction**: Some custom ROMs disable this feature
4. **DRM content**: Protected content (Netflix, etc.) cannot have audio captured

Try using "Microphone" or "No Audio" instead.

### Q: Why is audio quality poor?
**A:** Current settings:
- Bitrate: 128 kbps (standard quality)
- Sample rate: 44.1 kHz

This is sufficient for most use cases. Future versions may offer quality settings.

### Q: Can I record both internal and microphone audio together?
**A:** Not in the current version. You can choose one source at a time.

## Video Quality

### Q: How do I improve video quality?
**A:** Current quality settings are:
- Bitrate: 5 Mbps
- Frame rate: 30 fps
- Resolution: Native device resolution

These provide good quality for most use cases. Future versions may offer adjustable quality.

### Q: Why is the video quality poor?
**A:** Possible causes:
1. **Device performance**: Older devices may struggle with encoding
2. **High motion**: Fast-moving content is harder to encode
3. **Resolution**: Higher resolution requires more processing

### Q: Can I record in 60 fps?
**A:** Not in the current version. The app records at 30 fps, which is standard for screen recording.

### Q: What resolution does it record at?
**A:** Native device resolution (e.g., 1080x2340 for Full HD phones). The app automatically uses your device's screen resolution.

## Performance

### Q: Does recording drain battery?
**A:** Yes, screen recording is power-intensive:
- Typical drain: ~3-5% per 10 minutes
- Factors: Screen brightness, resolution, ongoing apps
- Recommendation: Record with charger connected for long sessions

### Q: Why does my device get hot during recording?
**A:** Screen recording uses CPU/GPU for video encoding, which generates heat. This is normal. If it gets very hot:
- Stop recording and let device cool
- Close other apps
- Reduce screen brightness

### Q: Can I record for hours?
**A:** Technically yes, but consider:
- Battery drain (charge while recording)
- Storage space (1-2 GB per hour)
- Device temperature (may slow down)
- Memory (app uses 50-100 MB)

### Q: Does it slow down my device?
**A:** Minimal impact on modern devices (~5-15% CPU usage). Older devices may experience slight slowdown.

## Storage

### Q: How much storage does a recording use?
**A:** Approximate sizes (at 5 Mbps bitrate):
- 1 minute: ~40 MB
- 5 minutes: ~200 MB
- 10 minutes: ~400 MB
- 1 hour: ~2.4 GB

### Q: Can I change where recordings are saved?
**A:** Not in the current version. Recordings go to Movies/ScreenRecordings per Android's MediaStore requirements.

### Q: Can I delete recordings?
**A:** Yes! Two ways:
1. Through your device's gallery app
2. Through a file manager app

Navigate to Movies/ScreenRecordings and delete files.

### Q: What happens if storage is full?
**A:** Recording may fail or stop automatically. The app will log an error. Free up space and try again.

## Troubleshooting

### Q: App crashes when starting recording. What to do?
**A:** Try:
1. Force stop app and restart
2. Check if permissions are granted
3. Restart device
4. Clear app cache (Settings → Apps → Screen Recorder → Storage → Clear Cache)
5. Check logcat for errors (if you're a developer)

### Q: Recording starts but nothing is saved. Why?
**A:** Possible causes:
1. **Storage full**: Check available space
2. **App killed**: Don't use task killers during recording
3. **Battery optimization**: Disable for this app
4. **Permission issue**: Check MediaStore access

### Q: Notification doesn't show during recording
**A:** On Android 13+, you need to grant notification permission. Or, check notification settings for the app.

### Q: Video plays in gallery but has no content
**A:** This can happen if:
- Recording was interrupted
- MediaRecorder failed to initialize
- File was corrupted during save

Check logcat for errors.

### Q: App is recording but I can't stop it
**A:** Try:
1. Open app and tap Stop
2. Use notification Stop button
3. Force stop app (will save partial recording)

### Q: "Permission denied" error
**A:** Check which permission:
- Screen capture: You must accept the system dialog
- Audio: Grant RECORD_AUDIO in app settings
- Notifications: Grant POST_NOTIFICATIONS in app settings

## Compatibility

### Q: Does it work on tablets?
**A:** Yes! The app works on any Android 10+ device, including tablets.

### Q: Does it work on Android TV?
**A:** Potentially, but not tested. The app is designed for phones/tablets with touch screens.

### Q: Does it work on foldable devices?
**A:** Yes, but consider:
- Orientation changes during recording
- Screen resolution changes
These are captured in the video.

### Q: Does it work on custom ROMs (LineageOS, etc.)?
**A:** Should work if the ROM:
- Supports Android 10+ APIs
- Doesn't restrict MediaProjection
- Supports MediaRecorder

### Q: Does it work on rooted devices?
**A:** Yes, no difference. Root is not required or used.

## Privacy & Security

### Q: Is my data safe?
**A:** Yes! The app:
- Stores everything locally
- Sends no data to servers
- Has no analytics or tracking
- Is open source (you can verify)

### Q: Can the app record without my knowledge?
**A:** No! Recording requires:
1. You to open the app
2. You to tap Start Recording
3. You to accept system permission
4. Visible notification while recording

### Q: Does it record DRM content like Netflix?
**A:** No. Android's system blocks recording of DRM-protected content. The screen will appear black in recordings.

### Q: Can others access my recordings?
**A:** Only if:
- They have physical access to your device
- You share the files yourself
- Your device is compromised (not related to this app)

Recordings are stored locally with normal file permissions.

## Development

### Q: Can I contribute to the project?
**A:** Yes! See DEVELOPMENT.md for setup instructions. We welcome:
- Bug reports
- Feature suggestions
- Code contributions
- Documentation improvements

### Q: How do I report bugs?
**A:** Open an issue on GitHub with:
- Device model and Android version
- Steps to reproduce
- Expected vs actual behavior
- Logcat output (if possible)

### Q: Will there be more features?
**A:** Possibly! Potential features:
- Quality settings
- Pause/resume
- Timer
- Video trimming
- GIF conversion

Check GitHub issues for roadmap.

### Q: Why is feature X not included?
**A:** The app follows the principle of "do one thing well." We focus on core screen recording functionality with minimal UI. Some features may be added later.

## Comparison

### Q: How is this different from other screen recorders?
**A:**
- **Open source**: Code is public and auditable
- **No ads**: Completely free, no monetization
- **Privacy-focused**: No tracking, no data collection
- **Simple**: Minimal UI, essential features only
- **Modern**: Uses latest Android APIs (MediaStore, etc.)

### Q: Why use this instead of Android's built-in recorder?
**A:** Android's built-in recorder (on supported devices) is great! This app offers:
- More control over audio source
- Available on more devices
- Customizable (open source)
- Consistent experience across devices

Use whichever works best for you!

## Technical Questions

### Q: What APIs does it use?
**A:**
- MediaProjection (screen capture)
- MediaRecorder (video encoding)
- MediaStore (storage)
- Foreground Service (background operation)

### Q: Why LifecycleService instead of regular Service?
**A:** LifecycleService provides lifecycle awareness and better integration with modern Android architecture components.

### Q: Can I use this code in my project?
**A:** Yes, it's open source! Check the LICENSE file for terms. Attribution appreciated.

### Q: Why Kotlin?
**A:** Kotlin is the preferred language for Android development, offering:
- Null safety
- Concise syntax
- Better interoperability
- Official Google support

---

## Still Have Questions?

- Check the documentation: README.md, API.md, DEVELOPMENT.md
- Search GitHub issues
- Open a new issue on GitHub
- Review the source code (it's open!)
