# Testing Guide

## Prerequisites for Testing

- Physical Android device running Android 10+ (API 29+)
- USB debugging enabled
- ADB installed on development machine
- Android Studio with device connected

**Note:** Emulators may not support MediaProjection properly, so physical device testing is recommended.

## Build and Install

### Using Android Studio

1. Open the project in Android Studio
2. Connect your Android device via USB
3. Click Run (▶) or press Shift+F10
4. Select your device from the deployment target
5. Wait for the app to install and launch

### Using Command Line

```bash
# Build debug APK
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug

# Or use adb directly
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Manual Testing Scenarios

### Test Case 1: Basic Video Recording (No Audio)

**Steps:**
1. Launch the Screen Recorder app
2. Keep "No Audio" selected (default)
3. Tap "Start Recording"
4. Accept the screen capture permission dialog
5. Navigate to other apps or home screen
6. Return to Screen Recorder app
7. Tap "Stop Recording"

**Expected Results:**
- Recording starts immediately after accepting permission
- Status message shows "Recording in progress…"
- Persistent notification appears
- Button changes to "Stop Recording" with red color
- Audio source options become disabled
- Recording stops successfully
- Video is saved to Movies/ScreenRecordings
- Notification disappears

**Verification:**
```bash
# Check if video was created
adb shell ls -la /storage/emulated/0/Movies/ScreenRecordings/

# Pull and play the video
adb pull /storage/emulated/0/Movies/ScreenRecordings/ScreenRecord_*.mp4 .
# Open the MP4 file in a video player
```

### Test Case 2: Recording with Microphone Audio

**Steps:**
1. Launch the Screen Recorder app
2. Select "Microphone" audio source
3. Tap "Start Recording"
4. Grant microphone permission when prompted
5. Accept screen capture permission
6. Speak into the device microphone
7. Play some sounds
8. Tap "Stop Recording"

**Expected Results:**
- App requests RECORD_AUDIO permission
- Recording starts after permissions granted
- Video contains microphone audio
- Audio is synchronized with video

**Verification:**
```bash
# Pull the video
adb pull /storage/emulated/0/Movies/ScreenRecordings/ScreenRecord_*.mp4 .

# Check video properties (requires ffmpeg)
ffmpeg -i ScreenRecord_*.mp4
# Should show audio stream with AAC codec
```

### Test Case 3: Recording with Internal Audio

**Steps:**
1. Launch the Screen Recorder app
2. Select "Internal Audio" audio source
3. Tap "Start Recording"
4. Accept screen capture permission
5. Play a YouTube video or music
6. Tap "Stop Recording"

**Expected Results:**
- Recording starts without additional permission
- Video contains system/media audio
- Audio is synchronized with video

**Known Limitation:** Some devices may not support internal audio capture.

### Test Case 4: Permission Denial

**Steps:**
1. Launch app
2. Select "Microphone"
3. Tap "Start Recording"
4. Deny microphone permission
5. Observe behavior

**Expected Results:**
- Toast message: "Permissions required"
- Recording does not start
- User can retry and grant permission

### Test Case 5: Screen Capture Permission Denial

**Steps:**
1. Tap "Start Recording"
2. Click "Cancel" on screen capture dialog

**Expected Results:**
- Recording does not start
- App remains on main screen
- User can retry

### Test Case 6: Stop via Notification

**Steps:**
1. Start recording
2. Pull down notification shade
3. Tap "Stop" action in notification

**Expected Results:**
- Recording stops immediately
- Video is saved
- Notification disappears
- App UI updates to "Start Recording"

### Test Case 7: Background Recording

**Steps:**
1. Start recording
2. Press Home button
3. Use device normally for 30 seconds
4. Return to Screen Recorder app
5. Stop recording

**Expected Results:**
- Recording continues in background
- Notification remains visible
- All screen activity is recorded
- Video plays back correctly

### Test Case 8: Device Rotation

**Steps:**
1. Start recording
2. Rotate device between portrait and landscape
3. Stop recording

**Expected Results:**
- Recording continues without interruption
- Orientation changes are captured
- Video dimensions match device resolution

### Test Case 9: Low Storage

**Steps:**
1. Fill device storage until <100MB free
2. Start a long recording (5+ minutes)
3. Observe behavior

**Expected Results:**
- Recording may fail or stop automatically
- Error is logged
- No app crash

### Test Case 10: Concurrent Audio Usage

**Steps:**
1. Start playing music in another app
2. Launch Screen Recorder
3. Select "Microphone"
4. Start recording

**Expected Results:**
- Music may pause or continue based on system policy
- Recording proceeds
- Either music or microphone audio is captured

## Automated Testing

### Unit Tests (Limited)

Due to Android framework dependencies, unit testing is limited. However, you can test:

```kotlin
// Example test for AudioSource enum
@Test
fun testAudioSourceValues() {
    assertEquals(3, AudioSource.values().size)
    assertEquals(AudioSource.INTERNAL, AudioSource.values()[0])
    assertEquals(AudioSource.MICROPHONE, AudioSource.values()[1])
    assertEquals(AudioSource.NONE, AudioSource.values()[2])
}
```

### Instrumentation Tests

Place in `app/src/androidTest/java/`:

```kotlin
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun testUIElements() {
        onView(withId(R.id.recordButton)).check(matches(isDisplayed()))
        onView(withId(R.id.audioSourceRadioGroup)).check(matches(isDisplayed()))
    }
    
    @Test
    fun testAudioSourceSelection() {
        onView(withId(R.id.microphoneRadio)).perform(click())
        onView(withId(R.id.microphoneRadio)).check(matches(isChecked()))
    }
}
```

Run instrumentation tests:
```bash
./gradlew connectedAndroidTest
```

## Performance Testing

### Video Quality Check

1. Record a high-motion scene (scrolling, gaming)
2. Pull and analyze video:

```bash
# Check video properties
ffprobe -v error -show_streams ScreenRecord_*.mp4

# Expected output includes:
# - codec_name=h264
# - width=1080 (or device width)
# - height=2340 (or device height)
# - r_frame_rate=30/1
# - bit_rate~=5000000
```

### Battery Consumption

1. Note battery percentage
2. Record for 10 minutes
3. Note battery percentage
4. Calculate drain rate

Expected: ~3-5% per 10 minutes depending on device

### CPU Usage

Monitor with Android Profiler in Android Studio:
1. Start recording
2. Observe CPU usage
3. Expected: 5-15% on modern devices

### Memory Usage

Monitor with Android Profiler:
1. Start recording
2. Record for 5 minutes
3. Stop recording
4. Check for memory leaks

Expected: ~50-100MB memory usage, no leaks

## Regression Testing Checklist

Before each release, verify:

- [ ] App installs successfully
- [ ] App launches without crash
- [ ] All permissions requested correctly
- [ ] Recording starts with all audio options
- [ ] Recording stops cleanly
- [ ] Videos saved to correct location
- [ ] Videos playable in gallery apps
- [ ] Notification shows and functions
- [ ] App survives background/foreground transitions
- [ ] No ANR (Application Not Responding)
- [ ] No crashes in production logs

## Device Compatibility Testing

Test on devices with different characteristics:

| Device Type | Example | Key Checks |
|------------|---------|------------|
| Low-end | Entry level phone | Performance, no lag |
| Mid-range | Popular mid-tier | Standard functionality |
| High-end | Flagship phone | Quality, all features |
| Tablet | Any Android tablet | Resolution handling |
| Android 10 | API 29 device | Min version support |
| Android 14 | API 34 device | Latest version support |

## Common Issues and Solutions

### Issue: "Permission denied" for audio

**Solution:** 
- Check app permissions in device settings
- Ensure RECORD_AUDIO declared in manifest
- Request permission at runtime

### Issue: Video file not found

**Solution:**
- Check MediaStore query
- Verify storage permissions
- Look in app's external files directory

### Issue: Recording stops unexpectedly

**Solution:**
- Check device storage space
- Review logcat for errors
- Check for battery optimization killing service

### Issue: No audio in recording

**Solution:**
- Verify device supports internal audio capture
- Check if microphone permission granted
- Test on different device

### Issue: Poor video quality

**Solution:**
- Increase video bitrate (currently 5 Mbps)
- Adjust frame rate
- Check device CPU capabilities

## Logcat Commands

Monitor app logs during testing:

```bash
# View all app logs
adb logcat -s ScreenRecorderService MainActivity

# Clear logs and start fresh
adb logcat -c && adb logcat -s ScreenRecorderService MainActivity

# Save logs to file
adb logcat -s ScreenRecorderService MainActivity > test_logs.txt

# Filter by priority (Error and above)
adb logcat *:E ScreenRecorderService:D MainActivity:D
```

## Bug Reporting Template

When reporting bugs, include:

1. **Device:** Model, Android version
2. **Steps to reproduce:** Detailed steps
3. **Expected behavior:** What should happen
4. **Actual behavior:** What actually happens
5. **Logs:** Relevant logcat output
6. **Screenshots:** UI state if relevant
7. **Video:** Sample recording if applicable

## Test Coverage Goals

- UI tests: 80%+ of user flows
- Critical paths: 100% (start recording, stop recording, save video)
- Edge cases: Permission denials, low storage, interruptions
- Performance: No regressions in speed or quality
