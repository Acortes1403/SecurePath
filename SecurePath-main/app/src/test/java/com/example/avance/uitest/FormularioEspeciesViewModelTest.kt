// FormularioEspeciesViewModelTest.kt
package com.example.avance.viewmodel

import junit.framework.TestCase.assertEquals
import org.junit.Test

class FormularioEspeciesViewModelTest {
    private val viewModel = FormularioEspeciesViewModel()

    @Test
    fun validarNumeroIndividuosSoloAceptaNumeros() {
        // Solo acepta números
        assertEquals("123", viewModel.checkNumeroIndividuosInput("123"))
        // No acepta letras
        assertEquals("", viewModel.checkNumeroIndividuosInput("123abc"))
        // No acepta caracteres especiales
        assertEquals("", viewModel.checkNumeroIndividuosInput("123@!"))
        // No acepta espacios
        assertEquals("", viewModel.checkNumeroIndividuosInput("123 456"))
        // Acepta vacío
        assertEquals("", viewModel.checkNumeroIndividuosInput(""))
    }
}
