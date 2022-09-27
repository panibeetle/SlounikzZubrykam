package zb.club.slounikzzubrykam

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
                0 -> zb.club.slounikzzubrykam.Meal()
                1 -> zb.club.slounikzzubrykam.Friends()
                else -> zb.club.slounikzzubrykam.Meal()
            }
        }
    }

