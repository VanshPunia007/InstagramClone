package com.vanshpunia.instagram.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.vanshpunia.instagram.Models.Post
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.Utils.USER_NODE
import com.vanshpunia.instagram.databinding.PostRvBinding

class PostAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.MyHolder>() {
    inner class MyHolder(var binding: PostRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var binding = PostRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        try {
            Firebase.firestore.collection(USER_NODE).document(postList.get(position).uid!!).get()
                .addOnSuccessListener {
                    var user = it.toObject<User>()
                    Glide.with(context).load(user!!.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
                    holder.binding.name.text = user.name
                }
            holder.binding.time.text = postList.get(position).time!!
        } catch (e: Exception) {

        }

        Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.loading).into(holder.binding.postImage)
        holder.binding.caption.text = postList.get(position).caption!!
    }
}