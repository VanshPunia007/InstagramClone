package com.vanshpunia.instagram.Post

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.vanshpunia.instagram.HomeActivity
import com.vanshpunia.instagram.Models.Post
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.Utils.POST
import com.vanshpunia.instagram.Utils.POST_FOLDER
import com.vanshpunia.instagram.Utils.USER_NODE
import com.vanshpunia.instagram.Utils.uploadImage
import com.vanshpunia.instagram.databinding.ActivityPostsBinding

class PostsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostsBinding.inflate(layoutInflater)
    }
    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
                url->
                if (url != null) {
                    binding.postImage.setImageURI(uri)
                    imageUrl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT
        setSupportActionBar(binding.materialToolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostsActivity, HomeActivity::class.java))
            finish()
        }
        binding.postImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.post.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user = it.toObject<User>()!!

                var imgUrl : String = imageUrl!!
                var caption : String = binding.caption.editText?.text.toString()
                var uid = Firebase.auth.currentUser!!.uid
                var time = System.currentTimeMillis().toString()

                val post: Post = Post(imgUrl, caption, uid, time)

                Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post).addOnSuccessListener {
                        startActivity(Intent(this@PostsActivity, HomeActivity::class.java))
                        finish()
                    }
                }
            }
        }
        binding.cancel.setOnClickListener {
            startActivity(Intent(this@PostsActivity, HomeActivity::class.java))
            finish()
        }

    }
}