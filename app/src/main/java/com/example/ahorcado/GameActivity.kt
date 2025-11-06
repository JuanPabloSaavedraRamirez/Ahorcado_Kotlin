package com.example.ahorcado

import GoLevelSelector
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorcado.logic.GameLogic

class GameActivity : AppCompatActivity()
{
    //Este script se encarga del manejo de los elementos de la UI
    private lateinit var _hiddenTxt: TextView
    private lateinit var _hangmanImg: ImageView
    private lateinit var _inputLetter: EditText
    private lateinit var _tryButton: Button
    private lateinit var _resultTxt: TextView

    private lateinit var _rootLayout: ViewGroup

    private lateinit var _gameLogic: GameLogic

    private val _hangmanImages = arrayOf(
        R.drawable.a_0,
        R.drawable.a_1,
        R.drawable.a_2,
        R.drawable.a_3,
        R.drawable.a_4,
        R.drawable.a_5,
        R.drawable.a_6
    )

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        _hiddenTxt = findViewById(R.id.txtHiddenWord)
        _hangmanImg = findViewById(R.id.imageHangman)
        _inputLetter = findViewById(R.id.inputLetter)
        _tryButton = findViewById(R.id.btnTry)
        _resultTxt = findViewById(R.id.txtResult)

        val word = intent.getStringExtra("WORD") ?: ""
        _gameLogic = GameLogic(word.uppercase()) //Obtiene la logica y a su vez pasa la palabra en mayusculas

        UpdateHiddenText()

        _inputLetter.requestFocus() //Con esto puede interactuar el usuario
        ShowKeyboard()

        _tryButton.setOnClickListener {
            val guess = _inputLetter.text.toString().uppercase()
            _inputLetter.text.clear()

            if (guess.isEmpty()) return@setOnClickListener //si esta vacio lo ignora

            val hit = _gameLogic.TryLetter(guess[0])
            if (!hit) UpdateHangman() //si la letra no es encontrada actualiza la imagen

            UpdateHiddenText()
            CheckEnd()
        }
    }

    //En esta funcion me ayudo GPT para poder sacar el teclado y poner el foco en el input
    private fun ShowKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(_inputLetter, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun UpdateHiddenText()
    {
        _hiddenTxt.text = _gameLogic.GetHiddenWord()
    }

    private fun UpdateHangman()
    {
        val fails = _gameLogic.GetFails()
        _hangmanImg.setImageResource(_hangmanImages[fails]) //Las actualiza segun el numero del fallo es el indice del array
    }

    private fun CheckEnd()
    {
        if (_gameLogic.IsGameWon())
        {
            _resultTxt.text = "Ganaste\nTap para volver"
            EndGame()
        }
        if (_gameLogic.IsGameLost())
        {
            _resultTxt.text = "Perdiste, la palabra era: ${_gameLogic.GetSolution()} \nTap para volver"
            EndGame()
        }
    }

    private fun EndGame()
    {
        //Desactivo el boton y el input para que no pueda escribir y activo la opcion para que regrese al Level selctor
        _tryButton.isEnabled = false
        _inputLetter.isEnabled = false

        ReturnBackLS()
    }

    //Funciona igual que en Main Activity
    private fun ReturnBackLS()
    {
        _rootLayout = findViewById(R.id.mainLayoutGame)
        val goLevelSelector = GoLevelSelector(this)
        _rootLayout.setOnClickListener{goLevelSelector.GoToLevelSelector()}
    }
}
