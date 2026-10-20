package com.dam.comunidaddelanillo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * Actividad inicial de bienvenida (Splash Screen).
 * Muestra el logo y una barra de progreso durante 3 segundos.
 */
class SplashActivity : AppCompatActivity() {

    // Tiempo de espera en milisegundos (3 segundos)
    private val SPLASH_TIME_OUT: Long = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Opcional: ocultar la barra de soporte para pantalla completa inmersiva
        supportActionBar?.hide()

        // Usamos Handler con el Looper principal para lanzar un temporizador en el hilo de UI
        Handler(Looper.getMainLooper()).postDelayed({
            // 1. Crear el Intent explícito hacia la actividad principal
            val intentPrincipal = Intent(this, MainActivity::class.java)
            startActivity(intentPrincipal)

            // 2. VITAL: Cerramos la actividad actual para sacarla de la pila de navegación.
            // Si no llamamos a finish(), cuando el usuario pulse 'Atrás' volvería al Splash.
            finish()
        }, SPLASH_TIME_OUT)
    }
}