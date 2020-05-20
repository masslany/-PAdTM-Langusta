package com.example.mobilne_projekt.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

@Dao
interface CourseDao {
    @Query("SELECT * FROM course ORDER BY courseName ASC")
    fun getAllCourses(): List<Course>

    @Query("SELECT * FROM course ORDER BY courseName ASC")
    fun getAllCoursesLiveData(): LiveData<List<Course>>

    @Query("SELECT * FROM course WHERE id=:id")
    fun getCourseById(id: Int): Course

    fun getWordsFromCourse(id: Int): List<Word> {
        return getCourseById(id).words
    }

    @Query("SELECT count(courseName) FROM course")
    fun getCoursesCountLiveData(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(course: Course, word: Word) {

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(course: Course)
}