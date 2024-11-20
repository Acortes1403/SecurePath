package com.example.avance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "validacion_cobertura",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormularioBase::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ValidacionCobertura (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formId: Int,
    var codigof4: String = "",
    var yesandno1: String = "",
    var yesandno2: String = "",
    var observationType: String = "",
    var disturbance: String = "",
    var observationNotes: String = "",
)