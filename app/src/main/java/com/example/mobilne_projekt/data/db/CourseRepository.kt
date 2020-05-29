package com.example.mobilne_projekt.data.db

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    fun getCourseByNameLiveData(name: String): LiveData<Course> {
        return courseDao.getCourseByNameLiveData(name)
    }

    fun getWordsFromCourse(name: String): List<Word> {
        return getCourseByName(name).words
    }

    fun getWordsLiveData(name: String): LiveData<List<Word>> {
        return courseDao.getWordsLiveData(name)
    }

    fun getWordsCountLiveData(name: String): LiveData<Int> {
        val wordsCount = MutableLiveData<Int>()
        wordsCount.postValue(getWordsFromCourse(name).size)
        return wordsCount
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
