package com.example.avance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "parcela_vegetacion",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormularioBase::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ParcelaVegetacion(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formId: Int,
    var codigof4: String = "",
    var selectedGrowthHabit: String? = null,  // Hábito de crecimiento (Arbusto, Arbolito, Árbol)
    var commonName: String = "",
    var scientificName: String = "",
    var placa: String = "",  // Placa identificadora
    var circunference: String = "",
    var distance: String = "",
    var biomonHeight: String = "",
    var height: String = "",
    var selectedQuadrant: String? = null,
    var selectedSubQuadrant: String? = null,
    var observationNotes: String = "",
    var imagen: String? = null, // Verifica que sea del tipo correcto

)