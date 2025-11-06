import android.content.Context
import android.content.Intent
import com.example.ahorcado.LevelSelector

//Funcion para cambiar la actividad a el Level selector
class GoLevelSelector(private val context: Context)
{
    fun GoToLevelSelector()
    {
        val intent = Intent(context, LevelSelector::class.java)
        context.startActivity(intent)
    }
}
