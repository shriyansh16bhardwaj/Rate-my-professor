package com.example.ratemyprofv1.ui.professor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ratemyprofv1.R
import com.example.ratemyprofv1.databinding.FragmentProfessorDetailsBinding

class ProfessorDetailsFragment : Fragment() {

    private var _binding: FragmentProfessorDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfessorDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWriteReview.setOnClickListener {
            findNavController().navigate(R.id.action_professorDetails_to_writeReview)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


