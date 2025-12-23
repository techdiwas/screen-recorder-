# Quick Start Guide

Get started with Screen Recorder in just a few minutes!

## Installation

### Option 1: Download APK (Easiest)

1. Download the latest APK from the [Releases](https://github.com/techdiwas/screen-recorder-/releases) page
2. On your Android device, go to **Settings → Security → Unknown Sources** and enable installation from unknown sources
3. Tap the downloaded APK file
4. Follow the installation prompts
5. Done! The app is installed

### Option 2: Build from Source

```bash
# Clone the repository
git clone https://github.com/techdiwas/screen-recorder-.git
cd screen-recorder-

# Build the app
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

## First Recording

### Step 1: Launch the App

Find and tap the **Screen Recorder** icon in your app drawer.

### Step 2: Choose Audio Source

You'll see three options:
- **Internal Audio**: Records system sounds (music, games, videos)
- **Microphone**: Records your voice
- **No Audio**: Records video only

Select your preferred option. For your first recording, try **No Audio** to keep it simple.

### Step 3: Start Recording

1. Tap the **"Start Recording"** button
2. If prompted, grant permissions:
   - **Notification permission** (Android 13+): Allow to see recording status
   - **Microphone permission** (if you selected Microphone): Allow to record audio
3. You'll see a system dialog asking to "Start capturing everything on your screen"
4. Tap **"Start now"**

🎉 **Recording started!** You'll see:
- A persistent notification showing "Recording Screen"
- The button changed to "Stop Recording" (red color)
- Status message "Recording in progress…"

### Step 4: Use Your Device

The app will record everything on your screen:
- Navigate to other apps
- Play games
- Browse the web
- Watch videos
- Do anything you want to record

The recording continues in the background!

### Step 5: Stop Recording

Two ways to stop:

**Option A**: From the app
1. Open Screen Recorder app
2. Tap **"Stop Recording"** button

**Option B**: From the notification
1. Pull down the notification shade
2. Find the "Recording Screen" notification
3. Tap **"Stop"** button

### Step 6: View Your Recording

Your recording is automatically saved! Find it in:

**Gallery App**:
1. Open your device's Gallery/Photos app
2. Look for your recording in the videos section
3. Tap to play

**File Manager**:
1. Open any file manager app
2. Navigate to `Movies/ScreenRecordings/`
3. Find files named like `ScreenRecord_20241223_142530.mp4`

## Common Issues

### "Permission required" message
**Solution**: Grant the requested permission in Settings → Apps → Screen Recorder → Permissions

### Recording has no audio
**Solutions**:
- Ensure you selected "Internal Audio" or "Microphone" before recording
- Check that microphone permission is granted (for Microphone option)
- Some devices don't support internal audio capture - try microphone instead

### Can't find the recording
**Solution**: Open your Gallery app or file manager and check `Movies/ScreenRecordings/` folder

### App crashes
**Solution**: 
1. Force stop the app
2. Clear app cache (Settings → Apps → Screen Recorder → Storage → Clear Cache)
3. Try again

## Tips for Better Recordings

### 1. Charge Your Device
Screen recording uses battery. For long recordings, keep your device plugged in.

### 2. Free Up Storage
Check available storage before recording:
- 1 minute ≈ 40 MB
- 10 minutes ≈ 400 MB
- 1 hour ≈ 2.4 GB

### 3. Close Unnecessary Apps
Close background apps to improve performance and reduce battery drain.

### 4. Use Airplane Mode
If recording offline content, enable airplane mode to prevent interruptions from calls and notifications.

### 5. Adjust Brightness
Lower screen brightness to reduce battery consumption during recording.

### 6. Test First
Do a short test recording (10-30 seconds) to verify everything works before a long recording.

## Recording Tips by Use Case

### Gaming
- Select **Internal Audio** to capture game sounds
- Close other apps to maximize performance
- Keep device cool - gaming + recording generates heat

### Tutorial/Demo
- Select **Microphone** to narrate while recording
- Plan what you'll demonstrate beforehand
- Speak clearly and at a moderate pace

### Video Calls (Audio only captures your side)
- Select **Microphone** to record your audio
- Note: Other participants' audio won't be captured (privacy feature)

### Streaming Content
- Select **Internal Audio** to capture video audio
- Note: DRM-protected content (Netflix, etc.) will appear black in recordings

## Next Steps

Now that you've made your first recording, explore more:

1. **Try different audio sources**: Experiment with internal audio and microphone
2. **Share recordings**: Send recordings via messaging apps or social media
3. **Edit recordings**: Use a video editor app to trim or enhance your recordings
4. **Read documentation**: Check out the full documentation for advanced features

## Need Help?

- **FAQ**: Check [FAQ.md](FAQ.md) for common questions
- **Documentation**: Read [README.md](README.md) for detailed information
- **Issues**: Report bugs on [GitHub Issues](https://github.com/techdiwas/screen-recorder-/issues)

---

Happy Recording! 📹✨
