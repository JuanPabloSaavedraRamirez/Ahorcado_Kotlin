package com.example.ahorcado.logic

class GameLogic(private val word: String, private val maxFails: Int = 6) {

    private val _guessedWord: CharArray = CharArray(word.length) { '_' }
    private var _fails: Int = 0

    fun GetHiddenWord(): String {
        return _guessedWord.joinToString(" ")
    }

    fun GetFails(): Int {
        return _fails
    }

    fun IsGameWon(): Boolean {
        return !_guessedWord.contains('_')
    }

    fun IsGameLost(): Boolean {
        return _fails >= maxFails
    }

    fun TryLetter(letter: Char): Boolean {
        var hit = false

        for (i in word.indices) {
            if (word[i] == letter) {
                _guessedWord[i] = letter
                hit = true
            }
        }

        if (!hit) {
            _fails++
        }

        return hit
    }

    fun GetSolution(): String {
        return word
    }
}
