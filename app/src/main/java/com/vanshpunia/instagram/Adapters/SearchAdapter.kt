package com.vanshpunia.instagram.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.databinding.FragmentSearchBinding
import com.vanshpunia.instagram.databinding.SearchRvBinding

class SearchAdapter(var context: Context, var userList: ArrayList<User>): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(userList.get(position).image).into(holder.binding.profileImage)
        holder.binding.name.text = userList.get(position).name
    }
}