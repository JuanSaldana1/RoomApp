package com.juansaldana.roomapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.juansaldana.roomapp.R
import com.juansaldana.roomapp.databinding.FragmentAddBinding
import com.juansaldana.roomapp.model.Song
import com.juansaldana.roomapp.viewmodel.SongViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private lateinit var mSongViewModel: SongViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        mSongViewModel = ViewModelProvider(this)[SongViewModel::class.java]
        binding.addBtn.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root

    }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEditText.text

        if (inputCheck(firstName, lastName, age)) {
            // Create User Object
            val song = Song(
                0,
                firstName,
                lastName,
                Integer.parseInt(age.toString())
            )
            // Add Data to Database
            mSongViewModel.addSong(song)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate Back
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}