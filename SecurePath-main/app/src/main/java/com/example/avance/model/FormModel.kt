package com.example.avance.model

data class FormData(
    var name: String = "",
    var date: String = "",
    var location: String = "",
    var time: String = "",
    var transectNumber: String = "",
    var selectedAnimal: String? = null,
    var selectedObservation: String = "La Vió",
    var commonName: String = "",
    var scientificName: String = "",
    var individualCount: String = "",
    var observationNotes: String = "",
    var selectedRegistro: String = "",
    val selectedGrowthHabit: String? = null,
    val placa: String = "",
    val circunference: String = "",
    val distance: String = "",
    val biomonHeight: String = "",
    val height: String = "",
    val selectedSubQuadrant: String = "",
    val zone: String = "",
    val cameraName: String = "",
    val cameraPlate: String = "",
    val guayaPlate: String = "",
    val pathWidth: String = "",
    val installationDate: String = "",
    val targetDistance: String = "",
    val lensHeight: String = "",
    val isProgrammed: Boolean = false,
    val hasMemory: Boolean = false,
    val hasGateTest: Boolean = false,
    val isInstalled: Boolean = false,
    val hasCameraSign: Boolean = false,
    val isOn: Boolean = false,
    val rainfall: String = "",
    val maxTemperature: String = "",
    val minTemperature: String = "",
    val maxHumidity: String = "",
    val minHumidity: String = "",
    val creekLevel: String = "",
    val observations: String = "",



)

enum class ObservationType {
    //LA_VIO, HUELLA, RASTRO, CACERIA, LE_DIJERON
}