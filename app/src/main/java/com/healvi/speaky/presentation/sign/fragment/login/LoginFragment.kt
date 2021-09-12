package com.healvi.speaky.presentation.sign.fragment.login


import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.healvi.speaky.R
import com.healvi.speaky.databinding.FragmentLoginBinding
import com.healvi.speaky.presentation.dashboard.MainActivity
import com.healvi.speaky.presentation.sign.fragment.register.RegisterFragment

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.daftarDisini.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.daftar_disini -> {
                toRegister()
            }
            R.id.btn_login -> {
                doLogin()
            }
        }
    }

    private fun doLogin() {
        if (binding.email.text.toString().isEmpty()) {
            binding.email.error = "Please input your email"
            binding.email.requestFocus()
            return
        }
        if (binding.password.text.toString().isEmpty()) {
            binding.password.error = "Please input your password"
            binding.password.requestFocus()
            return
        }
        showLoading(true)
        auth.signInWithEmailAndPassword(
            binding.email.text.toString(),
            binding.password.text.toString()
        )
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                    showLoading(false)
                } else {
                    updateUI(null)
                    showLoading(false)
                }
            }
    }

    private fun toRegister() {
        val mRegisterFragment = RegisterFragment()
        val mFragmentManager = fragmentManager
        mFragmentManager?.beginTransaction()?.apply {
            replace(
                R.id.frame_sign_container,
                mRegisterFragment,
                RegisterFragment::class.java.simpleName
            )
            commit()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            activity?.finish()
        } else {
            Toast.makeText(
                requireContext(), "Login failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun showLoading(i: Boolean) {
        if (i) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnLogin.isClickable = false
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnLogin.isClickable = true
        }
    }
}