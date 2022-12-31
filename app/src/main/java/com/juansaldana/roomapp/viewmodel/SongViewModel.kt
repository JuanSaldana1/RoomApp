package com.juansaldana.roomapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.juansaldana.roomapp.data.SongDatabase
import com.juansaldana.roomapp.model.Song
import com.juansaldana.roomapp.repository.SongRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Song>>
    private val repository: SongRepository

    init {
        val songDao = SongDatabase.getDatabase(
            application
        ).songDao()
        repository = SongRepository(songDao)
        readAllData = repository.readAllData
        //Log.i("MYTAG", readAllData.toString())
    }

    fun addSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSong(song)
        }
    }

    fun updateSong(song: Song) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSong(song)
        }
    }

    fun deleteAllSongs() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllSongs()
        }
    }
}