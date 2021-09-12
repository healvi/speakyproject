package com.healvi.speaky.presentation.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.healvi.speaky.R
import com.healvi.speaky.presentation.sign.fragment.login.LoginFragment

class SignActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        val mFragmentManager = supportFragmentManager
        val mLoginFragment = LoginFragment()
        val fragment = mFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)

        if (fragment !is LoginFragment) {
            Log.d("Speaky-Project", "Fragment Name: " + LoginFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_sign_container, mLoginFragment, LoginFragment::class.java.simpleName)
                .commit()
        }
    }
}