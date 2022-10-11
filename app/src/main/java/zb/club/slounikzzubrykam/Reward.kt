package zb.club.slounikzzubrykam

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil

import com.google.android.material.tabs.TabLayoutMediator
import zb.club.slounikzzubrykam.databinding.ActivityRewardBinding

class Reward : AppCompatActivity() {
    private lateinit var binding: ActivityRewardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_reward
        )
         binding.floatingActionButtonPlay.setOnClickListener {
             var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.tap1)
             mediaPlayer?.start()
             val intent =
                 android.content.Intent(this, zb.club.slounikzzubrykam.TopicList::class.java)
             startActivity(intent)
         }
        setupViewPager()
        setupTabLayout()


    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viepager
        ) { tab, position ->
            when(position) {
                0 -> {
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_flatware_24)
                }
                1 ->{
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_toy)
                }
                else -> {

                }}
        }.attach()

    }

    private fun setupViewPager() {
        val adapter = zb.club.slounikzzubrykam.AdapterReward(this, 2)
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









