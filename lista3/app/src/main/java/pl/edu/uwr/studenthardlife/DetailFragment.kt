package pl.edu.uwr.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2


class DetailFragment : Fragment() {
    private val dbHandler by lazy {
        DBHandler(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listNumber = requireArguments().getInt("valNum")
        view.findViewById<ViewPager2>(R.id.viewPager).apply {
            adapter = PagerAdapter(dbHandler, listNumber)
        }

        val button = view.findViewById<Button>(R.id.button4)
        button.setOnClickListener {
            view.findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToAddFragment(
                    valNum = listNumber
                )
            )
        }

        val button1 = view.findViewById<Button>(R.id.button_photos)
        button1.setOnClickListener {
            view.findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToGalleryFragment(
                    valNum = listNumber

                )
            )
        }
    }

}