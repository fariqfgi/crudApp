package com.example.crudapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = arrayOf(Mahasiswa::class, Prodi::class), version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
    abstract fun prodiDao(): ProdiDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        AppRoomDatabase::class.java, "APPDB"
    ).build()
}
