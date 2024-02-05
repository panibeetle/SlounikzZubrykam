package zb.club.slounikzzubrykam.reward


import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentFriendsBinding
import zb.club.slounikzzubrykam.dataclasses.Score
import zb.club.slounikzzubrykam.dataclasses.WordViewModel

class Friends : Fragment() {

    private lateinit var binding: FragmentFriendsBinding
    lateinit var  sharedPref: SharedPreferences



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        sharedPref = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        var heart = sharedPref.getInt("heart",0)
        binding.floatingActionButtonFriend.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
            mediaPlayer?.start()
            findNavController()?.navigate(R.id.action_rewards_to_topicFragment)
        }

        binding.textViewRewardHeart.text = heart.toString()
        if(heart < binding.textViewCostFriendOne.text.toString().toInt()){
            binding.imageFriendOne.alpha = 0.2f
            binding.imageFriendOne.isClickable = false
            }else{
                binding.imageFriendOne.alpha = 1f
                binding.imageFriendOne.isClickable = true
                binding.imageFriendOne.setOnClickListener {
                    val decreaseHeart = heart - binding.textViewCostFriendOne.text.toString().toInt()
                    with(sharedPref!!.edit()) {
                        putInt("heart",decreaseHeart)
                        apply()
                    }
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCostFriendOne.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
        if(heart < binding.textViewCoastFriendTwo.text.toString().toInt()){
            binding.imageFriendTwo.alpha = 0.2f
            binding.imageFriendTwo.isClickable = false
        }else{
            binding.imageFriendTwo.alpha = 1f
            binding.imageFriendTwo.isClickable = true
            binding.imageFriendTwo.setOnClickListener {
                val decreaseHeart = heart - binding.textViewCoastFriendTwo.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("heart",decreaseHeart)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastFriendTwo.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
        if(heart < binding.textViewCoastFriendTree.text.toString().toInt()){
            binding.imageFriendThree.alpha = 0.2f
            binding.imageFriendThree.isClickable = false
        }else{
            binding.imageFriendThree.alpha = 1f
            binding.imageFriendThree.isClickable = true
            binding.imageFriendThree.setOnClickListener {
                val decreaseHeart = heart - binding.textViewCoastFriendTree.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("heart",decreaseHeart)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastFriendTree.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
        if(heart < binding.textViewCoastFriendFor.text.toString().toInt()){
            binding.imageFriendFour.alpha = 0.2f
            binding.imageFriendFour.isClickable = false
        }else{
            binding.imageFriendFour.alpha = 1f
            binding.imageFriendFour.isClickable = true
            binding.imageFriendFour.setOnClickListener {
                val decreaseHeart = heart - binding.textViewCoastFriendFor.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("heart",decreaseHeart)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastFriendFor.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
        if(heart < binding.textViewFriendFive.text.toString().toInt()){
            binding.imageFriendFive.alpha = 0.2f
            binding.imageFriendFive.isClickable = false
        }else{
            binding.imageFriendFive.alpha = 1f
            binding.imageFriendFive.isClickable = true
            binding.imageFriendFive.setOnClickListener {
                val decreaseHeart = heart - binding.textViewFriendFive.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("heart",decreaseHeart)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewFriendFive.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
        if(heart < binding.textViewFriendSix.text.toString().toInt()){
            binding.imageFriendSix.alpha = 0.2f
            binding.imageFriendSix.isClickable = false
        }else{
            binding.imageFriendSix.alpha = 1f
            binding.imageFriendSix.isClickable = true
            binding.imageFriendSix.setOnClickListener {
                val decreaseHeart = heart - binding.textViewFriendSix.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("heart",decreaseHeart)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewFriendSix.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                } }
        return binding.root
    }

}