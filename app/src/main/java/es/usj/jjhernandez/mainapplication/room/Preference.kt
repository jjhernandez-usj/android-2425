package es.usj.jjhernandez.mainapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.usj.jjhernandez.mainapplication.TABLE

@Entity(tableName = TABLE)
data class Preference(
    @PrimaryKey
    val field: String,
    val value: String
)