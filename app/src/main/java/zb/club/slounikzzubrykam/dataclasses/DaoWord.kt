package zb.club.slounikzzubrykam.dataclasses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoWord {
    @Query("SELECT * FROM word_table WHERE topic =:topic")
    fun  readWordInTopic( topic: String):LiveData<List<Word>>

    @Query("SELECT * FROM topic_table")
    fun  getAllTopic():LiveData<List<Topic>>

    @Insert
    suspend fun insertWord(word: Word)



}