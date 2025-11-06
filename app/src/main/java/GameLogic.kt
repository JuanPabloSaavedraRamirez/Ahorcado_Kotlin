package com.example.ahorcado.logic

//Este script se encarga del manejo de la logica del juego
class GameLogic(private val word: String, private val maxFails: Int = 6) //Para cuando se llame la logica reciba la palabra y el maximo de intento lo defino a 6 por mis imagenes
{
    private val _guessedWord: CharArray = CharArray(word.length) { '_' } //Cambiar cada letra de la palabra por "_"
    private var _fails: Int = 0

    fun GetHiddenWord(): String
    {
        return _guessedWord.joinToString(" ") //ayuda para mostralo mejor en la UI
    }

    fun GetFails(): Int
    {
        return _fails
    }

    fun IsGameWon(): Boolean
    {
        return !_guessedWord.contains('_') //revisa que no queden mas "_" y si es el caso manda un boleano como true
    }

    fun IsGameLost(): Boolean
    {
        return _fails >= maxFails //checa que si las fallas actuales son iguales o mayores a las maximas, regresa un boleano en false
    }

    fun TryLetter(letter: Char): Boolean
    {
        var hit = false
        for (i in word.indices)
        {
            //si coincide la que le mandan con una de las letras de la palabra, entonces la agrega a guessedWord y manda que fue verdadero
            if (word[i] == letter)
            {
                _guessedWord[i] = letter
                hit = true
            }
        }
        //en caso de que nunca cambie, entonces suma un fallo
        if (!hit)
        {
            _fails++
        }
        return hit
    }

    //Develve la palabra a adivinar
    fun GetSolution(): String
    {
        return word
    }
}
