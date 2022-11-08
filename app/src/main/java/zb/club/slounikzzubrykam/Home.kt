package zb.club.slounikzzubrykam

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.databinding.ActivityMainBinding
import zb.club.slounikzzubrykam.databinding.FragmentHomeBinding
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import java.util.*


class Home : Fragment() {
    lateinit var  binding: FragmentHomeBinding
    private lateinit var viewModel: WordViewModel
    var iviteFriend = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container,false)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

        iviteFriend = sharedPref!!.getInt("invite", 0)
        if (iviteFriend == 1){
            val a = Random().nextInt(7)
            var nameDrow: String


            when(a){
                1-> nameDrow="anim_cute_caterpillar"
                2->nameDrow="anim_cat"
                3->nameDrow="anim_deer"
                5->nameDrow="anim_dog"
                6->nameDrow="anim_dog_tail"
                7->nameDrow="anim_rabbit"
                else ->nameDrow= "anim_cute_caterpillar"

            }
            val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
             binding.animationView.setAnimation(drawableId)

            binding.animationView.visibility = View.VISIBLE

        }
        with (sharedPref!!.edit()) {
            putInt("invite", 0)
            apply()
        }

        binding.button.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
            mediaPlayer?.start()

            findNavController()?.navigate(R.id.action_home2_to_topicFragment)
        }
        return binding.root
    }


}