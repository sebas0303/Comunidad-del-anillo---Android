package com.dam.comunidaddelanillo.model

import java.io.Serializable

/**
 * Modelo de datos que representa una etapa del viaje a Mordor.
 * Implementa Serializable para facilitar el paso seguro de objetos completos si se desea.
 */
data class Etapa(
    val id: Int,
    val nombre: String,
    val peligrosidad: String,     // "Baja", "Media", "Alta", "Extrema"
    val nivelNumerico: Int,       // 1 a 5
    val tipoTerreno: String,
    val descripcionCorta: String,
    val imagenResId: Int,         // R.drawable.xxx
    val amenazas: String
) : Serializable