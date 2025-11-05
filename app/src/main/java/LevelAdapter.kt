import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ahorcado.R


class LevelAdapter(
    private val levels: List<Level>,
    private val onClick: (Level) -> Unit
) : RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {

    inner class LevelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordText = itemView.findViewById<TextView>(R.id.txtWord)

        fun bind(level: Level) {
            wordText.text = level.word
            itemView.setOnClickListener {
                onClick(level)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_level_card, parent, false)
        return LevelViewHolder(view)
    }

    override fun getItemCount() = levels.size

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(levels[position])
    }
}

