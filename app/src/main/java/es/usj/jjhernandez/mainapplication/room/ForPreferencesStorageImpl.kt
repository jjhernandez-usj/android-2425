package es.usj.jjhernandez.mainapplication.room

import android.content.Context
import androidx.room.Room
import es.usj.jjhernandez.mainapplication.ForPreferencesStorage
import es.usj.jjhernandez.mainapplication.PREFERENCES_KEY
import es.usj.jjhernandez.mainapplication.STORAGE

class ForPreferencesStorageImpl(context: Context) : ForPreferencesStorage {
    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, STORAGE
    ).build()

    private val repository by lazy {
        db.repository()
    }
    override fun save(value: String) {
        repository.insert(Preference(PREFERENCES_KEY, value))
    }

    override fun delete(value: String) {
        repository.delete(PREFERENCES_KEY)
    }

    override fun list(): Set<String> {
        val values = mutableSetOf<String>()
        repository.getAll().forEach { values.addAll(arrayOf(it.field, it.value))  }
        return values
    }

    override fun contains(value: String): Boolean {
        return list().contains(value)
    }
}