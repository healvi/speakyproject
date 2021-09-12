package com.healvi.speaky.presentation.dashboard.ui.pratice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.healvi.speaky.databinding.FragmentPraticeBinding

class PraticeFragment : Fragment() {

    private lateinit var praticeViewModel: PraticeViewModel
    private lateinit var binding: FragmentPraticeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPraticeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}