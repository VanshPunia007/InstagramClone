package com.vanshpunia.instagram.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.vanshpunia.instagram.Adapters.SearchAdapter
import com.vanshpunia.instagram.Models.User
import com.vanshpunia.instagram.R
import com.vanshpunia.instagram.Utils.USER_NODE
import com.vanshpunia.instagram.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    lateinit var binding : FragmentSearchBinding
    var userList = ArrayList<User>()
    lateinit var adpater : SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        adpater = SearchAdapter(requireContext(), userList)
        binding.rv.adapter = adpater

        Firebase.firestore.collection(USER_NODE).get().addOnSuccessListener {
            var tempList = ArrayList<User>()
            userList.clear()
            for(i in it.documents){
                if(i.id.toString() != Firebase.auth.currentUser!!.uid.toString()){
                    var user: User = i.toObject<User>()!!
                    tempList.add(user!!)
                }
            }

            userList.addAll(tempList)
            adpater.notifyDataSetChanged()
        }
        binding.searchButton.setOnClickListener {
            var text = binding.searchView.text.toString()
            Firebase.firestore.collection(USER_NODE).whereEqualTo("name", text).get().addOnSuccessListener {
                var tempList = ArrayList<User>()
                userList.clear()
                if(it.isEmpty ){

                }
                else{
                    for(i in it.documents){
                        if(i.id.toString() != Firebase.auth.currentUser!!.uid.toString()){
                            var user: User = i.toObject<User>()!!
                            tempList.add(user!!)
                        }
                    }
                    userList.addAll(tempList)
                    adpater.notifyDataSetChanged()
                }
            }
        }
        return binding.root
    }

    companion object {
    }
}