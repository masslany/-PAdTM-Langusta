package com.example.mobilne_projekt.data.db.entity

import android.os.Parcel
import android.os.Parcelable
import android.provider.ContactsContract
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mobilne_projekt.data.db.DataConverter

@Entity(tableName = "course")

data class Course(
    @PrimaryKey
    var courseName: String,
    @TypeConverters(DataConverter::class)
    var words: MutableList<Word>
) {

}

