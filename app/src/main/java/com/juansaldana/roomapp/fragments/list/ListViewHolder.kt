package com.juansaldana.roomapp.fragments.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.juansaldana.roomapp.databinding.ItemSongBinding
import com.juansaldana.roomapp.model.Song

class ListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSongBinding.bind(view)
    fun render(song: Song){
        binding.idTxt.text = song.id.toString()
        binding.firstNameTxt.text = song.firstName
        binding.lastNameTxt.text = song.lastName
        binding.ageTxt.text = song.age.toString()
    }
}