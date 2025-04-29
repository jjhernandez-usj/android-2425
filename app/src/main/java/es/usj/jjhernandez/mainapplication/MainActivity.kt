package es.usj.jjhernandez.mainapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import es.usj.jjhernandez.mainapplication.databinding.ActivityMainBinding
import es.usj.jjhernandez.mainapplication.sharedpreferences.ForPreferencesStorage
import es.usj.jjhernandez.mainapplication.sharedpreferences.ForPreferencesStorageImpl
import es.usj.jjhernandez.mainapplication.sharedpreferences.SHARED_PREFERENCES_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val view by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val scope by lazy {
        CoroutineScope(Dispatchers.IO)
    }

    private val repository: ForPreferencesStorage by lazy {
        ForPreferencesStorageImpl(
            application.getSharedPreferences(
                SHARED_PREFERENCES_NAME,
                MODE_PRIVATE
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(view.root)
        scope.launch {
            repository.save("Hello")
            val content = repository.list().joinToString()
            withContext(Dispatchers.IO) {
                view.tvContent.text = content
            }
        }
    }
}