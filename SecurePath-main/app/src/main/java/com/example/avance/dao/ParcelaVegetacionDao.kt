package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Transaction
import com.example.avance.model.FormularioBase
import com.example.avance.model.ParcelaVegetacion

@Dao
interface ParcelaVegetacionDao {

    @Transaction
    suspend fun insertFormularioWithParcelas(formularioBase: FormularioBase, parcelaVegetacion: ParcelaVegetacion) {
        val formId = insertFormularioBase(formularioBase) // Inserta el formulario y obtiene el ID
        insertParcelasVegetacion(parcelaVegetacion.copy(formId = formId.toInt())) // Inserta FaunaTransecto con el formId asignado
    }

    @Insert
    suspend fun insertFormularioBase(formularioBase: FormularioBase): Long

    @Insert
    suspend fun insertParcelasVegetacion(parcelaVegetacion: ParcelaVegetacion)
}

