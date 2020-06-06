package com.example.mobilne_projekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel = ViewModelProvider(this).get(CourseLearnViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseName = arguments?.getString("courseName")

        if(courseName != null) {
            courseNameTextView.text = courseName
        } else {
            courseNameTextView.text = "Wszystkie kursy"
        }

        showTranslationButton.setOnClickListener {
            unknownWordTextView.visibility = View.VISIBLE
        }
    }

}
