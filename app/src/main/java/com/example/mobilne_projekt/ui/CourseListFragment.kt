package com.example.mobilne_projekt.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.adapter.CourseAdapter
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.android.synthetic.main.course_list_fragment.*

class CourseListFragment : Fragment() {

    companion object {
        fun newInstance() = CourseListFragment()
    }

    private lateinit var viewModel: CourseListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        val courseList = listOf<Course>(
            Course("Course 1", listOf<Word>()),
            Course("Course 2", listOf<Word>()),
            Course("Course 3", listOf<Word>()),
            Course("Course 4", listOf<Word>()),
            Course("Course 5", listOf<Word>()),
            Course("Course 6", listOf<Word>()),
            Course("Course 7", listOf<Word>()),
            Course("Course 8", listOf<Word>()),
            Course("Course 9", listOf<Word>()),
            Course("Course 10", listOf<Word>()),
            Course("Course 11", listOf<Word>()),
            Course("Course 11", listOf<Word>()),
            Course("Course 12", listOf<Word>()),
            Course("Course 13", listOf<Word>()),
            Course("Course 14", listOf<Word>()),
            Course("Course 15", listOf<Word>()),
            Course("Course 16", listOf<Word>()),
            Course("Course 17", listOf<Word>()),
            Course("Course 18", listOf<Word>()),
            Course("Course 19", listOf<Word>())

        )

        courseListRecycleView.adapter = CourseAdapter(courseList)
        courseListRecycleView.layoutManager = LinearLayoutManager(super.getContext())
        courseListRecycleView.setHasFixedSize(true)

    }

}
