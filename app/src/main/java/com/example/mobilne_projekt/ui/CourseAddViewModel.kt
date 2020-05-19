package com.example.mobilne_projekt.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word

class CourseAddViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private lateinit var courseRepository:CourseRepository




     suspend fun insertCourse(course: Course) {

         courseRepository.insert(course)
    }

    //utworzyc obiekt który odniesie do repo

    //fukcja przujmujaca kurs i przekazunie dodanie kursu repositorty

    //w widoku przekazac utrworzony kurs na viewmodel

}
