package com.example.ahorcado

import Level
import LevelAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LevelSelector : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selector)

        val recycler = findViewById<RecyclerView>(R.id.recyclerLevels)

        val levelList = listOf(
            Level("GATO"),
            Level("PERRO"),
            Level("CASA"),
            Level("JUEGO"),
            Level("CELULAR"),
            Level("PLAYA"),
            Level("PLATA")
        )

        recycler.layoutManager = LinearLayoutManager(this)

        recycler.adapter = LevelAdapter(levelList) { level ->
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("WORD", level.word)
            startActivity(intent)
        }
    }
}