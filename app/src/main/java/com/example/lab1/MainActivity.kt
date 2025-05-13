package com.example.lab1

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private var captureBack: CameraCapture? = null
    private var captureFront: CameraCapture? = null

    // File variables to store captured images
    private lateinit var photoFileBack: File
    private lateinit var photoFileFront: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Request camera permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
            return
        }

        // Initialize CameraX for back camera
        captureBack = CameraCapture(this)

        // Initialize CameraX for front camera
        captureFront = CameraCapture(this)

        // Button click listener to capture photo
        captureButton.setOnClickListener { capturePhotos() }
    }

    private fun capturePhotos() {
        // Capture photo from the back camera
        captureBack?.takePicture(object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFileBack)
                val msg = "Photo capture succeeded: $savedUri"
                Log.d(TAG, msg)
                // Process captured image data from back camera
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                // Handle error
            }
        })

        // Capture photo from the front camera
        captureFront?.takePicture(object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFileFront)
                val msg = "Photo capture succeeded: $savedUri"
                Log.d(TAG, msg)
                // Process captured image data from front camera
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                // Handle error
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}


