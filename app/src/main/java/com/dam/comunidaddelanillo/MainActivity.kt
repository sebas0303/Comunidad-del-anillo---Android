package com.dam.comunidaddelanillo

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dam.comunidaddelanillo.adapter.EtapaAdapter
import com.dam.comunidaddelanillo.model.Etapa

/**
 * Actividad Principal: muestra el viaje de la Comunidad del Anillo.
 * Gestiona el menú superior (Opciones) y el menú contextual flotante.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var lvEtapas: ListView
    private lateinit var listaEtapas: MutableList<Etapa>
    private lateinit var adapter: EtapaAdapter

    companion object {
        // Claves constantes para los extras del Intent (Buenas prácticas DAM)
        const val EXTRA_NOMBRE = "EXTRA_NOMBRE"
        const val EXTRA_PELIGRO = "EXTRA_PELIGRO"
        const val EXTRA_TERRENO = "EXTRA_TERRENO"
        const val EXTRA_DESCRIPCION = "EXTRA_DESCRIPCION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración del Toolbar
        supportActionBar?.title = "La Comunidad del Anillo"
        supportActionBar?.subtitle = "Ruta hacia el Monte del Destino"

        lvEtapas = findViewById(R.id.lvEtapas)

        // Cargar los datos de prueba
        cargarEtapas()

        // Asignar el adaptador personalizado
        adapter = EtapaAdapter(this, listaEtapas)
        lvEtapas.adapter = adapter

        // ==============================================================
        // REQUISITO TÉCNICO 4: Registrar el ListView para menús contextuales
        // VITAL: Sin esta llamada, las pulsaciones largas no hacen nada.
        // ==============================================================
        registerForContextMenu(lvEtapas)

        // Click simple informativo opcional
        lvEtapas.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val etapa = listaEtapas[position]
            Toast.makeText(this, "Mantén pulsado '${etapa.nombre}' para inspeccionar", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Inicializa los datos de las etapas de la Tierra Media
     */
    private fun cargarEtapas() {
        listaEtapas = mutableListOf(
            Etapa(1, "La Comarca y Hobbiton", "Baja", 1, "Colinas y praderas pacíficas", "Hogar de Frodo y Sam.", R.drawable.etapa_comarca, "Jinetes Negros patrullando"),
            Etapa(2, "Bree y El Poni Pisador", "Media", 2, "Posadas y encrucijadas", "Encuentro con Trancos.", R.drawable.etapa_bree, "Espías de Saruman"),
            Etapa(3, "Cima de los Vientos (Amon Sûl)", "Alta", 4, "Ruinas antiguas dúnedain", "Ataque del Rey Brujo.", R.drawable.etapa_amon_sul, "Los 5 Nazgûl, puñal de Morgul"),
            Etapa(4, "Rivendel", "Baja", 1, "Valle élfico protegido", "El Concilio de Elrond.", R.drawable.etapa_rivendel, "Ninguna inmediata"),
            Etapa(5, "Minas de Moria", "Extrema", 5, "Galerías subterráneas", "El Balrog despierta en el abismo.", R.drawable.etapa_moria, "El Balrog, Orcos, Troll"),
            Etapa(6, "Lothlórien", "Baja", 1, "Bosque de mallorns bendito", "El Espejo de Galadriel.", R.drawable.etapa_lorien, "Tentación del Anillo"),
            Etapa(7, "Amon Hen y Rauros", "Alta", 4, "Bosque ribereño del Anduin", "Muerte de Boromir y ruptura.", R.drawable.etapa_amon_hen, "Batallón de Uruk-hai"),
            Etapa(8, "Emyn Muil", "Media", 3, "Laberinto de roca escarpada", "Captura de Gollum.", R.drawable.etapa_emyn_muil, "Gollum, desfiladeros"),
            Etapa(9, "Ciénagas de los Muertos", "Alta", 4, "Pantanos con fuegos fatuos", "Caras en el agua cenagosa.", R.drawable.etapa_cienagas, "Nazgûl alados, espíritus"),
            Etapa(10, "Mordor y Monte del Destino", "Extrema", 5, "Calderas de lava y ceniza", "Destrucción del Anillo Único.", R.drawable.etapa_mordor, "El Ojo de Sauron, Gollum")
        )
    }

    // ==============================================================
    // REQUISITO TÉCNICO 3: MENÚ DE OPCIONES SUPERIOR (Toolbar)
    // ==============================================================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_abandonar -> {
                mostrarDialogoConfirmacionAbandono()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarDialogoConfirmacionAbandono() {
        AlertDialog.Builder(this)
            .setTitle("¿Abandonar Misión?")
            .setMessage("Si abandonas ahora, el destino de la Tierra Media caerá en manos de Sauron. ¿Deseas cerrar la app?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Abandonar") { _, _ ->
                // Cierre controlado de la aplicación
                finishAffinity()
            }
            .setNegativeButton("Continuar Ruta", null)
            .show()
    }

    // ==============================================================
    // REQUISITO TÉCNICO 4: MENÚ CONTEXTUAL FLOTANTE
    // ==============================================================
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Inflar el recurso de menú contextual
        menuInflater.inflate(R.menu.menu_contextual_etapa, menu)

        // Obtener la posición del ítem sobre el que se hizo la pulsación larga
        val info = menuInfo as? AdapterView.AdapterContextMenuInfo
        if (info != null) {
            val etapaSeleccionada = listaEtapas[info.position]
            menu?.setHeaderTitle("Etapa: ${etapaSeleccionada.nombre}")
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as? AdapterView.AdapterContextMenuInfo ?: return super.onContextItemSelected(item)
        val etapaSeleccionada = listaEtapas[info.position]

        return when (item.itemId) {
            R.id.action_inspeccionar -> {
                // Navegar a DetalleActivity mediante un Intent Explícito con Extras
                val intentDetalle = Intent(this, DetalleActivity::class.java).apply {
                    putExtra(EXTRA_NOMBRE, etapaSeleccionada.nombre)
                    putExtra(EXTRA_PELIGRO, etapaSeleccionada.peligrosidad)
                    putExtra(EXTRA_TERRENO, etapaSeleccionada.tipoTerreno)
                    putExtra(EXTRA_DESCRIPCION, etapaSeleccionada.descripcionCorta)
                }
                startActivity(intentDetalle)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}