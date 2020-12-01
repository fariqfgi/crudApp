package com.example.crudapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prodi")
data class Prodi(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "nama_prodi") val nama_prodi: String?
)
