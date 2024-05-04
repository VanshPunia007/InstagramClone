package com.vanshpunia.instagram.Fragment

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.vanshpunia.instagram.Adapters.MyPostRvAdapter
import com.vanshpunia.instagram.Adapters.MyReelsRvAdapter
import com.vanshpunia.instagram.Models.Post
import com.vanshpunia.instagram.Models.Reel
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.Utils.REEL
import com.vanshpunia.instagram.databinding.FragmentMyReelsBinding

class MyReelsFragment : Fragment() {
    private lateinit var binding : FragmentMyReelsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyReelsBinding.inflate(inflater, container, false)
        var reelList = ArrayList<Reel>()
        var adapter = MyReelsRvAdapter(requireContext(), reelList)
        binding.rv.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).
        get().addOnSuccessListener {

            var tempList = arrayListOf<Reel>()
            for (i in it.documents) {
                var reel: Reel = i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
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