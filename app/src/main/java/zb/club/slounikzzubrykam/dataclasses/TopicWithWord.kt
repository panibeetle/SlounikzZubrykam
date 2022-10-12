package zb.club.slounikzzubrykam.dataclasses

import androidx.room.Embedded
import androidx.room.Relation

data class TopicWithWord(
    @Embedded val topic: Topic,
    @Relation(
    parentColumn = "id",
    entityColumn ="topic_id")
    val word: List<Word>


)


