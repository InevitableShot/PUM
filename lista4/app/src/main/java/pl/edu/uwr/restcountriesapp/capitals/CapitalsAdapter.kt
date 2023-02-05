package pl.edu.uwr.restcountriesapp.capitals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import pl.edu.uwr.restcountriesapp.api.Country
import pl.edu.uwr.restcountriesapp.databinding.CountryAndCapitalItemBinding

class CapitalsAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<CapitalsAdapter.CapitalsViewHolder>() {

    inner class CapitalsViewHolder(binding: CountryAndCapitalItemBinding): ViewHolder(binding.root) {
        val countryNameTV = binding.countryName
        val countryCapitalTV = binding.countryCapital
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapitalsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val countryItemBinding = CountryAndCapitalItemBinding.inflate(inflater, parent, false)
        return CapitalsViewHolder(countryItemBinding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CapitalsViewHolder, position: Int) {
        holder.countryNameTV.text = countries[position].name.common
        holder.countryCapitalTV.text = countries[position].capital.getOrElse(0) {"N/A"}
    }

}