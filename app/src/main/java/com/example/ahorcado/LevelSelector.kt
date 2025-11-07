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
            Level(getString(R.string.cat), R.drawable.l_gato),
            Level(getString(R.string.dog), R.drawable.l_perro),
            Level(getString(R.string.house), R.drawable.l_casa),
            Level(getString(R.string.game), R.drawable.l_juego),
            Level(getString(R.string.cellular), R.drawable.l_celular),
            Level(getString(R.string.beach), R.drawable.l_playa),
            Level(getString(R.string.videogame), R.drawable.l_videojuegos),
            Level(getString(R.string.pc), R.drawable.l_computadora),
            Level(getString(R.string.radio), R.drawable.l_radio),
            Level(getString(R.string.cape), R.drawable.l_capa),
            Level(getString(R.string.sword), R.drawable.l_espada),
            Level(getString(R.string.ice_cream), R.drawable.l_helado)
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