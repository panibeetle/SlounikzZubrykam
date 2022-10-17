package zb.club.slounikzzubrykam.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "topic_table")
data class Topic(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "topic") val topic: String,
    @ColumnInfo(name = "picture_topic") val picture: String,
    @ColumnInfo(name = "voice_topic") val voice: String,
    @ColumnInfo(name = "flag_one_topic") val flagOneTopic: Boolean,
    @ColumnInfo(name = "flag_two_topic") val flagTwoToic: Boolean,
    @ColumnInfo(name = " flag_three_topic") val flafThreeTopic: Boolean,
    @ColumnInfo(name = "flag_four") val flagFour: Boolean,
    @ColumnInfo(name = " flag_five") val flagFive: Boolean,

)