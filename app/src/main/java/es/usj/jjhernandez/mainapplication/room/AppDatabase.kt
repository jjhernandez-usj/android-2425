package es.usj.jjhernandez.mainapplication.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Preference::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repository(): RoomPreferencesRepository
}