package zb.club.slounikzzubrykam

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import zb.club.slounikzzubrykam.databinding.ActivityGuessGameBinding

class GuessGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGuessGameBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_guess_game
        )
        binding.imageView19.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.tap_2)
            mediaPlayer?.start()
            val intent = Intent(this, zb.club.slounikzzubrykam.Wordlee::class.java)
            startActivity(intent)
        }
    }
}