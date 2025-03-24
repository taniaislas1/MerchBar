package com.example.merchbar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.merchbar.R
import com.example.merchbar.databinding.FragmentSecondBinding
import com.example.merchbar.model.SignInViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SignInFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModelIs<SignInViewModel>()
    var isValid:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        setupView()
        return binding.root

    }

    private fun setupView (){
        binding .loginButton.setOnClickListener{
        if (isValid){
            requestLogin()
            Toast.makeText(activity, "Ingreso Valido", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(activity, "Ingreso Invalido", Toast.LENGTH_SHORT).show()
        }
        }
        binding.emailTIET.addTextChangedListener {
            if (binding.emailTIET.text.toString().isEmpty()){
                binding.emailTIET.error = "Por favor escribe un correo"
                isValid = false
            } else {
                isValid = true
            }
        }
        binding.passwordTIET.addTextChangedListener {
            if (binding.passwordTIET.text.toString().isEmpty()){
                binding.passwordTIET.error = "Por favor ingresa una contraseña "
                isValid = false
            } else {
                isValid = true
            }
        }
    }

   private fun requestLogin(){
       viewModel.requestSignIn(binding.emailTIET.text.toString(),
           binding.passwordTIET.text.toString())
   }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}