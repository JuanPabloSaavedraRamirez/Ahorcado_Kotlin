package com.example.ahorcado

import Level
import LevelAdapter
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView //(agregar esta libreria fue idea de chat gpt para el tema del scroll)
import com.example.ui.TopBar

//Un RecyclerView es un componente de Android que sirve para mostrar listas de elementos en pantalla, de manera eficiente.

// Muestra una lista scrollable con objetos repetidos (ítems).
// Como tal se reciclan los componentes, entonces usa ya las nuevas para mostrar los nuevos datos

class LevelSelector : AppCompatActivity()
{
    private lateinit var _topbar: TopBar

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selector)

        //Creo mi topbar
        val toolbar = findViewById<Toolbar>(R.id.topBarView)
        _topbar = TopBar(this, toolbar)
        _topbar.init()

        //Sigo con el selector de niveles
        val recycler = findViewById<RecyclerView>(R.id.recyclerLevels)

        val levelList = listOf(
            Level("GATO", R.drawable.l_gato),
            Level("PERRO", R.drawable.l_perro),
            Level("CASA", R.drawable.l_casa),
            Level("JUEGO", R.drawable.l_juego),
            Level("CELULAR", R.drawable.l_celular),
            Level("PLAYA", R.drawable.l_playa),
            Level("VIDEOJUEGOS", R.drawable.l_videojuegos)
        )

        recycler.layoutManager = LinearLayoutManager(this) //Los acomoda verticalmente

        recycler.adapter = LevelAdapter(levelList)
        {
            level -> //hago que mi clase Level adapter sea el puente
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("WORD", level.word)
            startActivity(intent)
        }
    }

    //Funciones de topbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        return _topbar.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return _topbar  .onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }
}