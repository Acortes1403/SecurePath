package com.example.avance.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.avance.model.FormularioBase
import kotlinx.coroutines.flow.Flow

@Dao
interface FormularioBaseDao {

    @Query("SELECT * FROM formulario_base ORDER BY id ASC")
    suspend fun getAllFormularios(): List<FormularioBase>

    @Delete
    suspend fun deleteFormulario(formulario: FormularioBase)

    @Query("SELECT * FROM formulario_base WHERE id = :id LIMIT 1")
    fun getFormularioById(id: Int): Flow<FormularioBase?>


}
