package com.example.avance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import com.example.avance.model.FormData

class FormularioViewModel : ViewModel() {
    val formData = mutableStateOf(FormData())

    // Métodos para actualizar los datos del formulario
    fun updateName(value: String) { formData.value = formData.value.copy(name = value) }

    fun updateDate(value: String) { formData.value = formData.value.copy(date = value) }

    fun updateLocation(value: String) { formData.value = formData.value.copy(location = value) }

    fun updateTime(value: String) { formData.value = formData.value.copy(time = value) }

    fun updateTransectNumber(value: String) { formData.value = formData.value.copy(transectNumber = value) }

    fun updateRegistro(value: String) { formData.value = formData.value.copy(selectedRegistro = value) }

    fun updateSelectedAnimal(animal: String) { formData.value = formData.value.copy(selectedAnimal = animal) }

    fun updateSelectedObservation(observation: String) { formData.value = formData.value.copy(selectedObservation = observation) }

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

    fun updateSelectedSubQuadrant(subQuadrant: String) { formData.value = formData.value.copy(selectedSubQuadrant = subQuadrant) }

    fun updateZone(zone: String) { formData.value = formData.value.copy(zone = zone) }

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

    fun updateObservations(observations: String) { formData.value = formData.value.copy(observations = observations) }

    fun updateRainfall(rainfall: String) { formData.value = formData.value.copy(rainfall = rainfall) }

    fun updateMaxTemperature(maxTemperature: String) { formData.value = formData.value.copy(maxTemperature = maxTemperature) }

    fun updateMinTemperature(minTemperature: String) { formData.value = formData.value.copy(minTemperature = minTemperature) }

    fun updateMaxHumidity(maxHumidity: String) { formData.value = formData.value.copy(maxHumidity = maxHumidity) }

    fun updateMinHumidity(minHumidity: String) { formData.value = formData.value.copy(minHumidity = minHumidity) }

    fun updateCreekLevel(creekLevel: String) { formData.value = formData.value.copy(creekLevel = creekLevel) }
}