package com.example.avance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fauna_conteo",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormularioBase::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FaunaConteo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formId: Int,
    var codigof4: String = "",
    var selectedZone: String? = null,  // Zona (Bosque, Arreglo Agroforestal, etc.)
    var commonName: String = "",
    var scientificName: String = "",
    var individualCount: String = "",
    var selectedObservation: String = "",
    var observationNotes: String = "",
    var imagen: String? = null
)