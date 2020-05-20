package com.example.mobilne_projekt.data.db

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

class CourseRepository(private val courseDao: CourseDao) {

    fun getAllCourses(): List<Course> {
        return courseDao.getAllCourses()
    }

    val allCourses: LiveData<List<Course>> = courseDao.getAllCoursesLiveData()
    val allCoursesCount: LiveData<Int> = courseDao.getCoursesCountLiveData()

    fun getCourseById(id: Int): Course {
        return courseDao.getCourseById(id)
    }

    fun getWordsFromCourse(id: Int): List<Word> {
        return getCourseById(id).words
    }

    suspend fun insertWord(course: Course, word: Word) {
        var newWordsList = mutableListOf<Word>()

        for(w in course.words)
            newWordsList.add(w)
        newWordsList.add(word)

        course.words = newWordsList
        insert(course)
    }

    suspend fun insert(course: Course) {
        return courseDao.insert(course)
    }

    suspend fun delete(course: Course) {
        return courseDao.delete(course)
    }

}