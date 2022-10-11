package zb.club.slounikzzubrykam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import zb.club.slounikzzubrykam.databinding.ActivityTopicListBinding

import zb.club.slounikzzubrykam.dataclasses.WordViewModel

class TopicList : AppCompatActivity() {
    lateinit var adapter:TopicRecyclerAdapter
    private lateinit var viewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTopicListBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_topic_list)
        adapter = TopicRecyclerAdapter()
        binding.recyclerTopic.adapter = adapter
        binding.recyclerTopic.layoutManager = GridLayoutManager(this,2)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        viewModel.getAllTopic.observe(this, Observer {
            topic -> adapter.setData(topic)
        })


    }
}