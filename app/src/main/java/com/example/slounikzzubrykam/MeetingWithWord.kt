package com.example.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.slounikzzubrykam.databinding.ActivityMeetingWithWordBinding
import com.example.slounikzzubrykam.databinding.ActivityRewardBinding

class MeetingWithWord : AppCompatActivity() {
    private lateinit var binding: ActivityMeetingWithWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting_with_word)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_meeting_with_word)

        binding.imageView6.setOnClickListener { binding.textView8.visibility = View.VISIBLE
        binding.textView7.visibility = View.INVISIBLE}

        binding.imageView7.setOnClickListener { binding.textView7.visibility = View.VISIBLE
            binding.textView8.visibility = View.INVISIBLE}

        binding.buttonRepeat.setOnClickListener {  val intent = Intent(this, RepeatWord::class.java)
            startActivity(intent) }
    }
}