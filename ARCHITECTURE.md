# Architecture Overview

## System Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                        User Interface                        │
│                                                               │
│  ┌────────────────────────────────────────────────────┐    │
│  │              MainActivity                           │    │
│  │  • Audio source selection (RadioGroup)             │    │
│  │  • Start/Stop button                               │    │
│  │  • Permission handling                             │    │
│  │  • MediaProjection launcher                        │    │
│  └─────────────────┬──────────────────────────────────┘    │
└────────────────────┼───────────────────────────────────────┘
                     │
                     │ Intent (ACTION_START / ACTION_STOP)
                     ↓
┌─────────────────────────────────────────────────────────────┐
│                     Service Layer                            │
│                                                               │
│  ┌────────────────────────────────────────────────────┐    │
│  │         ScreenRecorderService                       │    │
│  │  (Foreground Service)                               │    │
│  │                                                      │    │
│  │  ┌──────────────────────────────────────┐         │    │
│  │  │  Notification Management             │         │    │
│  │  │  • Create notification channel       │         │    │
│  │  │  • Show foreground notification      │         │    │
│  │  │  • Handle stop action                │         │    │
│  │  └──────────────────────────────────────┘         │    │
│  │                                                      │    │
│  │  ┌──────────────────────────────────────┐         │    │
│  │  │  Recording Management                │         │    │
│  │  │  • MediaProjection setup             │         │    │
│  │  │  • VirtualDisplay creation           │         │    │
│  │  │  • MediaRecorder configuration       │         │    │
│  │  │  • Audio source handling             │         │    │
│  │  └──────────────────────────────────────┘         │    │
│  │                                                      │    │
│  │  ┌──────────────────────────────────────┐         │    │
│  │  │  Storage Management                  │         │    │
│  │  │  • Temporary file creation           │         │    │
│  │  │  • MediaStore integration            │         │    │
│  │  │  • File cleanup                      │         │    │
│  │  └──────────────────────────────────────┘         │    │
│  └─────────────────┬──────────────────────────────────┘    │
└────────────────────┼───────────────────────────────────────┘
                     │
                     │ MediaProjection
                     ↓
┌─────────────────────────────────────────────────────────────┐
│                    Android Framework                         │
│                                                               │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │MediaProjection│  │ VirtualDisplay│  │MediaRecorder │     │
│  │  Manager      │  │               │  │              │     │
│  └──────┬───────┘  └──────┬───────┘  └──────┬───────┘     │
│         │                  │                  │              │
│         └──────────────────┴──────────────────┘              │
│                            │                                 │
└────────────────────────────┼─────────────────────────────────┘
                             │
                             ↓
┌─────────────────────────────────────────────────────────────┐
│                    Storage Layer                             │
│                                                               │
│  ┌─────────────────────┐      ┌──────────────────────┐     │
│  │  Temporary Storage  │  →   │   MediaStore API     │     │
│  │  /data/.../Movies/  │      │  /Movies/Screen...   │     │
│  │  ScreenRecordings/  │      │                       │     │
│  └─────────────────────┘      └──────────────────────┘     │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

## Component Interactions

### 1. Starting a Recording

```
User
  │
  │ Tap "Start Recording"
  ↓
MainActivity
  │
  ├─→ Check permissions (RECORD_AUDIO, POST_NOTIFICATIONS)
  │   │
  │   └─→ Request if missing
  │
  ├─→ Launch MediaProjection intent
  │   │
  │   └─→ User grants permission
  │
  └─→ Start ScreenRecorderService (ACTION_START)
      │
      ├─→ Create foreground notification
      │
      ├─→ Initialize MediaRecorder
      │   ├─ Set audio source (INTERNAL/MIC/NONE)
      │   ├─ Set video source (SURFACE)
      │   ├─ Set output format (MPEG_4)
      │   ├─ Set encoders (H264, AAC)
      │   └─ Prepare
      │
      ├─→ Get MediaProjection from result
      │
      ├─→ Create VirtualDisplay
      │   └─ Connect to MediaRecorder surface
      │
      └─→ Start recording
```

### 2. During Recording

```
ScreenRecorderService (Foreground)
  │
  ├─→ VirtualDisplay
  │   └─ Captures screen content
  │       └─→ Sends to MediaRecorder surface
  │
  ├─→ MediaRecorder
  │   ├─ Encodes video (H.264)
  │   ├─ Encodes audio (AAC)
  │   └─ Writes to temporary file
  │
  └─→ Notification
      └─ Shows recording status
          └─ Provides stop action
```

### 3. Stopping a Recording

```
User
  │
  │ Tap "Stop" (Activity or Notification)
  ↓
MainActivity/NotificationAction
  │
  └─→ Send ACTION_STOP to ScreenRecorderService
      │
      ├─→ Stop MediaRecorder
      │
      ├─→ Release VirtualDisplay
      │
      ├─→ Stop MediaProjection
      │
      ├─→ Save to MediaStore
      │   ├─ Insert video entry
      │   ├─ Copy file content
      │   └─ Mark as complete
      │
      ├─→ Delete temporary file
      │
      └─→ Stop foreground service
```

## Data Flow

### Video/Audio Pipeline

```
Screen Content → VirtualDisplay → MediaRecorder Surface
                                         ↓
Audio Input   → Audio Source    → MediaRecorder
(Mic/Internal)                          ↓
                                   H.264 Encoder
                                         ↓
                                   AAC Encoder (if audio)
                                         ↓
                                   MP4 Muxer
                                         ↓
                                   Temporary File
                                         ↓
                                   MediaStore
                                         ↓
                              Movies/ScreenRecordings
```

## Permission Flow

```
┌─────────────────┐
│  App Launch     │
└────────┬────────┘
         │
         ↓
┌─────────────────────────────┐
│ User selects audio source   │
└────────┬────────────────────┘
         │
         ↓
    ┌───────┐
    │ MIC ? │──No──→ Skip RECORD_AUDIO
    └───┬───┘
        │Yes
        ↓
┌───────────────────────┐
│ Check RECORD_AUDIO    │
└───────┬───────────────┘
        │
    ┌───┴────┐
    │Granted?│──Yes──┐
    └───┬────┘       │
        │No          │
        ↓            │
┌───────────────┐   │
│Request permission│ │
└───────┬───────┘   │
        │           │
        └─────┬─────┘
              │
              ↓
    ┌─────────────────────┐
    │Check POST_NOTIFICATIONS│
    │  (Android 13+)       │
    └─────────┬───────────┘
              │
          ┌───┴────┐
          │Granted?│──Yes──┐
          └───┬────┘       │
              │No          │
              ↓            │
      ┌───────────────┐   │
      │Request permission│ │
      └───────┬───────┘   │
              │           │
              └─────┬─────┘
                    │
                    ↓
          ┌──────────────────┐
          │Launch MediaProjection│
          │   consent dialog  │
          └─────────┬────────┘
                    │
                ┌───┴────┐
                │Granted?│──Yes──→ Start Recording
                └───┬────┘
                    │No
                    ↓
              ┌─────────┐
              │  Abort  │
              └─────────┘
```

## State Diagram

### Service States

```
┌─────────┐
│ STOPPED │
└────┬────┘
     │ ACTION_START
     ↓
┌────────────┐
│ STARTING   │
│ • Creating │
│   resources│
└────┬───────┘
     │ Success
     ↓
┌────────────┐
│ RECORDING  │ ←──┐
│ • Active   │    │ Continue
│ • Encoding │    │
└────┬───────┘    │
     │ ACTION_STOP│
     ↓            │
┌────────────┐    │
│ STOPPING   │    │
│ • Saving   │────┘ Error
└────┬───────┘
     │ Complete
     ↓
┌─────────┐
│ STOPPED │
└─────────┘
```

## Thread Model

```
Main Thread (UI Thread)
  │
  ├─ MainActivity
  │  ├─ UI interactions
  │  ├─ Permission requests
  │  └─ Service communication
  │
Background Service Thread
  │
  └─ ScreenRecorderService
     ├─ MediaRecorder operations
     ├─ VirtualDisplay management
     └─ File I/O operations
     
MediaRecorder Internal Thread
  │
  └─ Video/Audio encoding
  
Notification Thread
  │
  └─ Notification updates
```

## Security Considerations

1. **MediaProjection Permission**
   - System-level permission
   - User must explicitly grant each time
   - Cannot be persisted

2. **Audio Recording**
   - Runtime permission required
   - Can be revoked at any time
   - Graceful degradation to video-only

3. **Storage Access**
   - MediaStore API (scoped storage)
   - No broad storage permissions needed
   - App-specific temporary storage

4. **Foreground Service**
   - Must show notification
   - Cannot be hidden or silent
   - Type-specific (mediaProjection)

## Performance Considerations

1. **Video Encoding**
   - Hardware-accelerated H.264
   - 5 Mbps bitrate (adjustable)
   - 30 fps (adjustable)

2. **Memory Usage**
   - ~50-100 MB during recording
   - Circular buffers for encoding
   - Released when stopped

3. **CPU Usage**
   - ~5-15% on modern devices
   - Varies with resolution
   - GPU assists when available

4. **Battery Impact**
   - ~3-5% per 10 minutes
   - Screen keeps on (implicit)
   - Wake lock held

## Error Handling Strategy

```
Error Category → Handling Strategy → User Impact
───────────────────────────────────────────────
MediaRecorder    → Log + Stop      → Recording fails
VirtualDisplay   → Log + Stop      → Recording fails
Storage Full     → Log + Stop      → Recording incomplete
Permission Denied→ Show Toast      → Recording blocked
Service Killed   → Restart attempt → Recording lost
```
