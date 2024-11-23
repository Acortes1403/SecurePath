package com.example.avance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "fauna_transecto",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormularioBase::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FaunaTransecto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formId: Int,
    var commonName: String = "",
    var scientificName: String = "",
    var individualCount: String = "",
    var observationNotes: String = "",
    var selectedAnimal: String? = null,
    var selectedObservation: String = "",
    var imagen: String? = null
)