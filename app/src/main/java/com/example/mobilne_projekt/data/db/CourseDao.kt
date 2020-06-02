package com.example.mobilne_projekt.data.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

@Dao
interface CourseDao {
    @Query("SELECT * FROM course ORDER BY courseName ASC")
    fun getAllCourses(): List<Course>

    @Query("SELECT * FROM course ORDER BY courseName ASC")
    fun getAllCoursesLiveData(): LiveData<List<Course>>

    @Query("SELECT * FROM course WHERE courseName=:name")
    fun getCourseByName(name: String): Course

    @Query("SELECT * FROM course WHERE courseName=:name")
    fun getCourseByNameLiveData(name: String): LiveData<Course>

    fun getWordsFromCourse(name: String): List<Word> {
        return getCourseByName(name).words
    }

    fun getWordsLiveData(name: String): LiveData<List<Word>> {
        val wordMutableLiveData = MutableLiveData<List<Word>>()
        wordMutableLiveData.postValue(getCourseByName(name).words)

        return wordMutableLiveData
    }

    @Query("UPDATE Course SET courseName = :name WHERE courseName = :oldName")
    fun updateCourseName(oldName:String, name: String)

    @Query("SELECT count(courseName) FROM course")
    fun getCoursesCountLiveData(): LiveData<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(course: Course)

    @Delete
    fun delete(course: Course)
}
