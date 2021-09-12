package com.healvi.speaky.presentation.sign.fragment.register

import android.annotation.SuppressLint
import android.content.Intent.EXTRA_EMAIL
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.healvi.speaky.R
import com.healvi.speaky.databinding.FragmentRegisterBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable


class RegisterFragment : Fragment(), View.OnClickListener {
    private lateinit var binding : FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        auth = Firebase.auth
        database = Firebase.database.reference
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateInput()
        binding.btnRegister.setOnClickListener(this)
        binding.masukDisini.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_register -> {
                registerUser()
            }
            R.id.masuk_disini -> {

            }
        }
    }

    private fun registerUser() {
        if (binding.edtEmailRegis.text.toString().isEmpty()) {
            binding.edtEmailRegis.error = "Please input your email"
            binding.edtEmailRegis.requestFocus()
            return
        }
        if (binding.edtPasswordRegist.text.toString().isEmpty()) {
            binding.edtPasswordRegist.error = "Please input your password"
            binding.edtPasswordRegist.requestFocus()
            return
        }
        if (binding.edtPasswordKonfRegist.text.toString().isEmpty()) {
            binding.edtPasswordKonfRegist.error = "Please input your confirmation password"
            binding.edtPasswordKonfRegist.requestFocus()
            return
        }

        showLoading(true)
        auth.createUserWithEmailAndPassword(binding.edtEmailRegis.text.toString(), binding.edtPasswordRegist.text.toString())
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val mUserFragment = UserInfoFragment()
                    val mBundle = Bundle()
                    mBundle.putString(UserInfoFragment.EXTRA_EMAIL, binding.edtEmailRegis.text.toString())
                    mUserFragment.arguments = mBundle
                    val mFragmentManager = fragmentManager
                    mFragmentManager?.beginTransaction()?.apply {
                        setCustomAnimations(
                            R.anim.enter,
                            R.anim.exit,
                            R.anim.pop_enter,
                            R.anim.pop_exit
                        )
                        replace(
                            R.id.frame_sign_container,
                            mUserFragment,
                            UserInfoFragment::class.java.simpleName
                        )
                        addToBackStack(null)
                        commit()
                    }
                    showLoading(false)
                } else {
                    Toast.makeText(
                        requireContext(), "Register failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    showLoading(false)
                }
            }

    }

    @SuppressLint("CheckResult")
    private fun validateInput() {
        val emailStream = RxTextView.textChanges(binding.edtEmailRegis)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailExistAlert(it)
        }

        val passwordStream = RxTextView.textChanges(binding.edtPasswordRegist)
            .skipInitialValue()
            .map { password ->
                password.length < 6
            }
        passwordStream.subscribe {
            showPasswordMinimalAlert(it)
        }

        val passwordConfirmationStream = Observable.merge(
            RxTextView.textChanges(binding.edtPasswordRegist)
                .map { password ->
                    password.toString() != binding.edtPasswordKonfRegist.text.toString()
                },
            RxTextView.textChanges(binding.edtPasswordKonfRegist)
                .map { confirmPassword ->
                    confirmPassword.toString() != binding.edtPasswordRegist.text.toString()
                }
        )
        passwordConfirmationStream.subscribe {
            showPasswordConfirmationAlert(it)
        }

        val invalidFieldsStream = Observable.combineLatest(
            emailStream,
            passwordStream,
            passwordConfirmationStream,
            { emailInvalid: Boolean, passwordInvalid: Boolean, passwordConfirmationInvalid: Boolean ->
                !emailInvalid && !passwordInvalid && !passwordConfirmationInvalid
            }
        )
        invalidFieldsStream.subscribe { isValid ->
            binding.btnRegister.isEnabled = isValid
        }
    }

    private fun showEmailExistAlert(isNotValid: Boolean) {
        binding.edtEmailRegis.error = if (isNotValid) getString(R.string.email_not_valid) else null
    }

    private fun showPasswordMinimalAlert(isNotValid: Boolean) {
        binding.edtPasswordRegist.error = if (isNotValid) getString(R.string.password_not_valid) else null
    }

    private fun showPasswordConfirmationAlert(isNotValid: Boolean) {
        binding.edtPasswordKonfRegist.error =
            if (isNotValid) getString(R.string.password_not_same) else null
    }


    fun showLoading(i: Boolean) {
        if (i) {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnRegister.isClickable = false
        } else {
            binding.progressBar.visibility = View.INVISIBLE
            binding.btnRegister.isClickable = true
        }
    }

}