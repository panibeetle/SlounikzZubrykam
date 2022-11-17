package zb.club.slounikzzubrykam.dataclasses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "filling") val filling: Int,
    @ColumnInfo(name = "heart") val heart: Int,
    @ColumnInfo(name = "age") val age:Int
)
