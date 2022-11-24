package zb.club.slounikzzubrykam

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import zb.club.slounikzzubrykam.databinding.FragmentWordlyBinding
import zb.club.slounikzzubrykam.dataclasses.Score
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel

class WordlyFragment : Fragment() {
    val args: WordlyFragmentArgs by navArgs()
    lateinit var binding: FragmentWordlyBinding
    var wordRandom = String()
    private lateinit var viewModel: WordViewModel
    var wordCount = 0
   var wordForGame = arrayListOf<Word>()

    lateinit private var mediaPlayer: MediaPlayer

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_wordly, container,false)


        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        val words = args.idWords
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
                wordFromGame.append(children.text.toString().trim())

            }
            var wordFromGameString = wordFromGame.toString()
            if(wordFromGameString == wordRandom){

                Toast.makeText(requireContext(),"Так, слушна!", Toast.LENGTH_LONG).show()

                playMusic(R.raw.win)
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
                if(wordCount == 7){
                    binding.animationViewConfettiWordly.visibility = View.VISIBLE
                    playMusic(R.raw.magic)
                    binding.button3.text = "Перайсці да ўзнагароды!!!"
                    binding.button3.setOnClickListener {

                        wordCount = 0
                        val action = WordlyFragmentDirections.actionWordlyFragmentToRewards(0)
                        findNavController().navigate(action) }
                }else{wordly()}



            } else{ Toast.makeText(requireContext(),"Паспрабуй яшчэ раз!", Toast.LENGTH_LONG).show()
                binding.readyWord.removeAllViews()
                binding.wordbybook.removeAllViews()
                wordly()
                }
        }
        wordly()

        return binding.root

    }

    private fun updateProgressBar(){

        binding.countWordly.text="$wordCount/7"
        binding.progressBarInWordly.progress = wordCount


    }

    fun wordly(){

        if(wordForGame.size>0){
        val word: Word = wordForGame.random()
        wordRandom = word.word
        val picture = resources.getIdentifier(word.picture, "drawable", requireContext().packageName )
        binding.imageViewGuessingWord.setImageResource(picture)

        var letters = wordRandom.removeSurrounding("[", "]")
            .takeIf(String::isNotEmpty)
            ?.split("")
            ?.toTypedArray()
            ?: emptyArray()

        letters.shuffle()
        for(letter in letters){

            if(letter.isNotEmpty()){
                val dynamicTextview = TextView(requireContext())
                val dynamicTextviewReady = TextView(requireContext())
                dynamicTextview.text = letter
                dynamicTextview!!.typeface = ResourcesCompat.getFont(requireContext(), R.font.rubik_light)
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(8,4,8,4)
                dynamicTextview.setLayoutParams(params)
                dynamicTextview.textSize = (40F)
                dynamicTextview.background = resources.getDrawable(R.drawable.square_letters)
                dynamicTextview.setOnClickListener {
                    playMusic(R.raw.tap1)
                    var letterForReadyWord = dynamicTextview.text.toString().trim()

                    dynamicTextviewReady.text = letterForReadyWord
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(8,4,8,4)
                    dynamicTextviewReady.setLayoutParams(params)
                    dynamicTextviewReady!!.typeface = ResourcesCompat.getFont(requireContext(), R.font.rubik_light)
                    dynamicTextviewReady.textSize = (40F)
                    dynamicTextviewReady.background = resources.getDrawable(R.drawable.square_letters)
                    binding.readyWord.addView(dynamicTextviewReady)
                    binding.wordbybook.removeView(dynamicTextview)
                }

                dynamicTextviewReady.setOnClickListener {
                    playMusic(R.raw.tap_2)
                    var letterForReadyWordReady = dynamicTextviewReady.text.toString().trim()

                    dynamicTextviewReady.text = letterForReadyWordReady
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.setMargins(8,4,8,4)
                    dynamicTextview.setLayoutParams(params)
                    dynamicTextview!!.typeface = ResourcesCompat.getFont(requireContext(), R.font.rubik_light)
                    dynamicTextview.textSize = (40F)
                    dynamicTextview.background = resources.getDrawable(R.drawable.square_letters)
                    binding.wordbybook.addView(dynamicTextview)
                    binding.readyWord.removeView(dynamicTextviewReady)
                }
                binding.wordbybook.addView(dynamicTextview)
            }
        }}

    }
    fun playMusic(id: Int){
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
    }


}