package zb.club.slounikzzubrykam.dataclasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executors

class WordRepository(private val daoWord: DaoWord) {

    val getAllTopic: LiveData<List<Topic>> = daoWord.getAllTopic()

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




}