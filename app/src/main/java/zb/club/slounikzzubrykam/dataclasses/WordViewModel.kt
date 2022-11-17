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
    val getScore: LiveData<Score>
    private val  repository: WordRepository
    val returnedWord: MutableLiveData<List<Word>> = MutableLiveData()

   init {
       val wordDao = DataBaseWord.getDatabase(application).daoWord()
       repository = WordRepository(wordDao)
       getAllTopic = repository.getAllTopic
       getScore = repository.getScore
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




    private val _arrayWordForGuess = MutableLiveData<MutableList<Word>>()
    val arrayWordForGuess: LiveData<MutableList<Word>> get() = _arrayWordForGuess


    fun setWordForGuess(arrayWord: MutableList<Word>) {
        _arrayWordForGuess.value  = arrayWord
    }

    private val _arrayWordForGuessPlay = MutableLiveData<MutableList<Word>>()
    val arrayWordForGuessPlay: LiveData<MutableList<Word>> get() = _arrayWordForGuessPlay


    fun setWordForGuessPlay(arrayWord: MutableList<Word>) {
        _arrayWordForGuessPlay.value  = arrayWord
    }

    fun getGuessWordSuspend(id:List<Long>){
        var list:MutableList<Word>
        viewModelScope.launch(Dispatchers.IO) {
            list = repository.getWordById(id)
            _arrayWordForGuess.postValue(list)
            _arrayWordForGuessPlay.postValue(list)

        }
    }


    fun updateScore(score: Score){
        viewModelScope.launch {
            repository.updateScore(score)
        }
    }








}