package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.FaunaTransecto
import com.example.avance.model.FormularioBase

@Dao
interface FaunaTransectoDao {

    @Transaction
    suspend fun insertFormularioWithTransecto(formularioBase: FormularioBase, faunaTransecto: FaunaTransecto) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertFaunaTransecto(faunaTransecto.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertFaunaTransecto(faunaTransecto: FaunaTransecto)
}

