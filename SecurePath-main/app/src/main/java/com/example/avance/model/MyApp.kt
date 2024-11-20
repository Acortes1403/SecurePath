package com.example.avance.model

import android.app.Application
import androidx.room.Room
import com.example.avance.database.AppDatabase

class MyApp : Application() {
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "mi_base_datos")
            .fallbackToDestructiveMigration()
            .build()
    }
}