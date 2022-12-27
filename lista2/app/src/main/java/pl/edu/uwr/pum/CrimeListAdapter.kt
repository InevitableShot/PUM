package pl.edu.uwr.pum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CrimeListAdapter(private val crimes: List<Crime>) :
    RecyclerView.Adapter<CrimeListAdapter.CrimeListViewHolder>() {

    override fun getItemCount(): Int = crimes.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeListViewHolder {
        return CrimeListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.crime_list_item,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: CrimeListViewHolder, position: Int) {
        val crime: Crime = crimes[position]
        setItemParams(holder, crime)
        setOnClickListener(holder, crime)
    }

    class CrimeListViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private fun setItemParams(holder: CrimeListViewHolder, crime: Crime) {
        val title: TextView = holder.itemView.findViewById(R.id.crimeListItemTitle)
        val icon: ImageView = holder.itemView.findViewById(R.id.crimeListItemIcon)
        title.text = crime.title
        icon.visibility = if (crime.isSolved) View.VISIBLE else View.INVISIBLE
    }

    private fun setOnClickListener(holder: CrimeListViewHolder, crime: Crime) {
        val item = holder.itemView.findViewById<RelativeLayout>(R.id.crimeListItem)
        item.setOnClickListener {
            navigateToCrimeDetails(holder, crime)
        }
    }

    private fun navigateToCrimeDetails(
        holder: CrimeListViewHolder,
        crime: Crime
    ) {
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(
            crime = crime
        )
        holder.itemView.findNavController().navigate(action)
    }
}