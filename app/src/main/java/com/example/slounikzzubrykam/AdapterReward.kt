package com.example.slounikzzubrykam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AdapterReward (fragmentActivity: FragmentActivity, private var totalCount: Int) :
        FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return totalCount
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> Meal()
                1 -> Friends()
                else -> Meal()
            }
        }
    }

