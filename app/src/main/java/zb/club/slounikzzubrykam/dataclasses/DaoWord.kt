package zb.club.slounikzzubrykam.dataclasses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoWord {
    @Query("SELECT * FROM word_table WHERE topic =:topic")
    fun  readWordInTopic( topic: String):LiveData<List<Word>>

    @Query("SELECT * FROM word_table WHERE topic =:topic AND flag_one = 0 ORDER BY random() LIMIT 7")
    fun  getSevenRandomWords( topic: String):LiveData<List<Word>>

    @Query("SELECT * FROM topic_table")
    fun  getAllTopic():LiveData<List<Topic>>

    @Insert
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM word_table WHERE topic =:topic AND flag_one < :n ORDER BY random() LIMIT 7")
    suspend fun  getSevenRandomWordsSuspend( topic: String, n:Int):MutableList<Word>

}