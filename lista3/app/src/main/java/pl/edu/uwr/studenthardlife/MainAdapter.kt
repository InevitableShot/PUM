package pl.edu.uwr.studenthardlife

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainAdapter.ListMainViewHolder>() {
    private val lista = Lists.list

    inner class ListMainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listNameButton: Button = itemView.findViewById(R.id.button2)
        @SuppressLint("SetTextI18n")
        fun bind(item: TaskList) {
            listNameButton.text =
                "Lista " + item.id.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMainViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ListMainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListMainViewHolder, position: Int) {
        val currentItem = lista[position]
        holder.bind(currentItem)
        holder.listNameButton.setOnClickListener {
            holder.listNameButton.findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    valNum = lista[position].num

                )
            )
        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }
}
