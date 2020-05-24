package com.example.mobilne_projekt.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.util.Util

import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.android.synthetic.main.course_add_word_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CourseAddWord : Fragment() {

    companion object {
        fun newInstance() = CourseAddWord()
    }

    private lateinit var viewModel: CourseAddWordViewModel
    private lateinit var navController: NavController
    private lateinit var courseName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_add_word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseAddWordViewModel::class.java)


        courseName = arguments!!.getString("courseName", "unknown")

        val courseLiveData = viewModel.getCourseLiveData(courseName)

        courseLiveData.observe(viewLifecycleOwner, Observer {
            courseNameTextView.text = it.courseName
            wordsCountTextView.text = it.words.size.toString()
        })

       lateinit var course: Course

        lifecycleScope.launch(Dispatchers.IO) {
            course = viewModel.getCourse(courseName)
            Log.d("COURSE WORDS: ", course.words.toString())
        }

        addWordButton.setOnClickListener {
            val knowWord = addKnownWordEditText.text.toString()
            val unknownWord = addUnknownWordEditText.text.toString()

            if(knowWord.isNotEmpty() && unknownWord.isNotEmpty()) {
                val word = Word(knowWord, unknownWord, false)
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.addWordToCourse(course, word)
                }
                val bundle = bundleOf("courseName" to courseName)
                navController.navigate(R.id.action_courseAddWord_to_courseDetailFragment, bundle)
            }

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}
