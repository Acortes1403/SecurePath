package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.FaunaBusquedalibre
import com.example.avance.model.FormularioBase

@Dao
interface FaunaBusquedalibreDao {

    @Transaction
    suspend fun insertFormularioWithBusquedalibre(formularioBase: FormularioBase, faunaBusquedalibre: FaunaBusquedalibre) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertFaunaBusquedalibre(faunaBusquedalibre.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertFaunaBusquedalibre(faunaBusquedalibre: FaunaBusquedalibre)
}

