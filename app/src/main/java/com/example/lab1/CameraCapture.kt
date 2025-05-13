package com.example.lab1

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraCapture(private val context: Context) {

    private var imageCapture: ImageCapture? = null

    init {
        // Initialize the ImageCapture
        imageCapture = ImageCapture.Builder().build()
    }

    fun takePicture(callback: ImageCapture.OnImageSavedCallback) {
        val outputOptions = ImageCapture.OutputFileOptions.Builder(createOutputFile()).build()
        imageCapture?.takePicture(outputOptions, ContextCompat.getMainExecutor(context), callback)
    }

    private fun createOutputFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "IMG_$timeStamp.jpg"
        val externalFilesDirs = context.getExternalFilesDirs(null)
        val file = File(externalFilesDirs.firstOrNull(), fileName)
        file.parentFile?.mkdirs() // Ensure the parent directory exists
        return file
    }

}




