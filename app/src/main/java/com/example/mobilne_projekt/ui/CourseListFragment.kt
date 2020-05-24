package com.example.mobilne_projekt.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilne_projekt.MainActivity

import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.adapter.CourseAdapter
import com.example.mobilne_projekt.data.db.CourseDao
import com.example.mobilne_projekt.data.db.CourseRepository
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.android.synthetic.main.course_list_fragment.*
import kotlinx.android.synthetic.main.list_statistics.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CourseListFragment : Fragment() {

    companion object {
        fun newInstance() = CourseListFragment()
    }

    private lateinit var viewModel: CourseListViewModel
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)
        mContext = activity!!.applicationContext

        val adapter = CourseAdapter(mContext)
        courseListRecycleView.adapter = adapter
        courseListRecycleView.layoutManager = LinearLayoutManager(mContext)

//        val course = Course("EXAMPLE", emptyList<Word>())
//        lifecycleScope.launch(Dispatchers.IO) {
//            val database = FlashcardsDatabase.getDatabase(mContext);
//            val courseDao = database.courseDao()
//            courseDao.insert(course)
//        }

        viewModel.allCourses.observe(viewLifecycleOwner, Observer { courses ->
            courses.let {adapter.setCourses(it)}
        })

        bindUI()
    }

    private fun bindUI() {
        viewModel.allCoursesCount.observe(viewLifecycleOwner, Observer {
            if(it != null)
                createdCoursesNumberTextView.text = it.toString()
        })
    }

}
