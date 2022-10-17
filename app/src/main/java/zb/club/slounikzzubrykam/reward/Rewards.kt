package zb.club.slounikzzubrykam.reward

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentRewardsBinding
import zb.club.slounikzzubrykam.databinding.FragmentTopicBinding


class Rewards : Fragment() {
    lateinit var  binding: FragmentRewardsBinding
    private var fragmentList = arrayListOf<Fragment>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_rewards, container,false)
      fragmentList = arrayListOf(
             Meal(),
             Friends()
         )



        setupViewPager()
        setupTabLayout()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.woosh)
                mediaPlayer?.start()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        return binding.root
    }

    private fun setupTabLayout() {
        TabLayoutMediator(
            binding.tabLayout, binding.viepager
        ) { tab, position ->
            when(position){
                0 -> tab.setIcon(R.drawable.ic_baseline_flatware_24)
                1 -> tab.setIcon(R.drawable.ic_toy)
            }

           }.attach()

    }


    private fun setupViewPager() {
        val adapter = RewardsAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viepager.adapter = adapter
    }



}