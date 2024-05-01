package com.vanshpunia.instagram.Post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.vanshpunia.instagram.HomeActivity
import com.vanshpunia.instagram.Models.Reel
import com.vanshpunia.instagram.Utils.POST
import com.vanshpunia.instagram.Utils.REEL
import com.vanshpunia.instagram.Utils.REEL_FOLDER
import com.vanshpunia.instagram.Utils.uploadVideo
import com.vanshpunia.instagram.databinding.ActivityReelsBinding

class ReelsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityReelsBinding.inflate(layoutInflater)
    }
    var videoUrl: String? = null
    lateinit var progressDialog : ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER, progressDialog) {
                    url->
                if (url != null) {
                    videoUrl = url
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        progressDialog = ProgressDialog(this)
        setSupportActionBar(binding.materialToolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
        binding.selectReel.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.post.setOnClickListener {
            val reel: Reel = Reel(videoUrl!!, binding.caption.editText?.text.toString())
            Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document().set(reel).addOnSuccessListener {
                    startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                    finish()
                }
            }
        }
        binding.cancel.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
    }
}