package zb.club.slounikzzubrykam

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import zb.club.slounikzzubrykam.databinding.ActivityMeetingWithWordBinding


class MeetingWithWord : AppCompatActivity() {
    private lateinit var binding: ActivityMeetingWithWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meeting_with_word)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_meeting_with_word)

        binding.imageView8.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.garbuz)
            mediaPlayer?.start()
            binding.textView8.visibility = View.VISIBLE
        binding.textView7.visibility = View.INVISIBLE}

        binding.imageView7.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.burak)
            mediaPlayer?.start()
            binding.textView7.visibility = View.VISIBLE
            binding.textView8.visibility = View.INVISIBLE}

        binding.buttonRepeat.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.tap1)
            mediaPlayer?.start()
            val intent = Intent(this, RepeatWord::class.java)
            startActivity(intent) }
    }
}