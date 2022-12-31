package com.juansaldana.roomapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.juansaldana.roomapp.R
import com.juansaldana.roomapp.model.Song

class ListAdapter : RecyclerView.Adapter<ListViewHolder>() {

    private var songList = emptyList<Song>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = songList[position]
        holder.render(currentItem)
        holder.itemView.setOnClickListener {
            val action = ListFragmentDirections.actionFirstFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(song: List<Song>) {
        this.songList = song
        notifyDataSetChanged()
    }
}
