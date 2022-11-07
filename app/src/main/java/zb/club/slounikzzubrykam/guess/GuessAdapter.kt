package zb.club.slounikzzubrykam.guess

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.dataclasses.Word


class GuessAdapter(var selectedWord: GuessSelectedWordPosition): RecyclerView.Adapter<GuessAdapter.MyHolder>() {
    private var wordList = mutableListOf<Word>()
    private var lastCheckedPos = 0
    private var lastChecked: MaterialCardView? = null

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_for_guess_word, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var res = 0

        val context: Context = holder.itemView.context
        val currentItem = wordList[position]
        val imageWord= holder.itemView.findViewById<ImageView>(R.id.imageViewForGuess)
        val card = holder.itemView.findViewById<CardView>(R.id.cardwithpickguess)
        if (position == lastCheckedPos){
            lastChecked = card as MaterialCardView?
        }else{card.isSelected = false}

        card.setOnClickListener {
            lastChecked!!.isSelected = false
            lastCheckedPos = holder.adapterPosition
            card.isSelected = true
            selectedWord.oSelectedWord(currentItem)

            try {
            val nameVoice = currentItem.voice
            val voiceId = context.resources.getIdentifier(nameVoice, "raw", context.packageName)
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, voiceId)
            mediaPlayer?.start()

        }catch (e: IllegalAccessException) {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.tap1)
            mediaPlayer?.start()
        }
        notifyDataSetChanged()}



        // Try to find the resource with that name (icons in drawable folder)
        try {
            val nameDrow = currentItem.picture
            val drawableId = context.resources.getIdentifier(nameDrow, "drawable", context.packageName)
            imageWord.setImageResource(drawableId)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            // if no icon is found
            imageWord.setImageResource(R.drawable.art_cup)
        }





    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun setData(words: MutableList<Word>){
        this.wordList = words
        notifyDataSetChanged()
    }
}