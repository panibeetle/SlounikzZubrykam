package zb.club.slounikzzubrykam.dataclasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.Executors

class WordRepository(private val daoWord: DaoWord) {

    val getAllTopic: LiveData<List<Topic>> = daoWord.getAllTopic()

   fun readWordInTopic(topic:String): LiveData<List<Word>>{
        return daoWord.readWordInTopic(topic)

    }

    suspend fun insertWord(word: Word){
        daoWord.insertWord(word)
    }






}