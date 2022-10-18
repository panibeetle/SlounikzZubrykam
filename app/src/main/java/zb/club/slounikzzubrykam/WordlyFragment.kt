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
import androidx.lifecycle.ViewModelProvider
import zb.club.slounikzzubrykam.databinding.FragmentWordlyBinding
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel

class WordlyFragment : Fragment() {
    lateinit var binding: FragmentWordlyBinding
    var wordRandom = String()
    private lateinit var viewModel: WordViewModel
    var words = emptyList<Word>()



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_wordly, container,false)


        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)


        binding.button3.setOnClickListener {

            val wordFromGame = StringBuilder()
            for(child in 0 until (binding.readyWord.childCount)){
                val children = binding.readyWord.getChildAt(child) as TextView
                wordFromGame.append(children.text.toString().trim())

            }
            var wordFromGameString = wordFromGame.toString()
            if(wordFromGameString == wordRandom){
                Toast.makeText(requireContext(),"Так, слушна!", Toast.LENGTH_LONG).show()
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.win)
                mediaPlayer?.start()
                binding.readyWord.removeAllViews()
                binding.wordbybook.removeAllViews()
                wordly()

            } else{ Toast.makeText(requireContext(),"Паспрабуй яшчэ раз!", Toast.LENGTH_LONG).show()
                binding.readyWord.removeAllViews()
                binding.wordbybook.removeAllViews()
                wordly()}
        }

        wordly()
        return binding.root

    }



    fun wordly(){

        val wordList = arrayListOf("гарбуз", "бурак", "агурок", "кроп", "капуста", "бульба")

        wordRandom = wordList.random()

        var letters = wordRandom.removeSurrounding("[", "]")
            .takeIf(String::isNotEmpty)
            ?.split("")
            ?.toTypedArray()
            ?: emptyArray()

        letters.shuffle()
        for(letter in letters){

            if(letter.isNotEmpty()){
                val dynamicTextview = TextView(requireContext())
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
                    var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap1)
                    mediaPlayer?.start()
                    var letterForReadyWord = dynamicTextview.text.toString().trim()
                    val dynamicTextviewReady = TextView(requireContext())
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
                binding.wordbybook.addView(dynamicTextview)
            }
        }

    }


}