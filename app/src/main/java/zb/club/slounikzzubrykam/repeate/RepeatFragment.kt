package zb.club.slounikzzubrykam.repeate

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
import zb.club.slounikzzubrykam.databinding.FragmentRepeatBinding
import zb.club.slounikzzubrykam.dataclasses.WordViewModel
import zb.club.slounikzzubrykam.topic.TopicRecyclerAdapter


class RepeatFragment : Fragment() {
    private lateinit var viewModel: WordViewModel
    lateinit var adapter: RepeateRecyclerAdapter
    lateinit var  binding: FragmentRepeatBinding
    val args: RepeatFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_repeat, container,false)
        binding.button2.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.tap_2)
            mediaPlayer?.start()
            findNavController().navigate(R.id.action_repeatFragment_to_guessFragment)
        }

        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        val topic = args.topic.toString()
        adapter = RepeateRecyclerAdapter()
        binding.recyclerWord.adapter = adapter
        binding.recyclerWord.layoutManager =LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        viewModel.getSevenWordSuspend(topic)
        viewModel.arrayWordForGame.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        return binding.root
    }


}