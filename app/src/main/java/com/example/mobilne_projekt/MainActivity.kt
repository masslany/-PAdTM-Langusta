package com.example.mobilne_projekt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobilne_projekt.data.db.FlashcardsDatabase
import com.example.mobilne_projekt.data.db.entity.Course
import com.example.mobilne_projekt.data.db.entity.Word
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
