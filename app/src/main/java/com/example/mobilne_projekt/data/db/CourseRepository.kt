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

    fun getCourseByName(name: String): Course {
        return courseDao.getCourseByName(name)
    }

    fun getWordsFromCourse(name: String): List<Word> {
        return getCourseByName(name).words
    }

    fun insertWord(course: Course, word: Word) {

        val newWordsList = mutableListOf<Word>().apply { addAll(course.words)}
        newWordsList.add(word)

        course.words = newWordsList
        insertCourse(course)
    }

    fun insertCourse(course: Course) {
        return courseDao.insert(course)
    }

    fun deleteCourse(course: Course) {
        return courseDao.delete(course)
    }

}
