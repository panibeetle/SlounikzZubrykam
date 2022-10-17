package zb.club.slounikzzubrykam.dataclasses

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application):AndroidViewModel(application) {
    val getAllTopic: LiveData<List<Topic>>
    private val  repository: WordRepository
    val returnedWord: MutableLiveData<List<Word>> = MutableLiveData()

   init {
       val wordDao = DataBaseWord.getDatabase(application).daoWord()
       repository = WordRepository(wordDao)
       getAllTopic = repository.getAllTopic
   }
     fun  readWordInTopic(topic:String): LiveData<List<Word>>{
        return repository.readWordInTopic(topic)

    }


    fun addWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWord(word)

        }
    }






}