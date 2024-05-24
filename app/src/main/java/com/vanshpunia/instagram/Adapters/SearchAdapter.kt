package com.vanshpunia.instagram.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.Utils.FOLLOW
import com.vanshpunia.instagram.databinding.SearchRvBinding

class SearchAdapter(var context: Context, var userList: ArrayList<User>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isFollow = false
        Glide.with(context).load(userList.get(position).image).into(holder.binding.profileImage)
        holder.binding.name.text = userList.get(position).name
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
            .whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {
                if (it.documents.size == 0) {
                    isFollow = false
                } else {
                    holder.binding.follow.text = "Following"
                    isFollow = true
                }
            }
        holder.binding.follow.setOnClickListener {
            if (isFollow) {
                Toast.makeText(context, "Unfollowed " + userList.get(position).name, Toast.LENGTH_SHORT).show()
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
                    .whereEqualTo("email", userList.get(position).email).get()
                    .addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
                            .document(it.documents.get(0).id).delete()
                        holder.binding.follow.text = "Follow"
                    }
                isFollow = false
            } else {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
                    .document()
                    .set(userList.get(position))
                holder.binding.follow.text = "Following"
                isFollow = true
            }
        }
    }
}