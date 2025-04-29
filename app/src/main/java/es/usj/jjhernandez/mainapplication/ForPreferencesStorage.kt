package es.usj.jjhernandez.mainapplication

const val STORAGE = "PREFERENCES"
const val PREFERENCES_KEY = "VALUES"

interface ForPreferencesStorage {
    fun save(value: String)
    fun delete(value: String)
    fun list(): Set<String>
    fun contains(value: String): Boolean
}