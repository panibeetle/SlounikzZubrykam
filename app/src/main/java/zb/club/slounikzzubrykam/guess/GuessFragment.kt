package zb.club.slounikzzubrykam.guess

import android.content.Context
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
    private lateinit var viewModel: WordViewModel
    lateinit var  binding: FragmentGuessBinding
    lateinit var guessedWord:Word
    lateinit private var mediaPlayer: MediaPlayer

    var voiceGuessing =""
    var score =0
    var arrayForGuessing = mutableListOf<Word>()
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
        viewModel.arrayWordForGuess.observe(viewLifecycleOwner, Observer {
            var isButtonInviteVisibility: Boolean = false
            for (i in it){
                if(!i.flafThree){
                    isButtonInviteVisibility = false
                    break
                }
                isButtonInviteVisibility= true
            }
            if(isButtonInviteVisibility) {
                binding.animationViewConfetti.visibility = View.VISIBLE
                playMusic(R.raw.magic)
                binding.buttonInvite.visibility= View.VISIBLE
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                with (sharedPref!!.edit()) {
                    putInt("invite", 1)
                    apply()
                }

            }
            var a = 0
            for (i in it){
                if(i.flafThree){
                    a = a+1
                }
            }
            score = a
            updateProgressBar()
        })
        binding.buttonInvite.setOnClickListener {

            findNavController().navigate(R.id.action_guessFragment_to_home2)
        }

        return binding.root
    }

    private fun playGuess() {
        viewModel.arrayWordForGuess.observe(viewLifecycleOwner, Observer {
            var listFour = mutableListOf<Word>()
            var a = 0
            for(i in it){
                if (it[0].flafThree){
                    a= a+1
                }
            }
            it.shuffle()
            if(a <6){
                while (it[0].flafThree){it.shuffle()}
                for (i in 0..3){listFour.add(it[i])}
                binding.textView.text = listFour[0].word
                voiceGuessing = listFour[0].voice
                listFour.shuffle()
                adapter.setData(listFour)
                binding.recyclerGuess.isEnabled = false

                } else{
                for (i in 0..3){listFour.add(it[i])
            }
                binding.textView.text = listFour[0].word
                voiceGuessing = listFour[0].voice
                listFour.shuffle()
                adapter.setData(listFour)
               }


            binding.cardViewGuess.setOnClickListener {
                binding.recyclerGuess.isEnabled = false
                binding.cardViewGuess.isEnabled=false
                val voiceId = requireContext().resources.getIdentifier(voiceGuessing, "raw", requireContext().packageName)
                playMusic(voiceId)

               mediaPlayer.setOnCompletionListener { binding.recyclerGuess.isEnabled = true
                   binding.cardViewGuess.isEnabled=true
               }}

        })
    }

    override fun oSelectedWord(selectedWord: Word) {
        guessedWord=selectedWord
        playMusic(R.raw.ding)
        if(guessedWord.word == binding.textView.text.toString()){
            arrayForGuessing = viewModel.arrayWordForGuess.value!!

            binding.recyclerGuess.isEnabled = false
            binding.cardViewGuess.isEnabled=false
            val voiceId = requireContext().resources.getIdentifier(selectedWord.voice, "raw", requireContext().packageName)
            playMusic(voiceId)
            mediaPlayer.setOnCompletionListener {
                binding.recyclerGuess.isEnabled = true
                binding.cardViewGuess.isEnabled=true
                if(!guessedWord.flafThree){
                    arrayForGuessing?.find { it.idWord == guessedWord.idWord }?.flafThree = true
                    viewModel.setWordForGuess(arrayForGuessing) }
                playGuess()
            } } else{
            binding.recyclerGuess.isEnabled = false
            binding.cardViewGuess.isEnabled=false
            playMusic(R.raw.voice_another_pic)
            mediaPlayer.setOnCompletionListener {
                binding.recyclerGuess.isEnabled = true
                binding.cardViewGuess.isEnabled=true
            }}



    }
    private fun updateProgressBar(){
        binding.countGues.text="$score/7"
        binding.progressBarInGuess.progress = score

    }
    fun playMusic(id: Int){
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
    }


}