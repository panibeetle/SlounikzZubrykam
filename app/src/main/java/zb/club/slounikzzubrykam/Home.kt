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
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import zb.club.slounikzzubrykam.databinding.ActivityMainBinding
import zb.club.slounikzzubrykam.databinding.FragmentHomeBinding
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import java.util.*


class Home : Fragment() {
    lateinit var  binding: FragmentHomeBinding
    private lateinit var viewModel: WordViewModel
    var iviteFriend = 0
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container,false)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        var animUpDown = AnimationUtils.loadAnimation(requireContext(),
            R.anim.up_down)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var voiceId = R.raw.voice_glad_to_see
        iviteFriend = sharedPref!!.getInt("invite", 0)
        if (iviteFriend == 1){
            voiceId = R.raw.voice_milota
            val a = Random().nextInt(6)
            var nameDrow: String


            when(a){
                1-> nameDrow="anim_cute_caterpillar"
                2->nameDrow="anim_cat"

                3->nameDrow="anim_dog"
                4->nameDrow="anim_dog_tail"
                5->nameDrow="anim_rabbit"
                else ->nameDrow= "anim_cute_caterpillar"

            }
            val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
             binding.animationView.setAnimation(drawableId)

            binding.animationView.visibility = View.VISIBLE

        }


        binding.imageView.startAnimation(animUpDown)



        with (sharedPref!!.edit()) {
            putInt("invite", 0)
            apply()
        }

        binding.button.setOnClickListener {
             playMusic(R.raw.tap1)

            findNavController()?.navigate(R.id.action_home2_to_rewards)
        }

        binding.animationView.setOnClickListener {
            binding.animationView.repeatCount = 1
            binding.animationView.playAnimation() }
        binding.imageView.setOnClickListener { binding.imageView.startAnimation(animUpDown) }
        playMusic(voiceId)
        binding.button.isEnabled = false
        mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true }
        val word = Word(0, 0,"слова", "pic", "vioce", false,false,false,false,"univerce", "slowa", "sl")
        viewModel.addWord(word)
        return binding.root
    }
    fun playMusic(id: Int){
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
    }


}