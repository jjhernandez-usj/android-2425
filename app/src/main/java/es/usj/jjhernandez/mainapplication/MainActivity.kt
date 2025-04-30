package es.usj.jjhernandez.mainapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.jjhernandez.mainapplication.databinding.ActivityMainBinding
import es.usj.jjhernandez.mainapplication.sqlite.ForPreferencesStorageImpl as SQLiteImpl
import es.usj.jjhernandez.mainapplication.sharedpreferences.ForPreferencesStorageImpl as SharedPreferencesImpl
import es.usj.jjhernandez.mainapplication.room.ForPreferencesStorageImpl as RoomImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val view by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val scope by lazy { CoroutineScope(Dispatchers.IO) }

    private val repository: ForPreferencesStorage by lazy {
        /*SharedPreferencesImpl(
            application.getSharedPreferences(
                STORAGE,
                MODE_PRIVATE
            )
        )
        SQLiteImpl(
            PreferenceSQLDatabaseHelper(this, STORAGE, null, 2)
        )*/
        RoomImpl(
            applicationContext
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        scope.launch {
            repository.save("Hello Juanjo!")
            val content = repository.list().joinToString()
            withContext(Dispatchers.Main) {
                view.tvContent.text = content
            }
        }
    }
}