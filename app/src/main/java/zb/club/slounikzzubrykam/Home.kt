package zb.club.slounikzzubrykam

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.databinding.ActivityMainBinding
import zb.club.slounikzzubrykam.databinding.FragmentHomeBinding


class Home : Fragment() {
    lateinit var  binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container,false)


        binding.button.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
            mediaPlayer?.start()

            findNavController()?.navigate(R.id.action_home2_to_topicFragment)
        }
        return binding.root
    }


}