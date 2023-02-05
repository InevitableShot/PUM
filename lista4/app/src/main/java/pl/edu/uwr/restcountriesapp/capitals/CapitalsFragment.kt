package pl.edu.uwr.restcountriesapp.capitals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.restcountriesapp.databinding.FragmentCapitalsBinding
import pl.edu.uwr.restcountriesapp.viewmodel.CountriesViewModel

class CapitalsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentCapitalsBinding

    private lateinit var countriesViewModel : CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        countriesViewModel = ViewModelProvider(requireActivity())[CountriesViewModel::class.java]
        countriesViewModel.allCountries.observe(viewLifecycleOwner) {countries ->
            val adapter = CapitalsAdapter(countries)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = adapter

        }

        binding = FragmentCapitalsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerView

        return binding.root
    }

}