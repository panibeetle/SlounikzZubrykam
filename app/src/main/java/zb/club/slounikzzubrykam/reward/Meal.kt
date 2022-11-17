package zb.club.slounikzzubrykam.reward

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentMealBinding
import zb.club.slounikzzubrykam.dataclasses.Score
import zb.club.slounikzzubrykam.dataclasses.WordViewModel


class Meal : Fragment() {
    private lateinit var viewModel: WordViewModel
     private lateinit var binding: FragmentMealBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMealBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        binding.floatingActionButtonMeal.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
            mediaPlayer?.start()
            findNavController()?.navigate(R.id.action_rewards_to_topicFragment)
        }
        viewModel.getScore.observe(viewLifecycleOwner, Observer {
            binding.textViewRewardCoin.text = it.count.toString()
            if(it.count < binding.textViewCostMealOne.text.toString().toInt()){
                binding.imageMealOne.alpha = 0.2f
                binding.imageMealOne.isClickable = false
            }else{
                binding.imageMealOne.alpha = 1f
                binding.imageMealOne.isClickable = true
                binding.imageMealOne.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.count - binding.textViewCostMealOne.text.toString().toInt()
                    val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCostMealOne.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.count < binding.textViewCoastMealTwo.text.toString().toInt()){
                binding.imageMealTwo.alpha = 0.2f
                binding.imageMealTwo.isClickable = false
            }else{
                binding.imageMealTwo.alpha = 1f
                binding.imageMealTwo.isClickable = true
                binding.imageMealTwo.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.count - binding.textViewCoastMealTwo.text.toString().toInt()
                    val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealTwo.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.count < binding.textViewCoastMealTree.text.toString().toInt()){
                binding.imageMealThree.alpha = 0.2f
                binding.imageMealThree.isClickable = false
            }else{
                binding.imageMealThree.alpha = 1f
                binding.imageMealThree.isClickable = true
                binding.imageMealThree.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.count - binding.textViewCoastMealTree.text.trim().toString().toInt()
                    val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealTree.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.count < binding.textViewCoastMealFor.text.toString().toInt()){
                binding.imageMealFour.alpha = 0.2f
                binding.imageMealFour.isClickable = false
            }else{
                binding.imageMealFour.alpha = 1f
                binding.imageMealFour.isClickable = true
                binding.imageMealFour.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.count - binding.textViewCoastMealFor.text.toString().toInt()
                    val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealFor.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.count < binding.textViewCoastMealFive.text.toString().toInt()){
                binding.imageMealFive.alpha = 0.2f
                binding.imageMealFive.isClickable = false
            }else{
                binding.imageMealFive.alpha = 1f
                binding.imageMealFive.isClickable = true
                binding.imageMealFive.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.count - binding.textViewCoastMealFive.text.toString().toInt()
                    val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealFive.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }
            if(it.count < binding.textViewCoastMealSix.text.toString().toInt()){
                binding.imageMealSix.alpha = 0.2f
                binding.imageMealSix.isClickable = false
            }else{
                binding.imageMealSix.alpha = 1f
                binding.imageMealSix.isClickable = true
                binding.imageMealSix.setOnClickListener {
                    val oldScore = viewModel.getScore.value
                    val increaseScore = oldScore!!.count - binding.textViewCoastMealSix.text.toString().toInt()
                    val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                    viewModel.updateScore(newScore)
                    val navigateHome = RewardsDirections.actionRewardsToHome2(binding.textViewCoastMealSix.text.toString().toInt())
                    findNavController().navigate(navigateHome)

                }
            }












        })
        return binding.root

    }

}