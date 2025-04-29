package es.usj.jjhernandez.mainapplication.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import es.usj.jjhernandez.mainapplication.STORAGE

class PreferenceSQLDatabaseHelper(context: Context?, database: String?,
    cursorFactory: SQLiteDatabase.CursorFactory?,
    version: Int) : SQLiteOpenHelper(context, database, cursorFactory, version) {

    companion object {
        private const val CREATE_TABLE = "CREATE TABLE $STORAGE (field TEXT, value TEXT)"
        private const val DROP_TABLE = "DROP TABLE IF EXISTS $STORAGE"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(DROP_TABLE)
        db.execSQL(CREATE_TABLE)
    }
}
