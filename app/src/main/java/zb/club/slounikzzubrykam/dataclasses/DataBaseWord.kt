package zb.club.slounikzzubrykam.dataclasses

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [Word::class, Topic::class], version = 1, exportSchema = false)
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
                    "worddatabase.db"
                ).createFromAsset("database/worddatabase.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }


}