package com.example.mobilne_projekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.mobilne_projekt.R
import kotlinx.android.synthetic.main.course_learn_fragment.*

class CourseLearnFragment : Fragment() {

    private var courseName: String? = null

    companion object {
        fun newInstance() = CourseLearnFragment()
    }

    private lateinit var viewModel: CourseLearnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_learn_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseName = arguments?.getString("courseName")
        viewModel = ViewModelProvider(this).get(CourseLearnViewModel::class.java)
        viewModel.initializeCourse(courseName)

        viewModel.courseNameLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null)
                courseNameTextView.text = it
        })

        viewModel.wordLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                println(it.original)
                println(it.translated)
                knownWordTextView.text = it.original
                unknownWordTextView.text = it.translated
            }
        })

        showTranslationButton.setOnClickListener {
            unknownWordTextView.visibility = View.VISIBLE
        }

        knowWordButton.setOnClickListener {
            viewModel.updateWord()
            if(courseName == null)
                viewModel.initializeCourse(courseName)
            else
                viewModel.setNonLearnedWordLiveData()
            unknownWordTextView.visibility = View.INVISIBLE
        }

        nextWordTextView.setOnClickListener {
            if(courseName == null)
                viewModel.initializeCourse(courseName)
            else
                viewModel.setNonLearnedWordLiveData()
            unknownWordTextView.visibility = View.INVISIBLE
        }
    }

}
