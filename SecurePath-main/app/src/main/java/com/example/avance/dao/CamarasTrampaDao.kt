package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.CamarasTrampa
import com.example.avance.model.FormularioBase

@Dao
interface CamarasTrampaDao {

    @Transaction
    suspend fun insertFormularioWithCamaras(formularioBase: FormularioBase, camarasTrampa: CamarasTrampa) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertCamarasTrampa(camarasTrampa.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertCamarasTrampa(camarasTrampa: CamarasTrampa)
}
