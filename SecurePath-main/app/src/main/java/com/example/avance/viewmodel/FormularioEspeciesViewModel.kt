
// FormularioEspeciesViewModel.kt
package com.example.avance.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FormularioEspeciesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FormEspeciesUiState())
    val uiState: StateFlow<FormEspeciesUiState> = _uiState.asStateFlow()

    var numeroIndividuos by mutableStateOf("")

    // Metodo para validar que el input solo tenga números
    fun updateNumeroIndividuos(input: String) {
        numeroIndividuos = checkNumeroIndividuosInput(input)
    }

    // Metodo para validar que solo acepte numeros
    fun checkNumeroIndividuosInput(input: String): String {
        return if (input.all { it.isDigit() }) {
            input
        } else {
            ""
        }
    }
}


