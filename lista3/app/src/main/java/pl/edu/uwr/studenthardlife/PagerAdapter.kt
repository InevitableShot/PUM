package pl.edu.uwr.studenthardlife

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.studenthardlife.databinding.DetailViewBinding

class PagerAdapter(private val dbHandler: DBHandler, private val number: Int) :
    RecyclerView.Adapter<PagerAdapter.ViewHolder>() {
    class ViewHolder(private var itemBinding: DetailViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val del = itemBinding.DELETE
        val edit = itemBinding.EDIT
        fun bind(text: TableList) {
            itemBinding.textView.text = text.opis
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = DetailViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(dbHandler.getElementList(number)[position])
        holder.del.setOnClickListener {
            dbHandler.deleteElement(dbHandler.getElementList(number)[position].id)
            notifyItemChanged(position)
        }
        holder.edit.setOnClickListener {
            holder.itemView.findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToEditFragment(
                    valId = dbHandler.getElementList(number)[position].id
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dbHandler.getElementList(number).size
    }
}