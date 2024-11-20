package com.example.avance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.avance.model.*
import com.example.avance.dao.*

@Database(entities = [FormularioBase::class, FaunaTransecto::class, FaunaConteo::class, FaunaBusquedalibre::class, ValidacionCobertura::class, ParcelaVegetacion::class, CamarasTrampa::class, VariablesClimaticas::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun faunaTransectoDao(): FaunaTransectoDao
    abstract fun faunaConteoDao(): FaunaConteoDao
    abstract fun faunaBusquedalibreDao(): FaunaBusquedalibreDao
    abstract fun parcelaVegetacionDao(): ParcelaVegetacionDao
    abstract fun validacionCoberturaDao(): ValidacionCoberturaDao
    abstract fun camarasTrampaDao(): CamarasTrampaDao
    abstract fun variablesClimaticasDao(): VariablesClimaticasDao
    abstract fun formularioBaseDao(): FormularioBaseDao
}