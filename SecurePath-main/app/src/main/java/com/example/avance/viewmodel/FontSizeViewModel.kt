package com.example.avance.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FontSizeViewModel : ViewModel() {
    private val _fontSize = MutableStateFlow(16f)
    val fontSize: StateFlow<Float> = _fontSize

    // Método para actualizar el tamaño de letra
    fun updateFontSize(newFontSize: Float) {
        _fontSize.value = newFontSize
    }
}
