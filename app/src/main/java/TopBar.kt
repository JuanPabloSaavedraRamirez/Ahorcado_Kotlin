package com.example.ui

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.ahorcado.R

class TopBar( //pido un AppCompatActivity y un toolbar de donde sacare las referencias
    private val _activity: AppCompatActivity,
    private val _toolbar: Toolbar
) {

    fun init()
    {
        _activity.setSupportActionBar(_toolbar) //Para iniciarlo
    }

    //funciones para solo hacer override en las funciones de las activitys
    fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        _activity.menuInflater.inflate(R.menu.custom_toolbar, menu)
        return true
    }

    fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId) {
            R.id.LightMode -> {
                //Para poner su logica
                true
            }

            R.id.DarkMode -> {
                // Para poner su logica
                true
            }
            else -> false
        }
    }
}
