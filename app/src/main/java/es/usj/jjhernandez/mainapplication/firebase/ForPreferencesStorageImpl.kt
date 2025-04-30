package es.usj.jjhernandez.mainapplication.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import es.usj.jjhernandez.mainapplication.ForPreferencesStorage
import es.usj.jjhernandez.mainapplication.PREFERENCES_KEY
import es.usj.jjhernandez.mainapplication.STORAGE

class ForPreferencesStorageImpl : ForPreferencesStorage {

    private val database = FirebaseDatabase.getInstance("https://mainapplicationinfirebase-default-rtdb.europe-west1.firebasedatabase.app/")
    private val preferencesRef = database.getReference(STORAGE)

    override fun save(value: String) {
        preferencesRef.child(PREFERENCES_KEY).setValue(value)
    }

    override fun delete(value: String) {
        preferencesRef.child(PREFERENCES_KEY).removeValue()
    }

    override fun list(): Set<String> {
        val preferences = mutableSetOf<String>()
        var loading = true
        preferencesRef.addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                for (childSnapshot in snapshot.children) {
                    val key = childSnapshot.key
                    val value = childSnapshot.getValue(String::class.java)
                    key?.let {
                        preferences.add(key)
                    }
                    value?.let {
                        preferences.add(value)
                    }
                    loading = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                print(error)
            }
        })
        while(loading) {
            //doNothing()
        }
        return preferences
    }

    override fun contains(value: String): Boolean {
        return list().contains(value)
    }

}