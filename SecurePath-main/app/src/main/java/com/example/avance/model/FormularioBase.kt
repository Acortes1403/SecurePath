package com.example.avance.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "formulario_base")
data class FormularioBase(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val date: String?,
    val location: String?,
    val hora: String?,
    val tipoDeRegistro: String?,
)