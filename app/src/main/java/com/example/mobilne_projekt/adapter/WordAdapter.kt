package com.example.mobilne_projekt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilne_projekt.R
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>(){

    private var words = emptyList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_word,
                        parent, false)
        return  WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentItem = words[position]
        holder.wordOriginalTextView.text = currentItem.original
        holder.wordTranslationTextView.text = currentItem.translated
    }

    override fun getItemCount(): Int  = words.size

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordOriginalTextView: TextView = itemView.wordOriginalTextView
        val wordTranslationTextView: TextView = itemView.wordTranslationTextView
    }

}