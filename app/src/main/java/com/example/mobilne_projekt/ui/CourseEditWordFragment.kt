package com.example.mobilne_projekt.ui

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.edit_word_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CourseEditWordFragment : Fragment() {

    companion object {
        fun newInstance() = CourseEditWordFragment()
    }

    private lateinit var viewModel: CourseEditWordViewModel
    private lateinit var wordsLiveData: LiveData<List<Word>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CourseEditWordViewModel::class.java)

        val courseName = arguments!!.getString("courseName", "unknown")
        val position = arguments!!.getInt("position", 0)
        lateinit var course: Course

        lifecycleScope.launch(Dispatchers.IO) {
            wordsLiveData = viewModel.getWordsLiveData(courseName)



            lifecycleScope.launch(Dispatchers.IO) {
                course = viewModel.getCourse(courseName)
            }

            withContext(Dispatchers.Main) {
                wordsLiveData.observe(viewLifecycleOwner, Observer { words ->
                    editKnownWordEditText.setText(words[position].original)
                    editUnknownWordEditText.setText(words[position].translated)
                    isWordKnownCheckbox.isChecked = words[position].isKnown!!
                })
            }
        }

        editWordButton.setOnClickListener {
            val newWord = Word(
                editKnownWordEditText.text.toString(),
                editUnknownWordEditText.text.toString(),
                isWordKnownCheckbox.isChecked
            )
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.updateWord(course, newWord, position)
            }
            Snackbar.make(it,"Pomyślnie edytowano słówko",Snackbar.LENGTH_SHORT).show()
        }

    }

}