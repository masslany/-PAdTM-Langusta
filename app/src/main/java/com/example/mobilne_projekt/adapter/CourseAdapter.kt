package com.example.mobilne_projekt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Course
import kotlinx.android.synthetic.main.item_course.view.*

class CourseAdapter internal constructor(context: Context) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var courses = emptyList<Course>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_course,
                       parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentItem = courses[position]
        holder.courseNameTextView.text = currentItem.courseName
        holder.courseWordCountTextView.text = currentItem.words.size.toString()

        val bundle = bundleOf("courseName" to currentItem.courseName)

        holder.itemView.setOnClickListener(

            Navigation.createNavigateOnClickListener(
                R.id.action_courseListFragment_to_courseDetailFragment,
                bundle)
        )
    }

    override fun getItemCount() = courses.size

    internal fun setCourses(courses: List<Course>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTextView: TextView = itemView.courseNameTextView
        val courseWordCountTextView: TextView = itemView.wordCountTextView
    }

}