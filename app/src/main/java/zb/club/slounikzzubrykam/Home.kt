package zb.club.slounikzzubrykam

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.core.animation.doOnEnd
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import zb.club.slounikzzubrykam.databinding.FragmentHomeBinding
import zb.club.slounikzzubrykam.dataclasses.Score
import zb.club.slounikzzubrykam.dataclasses.Topic
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import zb.club.slounikzzubrykam.guess.GuessFragmentDirections
import java.util.*


class Home : Fragment() {
    val args: HomeArgs by navArgs()
    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPref: SharedPreferences
    lateinit var  animUpDown: Animation


    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        sharedPref = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        if (!sharedPref.contains("fill") && !sharedPref.contains("count") && !sharedPref.contains("heart") && !sharedPref.contains(
                "score"
            )
        ) {
            with(sharedPref!!.edit()) {
                putInt("fill", 0)
                putInt("count", 100)
                putInt("heart", 2)
                putInt("score", 0)
                apply()
            }
        }
        binding.imageViewHearHome.visibility = View.INVISIBLE

        val fill = sharedPref!!.getInt("fill", 0)
        binding.countHome.text = "$fill/4"
        binding.progressBarInHome.progress = fill
        binding.button.isEnabled = false
        binding.button.setOnClickListener {
            setupMediaPlayer(R.raw.tap1){val action = HomeDirections.actionHome2ToRewards(0)
                findNavController().navigate(action)}

        }


        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (mediaPlayer != null && mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()}
                finishAffinity(requireActivity())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            onBackPressedCallback
        )
        animUpDown = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.up_down
        )
        binding.animationView.setOnClickListener {
            binding.animationView.repeatCount = 1
            binding.animationView.playAnimation()
            binding.animationView.startAnimation(animUpDown)
        }
        binding.imageView.setOnClickListener { binding.imageView.startAnimation(animUpDown) }
        handleRewardType()

        return binding.root
    }



    private fun handleRewardType() {
        when (val reward = args.reward) {
            0 -> playVoiceGladToSee()
            in 1..6 -> {
                val nameDrow = when (reward) {
                    1 -> "anim_cute_caterpillar"
                    2 -> "anim_dog"
                    3 -> "anim_cat"
                    4 -> "anim_tiger"
                    5 -> "anim_dog_tail"
                    6 -> "anim_owl"
                    else -> throw IllegalArgumentException("Invalid reward type: $reward")
                }
                afterFrend(nameDrow)
                friend(nameDrow)
            }
            in 7..17 -> {
                val nameDrow = when (reward) {
                    7 -> "anim_carrot"
                    9 -> "anim_radish"
                    11 -> "anim_tomato"
                    13 -> "anim_avocado"
                    15 -> "anim_aubergine"
                    17 -> "anim_peer"
                    else -> throw IllegalArgumentException("Invalid reward type: $reward")
                }
                eating(nameDrow)
            }
            // Add more cases as needed
            else -> throw IllegalArgumentException("Invalid reward type: $reward")
        }
    }

    private fun playVoiceGladToSee() {
        binding.imageView.setImageResource(R.drawable.zubr_want_to_play)
        binding.imageView.startAnimation(animUpDown)
        binding.animationView.visibility = View.INVISIBLE
        binding.animationMeal.visibility = View.INVISIBLE
        val friendName = sharedPref.getString("friendName", "Empty")
        if (friendName != "Empty") {
            friend(friendName!!)

        }
        setupMediaPlayer(R.raw.voice_glad_to_see) {
            binding.button.isEnabled = true

        }
    }




//    private fun eatAndFriend() {
//        val a = args.reward
//        val nameDrow: String
//
//        when (a) {
//            0 -> {
//                var friendName: String? = "Empty"
//                friendName = sharedPref!!.getString("friendName", "Empty")
//                setupMediaPlayer(R.raw.voice_glad_to_see){binding.button.isEnabled = true}
//                binding.imageView.setImageResource(R.drawable.zubr_want_to_play)
//                binding.imageView.startAnimation(animUpDown)
//                binding.animationView.visibility = View.INVISIBLE
//                binding.animationMeal.visibility = View.INVISIBLE
//                if (friendName != "Empty") {
//                    if (friendName != null) {
//                        nameDrow = friendName
//                        binding.animationView.visibility = View.VISIBLE
//                        friend((nameDrow))
//                    }
//                }
//
//
//            }
//            1 -> {
//                nameDrow = "anim_cute_caterpillar"
//                afterFrend(nameDrow)
//                friend(nameDrow)
//            }
//            2 -> {
//                nameDrow = "anim_dog"
//                afterFrend(nameDrow)
//                friend(nameDrow)
//            }
//
//
//            3 -> {
//                nameDrow = "anim_cat"
//                afterFrend(nameDrow)
//                friend(nameDrow)
//            }
//            4 -> {
//                nameDrow = "anim_tiger"
//                afterFrend(nameDrow)
//                friend(nameDrow)
//            }
//            5 -> {
//                nameDrow = "anim_dog_tail"
//                afterFrend(nameDrow)
//                friend(nameDrow)
//            }
//
//            6 -> {
//                nameDrow = "anim_owl"
//                afterFrend(nameDrow)
//                friend(nameDrow)
//            }
//
//
//            7 -> {
//                nameDrow = "anim_carrot"
//                eating(nameDrow)
//
//            }
//            9 -> {
//
//                nameDrow = "anim_radish"
//                eating(nameDrow)
//            }
//            11 -> {
//                nameDrow = "anim_tomato"
//                eating(nameDrow)
//
//            }
//            13 -> {
//                nameDrow = "anim_avocado"
//                eating(nameDrow)
//
//
//            }
//            15 -> {
//                nameDrow = "anim_aubergine"
//                eating(nameDrow)
//
//
//            }
//            17 -> {
//                nameDrow = "anim_peer"
//                eating(nameDrow)
//            }
//
//        }
//    }





    private fun afterFrend(nameDrow: String) {
        binding.imageView.setImageResource(R.drawable.zubr_happy)
        setupMediaPlayer(R.raw.voice_milota){binding.button.isEnabled = true}
        with(sharedPref!!.edit()) {
            putString("friendName", nameDrow)
            apply()
        }
    }

    private fun eating(nameDrow: String) {
        var fillToChange = sharedPref!!.getInt("fill", 0)
        fillToChange++

        binding.imageView.setImageResource(R.drawable.zubr_happy)
        val drawableId =
            requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
        binding.animationMeal.setAnimation(drawableId)
        binding.animationMeal.visibility = View.VISIBLE
        binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                setupMediaPlayer(R.raw.sound_chewing){}
            }

            override fun onAnimationEnd(p0: Animator?) {
                binding.animationMeal.visibility = View.INVISIBLE
                setupMediaPlayer(R.raw.sound_bel_dziakuj_paczast){
                    binding.imageView.startAnimation(animUpDown)
                    if (fillToChange < 4) {
                        binding.countHome.text = "$fillToChange/4"
                        binding.progressBarInHome.progress = fillToChange
                        with(sharedPref!!.edit()) {
                            putInt("fill", fillToChange)
                            apply()
                        }
                        binding.button.isEnabled = true
                    } else if (fillToChange == 4) {
                        binding.button.isEnabled = false
                        binding.countHome.text = "$fillToChange/4"
                        binding.progressBarInHome.progress = fillToChange
                        var heart = sharedPref!!.getInt("heart", 0)
                        with(sharedPref!!.edit()) {
                            heart++
                            putInt("fill", 0)
                            putInt("heart", heart)
                            apply()
                        }
                        binding.imageViewHearHome.visibility = View.VISIBLE
                        setupMediaPlayer(R.raw.magic){
                            binding.imageViewHearHome.visibility = View.INVISIBLE
                            val action = HomeDirections.actionHome2ToRewards(1)
                            findNavController().navigate(action)
                        }

                        val anim = ValueAnimator.ofFloat(1f, 3f)
                        anim.duration = 2000
                        anim.repeatCount = 1
                        anim.repeatMode = ValueAnimator.REVERSE
                        anim.addUpdateListener { animation ->
                            binding.imageViewHearHome.scaleX = animation.animatedValue as Float
                            binding.imageViewHearHome.scaleY = animation.animatedValue as Float
                        }
                        anim.start()


                    }
                }


            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })


    }

    private fun friend(nameDrow: String) {

        val drawableId =
            requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
        binding.animationView.setAnimation(drawableId)
        binding.animationView.visibility = View.VISIBLE
        binding.button.isEnabled = false
        var animUpDown = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.up_down
        )
        binding.imageView.startAnimation(animUpDown)
    }

    private fun setupMediaPlayer(id: Int, onCompletion: () -> Unit) {
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
            onCompletion.invoke()
        }
        mediaPlayer.start()
    }
}