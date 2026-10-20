package com.dam.comunidaddelanillo

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Pantalla de Detalle de Terreno.
 * Recibe y muestra la información inyectada a través de los Extras del Intent.
 */
class DetalleActivity : AppCompatActivity() {

    private lateinit var tvNombreDetalle: TextView
    private lateinit var tvPeligroDetalle: TextView
    private lateinit var tvTerrenoDetalle: TextView
    private lateinit var tvDescripcionDetalle: TextView
    private lateinit var btnVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Habilitar botón de navegación 'Atrás' (Up Navigation) en el ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Inspección de Terreno"

        // Vincular vistas del Layout XML
        tvNombreDetalle = findViewById(R.id.tvNombreDetalle)
        tvPeligroDetalle = findViewById(R.id.tvPeligroDetalle)
        tvTerrenoDetalle = findViewById(R.id.tvTerrenoDetalle)
        tvDescripcionDetalle = findViewById(R.id.tvDescripcionDetalle)
        btnVolver = findViewById(R.id.btnVolver)

        // ==============================================================
        // REQUISITO TÉCNICO 4: Desempaquetar los Extras del Intent
        // El objeto intent (en minúscula) ya viene integrado en la actividad.
        // ==============================================================
        val nombre = intent.getStringExtra(MainActivity.EXTRA_NOMBRE) ?: "Etapa desconocida"
        val peligro = intent.getStringExtra(MainActivity.EXTRA_PELIGRO) ?: "Indeterminado"
        val terreno = intent.getStringExtra(MainActivity.EXTRA_TERRENO) ?: "Terreno no especificado"
        val descripcion = intent.getStringExtra(MainActivity.EXTRA_DESCRIPCION) ?: "Sin información histórica."

        // Asignar los valores a los elementos gráficos
        tvNombreDetalle.text = nombre
        tvPeligroDetalle.text = "Nivel de Peligrosidad: $peligro"
        tvTerrenoDetalle.text = "Tipo de Terreno: $terreno"
        tvDescripcionDetalle.text = descripcion

        // Acción del botón Volver: finaliza esta actividad y regresa a MainActivity
        btnVolver.setOnClickListener {
            finish()
        }
    }

    // Gestionar la pulsación de la flecha Atrás en el ActionBar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish() // Cierra DetalleActivity y regresa a MainActivity
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}