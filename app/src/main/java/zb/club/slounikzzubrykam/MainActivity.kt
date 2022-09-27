package zb.club.slounikzzubrykam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.slounikzzubrykam.R
import com.example.slounikzzubrykam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)
        binding.button.setOnClickListener {
            val intent = Intent(this, Reward::class.java)
            startActivity(intent)
        }

    }
}