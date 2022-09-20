package com.example.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.slounikzzubrykam.databinding.ActivityGuessGameBinding
import com.example.slounikzzubrykam.databinding.ActivityMainBinding

class GuessGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGuessGameBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_guess_game)
        binding.imageView19.setOnClickListener {
            val intent = Intent(this, Reward::class.java)
            startActivity(intent)
        }
    }
}