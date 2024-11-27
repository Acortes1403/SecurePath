package com.example.avance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.avance.dao.CamarasTrampaDao
import com.example.avance.dao.FaunaBusquedalibreDao
import com.example.avance.dao.FaunaConteoDao
import com.example.avance.dao.FaunaTransectoDao
import com.example.avance.dao.FormularioBaseDao
import com.example.avance.dao.ParcelaVegetacionDao
import com.example.avance.dao.ValidacionCoberturaDao
import com.example.avance.dao.VariablesClimaticasDao
import com.example.avance.model.CamarasTrampa
import com.example.avance.model.FaunaBusquedalibre
import com.example.avance.model.FaunaConteo
import com.example.avance.model.FaunaTransecto
import com.example.avance.model.FormData
import com.example.avance.model.FormularioBase
import com.example.avance.model.ParcelaVegetacion
import com.example.avance.model.ValidacionCobertura
import com.example.avance.model.VariablesClimaticas
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


class FormularioViewModel(
    private val faunaTransectoDao: FaunaTransectoDao,
    private val faunaConteoDao: FaunaConteoDao,
    private val faunaBusquedalibreDao: FaunaBusquedalibreDao,
    private val parcelaVegetacionDao: ParcelaVegetacionDao,
    private val validacionCoberturaDao: ValidacionCoberturaDao,
    private val camarasTrampaDao: CamarasTrampaDao,
    private val variablesClimaticasDao: VariablesClimaticasDao,
    private val formularioBaseDao: FormularioBaseDao,
    private val dao: FormularioBaseDao


): ViewModel() {
    val formData = mutableStateOf(FormData())
    var location = mutableStateOf("")





    // Métodos para actualizar los datos del formulario
    fun updateName(value: String) { formData.value = formData.value.copy(name = value) }
    fun updateDate(value: String) { formData.value = formData.value.copy(date = value) }
    fun updateLocation(value: String) { formData.value = formData.value.copy(location = value) }
    fun updateTime(value: String) { formData.value = formData.value.copy(hora = value) }
    fun updateTransectNumber(value: String) { formData.value = formData.value.copy(transectNumber = value) }
    fun updateRegistro(value: String) { formData.value = formData.value.copy(tipoDeRegistro = value) }
    fun updateSelectedAnimal(animal: String) { formData.value = formData.value.copy(selectedAnimal = animal) }
    fun updateSelectedObservation(observation: String) { formData.value = formData.value.copy(selectedObservation = observation) }
    fun updateCodigof4(codigof4: String) { formData.value = formData.value.copy(codigof4 = codigof4)}
    fun updateCommonName(name: String) { formData.value = formData.value.copy(commonName = name) }
    fun updateScientificName(name: String) { formData.value = formData.value.copy(scientificName = name) }
    fun updateIndividualCount(count: String) { formData.value = formData.value.copy(individualCount = count) }
    fun updateObservationNotes(notes: String) { formData.value = formData.value.copy(observationNotes = notes) }
    fun updateSelectedGrowthHabit(habit: String) { formData.value = formData.value.copy(selectedGrowthHabit = habit) }
    fun updatePlaca(placa: String) { formData.value = formData.value.copy(placa = placa) }
    fun updateCircunference(circumference: String) { formData.value = formData.value.copy(circunference = circumference) }
    fun updateDistance(distance: String) { formData.value = formData.value.copy(distance = distance) }
    fun updateBiomonHeight(height: String) { formData.value = formData.value.copy(biomonHeight = height) }
    fun updateHeight(height: String) { formData.value = formData.value.copy(height = height) }
    fun updateSelectedQuadrant(quadrant: String) { formData.value = formData.value.copy(selectedQuadrant = quadrant) }
    fun updateSelectedSubQuadrant(subQuadrant: String) { formData.value = formData.value.copy(selectedSubQuadrant = subQuadrant) }
    fun updateZone(selectedZone: String) { formData.value = formData.value.copy(selectedZone = selectedZone) }
    fun updateCameraName(name: String) { formData.value = formData.value.copy(cameraName = name) }
    fun updateCameraPlate(plate: String) { formData.value = formData.value.copy(cameraPlate = plate) }
    fun updateGuayaPlate(plate: String) { formData.value = formData.value.copy(guayaPlate = plate) }
    fun updatePathWidth(width: String) { formData.value = formData.value.copy(pathWidth = width) }
    fun updateInstallationDate(date: String) { formData.value = formData.value.copy(installationDate = date) }
    fun updateTargetDistance(distance: String) { formData.value = formData.value.copy(targetDistance = distance) }
    fun updateLensHeight(height: String) { formData.value = formData.value.copy(lensHeight = height) }
    fun updateIsProgrammed(isProgrammed: Boolean) { formData.value = formData.value.copy(isProgrammed = isProgrammed) }
    fun updateHasMemory(hasMemory: Boolean) { formData.value = formData.value.copy(hasMemory = hasMemory) }
    fun updateHasGateTest(hasGateTest: Boolean) { formData.value = formData.value.copy(hasGateTest = hasGateTest) }
    fun updateIsInstalled(isInstalled: Boolean) { formData.value = formData.value.copy(isInstalled = isInstalled) }
    fun updateHasCameraSign(hasSign: Boolean) { formData.value = formData.value.copy(hasCameraSign = hasSign) }
    fun updateIsOn(isOn: Boolean) { formData.value = formData.value.copy(isOn = isOn) }
    fun updateRainfall(rainfall: String) { formData.value = formData.value.copy(pluviosidad = rainfall) }
    fun updateMaxTemperature(maxTemperature: String) { formData.value = formData.value.copy(temperaturaMaxima = maxTemperature) }
    fun updateMinTemperature(minTemperature: String) { formData.value = formData.value.copy(temperaturaMinima = minTemperature) }
    fun updateMaxHumidity(maxHumidity: String) { formData.value = formData.value.copy(humedadMaxima = maxHumidity) }
    fun updateMinHumidity(minHumidity: String) { formData.value = formData.value.copy(humedadMinima = minHumidity) }
    fun updateCreekLevel(creekLevel: String) { formData.value = formData.value.copy(nivelQuebrada = creekLevel) }
    fun updateObservationType(selectedObservationType: String) { formData.value = formData.value.copy(observationType = selectedObservationType) }
    fun updateYesNo1(yesandno1: String) { formData.value = formData.value.copy(yesandno1 = yesandno1) }
    fun updateYesNo2(yesandno2: String) { formData.value = formData.value.copy(yesandno2 = yesandno2) }
    fun updateDisturbance(disturbance: String) { formData.value = formData.value.copy(disturbance = disturbance) }
    fun updateImageUri(uri: String) {formData.value = formData.value.copy(imagen = uri) }



    //ViewModel para guardar
    //FaunaTransecto
    fun saveFaunaTransecto() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
            )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val faunaTransecto = FaunaTransecto(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                commonName = formData.value.commonName,
                scientificName = formData.value.scientificName,
                individualCount = formData.value.individualCount,
                observationNotes = formData.value.observationNotes,
                selectedAnimal = formData.value.selectedAnimal,
                selectedObservation = formData.value.selectedObservation
            )

            // Insertar ambos datos en la base de datos mediante una transacción
            faunaTransectoDao.insertFormularioWithTransecto(formularioBase, faunaTransecto)
        }
    }
    //FaunaConteo
    fun saveFaunaConteo() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
            )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val faunaConteo = FaunaConteo(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                codigof4 = formData.value.codigof4,
                selectedZone = formData.value.selectedZone,
                commonName = formData.value.commonName,
                scientificName = formData.value.scientificName,
                individualCount = formData.value.individualCount,
                selectedObservation = formData.value.selectedObservation,
                imagen = formData.value.imagen,
                observationNotes = formData.value.observationNotes

            )

            // Insertar ambos datos en la base de datos mediante una transacción
            faunaConteoDao.insertFormularioWithConteo(formularioBase, faunaConteo)
        }
    }
    //FaunaBusquedalibre
    fun saveFaunaBusquedalibre() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
                )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val faunaBusquedalibre = FaunaBusquedalibre(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                codigof4 = formData.value.codigof4,
                selectedZone = formData.value.selectedZone,
                commonName = formData.value.commonName,
                scientificName = formData.value.scientificName,
                individualCount = formData.value.individualCount,
                selectedObservation = formData.value.selectedObservation,
                imagen = formData.value.imagen,
                observationNotes = formData.value.observationNotes

                )

            // Insertar ambos datos en la base de datos mediante una transacción
            faunaBusquedalibreDao.insertFormularioWithBusquedalibre(formularioBase, faunaBusquedalibre)
        }
    }
    //Validacion de cobertura
    fun saveValidacionCobertura() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
            )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val validacionCobertura = ValidacionCobertura(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                codigof4 = formData.value.codigof4,
                yesandno1 = formData.value.yesandno1,
                yesandno2 = formData.value.yesandno2,
                observationType = formData.value.observationType,
                disturbance = formData.value.disturbance,
                imagen = formData.value.imagen,
                observationNotes = formData.value.observationNotes
            )

            // Insertar ambos datos en la base de datos mediante una transacción
            validacionCoberturaDao.insertFormularioWithValidacion(formularioBase, validacionCobertura)
        }
    }
    //Parcela de vegetacion
    fun saveParcelaVegetacion() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
            )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val parcelaVegetacion = ParcelaVegetacion(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                codigof4 = formData.value.codigof4,
                selectedGrowthHabit = formData.value.selectedGrowthHabit,
                commonName = formData.value.commonName,
                scientificName = formData.value.scientificName,
                placa = formData.value.placa,
                circunference = formData.value.circunference,
                distance = formData.value.distance,
                biomonHeight = formData.value.biomonHeight,
                height = formData.value.height,
                selectedQuadrant = formData.value.selectedQuadrant,
                selectedSubQuadrant = formData.value.selectedSubQuadrant,
                imagen = formData.value.imagen,
                observationNotes = formData.value.observationNotes
            )

            // Insertar ambos datos en la base de datos mediante una transacción
            parcelaVegetacionDao.insertFormularioWithParcelas(formularioBase, parcelaVegetacion)
        }
    }
    //Camaras Trampa
    fun saveCamarasTrampa() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
            )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val camarasTrampa = CamarasTrampa(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                selectedZone = formData.value.selectedZone,
                cameraName = formData.value.cameraName,
                cameraPlate = formData.value.cameraPlate,
                guayaPlate = formData.value.guayaPlate,
                pathWidth = formData.value.pathWidth,
                installationDate = formData.value.installationDate,
                targetDistance = formData.value.targetDistance,
                lensHeight = formData.value.lensHeight,
                isProgrammed = formData.value.isProgrammed,
                hasMemory = formData.value.hasMemory,
                hasGateTest = formData.value.hasGateTest,
                isInstalled = formData.value.isInstalled,
                isOn = formData.value.isOn,
                imagen = formData.value.imagen,
                observationNotes = formData.value.observationNotes

            )

            // Insertar ambos datos en la base de datos mediante una transacción
            camarasTrampaDao.insertFormularioWithCamaras(formularioBase, camarasTrampa)
        }
    }
    //Variables Climaticas
    fun saveVariablesClimaticas() {
        viewModelScope.launch {
            // Crear el objeto `FormularioBase` con los datos generales
            val formularioBase = FormularioBase(
                name = formData.value.name,
                date = formData.value.date,
                location = formData.value.location,
                hora = formData.value.hora,
                tipoDeRegistro = formData.value.tipoDeRegistro
            )

            // Crear el objeto `FaunaBusquedalibre` con los datos específicos del formulario
            val variablesClimaticas = VariablesClimaticas(
                formId = 0,  // Este valor se establecerá en el DAO al insertar
                selectedZone = formData.value.selectedZone,
                pluviosidad = formData.value.pluviosidad,
                temperaturaMaxima = formData.value.temperaturaMaxima,
                humedadMaxima = formData.value.humedadMaxima,
                temperaturaMinima = formData.value.temperaturaMinima,
                humedadMinima = formData.value.humedadMinima,
                nivelQuebrada = formData.value.nivelQuebrada
            )

            // Insertar ambos datos en la base de datos mediante una transacción
            variablesClimaticasDao.insertFormularioWithVariables(formularioBase, variablesClimaticas)
        }
    }
    private val _formularios = MutableStateFlow<List<FormularioBase>>(emptyList())
    val formularios: StateFlow<List<FormularioBase>> = _formularios
    init {
        fetchFormularios()
    }
    fun fetchFormularios() {
        viewModelScope.launch {
            _formularios.value = formularioBaseDao.getAllFormularios()
        }
    }

    fun deleteFormulario(formulario: FormularioBase) {
        viewModelScope.launch {
            dao.deleteFormulario(formulario)
            fetchFormularios() // Refrescamos la lista después de eliminar
        }
    }
    fun getFormularioById(formId: Int): Flow<FormularioBase?> {
        return dao.getFormularioById(formId)
    }
    fun updateLatitude(latitude: Double) {
        formData.value = formData.value.copy(latitude = latitude)
    }

    fun updateLongitude(longitude: Double) {
        formData.value = formData.value.copy(longitude = longitude)
    }

}