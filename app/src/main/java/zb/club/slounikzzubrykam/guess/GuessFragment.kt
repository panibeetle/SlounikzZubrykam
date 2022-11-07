package zb.club.slounikzzubrykam.guess

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentGuessBinding
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import zb.club.slounikzzubrykam.repeate.RepeateRecyclerAdapter


class GuessFragment : Fragment(), GuessSelectedWordPosition {
    lateinit var adapter: GuessAdapter
    val args: GuessFragmentArgs by navArgs()
    var arrayForChanging = mutableListOf<Word>()
    private lateinit var viewModel: WordViewModel
    lateinit var  binding: FragmentGuessBinding
    lateinit var guessedWord:Word
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_guess, container,false)


        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)


        viewModel.getGuessWordSuspend(args.idWord.toList())
        adapter = GuessAdapter(this)
        binding.recyclerGuess.adapter = adapter
        binding.recyclerGuess.layoutManager = GridLayoutManager(requireContext(),  2)
        playGuess()


        binding.buttonCheckWord.setOnClickListener {
            if(guessedWord.word == binding.textView.text.toString()){
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.magic)
                mediaPlayer?.start()
            }

            playGuess() }
        binding.buttonNewGuess.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(requireContext(), R.raw.tap_2)
            mediaPlayer?.start()
            val action = GuessFragmentDirections.actionGuessFragmentToWordlyFragment(args.idWord)
            findNavController().navigate(action)

        }





        return binding.root
    }

    private fun playGuess() {
        viewModel.arrayWordForGuess.observe(viewLifecycleOwner, Observer {
            var listFour = mutableListOf<Word>()
            it.shuffle()
            var a = 0
            for(i in it){
                if (it[0].flafThree){
                    a= a+1
                }
            }
            if(a <6){
            while (it[0].flafThree){it.shuffle()}
                for (i in 0..3){listFour.add(it[i])}
                binding.textView.text = listFour[0].word
                listFour.shuffle()
                adapter.setData(listFour)} else{
                it.shuffle()
                for (i in 0..3){listFour.add(it[i])
            }
                binding.textView.text = listFour[0].word
                listFour.shuffle()
                adapter.setData(listFour)}

            val voiceId = requireContext().resources.getIdentifier(listFour[0].voice, "raw", requireContext().packageName)
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, voiceId)
            mediaPlayer?.start()
            binding.cardViewGuess.setOnClickListener { val voiceId = requireContext().resources.getIdentifier(listFour[0].voice, "raw", requireContext().packageName)
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, voiceId)
                mediaPlayer?.start() }

        })
    }

    override fun oSelectedWord(selectedWord: Word) {
        guessedWord=selectedWord
    }


}