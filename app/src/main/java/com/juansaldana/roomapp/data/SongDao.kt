package com.juansaldana.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.juansaldana.roomapp.model.Song

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSong(song: Song)

    @Update
    suspend fun updateSong(song: Song)

    @Query("DELETE FROM songTable")
    suspend fun deleteAllSongs()

    @Query("SELECT * FROM songTable ORDER BY id ASC")
    fun readAllData(): LiveData<List<Song>>

}