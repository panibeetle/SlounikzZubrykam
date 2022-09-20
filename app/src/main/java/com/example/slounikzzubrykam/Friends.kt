package com.example.slounikzzubrykam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.slounikzzubrykam.databinding.FragmentFriendsBinding
import com.example.slounikzzubrykam.databinding.FragmentMealBinding

class Friends : Fragment() {
    private lateinit var binding: FragmentFriendsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)

        binding = FragmentFriendsBinding.inflate(layoutInflater)
        return binding.root
    }

}