package com.example.mobilne_projekt.data.db

import android.app.Application
import android.content.Context
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

class CourseRepository(private val courseDao: CourseDao) {

    fun getAllCourses(): List<Course> {
        return courseDao.getAllCourses()
    }

    fun getCourseById(id: Int): Course {
        return courseDao.getCourseById(id)
    }

    fun getWordsFromCourse(id: Int): List<Word> {
        return getCourseById(id).words
    }

    fun insertWord(course: Course, word: Word) {
        var newWordsList = mutableListOf<Word>()

        for(w in course.words)
            newWordsList.add(w)
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
