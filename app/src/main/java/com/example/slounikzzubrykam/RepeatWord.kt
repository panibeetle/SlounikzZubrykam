package com.example.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.slounikzzubrykam.databinding.ActivityMainBinding
import com.example.slounikzzubrykam.databinding.ActivityRepeatWordBinding

class RepeatWord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRepeatWordBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_repeat_word)

        binding.button2.setOnClickListener {
            val intent = Intent(this, GuessGame::class.java)
            startActivity(intent)
        }
    }
}