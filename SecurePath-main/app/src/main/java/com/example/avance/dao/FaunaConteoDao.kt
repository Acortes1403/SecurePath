package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.FaunaConteo
import com.example.avance.model.FormularioBase

@Dao
interface FaunaConteoDao {

    @Transaction
    suspend fun insertFormularioWithConteo(formularioBase: FormularioBase, faunaConteo: FaunaConteo) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertFaunaConteo(faunaConteo.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertFaunaConteo(faunaConteo: FaunaConteo)
}
