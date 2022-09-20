package com.example.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.slounikzzubrykam.databinding.ActivityMainBinding
import com.example.slounikzzubrykam.databinding.ActivityTopicListBinding

class TopicList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityTopicListBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_topic_list)
        binding.cardViewVeg.setOnClickListener {
            val intent = Intent(this, MeetingWithWord::class.java)
            startActivity(intent)
        }
    }
}