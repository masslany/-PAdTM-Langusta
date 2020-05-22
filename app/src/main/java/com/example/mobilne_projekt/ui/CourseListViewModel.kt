package com.example.mobilne_projekt.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course

class CourseListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CourseRepository

    val allCourses: LiveData<List<Course>>
    var allCoursesCount: LiveData<Int>

    init {
        val courseDao = FlashcardsDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
        allCourses = repository.allCourses

        allCoursesCount = repository.allCoursesCount
    }



}
