package com.example.random_music_alarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.random_music_alarm.databinding.FragmentFirstBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener { view ->
            navController.navigate(R.id.action_FirstFragment_to_CreateAlarm)
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_CreateAlarm)
            (activity as MainActivity).findViewById<FloatingActionButton>(R.id.fab).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}