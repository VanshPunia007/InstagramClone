package com.vanshpunia.instagram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.Utils.USER_NODE
import com.vanshpunia.instagram.Utils.USER_PROFILE_FOLDER
import com.vanshpunia.instagram.Utils.uploadImage
import com.vanshpunia.instagram.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    val binding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    lateinit var user: User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) {
                if(it == null){

                }else{
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        binding.profileImage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.signUpBtn.setOnClickListener {
            if(binding.name.editText?.text.toString().equals("") or
                binding.email.editText?.text.toString().equals("")  or
                binding.password.editText?.text.toString().equals("")
            ){
                Toast.makeText(this@SignUpActivity, "Please fill required info", Toast.LENGTH_SHORT ).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.email.editText?.text.toString(),
                    binding.password.editText?.text.toString()
                ).addOnCompleteListener {
                    result->

                    if(result.isSuccessful){
                        Toast.makeText(this@SignUpActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                        user.name = binding.name.editText?.text.toString()
                        user.email = binding.email.editText?.text.toString()
                        user.password = binding.password.editText?.text.toString()
                        Firebase.firestore.collection(USER_NODE)
                            .document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                startActivity(Intent(this@SignUpActivity, HomeActivity:: class.java))
                            }
                    }else{
                        Toast.makeText(this@SignUpActivity,
                            result.exception?.localizedMessage ?: null, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}