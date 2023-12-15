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
    private lateinit var viewModel: WordViewModel
    lateinit var  sharedPref: SharedPreferences


    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)!!

        with(sharedPref!!.edit()) {
            putInt("fill", 0)
            apply()
        }


        binding.imageViewHearHome.visibility = View.INVISIBLE
        viewModel.getScore.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            binding.countHome.text = "${it.filling}/4"
            binding.progressBarInHome.progress = it.filling


            var fill = sharedPref!!.getInt("fill", 0)

            if (it.filling.equals(4) and fill.equals(0)) {

                with(sharedPref!!.edit()) {
                    putInt("fill", 1)
                    apply()
                }

                val newScore = Score(it.id, it.count, 0, it.heart + 1, it.age)
                viewModel.updateScore(newScore)
                playMusic(R.raw.magic)
                binding.imageViewHearHome.visibility = View.VISIBLE
                val anim = ValueAnimator.ofFloat(1f, 3f)
                anim.duration = 2000
                anim.repeatCount = 1
                anim.setRepeatMode(ValueAnimator.REVERSE)
                anim.addUpdateListener { animation ->
                    binding.imageViewHearHome.setScaleX(animation.animatedValue as Float)
                    binding.imageViewHearHome.setScaleY(animation.animatedValue as Float)
                }
                anim.start()

                anim.doOnEnd {
                    binding.imageViewHearHome.visibility = View.INVISIBLE

                }


            }


        })

        binding.button.setOnClickListener {
            playMusic(R.raw.tap1)

            val action = HomeDirections.actionHome2ToRewards(0)
            findNavController().navigate(action)
        }


        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mediaPlayer.stop()
                finishAffinity(requireActivity())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            onBackPressedCallback
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()



        var animUpDown = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.up_down
        )
        binding.animationView.setOnClickListener {
            binding.animationView.repeatCount = 1
            binding.animationView.playAnimation()
            binding.animationView.startAnimation(animUpDown)
        }
        binding.imageView.setOnClickListener { binding.imageView.startAnimation(animUpDown) }
        val a = args.reward.toInt()
        var nameDrow: String
        when (a) {
            0 -> {
                var friendName:String? = "Empty"
                var friendsAppear:Int = 0
                friendName = sharedPref!!.getString("friendName", "Empty")
                friendsAppear = sharedPref!!.getInt("friendsAppear",0)
                binding.button.isEnabled = false
                playMusic(R.raw.voice_glad_to_see)
                binding.imageView.setImageResource(R.drawable.zubr_want_to_play)

                mediaPlayer.setOnCompletionListener {
                    binding.button.isEnabled = true
                    binding.imageView.startAnimation(animUpDown)
                }
                binding.animationView.visibility = View.INVISIBLE
                binding.animationMeal.visibility = View.INVISIBLE


                if(friendsAppear in 1..3 && friendName != "Empty"  ){
                    if (friendName != null) {
                        nameDrow = friendName
                        binding.animationView.visibility = View.VISIBLE
                        friend((nameDrow))
                        friendsAppear -= 1
                        with(sharedPref!!.edit()) {
                            putInt("friendsAppear", friendsAppear)
                            apply()
                        }

                    }
                }




            }
            1 -> {
                nameDrow = "anim_cute_caterpillar"

                afterFrend(nameDrow)
                friend(nameDrow)
            }
            2 -> {
                nameDrow = "anim_dog"

                afterFrend(nameDrow)
                friend(nameDrow)
            }


            3 -> {
                nameDrow = "anim_cat"

                afterFrend(nameDrow)
                friend(nameDrow)
            }
            4 -> {
                nameDrow = "anim_tiger"

                afterFrend(nameDrow)
                friend(nameDrow)
            }
            5 -> {
                nameDrow = "anim_dog_tail"

                afterFrend(nameDrow)
                friend(nameDrow)
            }

            6 -> {
                nameDrow = "anim_owl"

                afterFrend(nameDrow)
                friend(nameDrow)
            }


            7 -> {
                nameDrow = "anim_carrot"
                eating(nameDrow)

            }
            9 -> {

                nameDrow = "anim_radish"
                eating(nameDrow)
            }
            11 -> {
                nameDrow = "anim_tomato"
                eating(nameDrow)

            }
            13 -> {
                nameDrow = "anim_avocado"
                eating(nameDrow)


            }
            15 -> {
                nameDrow = "anim_aubergine"
                eating(nameDrow)


            }
            17 -> {
                nameDrow = "anim_peer"
                eating(nameDrow)
            }

        }
    }

    private fun afterFrend(nameDrow: String) {
        binding.imageView.setImageResource(R.drawable.zubr_happy)
        playMusic(R.raw.voice_milota)
        with(sharedPref!!.edit()) {
            putInt("friendsAppear", 3)
            putString("friendName", nameDrow)
            apply()
        }
    }

    private fun eating(nameDrow: String) {
        val oldScore: Score? = viewModel.getScore.value
        binding.imageView.setImageResource(R.drawable.zubr_happy)
        val drawableId =
            requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
        binding.animationMeal.setAnimation(drawableId)
        binding.animationMeal.visibility = View.VISIBLE
        binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                playMusic(R.raw.sound_chewing)
            }

            override fun onAnimationEnd(p0: Animator?) {
                binding.animationMeal.visibility = View.INVISIBLE
                playMusic(R.raw.sound_bel_dziakuj_paczast)

                if (oldScore!!.filling != null && oldScore!!.filling < 4) {
                    playMusic(R.raw.woosh)
                    var increaseScore = oldScore!!.filling + 1
                    val newScore = Score(
                        oldScore!!.id,
                        oldScore.count,
                        increaseScore,
                        oldScore.heart,
                        oldScore.age
                    )
                    viewModel.updateScore(newScore)
                }
            }

            override fun onAnimationCancel(p0: Animator?) {}

            override fun onAnimationRepeat(p0: Animator?) {}
        })

        binding.button.isEnabled = false
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

    fun playMusic(id: Int) {
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true }
    }


}