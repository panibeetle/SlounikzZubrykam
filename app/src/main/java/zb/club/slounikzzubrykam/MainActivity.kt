package zb.club.slounikzzubrykam

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import zb.club.slounikzzubrykam.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.button.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.tap1)
            mediaPlayer?.start()
            val intent = Intent(this, Reward::class.java)
            startActivity(intent)
        }

    }
}