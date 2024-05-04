package com.vanshpunia.instagram.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.vanshpunia.instagram.Adapters.ReelAdapter
import com.vanshpunia.instagram.Models.Reel
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.Utils.REEL
import com.vanshpunia.instagram.databinding.FragmentReelBinding


class ReelFragment : Fragment() {
    private lateinit var binding : FragmentReelBinding
    var reelList = ArrayList<Reel>()
    lateinit var adapter: ReelAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReelBinding.inflate(inflater, container, false)
        adapter = ReelAdapter(requireContext(), reelList)
        binding.viewPager.adapter = adapter
        Firebase.firestore.collection(REEL).get().addOnSuccessListener {
            var tempList = arrayListOf<Reel>()
            reelList.clear()
            for(i in it.documents){
                var reel= i.toObject<Reel>()!!
                tempList.add(reel)
            }
            reelList.addAll(tempList)
//            reelList.reverse()
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {
    }
}