package com.example.crudapp.Database

import androidx.room.*

@Dao
interface HelmDao {
    @Insert
    suspend fun addHelm(helm: Helm)

    @Update
    suspend fun updateHelm(helm: Helm)

    @Delete
    suspend fun deleteHelm(helm: Helm)

    @Query("SELECT * FROM helm")
    suspend fun getAllHelm(): List<Helm>
}