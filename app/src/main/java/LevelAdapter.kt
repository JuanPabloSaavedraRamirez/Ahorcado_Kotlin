import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ahorcado.R

//Esta fue una idea la cual me ayudo a completar Chat GPT

// Adaptador para manejar y mostrar una lista de Level dentro de un RecyclerView
class LevelAdapter(
    private val _levels: List<Level>,
    private val _onClick: (Level) -> Unit  // Función que se ejecuta al hacer clic en un ítem
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() { //Hereda de  RecyclerView.Adapter

    //Inner marca que es una clase interna
    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val _wordText = itemView.findViewById<TextView>(R.id.txtWord)

        // Función que recibe un Level y lo muestra en pantalla
        fun SetAndShowLevel(level: Level) {
            _wordText.text = level.word
            itemView.setOnClickListener {
                _onClick(level)
            }
        }
    }

    //Estas funcion fue creada coon ayuda de Chat GPT
    //Las funciones son parte de RecyclerView.Adapter

    //Parent es el RecyclerView que contiene los itens que se le pasan (en la jerarquia visual)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context) // LayoutInflater sirve para convertir el XML em ima vista real (es como una instancia en unity)
            .inflate(R.layout.item_level_card, parent, false)
        // es falso porque RecyclerView se encarga de agregar la vista al contenedor en el momento correcto y ponerlo como true se agregaria de inmediato y puede causar problemas
        return LevelViewHolder(view)
    }

    override fun getItemCount() = _levels.size

    //Se encarga de asociar los datos del nivel con su viewholder a su posicion
    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.SetAndShowLevel(_levels[position])
    }
}

