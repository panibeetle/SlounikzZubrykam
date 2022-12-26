package zb.club.slounikzzubrykam.dataclasses

import androidx.room.Embedded
import androidx.room.Relation

data class TopicWithWord(
    @Embedded val topic: Topic,
    @Relation(
    parentColumn = "topic",
    entityColumn ="topic")
    val word: List<Word>


)


