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
    fun  getSevenRandomWords(topic:String): LiveData<List<Word>>{

        return repository.getSevenWordInTopic(topic)

    }

    fun addWord(word: Word){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertWord(word)

        }
    }


    private val _arrayWordForGame = MutableLiveData<MutableList<Word>>()
    val arrayWordForGame: LiveData<MutableList<Word>> get() = _arrayWordForGame

    fun setWordForGame(arrayWord: MutableList<Word>) {
        _arrayWordForGame.value  = arrayWord
    }
    private val _sizeWordForGame = MutableLiveData<Int>(0)
    val sizeWordForGame: LiveData<Int> get() = _sizeWordForGame



    fun getSevenWordSuspend(topic:(String), n: Int){
        var list = mutableListOf<Word>()
        viewModelScope.launch(Dispatchers.IO) {
            list = repository.getSevenWordSuspend(topic, n)
            _arrayWordForGame.postValue(list)
            _sizeWordForGame.postValue(list.size)
        }
    }




}