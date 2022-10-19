package zb.club.slounikzzubrykam.repeate

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.dataclasses.Topic
import zb.club.slounikzzubrykam.dataclasses.Word
import zb.club.slounikzzubrykam.topic.TopicRecyclerAdapter

class RepeateRecyclerAdapter(var tappedWord: WordRepeateInterface): RecyclerView.Adapter<RepeateRecyclerAdapter.MyHolder>() {
    private var wordList = emptyList<Word>()

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_word, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var res = 0
        val context: Context = holder.itemView.context
        val currentItem = wordList[position]
        val imageViewChek = holder.itemView.findViewById<ImageView>(R.id.imageViewCheck)
        val textViewWord = holder.itemView.findViewById<TextView>(R.id.textViewWord)
        val imageWord= holder.itemView.findViewById<ImageView>(R.id.imageViewWordPicture)
        val card = holder.itemView.findViewById<CardView>(R.id.card_word)
        textViewWord.text = currentItem.word
        if(currentItem.flagTwo) {
            imageViewChek.visibility = View.VISIBLE
        } else {imageViewChek.visibility = View.INVISIBLE}



        card.setOnClickListener {

            try {
                val nameVoice = currentItem.voice
                val voiceId = context.resources.getIdentifier(nameVoice, "raw", context.packageName)
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, voiceId)
                mediaPlayer?.start()}catch (e: IllegalAccessException) {
                var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.tap1)
                mediaPlayer?.start()
            }


            tappedWord.onTappedWord(currentItem)

        }

        // Try to find the resource with that name (icons in drawable folder)
        try {
            val nameDrow = currentItem.picture
            val drawableId = context.resources.getIdentifier(nameDrow, "drawable", context.packageName)
            imageWord.setImageResource(drawableId)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            // if no icon is found
            imageWord.setImageResource(R.drawable.zubr_happy)
        }





    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun setData(words: List<Word>){
        this.wordList = words
        notifyDataSetChanged()
    }
}