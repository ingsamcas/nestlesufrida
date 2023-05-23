package net.gshp.nestlesufrida

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import net.gshp.nestlesufrida.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI();
        initListeners(view)
    }

    private fun initUI() {
        val version ="v. ${BuildConfig.VERSION_NAME}"
        binding.tvVersion.text = version
    }

    private fun initListeners(view: View){
        binding.tvTerms.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonLogin.setOnClickListener(){
            Snackbar.make(view, "esto es una prueba", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        binding.tvSupport.setOnClickListener(){
            //findNavController().navigate(R.id.action_FirstFragment_to_SupportFragment)
            showDialog()
        }

    }

    private fun showDialog(){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_support)

        dialog.show()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}