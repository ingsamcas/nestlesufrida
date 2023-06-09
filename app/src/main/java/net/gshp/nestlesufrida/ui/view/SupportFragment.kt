package net.gshp.nestlesufrida.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.gshp.nestlesufrida.R
import net.gshp.nestlesufrida.databinding.FragmentSupportBinding


class SupportFragment : Fragment() {

    private var _binding: FragmentSupportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSupportBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSupport.setOnClickListener(){
            findNavController().navigate(R.id.action_SupportFragment_to_LoginFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}