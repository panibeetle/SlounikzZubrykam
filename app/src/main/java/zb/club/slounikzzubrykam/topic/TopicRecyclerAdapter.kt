package zb.club.slounikzzubrykam.topic


import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import zb.club.slounikzzubrykam.R
import zb.club.slounikzzubrykam.dataclasses.Topic
import zb.club.slounikzzubrykam.dataclasses.TopicWithWord
import zb.club.slounikzzubrykam.dataclasses.WordViewModel


class TopicRecyclerAdapter: RecyclerView.Adapter<TopicRecyclerAdapter.MyHolder>() {

    private var topicList = emptyList<TopicWithWord>()
    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.topic_card_recycler, parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val currentItem = topicList[position]

        val textViewTopic = holder.itemView.findViewById<TextView>(R.id.text_view_topic_rec)
        val imageTopic = holder.itemView.findViewById<ImageView>(R.id.image_topic)
        val card = holder.itemView.findViewById<CardView>(R.id.topiccard)
        val count = holder.itemView.findViewById<TextView>(R.id.countTopic)
        val progressBarInCount = holder.itemView.findViewById<ProgressBar>(R.id.progressBarInRecycler)

        textViewTopic.text = currentItem.topic.topic
        var countLearned = 0
        for(i in currentItem.word){
            if (i.flagFour) {
                countLearned += 1
            }

        }

        count.text="${countLearned}/${currentItem.word.size}"

        progressBarInCount.max = currentItem.word.size
        progressBarInCount.progress = countLearned
        val topic =currentItem.topic
        val context: Context = holder.itemView.context
        card.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.tap1)
            mediaPlayer?.start()
            val action = TopicFragmentDirections.actionTopicFragmentToRepeatFragment(topic.topic)
            it.findNavController().navigate(action)
            }

   // Try to find the resource with that name (icons in drawable folder)
        try {
            val nameDrow = currentItem.topic.picture

            val drawableId = context.resources.getIdentifier(nameDrow, "drawable", context.packageName)



            imageTopic.setImageResource(drawableId)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: NoSuchFieldException) {
            // if no icon is found
            imageTopic.setImageResource(R.drawable.zubr_happy)
        }




    }

    override fun getItemCount(): Int {
       return topicList.size
    }

    fun setData(topic: List<TopicWithWord>){
        this.topicList = topic
        notifyDataSetChanged()
    }
}