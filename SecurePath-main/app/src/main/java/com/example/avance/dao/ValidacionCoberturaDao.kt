package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.FormularioBase
import com.example.avance.model.ValidacionCobertura

@Dao
interface ValidacionCoberturaDao {

    @Transaction
    suspend fun insertFormularioWithValidacion(formularioBase: FormularioBase, validacionCobertura: ValidacionCobertura) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertValidacionCobertura(validacionCobertura.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertValidacionCobertura(validacionCobertura: ValidacionCobertura)
}

