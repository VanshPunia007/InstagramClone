package com.vanshpunia.instagram.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.databinding.FollowRvBinding

class FollowAdapter(var context: Context, var followList: ArrayList<User>): RecyclerView.Adapter<FollowAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: FollowRvBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = FollowRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return followList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(followList.get(position).image).into(holder.binding.userImage)
        holder.binding.name.text = followList.get(position).name
    }
}