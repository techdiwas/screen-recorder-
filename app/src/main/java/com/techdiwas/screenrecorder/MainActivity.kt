package com.techdiwas.screenrecorder

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.techdiwas.screenrecorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isRecording = false
    private lateinit var mediaProjectionManager: MediaProjectionManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.entries.all { it.value }
        if (allGranted) {
            startRecordingWithPermission()
        } else {
            Toast.makeText(this, R.string.permission_required, Toast.LENGTH_SHORT).show()
        }
    }

    private val mediaProjectionLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val audioSource = getSelectedAudioSource()
            val serviceIntent = Intent(this, ScreenRecorderService::class.java).apply {
                action = ScreenRecorderService.ACTION_START
                putExtra(ScreenRecorderService.EXTRA_RESULT_CODE, result.resultCode)
                putExtra(ScreenRecorderService.EXTRA_RESULT_DATA, result.data)
                putExtra(ScreenRecorderService.EXTRA_AUDIO_SOURCE, audioSource)
            }
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
            
            isRecording = true
            updateUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaProjectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        binding.recordButton.setOnClickListener {
            if (isRecording) {
                stopRecording()
            } else {
                checkPermissionsAndStartRecording()
            }
        }
    }

    private fun checkPermissionsAndStartRecording() {
        val permissions = mutableListOf<String>()
        
        // Check for audio permission based on selected source
        val audioSource = getSelectedAudioSource()
        if (audioSource == AudioSource.MICROPHONE) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.RECORD_AUDIO)
            }
        }

        // Check for notification permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        if (permissions.isEmpty()) {
            startRecordingWithPermission()
        } else {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    }

    private fun startRecordingWithPermission() {
        val intent = mediaProjectionManager.createScreenCaptureIntent()
        mediaProjectionLauncher.launch(intent)
    }

    private fun stopRecording() {
        val serviceIntent = Intent(this, ScreenRecorderService::class.java).apply {
            action = ScreenRecorderService.ACTION_STOP
        }
        startService(serviceIntent)
        
        isRecording = false
        updateUI()
    }

    private fun updateUI() {
        if (isRecording) {
            binding.recordButton.text = getString(R.string.stop_recording)
            binding.recordButton.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            binding.statusTextView.text = getString(R.string.recording_in_progress)
            binding.statusTextView.visibility = View.VISIBLE
            binding.audioSourceCard.isEnabled = false
            binding.audioSourceRadioGroup.isEnabled = false
            for (i in 0 until binding.audioSourceRadioGroup.childCount) {
                binding.audioSourceRadioGroup.getChildAt(i).isEnabled = false
            }
        } else {
            binding.recordButton.text = getString(R.string.start_recording)
            binding.recordButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_500))
            binding.statusTextView.visibility = View.GONE
            binding.audioSourceCard.isEnabled = true
            binding.audioSourceRadioGroup.isEnabled = true
            for (i in 0 until binding.audioSourceRadioGroup.childCount) {
                binding.audioSourceRadioGroup.getChildAt(i).isEnabled = true
            }
        }
    }

    private fun getSelectedAudioSource(): AudioSource {
        return when (binding.audioSourceRadioGroup.checkedRadioButtonId) {
            R.id.internalAudioRadio -> AudioSource.INTERNAL
            R.id.microphoneRadio -> AudioSource.MICROPHONE
            else -> AudioSource.NONE
        }
    }

    override fun onResume() {
        super.onResume()
        // Check if recording is active
        isRecording = ScreenRecorderService.isRunning
        updateUI()
    }
}

enum class AudioSource {
    INTERNAL,
    MICROPHONE,
    NONE
}
