package es.usj.jjhernandez.mainapplication.sharedpreferences

import android.content.SharedPreferences
import es.usj.jjhernandez.mainapplication.ForPreferencesStorage
import es.usj.jjhernandez.mainapplication.PREFERENCES_KEY

class ForPreferencesStorageImpl(private val sharedPreferences: SharedPreferences) :
    ForPreferencesStorage {

    override fun save(value: String) {
        val values = sharedPreferences.getStringSet(PREFERENCES_KEY, mutableSetOf())!!
        with(sharedPreferences.edit()) {
            putStringSet(PREFERENCES_KEY, values.plus(value))
            apply()
        }
    }

    override fun delete(value: String) {
        val values = sharedPreferences.getStringSet(PREFERENCES_KEY, mutableSetOf())
        with(sharedPreferences.edit()) {
            putStringSet(PREFERENCES_KEY, values!!.minus(value))
            apply()
        }
    }

    override fun list(): Set<String> {
        return sharedPreferences.getStringSet(PREFERENCES_KEY, mutableSetOf())!!
    }

    override fun contains(value: String): Boolean {
        return list().contains(value)
    }

}
