package es.usj.jjhernandez.mainapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.jjhernandez.mainapplication.TABLE

@Dao
interface RoomPreferencesRepository {

    @Query("SELECT field, value FROM $TABLE")
    fun getAll(): List<Preference>

    @Insert
    fun insert(value: Preference)

    @Query("DELETE FROM $TABLE WHERE field = :key")
    fun delete(key: String)
}