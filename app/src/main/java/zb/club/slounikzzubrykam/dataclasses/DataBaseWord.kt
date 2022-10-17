package zb.club.slounikzzubrykam.dataclasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Word::class, Topic::class], version = 1, exportSchema = true)
abstract class DataBaseWord: RoomDatabase() {

    abstract fun daoWord(): DaoWord

    companion object {
        private var INSTANCE: DataBaseWord? = null

        fun getDatabase(context:Context): DataBaseWord {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return  tempInstance
            }
            synchronized(this ){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseWord::class.java,
                    "word_database.db"
                ).createFromAsset("database/word_database.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }


}