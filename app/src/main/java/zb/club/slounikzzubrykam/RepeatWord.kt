package zb.club.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import zb.club.slounikzzubrykam.databinding.ActivityRepeatWordBinding


class RepeatWord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRepeatWordBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_repeat_word)

        binding.button2.setOnClickListener {
            val intent = Intent(this, zb.club.slounikzzubrykam.GuessGame::class.java)
            startActivity(intent)
        }
    }
}