import android.content.Context
import android.content.Intent
import com.example.ahorcado.LevelSelector

class GoLevelSelector(private val context: Context) {

    fun GoToLevelSelector() {
        val intent = Intent(context, LevelSelector::class.java)
        context.startActivity(intent)
    }
}
