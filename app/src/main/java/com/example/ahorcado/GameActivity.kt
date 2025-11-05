package com.example.ahorcado

import GoLevelSelector
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.ahorcado.logic.GameLogic

class GameActivity : AppCompatActivity() {

    private lateinit var _hiddenTxt: TextView
    private lateinit var _hangmanImg: ImageView
    private lateinit var _inputLetter: EditText
    private lateinit var _tryButton: Button
    private lateinit var _resultTxt: TextView
    private lateinit var _rootLayout: ViewGroup

    private lateinit var _layout: LinearLayout

    private lateinit var _gameLogic: GameLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        _hiddenTxt = findViewById(R.id.txtHiddenWord)
        _hangmanImg = findViewById(R.id.imageHangman)
        _inputLetter = findViewById(R.id.inputLetter)
        _tryButton = findViewById(R.id.btnTry)
        _resultTxt = findViewById(R.id.txtResult)
        _rootLayout = findViewById(R.id.mainLayoutGame)

        val word = intent.getStringExtra("WORD") ?: ""
        _gameLogic = GameLogic(word.uppercase())

        UpdateHiddenText()

        _inputLetter.requestFocus()
        ShowKeyboard()

        _tryButton.setOnClickListener {
            val guess = _inputLetter.text.toString().uppercase()
            _inputLetter.text.clear()

            if (guess.isEmpty()) return@setOnClickListener

            val hit = _gameLogic.TryLetter(guess[0])
            if (!hit) UpdateHangman()

            UpdateHiddenText()
            CheckEnd()
        }
    }

    private fun ShowKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(_inputLetter, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun UpdateHiddenText() {
        _hiddenTxt.text = _gameLogic.GetHiddenWord()
    }

    private fun UpdateHangman() {
        val fails = _gameLogic.GetFails()
        val id = resources.getIdentifier("a_$fails", "drawable", packageName)
        _hangmanImg.setImageResource(id)
    }

    private fun CheckEnd() {

        if (_gameLogic.IsGameWon()) {
            _resultTxt.text = "Ganaste\nTap para volver"
            EndGame()
        }

        if (_gameLogic.IsGameLost()) {
            _resultTxt.text = "Perdiste, la palabra era: ${_gameLogic.GetSolution()} \nTap para volver"
            EndGame()
        }
    }

    private fun EndGame() {
        _tryButton.isEnabled = false
        _inputLetter.isEnabled = false

        ReturnBackLS()
    }

    private fun ReturnBackLS() {
        _layout = findViewById(R.id.mainLayoutGame)
        val goLevelSelector = GoLevelSelector(this)
        _layout.setOnClickListener{goLevelSelector.GoToLevelSelector()}
    }
}
