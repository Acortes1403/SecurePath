// FormularioViewModelFactory.kt
package com.example.avance.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.avance.dao.CamarasTrampaDao
import com.example.avance.dao.FaunaBusquedalibreDao
import com.example.avance.dao.FaunaConteoDao
import com.example.avance.dao.FaunaTransectoDao
import com.example.avance.dao.ParcelaVegetacionDao
import com.example.avance.dao.ValidacionCoberturaDao
import com.example.avance.dao.VariablesClimaticasDao


class FormularioViewModelFactory(
    private val faunaTransectoDao: FaunaTransectoDao,
    private val faunaConteoDao: FaunaConteoDao,
    private val faunaBusquedalibreDao: FaunaBusquedalibreDao,
    private val parcelaVegetacionDao: ParcelaVegetacionDao,
    private val validacionCoberturaDao: ValidacionCoberturaDao,
    private val camarasTrampaDao: CamarasTrampaDao,
    private val variablesClimaticasDao: VariablesClimaticasDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormularioViewModel::class.java)) {
            return FormularioViewModel(
                faunaTransectoDao,
                faunaConteoDao,
                faunaBusquedalibreDao,
                parcelaVegetacionDao,
                validacionCoberturaDao,
                camarasTrampaDao,
                variablesClimaticasDao,

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

