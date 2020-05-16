package com.example.mobilne_projekt.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import androidx.room.TypeConverters
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

@Database(entities = [Course::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class FlashcardsDatabase : RoomDatabase() {

    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: FlashcardsDatabase? = null

        fun getDatabase(context: Context): FlashcardsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlashcardsDatabase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}