package com.example.mobilne_projekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Course
import kotlinx.android.synthetic.main.course_add_word_fragment.*
import kotlinx.android.synthetic.main.course_add_word_fragment.courseNameTextView
import kotlinx.android.synthetic.main.course_add_word_fragment.wordsCountTextView
import kotlinx.android.synthetic.main.course_edit_fragment.*
import kotlinx.coroutines.*

class CourseEditFragment : Fragment() {

    companion object {
        fun newInstance() = CourseEditFragment()
    }

    private lateinit var viewModel: CourseEditViewModel
    private lateinit var navController: NavController
    private lateinit var courseName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseEditViewModel::class.java)
        courseName = arguments!!.getString("courseName", "unknown")


        var courseLiveData = viewModel.getCourseLiveData(courseName)
        observeCourse(courseLiveData)

        lateinit var course: Course

        lifecycleScope.launch(Dispatchers.IO) {
            course = viewModel.getCourse(courseName)
        }

        editCourseButton.setOnClickListener {
            val newName = editCourseNameEditText.text.toString()

            if(newName.isNotEmpty()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.updateCourseName(course, newName)
                    withContext(Dispatchers.Main) {
                        courseLiveData = viewModel.getCourseLiveData(newName)
                        observeCourse(courseLiveData)
                    }
                }
            }
        }

    }

    fun observeCourse(courseLiveData: LiveData<Course>) {
        courseLiveData.observe(viewLifecycleOwner, Observer {
            courseNameTextView.text = it?.courseName
            wordsCountTextView.text = it?.words?.size.toString()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

}
