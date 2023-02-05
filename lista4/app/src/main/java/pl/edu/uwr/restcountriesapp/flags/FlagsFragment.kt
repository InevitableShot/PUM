package pl.edu.uwr.restcountriesapp.flags

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.edu.uwr.restcountriesapp.databinding.FragmentFlagsBinding
import pl.edu.uwr.restcountriesapp.viewmodel.CountriesViewModel

class FlagsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentFlagsBinding
    private lateinit var countriesViewModel : CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        countriesViewModel = ViewModelProvider(requireActivity())[CountriesViewModel::class.java]
        countriesViewModel.allCountries.observe(viewLifecycleOwner) {countries ->
            val adapter = FlagsAdapter(countries)
            binding.recyclerViewFlags.layoutManager = LinearLayoutManager(context)
            binding.recyclerViewFlags.adapter = adapter

        }

        binding = FragmentFlagsBinding.inflate(inflater, container, false)
        recyclerView = binding.recyclerViewFlags
        return binding.root
    }

}