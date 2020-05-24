package com.example.mobilne_projekt.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.example.mobilne_projekt.R
import kotlinx.android.synthetic.main.course_detail_fragment.*

class CourseDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CourseDetailFragment()
    }

    private lateinit var viewModel: CourseDetailViewModel
    private lateinit var courseName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseDetailViewModel::class.java)
        courseName = arguments!!.getString("courseName", "unknown")

        courseDetailNameTextView.text = courseName
    }

}
