package es.usj.jjhernandez.mainapplication.sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteOpenHelper
import es.usj.jjhernandez.mainapplication.ForPreferencesStorage
import es.usj.jjhernandez.mainapplication.PREFERENCES_KEY
import es.usj.jjhernandez.mainapplication.TABLE

class ForPreferencesStorageImpl(private val helper: SQLiteOpenHelper) : ForPreferencesStorage {

    override fun save(value: String) {
        val db = helper.writableDatabase
        val soccerPlayer = ContentValues()
        soccerPlayer.put("field", PREFERENCES_KEY)
        soccerPlayer.put("value", value)
        db.insert(TABLE, null, soccerPlayer)
        db.close()
    }

    override fun delete(value: String) {
        val db = helper.writableDatabase
        db.delete(TABLE, "field = ?", arrayOf(value))
        db.close()
    }

    override fun list(): Set<String> {
        val db = helper.readableDatabase
        val results = mutableListOf<String>()
        val cursor = db.rawQuery("SELECT field, value FROM $TABLE", arrayOf())
        if (cursor.moveToFirst()) {
            do {
                val field = cursor.getString(0)
                results.add(field)
                val value = cursor.getString(1)
                results.add(value)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return results.toSet()
    }

    override fun contains(value: String): Boolean {
        return list().contains(value)
    }
}