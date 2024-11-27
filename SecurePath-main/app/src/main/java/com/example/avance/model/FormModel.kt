package com.example.avance.model

data class FormData(
    // Campos generales, utilizados en todos los formularios
    var name: String = "",
    var date: String = "",
    var location: String = "",
    var hora: String = "",
    var transectNumber: String = "",
    var time: String = "",
    var tipoDeRegistro: String? = null,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,

    // Formulario específico: FormSelect1 - Fauna en Transecto
    var commonName: String = "",
    var scientificName: String = "",
    var individualCount: String = "",
    var observationNotes: String = "",
    var selectedAnimal: String? = null,
    var selectedObservation: String = "",
    var imagen: String = "",

    // Formulario específico: FormSelect2 - Fauna en Punto de Conteo
    var selectedZone: String = "",  // Zona (Bosque, Arreglo Agroforestal, etc.)

    // Formulario específico: FormSelect3 - Fauna Búsqueda Libre
    // Reutiliza campos de observación y zona (selectedZone y selectedObservation)

    // Formulario específico: FormSelect4 - Validación de Cobertura
    var codigof4: String = "",
    var yesandno1: String = "",
    var yesandno2: String = "",
    var observationType: String = "",
    var disturbance: String = "",

    // Formulario específico: FormSelect5 - Parcela de Vegetación
    var circunference: String = "",
    var distance: String = "",
    var biomonHeight: String = "",
    var height: String = "",
    var selectedQuadrant: String? = null,
    var selectedSubQuadrant: String? = null,
    var selectedGrowthHabit: String? = null,  // Hábito de crecimiento (Arbusto, Arbolito, Árbol)
    var placa: String = "",  // Placa identificadora

    // Formulario específico: FormSelect6 - Cámaras Trampa
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

    // Formulario específico: FormSelect7 - Variables Climáticas
    var pluviosidad: String = "",
    var temperaturaMaxima: String = "",
    var humedadMaxima: String = "",
    var temperaturaMinima: String = "",
    var humedadMinima: String = "",
    var nivelQuebrada: String = ""
)
