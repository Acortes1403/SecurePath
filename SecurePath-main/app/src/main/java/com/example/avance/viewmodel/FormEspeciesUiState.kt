package com.example.avance.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class FormEspeciesUiState(
    val numeroIndividuos: MutableState<String> = mutableStateOf("")
)


