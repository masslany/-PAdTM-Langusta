package com.example.mobilne_projekt.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.coroutines.*
import kotlin.random.Random

class CourseLearnViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CourseRepository
    var courseName: String? = null
    var wordLiveData: MutableLiveData<Word> = MutableLiveData()
    var courseNameLiveData: MutableLiveData<String> = MutableLiveData()
    var mCourse: Course? = null

    // use later?
    private var allCourses: List<Course>

    init {
        val courseDao = FlashcardsDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
        allCourses = runBlocking {
            withContext(Dispatchers.IO) {
                repository.getAllCourses()
            }
        }

        println(allCourses)
    }


    fun initializeCourse(name: String?) {
        var course: Course? = null
        viewModelScope.launch(Dispatchers.IO) {
            course = if (name != null) {
                repository.getCourseByName(name)
            } else {
                repository.getRandomUnfinishedCourse()
            }
            courseNameLiveData.postValue(course?.courseName ?: "ABC")
            courseName = course!!.courseName
            mCourse = course
            setNonLearnedWordLiveData()
        }

    }

    fun setNonLearnedWordLiveData() {
        val word = getNonLearnedWordFromCourse(mCourse!!)

        if(word != null) {
            wordLiveData.postValue(word)
        } else {
            val allLearned = Word("Wszystko już potrafisz!", "Gratulacje", true)
            wordLiveData.postValue(allLearned)
        }

    }

    private fun getNonLearnedWordFromCourse(course: Course): Word? {

        val randomized = course.words.filter { it.isKnown == false }.shuffled()

        val randomIndex = if(randomized.isNotEmpty() && randomized.size != 1) {
            Random.nextInt(0, randomized.size - 1)
        } else { 0 }

        if(randomized.isNotEmpty())
            return randomized[randomIndex]

        return null
    }

    fun updateWord() {
        viewModelScope.launch(Dispatchers.IO) {
            val index = mCourse!!.words.indexOf(wordLiveData.value)
            if(index == -1)
                return@launch
            mCourse!!.words[index].isKnown = true
            repository.updateWord(courseName!!, mCourse!!.words)

            withContext(Dispatchers.Main) {
                setNonLearnedWordLiveData()
            }
        }

    }


}
