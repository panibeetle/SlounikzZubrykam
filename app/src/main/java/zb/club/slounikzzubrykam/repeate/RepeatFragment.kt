package zb.club.slounikzzubrykam.repeate

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentRepeatBinding
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.dataclasses.WordViewModel


class RepeatFragment : Fragment(), WordRepeateInterface {
    private lateinit var viewModel: WordViewModel

    lateinit private var mediaPlayer: MediaPlayer
    lateinit var adapter: RepeateRecyclerAdapter
    lateinit var  binding: FragmentRepeatBinding
    val args: RepeatFragmentArgs by navArgs()
    lateinit var topic:String
    lateinit var idWords: LongArray
    var isMagic = true
    var score =0
    var arrayForChanging = mutableListOf<Word>()
    var idWord = arrayListOf<Long>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_repeat, container,false)
        score=0
        updateProgressBar()
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        topic = args.topic.toString()
        adapter = RepeateRecyclerAdapter(this)
        binding.recyclerWord.adapter = adapter
        binding.progressBarInRepeate.max = 7
        binding.recyclerWord.layoutManager =LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        getNewWordForGame(topic)

        viewModel.arrayWordForGame.observe(viewLifecycleOwner, Observer {

            adapter.setData(it)
            var isButtonVisible: Boolean = false
            for (i in it){

                if(i.flagTwo == false){

                    isButtonVisible = false
                    break
                }
                isButtonVisible = true

            }
            if(isButtonVisible == false){
                binding.button2.visibility = View.INVISIBLE
            }else{
                binding.button2.visibility = View.VISIBLE
                if(isMagic == true){
                    binding.button2.isEnabled = false
                    playMusic(R.raw.magic)
                    mediaPlayer.setOnCompletionListener { binding.button2.isEnabled = true }
                    isMagic=false}
               }
            var a = 0
            for (i in it){
                if(i.flagTwo){
                    a = a+1

                }
            }
            score = a
            updateProgressBar()

        })


        binding.button2.setOnClickListener {
            playMusic(R.raw.tap_2)
             val action = RepeatFragmentDirections.actionRepeatFragmentToGuessFragment(idWords)
            findNavController().navigate(action)
        }
        return binding.root
    }

    private fun getNewWordForGame(topic: String) {
        if(viewModel.sizeWordForGame.value!! == 0){
        viewModel.getSevenWordSuspend(topic, 1)
        if (viewModel.sizeWordForGame.value!! < 7){
            viewModel.getSevenWordSuspend(topic, 2)
        }}
    }

    override fun onTappedWord(tapedWord: Word) {


        binding.recyclerWord.setOnTouchListener(OnTouchListener { v, event -> // I will consume all touch events,
            // so View.onTouchEvent() will not be called.
            true
        })

        val nameVoice = tapedWord.voice
        val voiceId = requireContext().resources.getIdentifier(nameVoice, "raw", requireContext().packageName)
        playMusic(voiceId)
        mediaPlayer.setOnCompletionListener {

            binding.recyclerWord.setOnTouchListener(OnTouchListener { v, event -> // I will consume all touch events,
                // so View.onTouchEvent() will not be called.
                false
            })


        }

        arrayForChanging = viewModel.arrayWordForGame.value!!
        if(!tapedWord.flagTwo){
            playMusic(R.raw.ding)
        arrayForChanging?.find { it.idWord == tapedWord.idWord }?.flagTwo = true
        viewModel.setWordForGame(arrayForChanging)



        for(i in arrayForChanging){
            idWord.add(i.idWord)
        }
        idWords= idWord.toLongArray()

    }

    }
    private fun updateProgressBar(){
        binding.count.text="$score/7"
        binding.progressBarInRepeate.progress = score

    }

    fun playMusic(id: Int){
        mediaPlayer = MediaPlayer.create(requireContext(), id)
        mediaPlayer.start()
    }


}