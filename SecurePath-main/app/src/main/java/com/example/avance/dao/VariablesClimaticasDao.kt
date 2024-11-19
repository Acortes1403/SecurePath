package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.FormularioBase
import com.example.avance.model.VariablesClimaticas

@Dao
interface VariablesClimaticasDao {

    @Transaction
    suspend fun insertFormularioWithVariables(formularioBase: FormularioBase, variablesClimaticas: VariablesClimaticas) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertVariablesClimaticas(variablesClimaticas.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertVariablesClimaticas(variablesClimaticas: VariablesClimaticas)
}

