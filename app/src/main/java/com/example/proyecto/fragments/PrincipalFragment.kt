package com.example.proyecto.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyecto.R
import com.example.proyecto.databinding.FragmentPrincipalBinding


class PrincipalFragment : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }


}