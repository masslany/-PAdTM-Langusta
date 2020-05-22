package com.example.mobilne_projekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.android.synthetic.main.course_add_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseAddFragment : Fragment() {

    companion object {
        fun newInstance() = CourseAddFragment()
    }

    private lateinit var viewModel: CourseAddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_add_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseAddViewModel::class.java)


        add_curse_button.setOnClickListener {
            val courseTittle = name_curse_text.text.toString()
            val course = Course(courseTittle, emptyList<Word>())

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insertCourse(course)
            }

        }
    }

}
