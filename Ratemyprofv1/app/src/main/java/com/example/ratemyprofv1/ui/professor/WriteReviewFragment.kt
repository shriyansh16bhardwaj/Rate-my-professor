package com.example.ratemyprofv1.ui.professor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.ratemyprofv1.databinding.FragmentWriteReviewBinding

class WriteReviewFragment : Fragment() {

    private var _binding: FragmentWriteReviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWriteReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit.setOnClickListener {
            val teaching = binding.rbTeaching.rating.toInt()
            val clarity = binding.rbClarity.rating.toInt()
            val helpfulness = binding.rbHelpfulness.rating.toInt()
            if (teaching == 0 || clarity == 0 || helpfulness == 0) {
                Toast.makeText(requireContext(), "Please rate all criteria", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // TODO: Submit via ViewModel
            Toast.makeText(requireContext(), "Submitted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


