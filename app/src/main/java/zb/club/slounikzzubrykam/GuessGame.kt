package zb.club.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.slounikzzubrykam.databinding.ActivityGuessGameBinding

class GuessGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGuessGameBinding = DataBindingUtil.setContentView(
            this, com.example.slounikzzubrykam.R.layout.activity_guess_game
        )
        binding.imageView19.setOnClickListener {
            val intent = Intent(this, zb.club.slounikzzubrykam.Reward::class.java)
            startActivity(intent)
        }
    }
}