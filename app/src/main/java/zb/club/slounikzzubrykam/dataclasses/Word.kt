package zb.club.slounikzzubrykam.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey(autoGenerate = true) val idWord: Long,
    @ColumnInfo(name = "topic_id") val topicId: Long,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "voice") val voice: String,
    @ColumnInfo(name = "flag_one") val flagOne: Boolean,
    @ColumnInfo(name = "flag_two") val flagTwo: Boolean,
    @ColumnInfo(name = " flag_three") val flafThree: Boolean,
    @ColumnInfo(name = "flag_four") val flagFour: Boolean,
    @ColumnInfo(name = " flag_five") val flagFive: Boolean,
    @ColumnInfo(name = "topic") val topic:String
)