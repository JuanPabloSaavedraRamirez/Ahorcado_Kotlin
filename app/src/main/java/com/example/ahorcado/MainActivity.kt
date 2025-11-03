package com.example.ahorcado

import GoLevelSelector
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var _layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _layout = findViewById(R.id.mainLayout)
        val goLevelSelector = GoLevelSelector(this)

        _layout.setOnClickListener{goLevelSelector.GoToLevelSelector()}

    }
}