package com.example.ratemyprofv1.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ratemyprofv1.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val iiitdEmailRegex = Regex("^[A-Za-z0-9._%+-]+@iiitd\\.ac\\.in$")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            clearErrors()
            val fullName = binding.etFullName.text?.toString()?.trim().orEmpty()
            val email = binding.etEmail.text?.toString()?.trim().orEmpty()
            val username = binding.etUsername.text?.toString()?.trim().orEmpty()
            val password = binding.etPassword.text?.toString()?.trim().orEmpty()

            var valid = true
            if (fullName.isEmpty()) {
                binding.tilFullName.error = "Required"
                valid = false
            }
            if (!iiitdEmailRegex.matches(email)) {
                binding.tilEmail.error = "Use your IIITD email (…@iiitd.ac.in)"
                valid = false
            }
            if (username.isEmpty()) {
                binding.tilUsername.error = "Required"
                valid = false
            }
            if (password.length < 6) {
                binding.tilPassword.error = "Min 6 characters"
                valid = false
            }
            if (!valid) return@setOnClickListener

            // TODO: Trigger ViewModel register(fullName, email, username, password)
            findNavController().navigate(com.example.ratemyprofv1.R.id.action_register_to_otp)
        }
    }

    private fun clearErrors() {
        binding.tilFullName.error = null
        binding.tilEmail.error = null
        binding.tilUsername.error = null
        binding.tilPassword.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


