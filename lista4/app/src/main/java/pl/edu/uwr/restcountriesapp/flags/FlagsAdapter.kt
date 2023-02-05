package pl.edu.uwr.restcountriesapp.flags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import pl.edu.uwr.restcountriesapp.api.Country
import pl.edu.uwr.restcountriesapp.databinding.CountryAndFlagItemBinding

class FlagsAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<FlagsAdapter.FlagsViewHolder>() {

    inner class FlagsViewHolder(binding: CountryAndFlagItemBinding): ViewHolder(binding.root) {
        val countryNameTV = binding.countryNameFlagFragment
        val countryFlagTV = binding.countryFlag
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlagsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val countryItemBinding = CountryAndFlagItemBinding.inflate(inflater, parent, false)
        return FlagsViewHolder(countryItemBinding)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: FlagsViewHolder, position: Int) {
        holder.countryNameTV.text = countries[position].name.common
        holder.countryFlagTV.text = countries[position].flag
    }

}