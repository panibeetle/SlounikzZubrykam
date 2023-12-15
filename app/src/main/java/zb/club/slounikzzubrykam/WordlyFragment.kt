package zb.club.slounikzzubrykam

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import zb.club.slounikzzubrykam.databinding.FragmentWordlyBinding
import zb.club.slounikzzubrykam.dataclasses.Score
import zb.club.slounikzzubrykam.dataclasses.Topic
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import zb.club.slounikzzubrykam.guess.GuessFragmentDirections

class WordlyFragment : Fragment() {
    val args: WordlyFragmentArgs by navArgs()
    lateinit var binding: FragmentWordlyBinding
    var wordRandom = String()
    lateinit var word:Word
    private lateinit var viewModel: WordViewModel
    var wordCount = 0
    var wordForGame = arrayListOf<Word>()
    var arrayWordSize:Int = 0
    var checkMusik:Int = 0


    lateinit private var mediaPlayer: MediaPlayer

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_wordly, container,false)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)


        val words = args.idWords
        arrayWordSize = words.size
        binding.progressBarInWordly.max = arrayWordSize
        binding.countWordly.text="$wordCount/$arrayWordSize"
        binding.progressBarInWordly.progress = wordCount
        wordForGame = arrayListOf<Word>()
        viewModel.getScore.observe(viewLifecycleOwner, Observer {
            binding.textViewCoinWordly.text = it.count.toString()
        })
        for(word in words){
            wordForGame.add(word)
        }
        binding.button3.setOnClickListener {

            val wordFromGame = StringBuilder()
            for(child in 0 until (binding.readyWord.childCount)){
                val children = binding.readyWord.getChildAt(child) as TextView
                wordFromGame.append(children.text.toString())

            }
            var wordFromGameString = wordFromGame.toString()
            if(wordFromGameString == wordRandom){
                binding.button3.visibility = View.INVISIBLE

                playMusic(R.raw.win)
                word.flagFour = true
                viewModel.updateWord(word)


                mediaPlayer.setOnCompletionListener {


                    binding.button3.visibility = View.VISIBLE
                    if(wordCount == arrayWordSize){

                    binding.animationViewConfettiWordly.visibility = View.VISIBLE
                    playMusic(R.raw.magic)
                    binding.button3.text = "Перайсці да ўзнагароды!!!"

                    binding.button3.setOnClickListener {

                        wordCount = 0
                        val action = WordlyFragmentDirections.actionWordlyFragmentToRewards(0)
                        findNavController().navigate(action) }

                        binding.imageViewGuessingWord.setImageResource(R.drawable.zubr_laying)
                }else{ binding.readyWord.removeAllViews()
                    binding.wordbybook.removeAllViews()
                    wordlyFromRandom()} }
                binding.readyWord.removeAllViews()
                binding.wordbybook.removeAllViews()
                wordCount += 1
                val wordDel: Word? = wordForGame?.find { it.word== wordFromGameString }
                wordForGame.remove(wordDel)


                updateProgressBar()
                val oldScore = viewModel.getScore.value
                val increaseScore = oldScore!!.count + 1
                val newScore = Score(oldScore.id, increaseScore, oldScore.filling, oldScore.heart, oldScore.age)
                viewModel.updateScore(newScore)

            } else{ Toast.makeText(requireContext(),"Паспрабуй яшчэ раз!", Toast.LENGTH_LONG).show()
                binding.readyWord.removeAllViews()
                binding.wordbybook.removeAllViews()
                wordly(word)
                }
        }
        wordlyFromRandom()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val navigateHome = WordlyFragmentDirections.actionWordlyFragmentToRewards(0)
                findNavController().navigate(navigateHome)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), onBackPressedCallback)

        return binding.root

    }

    private fun updateProgressBar(){

        binding.countWordly.text="$wordCount/$arrayWordSize"
        binding.progressBarInWordly.progress = wordCount


    }
    fun wordlyFromRandom(){
        if(wordForGame.size>0){
            word= wordForGame.random()
            wordly(word)


    }}

    fun wordly(wordForParce:Word){


        wordRandom = wordForParce.word
        val picture = resources.getIdentifier(wordForParce.picture, "drawable", requireContext().packageName )
        binding.imageViewGuessingWord.setImageResource(picture)

        var letters = wordRandom.removeSurrounding("[", "]")
            .takeIf(String::isNotEmpty)
            ?.split("")
            ?.toTypedArray()
            ?: emptyArray()

        letters.shuffle()
            var a = 35F
        if (letters.size > 13) {a = 18F} else if (letters.size in 10..13) { a = 30F } else {a = 35F}
        for(letter in letters){


            if(letter.isNotEmpty()){
                val dynamicTextview = TextView(requireContext())
                val dynamicTextviewReady = TextView(requireContext())
                dynamicTextview.text = letter
                dynamicTextview.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                dynamicTextviewReady.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))

                dynamicTextview!!.typeface = ResourcesCompat.getFont(requireContext(), R.font.alice)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(1,4,1,4)

                dynamicTextview.setLayoutParams(params)
                dynamicTextview.textSize = (a)
                dynamicTextview.background = resources.getDrawable(R.drawable.square_letters)
                dynamicTextview.setOnClickListener {
                    playMusic(R.raw.tap1)
                    var letterForReadyWord = dynamicTextview.text.toString()

                    dynamicTextviewReady.text = letterForReadyWord
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(1,4,1,4)
                    dynamicTextviewReady.setLayoutParams(params)
                    dynamicTextviewReady!!.typeface = ResourcesCompat.getFont(requireContext(), R.font.alice)
                    dynamicTextviewReady.textSize = (a)
                    dynamicTextviewReady.background = resources.getDrawable(R.drawable.square_letters)
                    binding.readyWord.addView(dynamicTextviewReady)
                    binding.wordbybook.removeView(dynamicTextview)
                }

                dynamicTextviewReady.setOnClickListener {
                    playMusic(R.raw.tap_2)
                    var letterForReadyWordReady = dynamicTextviewReady.text.toString()

                    dynamicTextviewReady.text = letterForReadyWordReady
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(1,4,1,4)
                    dynamicTextview.setLayoutParams(params)
                    dynamicTextview!!.typeface = ResourcesCompat.getFont(requireContext(), R.font.alice)
                    dynamicTextview.textSize = (a)
                    dynamicTextview.background = resources.getDrawable(R.drawable.square_letters)
                    binding.wordbybook.addView(dynamicTextview)
                    binding.readyWord.removeView(dynamicTextviewReady)
                }
                binding.wordbybook.addView(dynamicTextview)
            }
        }

    }



    fun playMusic(id: Int){
        if (checkMusik == 1){
            if (mediaPlayer.isPlaying ){mediaPlayer.stop()
                mediaPlayer.release()
                checkMusik = 0
            }
        }
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
        checkMusik = 1
    }


}