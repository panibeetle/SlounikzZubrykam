package zb.club.slounikzzubrykam.dataclasses

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Entity(tableName = "word_table")
@Parcelize
data class Word(
    @PrimaryKey(autoGenerate = true) val idWord: Long,
    @ColumnInfo(name = "topic_id") val topicId: Long,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "picture") val picture: String,
    @ColumnInfo(name = "voice") val voice: String,
    @ColumnInfo(name = "flag_one") var flagOne: Boolean,
    @ColumnInfo(name = "flag_two") var flagTwo: Boolean,
    @ColumnInfo(name = " flag_three") var flafThree: Boolean,
    @ColumnInfo(name = "flag_four") var flagFour: Boolean,
    @ColumnInfo(name = "topic") val topic:String,
    @ColumnInfo(name = "word_pol") val wordPol: String,
    @ColumnInfo(name = "voice_pol") val voicePol: String
):Parcelable
