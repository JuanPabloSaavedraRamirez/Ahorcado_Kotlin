package com.example.ahorcado

import GoLevelSelector
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

//Unicamente se asigna su layout y llamamos a la funcion "GoToLevelSelector" al darse click sobre una parte del layout
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