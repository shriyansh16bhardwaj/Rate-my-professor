package com.example.ratemyprofv1.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ratemyprofv1.databinding.FragmentLoginBinding
import com.example.ratemyprofv1.data.SessionManager

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
	private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
		sessionManager = SessionManager(requireContext())
        binding.btnGoToRegister.setOnClickListener {
            findNavController().navigate(
                com.example.ratemyprofv1.R.id.action_login_to_register
            )
        }
        binding.btnLogin.setOnClickListener {
            // TODO: Integrate login via ViewModel
			sessionManager.setGuest(false)
			sessionManager.saveToken("local_dummy_token")
            findNavController().navigate(
                com.example.ratemyprofv1.R.id.action_login_to_home
            )
        }
		binding.btnGuest.setOnClickListener {
			sessionManager.clear()
			sessionManager.setGuest(true)
			findNavController().navigate(
				com.example.ratemyprofv1.R.id.action_login_to_home
			)
		}
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


