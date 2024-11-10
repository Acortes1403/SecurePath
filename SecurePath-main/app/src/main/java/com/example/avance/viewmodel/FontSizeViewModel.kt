package com.example.avance.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FontSizeViewModel : ViewModel() {
    // Estado mutable para el tamaño de la fuente
    private val _fontSize = MutableStateFlow(16f) // Tamaño de fuente inicial
    val fontSize: StateFlow<Float> get() = _fontSize

    // Método para actualizar el tamaño de la fuente
    fun updateFontSize(newSize: Float) {
        _fontSize.value = newSize
    }
}
