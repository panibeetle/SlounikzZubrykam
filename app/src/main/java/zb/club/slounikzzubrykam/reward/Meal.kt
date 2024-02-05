package zb.club.slounikzzubrykam.reward

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentMealBinding


class Meal : Fragment() {
    lateinit var  sharedPref: SharedPreferences
    private lateinit var binding: FragmentMealBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealBinding.inflate(layoutInflater)
        sharedPref = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        var count = sharedPref.getInt("count",0)

        binding.floatingActionButtonMeal.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
            mediaPlayer?.start()
            findNavController().navigate(R.id.action_rewards_to_topicFragment)
        }

        binding.textViewRewardCoin.text = count.toString()
        if(count < binding.textViewCostMealOne.text.toString().toInt()){
            binding.imageMealOne.alpha = 0.2f
            binding.imageMealOne.isClickable = false
        }else{
            binding.imageMealOne.alpha = 1f
            binding.imageMealOne.isClickable = true
            binding.imageMealOne.setOnClickListener {

                val decreaseScore = count - binding.textViewCostMealOne.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("count",decreaseScore)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCostMealOne.text.toString().toInt())
                findNavController().navigate(navigateHome)

                }
            }
        if(count < binding.textViewCoastMealTwo.text.toString().toInt()){
            binding.imageMealTwo.alpha = 0.2f
            binding.imageMealTwo.isClickable = false
        }else{
            binding.imageMealTwo.alpha = 1f
            binding.imageMealTwo.isClickable = true
            binding.imageMealTwo.setOnClickListener {
                val decreaseScore = count - binding.textViewCoastMealTwo.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("count",decreaseScore)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealTwo.text.toString().toInt())
                findNavController().navigate(navigateHome)

            }
        }
        if(count < binding.textViewCoastMealTree.text.toString().toInt()){
            binding.imageMealThree.alpha = 0.2f
            binding.imageMealThree.isClickable = false
        }else{
            binding.imageMealThree.alpha = 1f
            binding.imageMealThree.isClickable = true
            binding.imageMealThree.setOnClickListener {
                val decreaseScore = count - binding.textViewCoastMealTree.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("count",decreaseScore)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealTree.text.toString().toInt())
                findNavController().navigate(navigateHome)

            }
        }
        if(count < binding.textViewCoastMealFor.text.toString().toInt()){
            binding.imageMealFour.alpha = 0.2f
            binding.imageMealFour.isClickable = false
        }else{
            binding.imageMealFour.alpha = 1f
            binding.imageMealFour.isClickable = true
            binding.imageMealFour.setOnClickListener {
                val decreaseScore = count - binding.textViewCoastMealFor.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("count",decreaseScore)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealFor.text.toString().toInt())
                findNavController().navigate(navigateHome)

            }
        }
        if(count < binding.textViewCoastMealFive.text.toString().toInt()){
            binding.imageMealFive.alpha = 0.2f
            binding.imageMealFive.isClickable = false
        }else{
            binding.imageMealFive.alpha = 1f
            binding.imageMealFive.isClickable = true
            binding.imageMealFive.setOnClickListener {
                val decreaseScore = count - binding.textViewCoastMealFive.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("count",decreaseScore)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealFive.text.toString().toInt())
                findNavController().navigate(navigateHome)

            }
        }
        if(count < binding.textViewCoastMealSix.text.toString().toInt()){
            binding.imageMealSix.alpha = 0.2f
            binding.imageMealSix.isClickable = false
        }else{
            binding.imageMealSix.alpha = 1f
            binding.imageMealSix.isClickable = true
            binding.imageMealSix.setOnClickListener {
                val decreaseScore = count - binding.textViewCoastMealSix.text.toString().toInt()
                with(sharedPref!!.edit()) {
                    putInt("count",decreaseScore)
                    apply()
                }
                val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealSix.text.toString().toInt())
                findNavController().navigate(navigateHome)

            }
        }

        return binding.root

    }

}