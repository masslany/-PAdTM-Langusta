package com.example.mobilne_projekt.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Course
import kotlinx.android.synthetic.main.item_course.view.*

class CourseAdapter(private val courseList: List<Course>) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_course,
                       parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentItem = courseList[position]
        holder.courseNameTextView.text = currentItem.courseName
    }

    override fun getItemCount() = courseList.size

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTextView: TextView = itemView.courseNameTextView
    }

}