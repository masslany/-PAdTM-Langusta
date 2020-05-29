package com.example.mobilne_projekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseAddWordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CourseRepository

    init {
        val courseDao = FlashcardsDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
    }

    fun getCourseLiveData(courseName: String): LiveData<Course>  = repository.getCourseByNameLiveData(courseName)
    fun getCourse(courseName: String): Course = repository.getCourseByName(courseName)

    fun addWordToCourse(course: Course, word: Word) {
        repository.insertWord(course, word)
    }
}
