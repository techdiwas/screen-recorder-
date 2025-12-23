# API Documentation

## MainActivity

Main activity that provides the user interface for the screen recorder.

### Key Methods

#### `checkPermissionsAndStartRecording()`
Checks required permissions before starting recording:
- RECORD_AUDIO (if microphone audio selected)
- POST_NOTIFICATIONS (Android 13+)
- Requests missing permissions or proceeds with recording if all granted

#### `startRecordingWithPermission()`
Launches MediaProjection screen capture intent after permissions are granted.

#### `stopRecording()`
Sends stop action to ScreenRecorderService to end recording.

#### `updateUI()`
Updates UI state based on recording status:
- Changes button text and color
- Disables audio source selection during recording
- Shows/hides status text

#### `getSelectedAudioSource(): AudioSource`
Returns currently selected audio source from radio group.

### Activity Result Contracts

#### `requestPermissionLauncher`
- Type: `RequestMultiplePermissions`
- Purpose: Handle runtime permission requests
- Callback: Starts recording if all permissions granted

#### `mediaProjectionLauncher`
- Type: `StartActivityForResult`
- Purpose: Handle MediaProjection consent dialog
- Callback: Starts ScreenRecorderService with projection data

---

## ScreenRecorderService

Foreground service that handles screen recording operations.

### Service Actions

#### `ACTION_START`
Starts screen recording with the following extras:
- `EXTRA_RESULT_CODE`: MediaProjection result code (Int)
- `EXTRA_RESULT_DATA`: MediaProjection intent data (Intent)
- `EXTRA_AUDIO_SOURCE`: Selected audio source (Int - AudioSource ordinal)

#### `ACTION_STOP`
Stops current recording and saves the video.

### Key Methods

#### `startRecording(resultCode: Int, data: Intent, audioSource: AudioSource)`
Initializes and starts screen recording:
1. Creates foreground notification
2. Sets up output file
3. Configures MediaRecorder with video/audio settings
4. Creates MediaProjection from result data
5. Creates VirtualDisplay to capture screen
6. Starts MediaRecorder

##### MediaRecorder Configuration:
- **Video Source**: MediaRecorder.VideoSource.SURFACE
- **Audio Source**: 
  - MediaRecorder.AudioSource.DEFAULT (internal audio, Android 10+)
  - MediaRecorder.AudioSource.MIC (microphone)
  - None (video only)
- **Output Format**: MediaRecorder.OutputFormat.MPEG_4
- **Video Encoder**: MediaRecorder.VideoEncoder.H264
- **Audio Encoder**: MediaRecorder.AudioEncoder.AAC (if audio enabled)
- **Video Bitrate**: 5 Mbps
- **Frame Rate**: 30 fps
- **Video Size**: Device native resolution
- **Audio Bitrate**: 128 kbps
- **Audio Sample Rate**: 44.1 kHz

#### `stopRecording()`
Stops recording and cleans up resources:
1. Stops and releases MediaRecorder
2. Releases VirtualDisplay
3. Stops MediaProjection
4. Saves video to MediaStore
5. Deletes temporary file
6. Stops foreground service

#### `createVideoFile(): File`
Creates temporary video file with timestamp in app's external files directory.

Format: `ScreenRecord_yyyyMMdd_HHmmss.mp4`

Location: `/storage/emulated/0/Android/data/com.techdiwas.screenrecorder/files/Movies/ScreenRecordings/`

#### `saveVideoToMediaStore(file: File)`
Saves recorded video to MediaStore:
1. Creates ContentValues with video metadata
2. Inserts into MediaStore.Video.Media.EXTERNAL_CONTENT_URI
3. Copies file content to MediaStore output stream
4. Marks as not pending (Android 10+)
5. Deletes temporary file

Final location: `/storage/emulated/0/Movies/ScreenRecordings/`

#### `createNotification(): Notification`
Creates foreground service notification with:
- Title: "Recording Screen"
- Content: "Tap to stop recording"
- Stop action button
- Click to open MainActivity

---

## AudioSource Enum

Enum class defining available audio sources:

- `INTERNAL`: System/internal audio (Android 10+)
- `MICROPHONE`: Microphone audio
- `NONE`: Video only, no audio

---

## Permissions

### Required Permissions

1. **RECORD_AUDIO** (Dangerous)
   - Required for: Microphone audio recording
   - Runtime request: Yes (only if microphone selected)
   - Fallback: Recording continues without audio

2. **FOREGROUND_SERVICE** (Normal)
   - Required for: Running foreground service
   - Runtime request: No
   - Declared in manifest

3. **FOREGROUND_SERVICE_MEDIA_PROJECTION** (Normal)
   - Required for: Media projection foreground service (Android 14+)
   - Runtime request: No
   - Declared in manifest

4. **POST_NOTIFICATIONS** (Dangerous, Android 13+)
   - Required for: Showing notifications
   - Runtime request: Yes
   - Fallback: Notification may not show on Android 13+

5. **WAKE_LOCK** (Normal)
   - Required for: Keeping device awake during recording
   - Runtime request: No
   - Declared in manifest

---

## Data Flow

### Starting Recording

```
User taps "Start Recording"
    ↓
MainActivity checks permissions
    ↓
Request missing permissions (if any)
    ↓
Launch MediaProjection intent
    ↓
User grants screen capture permission
    ↓
MainActivity starts ScreenRecorderService
    ↓
Service creates foreground notification
    ↓
Service initializes MediaRecorder
    ↓
Service creates VirtualDisplay
    ↓
Recording begins
```

### Stopping Recording

```
User taps "Stop Recording" or notification action
    ↓
MainActivity sends ACTION_STOP to service
    ↓
Service stops MediaRecorder
    ↓
Service releases VirtualDisplay
    ↓
Service stops MediaProjection
    ↓
Service saves video to MediaStore
    ↓
Service deletes temporary file
    ↓
Service stops itself
    ↓
Recording complete
```

---

## Error Handling

### Common Errors

1. **MediaRecorder.start() throws IllegalStateException**
   - Cause: MediaRecorder not properly configured
   - Handling: Logged, recording aborted, service stopped

2. **MediaProjection is null**
   - Cause: Invalid result code or data
   - Handling: Recording not started

3. **MediaStore write fails**
   - Cause: Insufficient storage, permissions issue
   - Handling: Logged, temporary file kept for manual recovery

4. **Audio recording fails**
   - Cause: Audio source busy, permission denied
   - Handling: Falls back to video-only recording

---

## Testing APIs

### Manual Testing with ADB

Start recording:
```bash
adb shell am start-foreground-service \
    -n com.techdiwas.screenrecorder/.ScreenRecorderService \
    -a com.techdiwas.screenrecorder.ACTION_START
```

Stop recording:
```bash
adb shell am start-foreground-service \
    -n com.techdiwas.screenrecorder/.ScreenRecorderService \
    -a com.techdiwas.screenrecorder.ACTION_STOP
```

View saved videos:
```bash
adb shell ls -l /storage/emulated/0/Movies/ScreenRecordings/
```

Pull video from device:
```bash
adb pull /storage/emulated/0/Movies/ScreenRecordings/ScreenRecord_*.mp4 .
```

---

## Version Compatibility

| Feature | Min API | Notes |
|---------|---------|-------|
| Screen Recording | 29 | MediaProjection available from API 21, but targeting 29+ |
| Internal Audio | 29 | Android 10+ only |
| Scoped Storage | 29 | MediaStore without WRITE_EXTERNAL_STORAGE |
| Foreground Service Type | 29 | mediaProjection type required |
| Notification Permission | 33 | POST_NOTIFICATIONS required on Android 13+ |

---

## Future Enhancements

Potential features for future versions:

1. Pause/Resume recording
2. Quality settings (resolution, bitrate, fps)
3. Timer for scheduled recording
4. Video editor (trim, crop)
5. Screenshot capture
6. GIF conversion
7. Cloud upload integration
8. Drawing/annotation during recording
9. Face cam overlay
10. Custom countdown timer
