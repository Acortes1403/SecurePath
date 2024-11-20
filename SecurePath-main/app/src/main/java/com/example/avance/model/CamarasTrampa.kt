package com.example.avance.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "camaras_trampa",
    indices = [Index(value = ["formId"])],
    foreignKeys = [ForeignKey(
        entity = FormularioBase::class,
        parentColumns = ["id"],
        childColumns = ["formId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class CamarasTrampa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val formId: Int,
    var selectedZone: String = "",
    var cameraName: String = "",
    var cameraPlate: String = "",
    var guayaPlate: String = "",
    var pathWidth: String = "",
    var installationDate: String = "",
    var targetDistance: String = "",
    var lensHeight: String = "",
    var isProgrammed: Boolean = false,
    var hasMemory: Boolean = false,
    var hasGateTest: Boolean = false,
    var isInstalled: Boolean = false,
    var hasCameraSign: Boolean = false,
    var isOn: Boolean = false,
    var observationNotes: String = "",
    var imagen: String? = null, // Verifica que sea del tipo correcto
)
