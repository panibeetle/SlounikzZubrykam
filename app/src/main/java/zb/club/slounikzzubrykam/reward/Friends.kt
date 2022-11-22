package zb.club.slounikzzubrykam.reward

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.R


import zb.club.slounikzzubrykam.databinding.FragmentFriendsBinding
import zb.club.slounikzzubrykam.dataclasses.Score
import zb.club.slounikzzubrykam.dataclasses.WordViewModel

class Friends : Fragment() {
    private lateinit var viewModel: WordViewModel
    private lateinit var binding: FragmentFriendsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFriendsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        binding.floatingActionButtonFriend.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
            mediaPlayer?.start()
            findNavController()?.navigate(R.id.action_rewards_to_topicFragment)
        }
        viewModel.getScore.observe(viewLifecycleOwner, Observer {
            binding.textViewRewardHeart.text = it.heart.toString()
            if(it.heart < binding.textViewCostFriendOne.text.toString().toInt()){
                binding.imageFriendOne.alpha = 0.2f
                binding.imageFriendOne.isClickable = false
            }else{
                binding.imageFriendOne.alpha = 1f
                binding.imageFriendOne.isClickable = true
                binding.imageFriendOne.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.heart - binding.textViewCostFriendOne.text.toString().toInt()
                    val newScore = Score(oldScore.id, oldScore.count, oldScore.filling,increaseScore, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCostFriendOne.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.heart < binding.textViewCoastFriendTwo.text.toString().toInt()){
                binding.imageFriendTwo.alpha = 0.2f
                binding.imageFriendTwo.isClickable = false
            }else{
                binding.imageFriendTwo.alpha = 1f
                binding.imageFriendTwo.isClickable = true
                binding.imageFriendTwo.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.heart - binding.textViewCoastFriendTwo.text.toString().toInt()
                    val newScore = Score(oldScore.id, oldScore.count, oldScore.filling,increaseScore, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastFriendTwo.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.heart < binding.textViewCoastFriendTree.text.toString().toInt()){
                binding.imageFriendThree.alpha = 0.2f
                binding.imageFriendThree.isClickable = false
            }else{
                binding.imageFriendThree.alpha = 1f
                binding.imageFriendThree.isClickable = true
                binding.imageFriendThree.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.heart - binding.textViewCoastFriendTree.text.toString().toInt()
                    val newScore = Score(oldScore.id, oldScore.count, oldScore.filling,increaseScore, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastFriendTree.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.heart < binding.textViewCoastFriendFor.text.toString().toInt()){
                binding.imageFriendFour.alpha = 0.2f
                binding.imageFriendFour.isClickable = false
            }else{
                binding.imageFriendFour.alpha = 1f
                binding.imageFriendFour.isClickable = true
                binding.imageFriendFour.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.heart - binding.textViewCoastFriendFor.text.toString().toInt()
                    val newScore = Score(oldScore.id, oldScore.count, oldScore.filling,increaseScore, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastFriendFor.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.heart < binding.textViewFriendFive.text.toString().toInt()){
                binding.imageFriendFive.alpha = 0.2f
                binding.imageFriendFive.isClickable = false
            }else{
                binding.imageFriendFive.alpha = 1f
                binding.imageFriendFive.isClickable = true
                binding.imageFriendFive.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.heart - binding.textViewFriendFive.text.toString().toInt()
                    val newScore = Score(oldScore.id, oldScore.count, oldScore.filling,increaseScore, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewFriendFive.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.heart < binding.textViewFriendSix.text.toString().toInt()){
                binding.imageFriendSix.alpha = 0.2f
                binding.imageFriendSix.isClickable = false
            }else{
                binding.imageFriendSix.alpha = 1f
                binding.imageFriendSix.isClickable = true
                binding.imageFriendSix.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.heart - binding.textViewFriendSix.text.toString().toInt()
                    val newScore = Score(oldScore.id, oldScore.count, oldScore.filling,increaseScore, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewFriendSix.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                } }
        })

        return binding.root
    }

}