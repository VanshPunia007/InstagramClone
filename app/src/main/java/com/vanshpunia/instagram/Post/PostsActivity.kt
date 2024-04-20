package com.vanshpunia.instagram.Post

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {
    val binding by lazy{
        ActivityPostsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT
        setSupportActionBar(binding.materialToolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener{
            finish()
        }
    }
}