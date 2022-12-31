package com.juansaldana.roomapp.repository

import androidx.lifecycle.LiveData
import com.juansaldana.roomapp.data.SongDao
import com.juansaldana.roomapp.model.Song

class SongRepository(private val songDao: SongDao) {

    val readAllData: LiveData<List<Song>> = songDao.readAllData()

    suspend fun addSong(song: Song) {
        songDao.addSong(song)
    }

    suspend fun updateSong(song: Song) {
        songDao.updateSong(song)
    }

    suspend fun deleteAllSongs() {
        songDao.deleteAllSongs()
    }
}