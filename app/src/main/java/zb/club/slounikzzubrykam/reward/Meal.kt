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
        })
        return binding.root

    }

}