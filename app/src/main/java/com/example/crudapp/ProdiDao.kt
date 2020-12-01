package com.example.crudapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProdiDao {
    @Query("SELECT * FROM prodi")
    fun getAll(): List<Prodi>

    @Query("SELECT * FROM prodi WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Prodi>

    @Query("SELECT * FROM prodi WHERE nama_prodi LIKE :nama_prodi LIMIT 1")
    fun findByName(nama_prodi: String): Prodi

    @Insert
    fun insertAll(vararg prodi: Prodi)

    @Delete
    fun delete(prodi: Prodi)
}
