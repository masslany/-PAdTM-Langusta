package com.example.mobilne_projekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

class CourseDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CourseRepository


    init {
        val courseDao = FlashcardsDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
    }

    fun getWordsLiveData(courseName: String) = repository.getWordsLiveData(courseName)
    fun getWordsCountLiveData(courseName: String) = repository.getWordsCountLiveData(courseName)

}
