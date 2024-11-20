package com.example.avance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "variables_climaticas",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormularioBase::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class VariablesClimaticas(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formId: Int,
    var pluviosidad: String = "",
    var temperaturaMaxima: String = "",
    var humedadMaxima: String = "",
    var temperaturaMinima: String = "",
    var humedadMinima: String = "",
    var nivelQuebrada: String = "",
    var selectedZone: String = "",
)