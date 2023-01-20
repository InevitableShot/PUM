package pl.edu.uwr.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pl.edu.uwr.studenthardlife.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = DBHandler(requireContext())
        val listNumber = requireArguments().getInt("valNum")
        val bin = FragmentAddBinding.inflate(LayoutInflater.from(context))
        bin.apply {

            binding.buttonSAVE.setOnClickListener {

                val updateInfo = binding.editTextText.text.toString()
                if (updateInfo.isNotEmpty()) {
                    val d = TableList(0, 0, updateInfo, listNumber, 0)
                    dbHandler.addElement(d)
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}