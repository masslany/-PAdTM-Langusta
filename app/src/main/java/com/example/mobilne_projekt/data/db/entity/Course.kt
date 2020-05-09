package com.example.mobilne_projekt.data.db.entity

import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mobilne_projekt.data.db.DataConverter

@Entity(tableName = "course")
data class Course(
    val courseName: String,
    @TypeConverters(DataConverter::class)
    var words: List<Word>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}