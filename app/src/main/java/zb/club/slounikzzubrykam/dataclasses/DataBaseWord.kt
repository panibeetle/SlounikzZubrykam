package zb.club.slounikzzubrykam.dataclasses

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class, Topic::class, Score::class], version = 1, exportSchema = true)
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
                    "word.db"
                ).createFromAsset("database/wordd.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }


}