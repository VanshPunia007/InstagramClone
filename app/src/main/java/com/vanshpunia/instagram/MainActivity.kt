package com.vanshpunia.instagram

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vanshpunia.instagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var view: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)

        window.statusBarColor = Color.TRANSPARENT
        Handler(Looper.getMainLooper()).postDelayed({
            if(FirebaseAuth.getInstance().currentUser == null){
                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }, 3000)
    }
}