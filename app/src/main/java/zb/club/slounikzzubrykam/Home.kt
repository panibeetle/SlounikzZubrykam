package zb.club.slounikzzubrykam

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
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
    lateinit var  binding: FragmentHomeBinding
    private lateinit var viewModel: WordViewModel


    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container,false)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        viewModel.getAllTopic.observe(viewLifecycleOwner, androidx.lifecycle.Observer { topic ->
            for(top in topic ){
                var topInsert: Topic
                viewModel.countTopicWord(top.topic).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    topInsert  = Topic(top.id,top.topic,top.picture, top.voice, top.flagOneTopic, top.flagTwoToic, top.flafThreeTopic, top.flagFour, top.flagFive, top.topicPol, top.voicePol, top.wordBelLearned, top.wordPolLearned, it, top.wordPol)
                    viewModel.updateTopic(topInsert)
                })

                viewModel.countTopicWordDone(top.topic).observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                    topInsert  = Topic(top.id,top.topic,top.picture, top.voice, top.flagOneTopic, top.flagTwoToic, top.flafThreeTopic, top.flagFour, top.flagFive, top.topicPol, top.voicePol, it, top.wordPolLearned, top.wordBel, top.wordPol)
                    viewModel.updateTopic(topInsert)
                })
            }
        })



        var animUpDown = AnimationUtils.loadAnimation(requireContext(),
            R.anim.up_down)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)


        with (sharedPref!!.edit()) {
            putInt("fill", 0)
            apply()
        }
        val a = args.reward.toInt()
        binding.imageViewHearHome.visibility=View.INVISIBLE
        viewModel.getScore.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

            binding.countHome.text="${it.filling}/3"
            binding.progressBarInHome.progress = it.filling
            var fill  = sharedPref!!.getInt("fill", 0)

            if(it.filling.equals(3)  and fill.equals(0) ){

                with (sharedPref!!.edit()) {
                    putInt("fill", 1)
                    apply()
                }

            val newScore = Score(it.id, it.count, 0, it.heart+1, it.age)
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

                anim.doOnEnd { binding.imageViewHearHome.visibility = View.INVISIBLE

                }


            }


        })

        var nameDrow: String
        when(a){
            0 -> {
                binding.button.isEnabled = false
                playMusic(R.raw.voice_glad_to_see)
                binding.imageView.setImageResource(R.drawable.zubr_want_to_play)

                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true
                    binding.imageView.startAnimation(animUpDown)}
                binding.animationView.visibility = View.INVISIBLE
                binding.animationMeal.visibility = View.INVISIBLE
            }
            1 -> {nameDrow="anim_cute_caterpillar"
                   binding.imageView.setImageResource(R.drawable.zubr_happy)
                    val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                    binding.animationView.setAnimation(drawableId)
                    binding.animationView.visibility = View.VISIBLE
                    binding.button.isEnabled = false
                    playMusic(R.raw.voice_milota)
                   binding.imageView.startAnimation(animUpDown)



            }
            2->{nameDrow="anim_dog"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
               }





            3->{nameDrow="anim_cat"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)



            }
            4->{nameDrow="anim_tiger"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)

            }
           5->{nameDrow="anim_dog_tail"
               binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)

            }

            6->{nameDrow="anim_owl"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)

            }





            7->{nameDrow="anim_carrot"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                        playMusic(R.raw.sound_bel_dziakuj_paczast)
                        val oldScore = viewModel.getScore.value
                        if(oldScore!!.filling <3){
                            playMusic(R.raw.woosh)
                            val oldScore = viewModel.getScore.value
                            var increaseScore = oldScore!!.filling + 1
                            val newScore = Score(oldScore!!.id, oldScore.count, increaseScore, oldScore.heart, oldScore.age)
                            viewModel.updateScore(newScore)}
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })

                binding.button.isEnabled = false





            }
            9->{
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                nameDrow="anim_radish"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)

                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                        playMusic(R.raw.sound_bel_dziakuj_paczast)
                        val oldScore = viewModel.getScore.value
                        if(oldScore!!.filling <3){
                            playMusic(R.raw.woosh)

                            var increaseScore = oldScore!!.filling + 1
                            val newScore = Score(oldScore!!.id, oldScore.count, increaseScore, oldScore.heart, oldScore.age)
                            viewModel.updateScore(newScore)}
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)

            }
            11->{nameDrow="anim_tomato"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                        playMusic(R.raw.sound_bel_dziakuj_paczast)
                        val oldScore = viewModel.getScore.value
                        if(oldScore!!.filling <3){
                            playMusic(R.raw.woosh)

                            var increaseScore = oldScore!!.filling + 1
                            val newScore = Score(oldScore!!.id, oldScore.count, increaseScore, oldScore.heart, oldScore.age)
                            viewModel.updateScore(newScore)}
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)


            }
            13->{nameDrow="anim_avocado"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                        playMusic(R.raw.sound_bel_dziakuj_paczast)
                        val oldScore = viewModel.getScore.value
                        if(oldScore!!.filling <3){
                            playMusic(R.raw.woosh)

                            var increaseScore = oldScore!!.filling + 1
                            val newScore = Score(oldScore!!.id, oldScore.count, increaseScore, oldScore.heart, oldScore.age)
                            viewModel.updateScore(newScore)}
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)


            }
            15->{nameDrow="anim_aubergine"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                        playMusic(R.raw.sound_bel_dziakuj_paczast)
                        val oldScore = viewModel.getScore.value
                        if(oldScore!!.filling <3){
                            playMusic(R.raw.woosh)

                            var increaseScore = oldScore!!.filling + 1
                            val newScore = Score(oldScore!!.id, oldScore.count, increaseScore, oldScore.heart, oldScore.age)
                            viewModel.updateScore(newScore)}
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)


            }
            17->{nameDrow="anim_peer"
                binding.imageView.setImageResource(R.drawable.zubr_happy)
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                        playMusic(R.raw.sound_bel_dziakuj_paczast)
                        val oldScore = viewModel.getScore.value
                        if(oldScore!!.filling <3){
                            playMusic(R.raw.woosh)

                            var increaseScore = oldScore!!.filling + 1
                            val newScore = Score(oldScore!!.id, oldScore.count, increaseScore, oldScore.heart, oldScore.age)
                            viewModel.updateScore(newScore)}
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)


            }










        }










        binding.button.setOnClickListener {
             playMusic(R.raw.tap1)

            val action = HomeDirections.actionHome2ToRewards(0)
            findNavController().navigate(action)
        }

        binding.animationView.setOnClickListener {
            binding.animationView.repeatCount = 1
            binding.animationView.playAnimation()
        binding.animationView.startAnimation(animUpDown)}
        binding.imageView.setOnClickListener { binding.imageView.startAnimation(animUpDown) }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mediaPlayer.stop()
                finishAffinity(requireActivity())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), onBackPressedCallback)
        return binding.root
    }
    fun playMusic(id: Int){
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
    }


}