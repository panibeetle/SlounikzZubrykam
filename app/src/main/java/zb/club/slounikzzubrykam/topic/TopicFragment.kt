package zb.club.slounikzzubrykam.topic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.databinding.FragmentTopicBinding
import zb.club.slounikzzubrykam.dataclasses.WordViewModel


class TopicFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: WordViewModel
    lateinit var  binding: FragmentTopicBinding
    lateinit var adapter: TopicRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_topic, container,false)
        adapter = TopicRecyclerAdapter()
        binding.recyclerTopic.adapter = adapter
        binding.recyclerTopic.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        viewModel.getAllTopic.observe(viewLifecycleOwner, Observer {
                topic -> adapter.setData(topic)
        })


        return binding.root

    }


}