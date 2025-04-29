package es.usj.jjhernandez.mainapplication.sharedpreferences

interface ForPreferencesStorage {
    fun save(value: String)
    fun delete(value: String)
    fun list(): Set<String>
    fun contains(value: String): Boolean
}