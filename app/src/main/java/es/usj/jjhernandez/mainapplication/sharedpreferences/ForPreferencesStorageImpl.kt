package es.usj.jjhernandez.mainapplication.sharedpreferences

import android.content.SharedPreferences

const val SHARED_PREFERENCES_NAME = "PREFERENCES"
const val SHARED_PREFERENCES_KEY = "VALUES"

class ForPreferencesStorageImpl(private val sharedPreferences: SharedPreferences) :
    ForPreferencesStorage {

    override fun save(value: String) {
        val values = list()
        with(sharedPreferences.edit()) {
            putStringSet(SHARED_PREFERENCES_KEY, values.plus(value))
            apply()
        }
    }

    override fun delete(value: String) {
        val values = list()
        with(sharedPreferences.edit()) {
            putStringSet(SHARED_PREFERENCES_KEY, values!!.minus(value))
            apply()
        }
    }

    override fun list(): Set<String> {
        return sharedPreferences.getStringSet(SHARED_PREFERENCES_KEY, mutableSetOf())!!
    }

    override fun contains(value: String): Boolean {
        return list().contains(value)
    }

}
