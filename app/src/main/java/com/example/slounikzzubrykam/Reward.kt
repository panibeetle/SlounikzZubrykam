package com.example.slounikzzubrykam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.slounikzzubrykam.databinding.ActivityMainBinding
import com.example.slounikzzubrykam.databinding.ActivityRewardBinding
import com.example.slounikzzubrykam.databinding.FragmentFriendsBinding
import com.google.android.material.tabs.TabLayoutMediator

class Reward : AppCompatActivity() {
    private lateinit var binding: ActivityRewardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_reward
        )
         binding.floatingActionButtonPlay.setOnClickListener {
             val intent =
                 android.content.Intent(this, com.example.slounikzzubrykam.TopicList::class.java)
             startActivity(intent)
         }
        setupViewPager()
        setupTabLayout()


    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viepager
        ) { tab, position -> tab.text = "Tab " + (position + 1) }.attach()

    }

    private fun setupViewPager() {
        val adapter = AdapterReward(this, 2)
        binding.viepager.adapter = adapter
    }

    override fun onBackPressed() {
        val viewPager = binding.viepager
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

}









