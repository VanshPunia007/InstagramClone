package com.vanshpunia.instagram.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vanshpunia.instagram.Post.PostsActivity
import com.vanshpunia.instagram.Post.ReelsActivity
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.databinding.FragmentAddBinding
import com.vanshpunia.instagram.databinding.FragmentMyReelsBinding


class AddFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.post.setOnClickListener {
            activity?.startActivity(Intent(requireContext(), PostsActivity::class.java))
        }
        binding.reel.setOnClickListener {
        }

        return binding.root
    }

    companion object {

    }


}