package com.vanshpunia.instagram

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.vanshpunia.instagram.databinding.ActivityFullscreenImageBinding

class FullscreenImageActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityFullscreenImageBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val imageUri = intent.getStringExtra("image_uri")

        Glide.with(this).load(imageUri).into(binding.postImage)
    }
}