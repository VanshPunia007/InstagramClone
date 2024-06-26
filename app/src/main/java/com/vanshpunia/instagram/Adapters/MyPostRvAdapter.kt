package com.vanshpunia.instagram.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import com.vanshpunia.instagram.FullscreenImageActivity
import com.vanshpunia.instagram.Models.Post
import com.vanshpunia.instagram.databinding.MyPostRvDesignBinding

class MyPostRvAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<MyPostRvAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: MyPostRvDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MyPostRvDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(postList.get(position).postUrl).into(holder.binding.postImage)
        holder.binding.postImage.setOnClickListener {
            val intent = Intent(holder.binding.root.context, FullscreenImageActivity::class.java)
            intent.putExtra("image_uri", postList.get(position).postUrl)
            holder.binding.root.context.startActivity(intent)
        }
    }
}