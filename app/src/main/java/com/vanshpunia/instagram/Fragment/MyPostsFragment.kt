package com.vanshpunia.instagram.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.vanshpunia.instagram.Adapters.MyPostRvAdapter
import com.vanshpunia.instagram.Models.Post
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.databinding.FragmentMyPostsBinding

class MyPostsFragment : Fragment() {
    private lateinit var binding: FragmentMyPostsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyPostsBinding.inflate(inflater, container, false)
        var postList = ArrayList<Post>()
        var adapter = MyPostRvAdapter(requireContext(), postList)
        binding.rv.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).
            get().addOnSuccessListener {

            var tempList = arrayListOf<Post>()
            for (i in it.documents) {
                var post: Post = i.toObject<Post>()!!
                tempList.add(post)
            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

    }

    override fun onStart() {
        super.onStart()
    }
}