package zb.club.slounikzzubrykam.dataclasses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoWord {
    @Query("SELECT * FROM word_table")
    fun  readWords():LiveData<List<Word>>

    @Query("SELECT * FROM word_table WHERE topic =:topic")
    fun  readWordInTopic( topic: String):LiveData<List<Word>>

    @Query("SELECT * FROM word_table WHERE topic =:topic AND flag_one < 0 ORDER BY random() LIMIT 7")
    fun  getSevenRandomWords( topic: String):LiveData<List<Word>>

    @Query("SELECT * FROM topic_table ORDER BY topic DESC")
    fun  getAllTopic():LiveData<List<Topic>>

    @Insert
    suspend fun insertWord(word: Word)

    @Query("SELECT * FROM word_table WHERE topic =:topic AND flag_one < :n ORDER BY random() LIMIT 7")
    suspend fun  getSevenRandomWordsSuspend( topic: String, n:Int):MutableList<Word>

    @Query("SELECT * FROM word_table WHERE topic =:topic AND flag_one < 2 ORDER BY random() LIMIT :n")
    suspend fun  getNRandomWordsSuspend( topic: String, n:Int):MutableList<Word>


    @Query("SELECT * FROM word_table WHERE idWord IN (:id)")
    suspend fun  getWordById( id:List<Long>):MutableList<Word>


    @Query("SELECT * FROM score")
    fun getScore():LiveData<Score>

    @Update
    suspend fun updateScore(score: Score)

    @Update
    suspend fun updateTopic(topic: Topic)


    @Update
    suspend fun updateWords(words: List<Word>): Int

}