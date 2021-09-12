package com.healvi.speaky.presentation.sign.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.healvi.speaky.R
import com.healvi.speaky.databinding.FragmentTermBinding
import com.healvi.speaky.presentation.dashboard.MainActivity


class TermFragment : Fragment() {
    private lateinit var binding : FragmentTermBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTermBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btAccept.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
            }
        }
    }