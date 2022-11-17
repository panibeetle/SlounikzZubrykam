package zb.club.slounikzzubrykam

import android.animation.Animator
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import zb.club.slounikzzubrykam.databinding.FragmentHomeBinding
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import java.util.*


class Home : Fragment() {
    val args: HomeArgs by navArgs()
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

        iviteFriend = sharedPref!!.getInt("invite", 0)
        val a = args.reward.toInt()



        var nameDrow: String
        when(a){
            0 -> {
                binding.button.isEnabled = false
                playMusic(R.raw.voice_glad_to_see)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
                binding.animationView.visibility = View.INVISIBLE
                binding.animationMeal.visibility = View.INVISIBLE
            }
            1 -> {nameDrow="anim_cute_caterpillar"
                    val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                    binding.animationView.setAnimation(drawableId)
                    binding.animationView.visibility = View.VISIBLE
                    binding.button.isEnabled = false
                    playMusic(R.raw.voice_milota)
                   binding.imageView.startAnimation(animUpDown)
                    mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}


            }
            3->{nameDrow="anim_dog_tail"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}}

            3->{nameDrow="anim_dog"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}


            }
            5->{nameDrow="anim_cat"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}


            }
            7->{nameDrow="anim_tiger"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }
            9->{nameDrow="anim_dog"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }

            11->{nameDrow="anim_owl"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationView.setAnimation(drawableId)
                binding.animationView.visibility = View.VISIBLE
                binding.button.isEnabled = false
                playMusic(R.raw.voice_milota)
                binding.imageView.startAnimation(animUpDown)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }





            17->{nameDrow="anim_carrot"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })

                binding.button.isEnabled = false

                playMusic(R.raw.sound_bel_dziakuj_paczast)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }
            34->{nameDrow="anim_radish"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)
                playMusic(R.raw.sound_bel_dziakuj_paczast)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }
            51->{nameDrow="anim_tomato"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)
                playMusic(R.raw.sound_bel_dziakuj_paczast)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }
            68->{nameDrow="anim_avocado"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)
                playMusic(R.raw.sound_bel_dziakuj_paczast)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }
            85->{nameDrow="anim_aubergine"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)
                playMusic(R.raw.sound_bel_dziakuj_paczast)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }
            102->{nameDrow="anim_peer"
                val drawableId = requireContext().resources.getIdentifier(nameDrow, "raw", requireContext().packageName)
                binding.animationMeal.setAnimation(drawableId)
                binding.animationMeal.visibility = View.VISIBLE
                binding.animationMeal.addAnimatorListener(object : Animator.AnimatorListener{
                    override fun onAnimationStart(p0: Animator?) {
                        playMusic(R.raw.sound_chewing)
                    }

                    override fun onAnimationEnd(p0: Animator?) {
                        binding.animationMeal.visibility = View.INVISIBLE
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationRepeat(p0: Animator?) {

                    }
                })
                binding.button.isEnabled = false
                playMusic(R.raw.sound_chewing)
                playMusic(R.raw.sound_bel_dziakuj_paczast)
                mediaPlayer.setOnCompletionListener { binding.button.isEnabled = true}
            }










        }








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

        val word = Word(0, 0,"слова", "pic", "vioce", false,false,false,false,"univerce", "slowa", "sl")
        viewModel.addWord(word)
        return binding.root
    }
    fun playMusic(id: Int){
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
    }


}