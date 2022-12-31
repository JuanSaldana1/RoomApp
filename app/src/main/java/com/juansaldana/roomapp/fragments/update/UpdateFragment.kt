package com.juansaldana.roomapp.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.juansaldana.roomapp.R
import com.juansaldana.roomapp.databinding.FragmentUpdateBinding
import com.juansaldana.roomapp.model.Song
import com.juansaldana.roomapp.viewmodel.SongViewModel

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mSongViewModel: SongViewModel
    private var _binding: FragmentUpdateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mSongViewModel = ViewModelProvider(this)[SongViewModel::class.java]
        binding.updateFirstNameEditText.setText(args.currentSong.firstName)
        binding.updateLastNameEt.setText(args.currentSong.lastName)
        binding.updateAgeEt.setText(args.currentSong.age.toString())

        binding.updateBtn.setOnClickListener {
            updateItem()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_main, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.menu_delete) {
                    findNavController().navigate(R.id.action_updateFragment_to_FirstFragment)
                    //deleteUser()
                }
                return when (menuItem.itemId) {
                    R.id.menu_delete -> {
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEditText.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = Integer.parseInt(binding.updateAgeEt.text.toString())

        if (inputCheck(firstName, lastName, binding.updateAgeEt.text)) {
            // Create User Object
            val updatedSong = Song(args.currentSong.id, firstName, lastName, age)
            // Update Current User
            mSongViewModel.updateSong(updatedSong)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_FirstFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}