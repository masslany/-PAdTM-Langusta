package com.example.mobilne_projekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import java.text.FieldPosition

class CourseEditWordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CourseRepository

    init {
        val courseDao = FlashcardsDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
    }

    fun getWordsLiveData(courseName: String) = repository.getWordsLiveData(courseName)
    fun getCourse(courseName: String): Course = repository.getCourseByName(courseName)

    fun updateWord(course: Course, word: Word, position: Int) {
        course.words[position] = word
        repository.updateWord(course.courseName, course.words)
    }
}