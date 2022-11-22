package zb.club.slounikzzubrykam.dataclasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executors

class WordRepository(private val daoWord: DaoWord) {

    val getAllTopic: LiveData<List<Topic>> = daoWord.getAllTopic()
    val getScore: LiveData<Score> = daoWord.getScore()
    val getAllWords: LiveData<List<Word>> = daoWord.readWords()
   fun readWordInTopic(topic:String): LiveData<List<Word>>{
        return daoWord.readWordInTopic(topic)

    }
    fun getSevenWordInTopic(topic:String): LiveData<List<Word>>{
        return daoWord.getSevenRandomWords(topic)

    }

    suspend fun insertWord(word: Word){
        daoWord.insertWord(word)
    }

    suspend fun getSevenWordSuspend(topic: String, n:Int):MutableList<Word>{
        return daoWord.getSevenRandomWordsSuspend(topic, n)
    }

    suspend fun  getWordById( id:List<Long>):MutableList<Word>{
        return  daoWord.getWordById(id)
    }

    suspend fun updateScore(score: Score){
        daoWord.updateScore(score)
    }
    suspend fun udateWords(words:List<Word>){
        daoWord.updateWords(words)
    }
    suspend fun updateTopic(topic: Topic){daoWord.updateTopic(topic)}



}