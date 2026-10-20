package com.dam.comunidaddelanillo.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dam.comunidaddelanillo.R
import com.dam.comunidaddelanillo.model.Etapa

/**
 * Adaptador personalizado para alimentar el ListView de etapas.
 * Hereda de BaseAdapter e implementa el patrón ViewHolder para reciclaje óptimo.
 */
class EtapaAdapter(
    private val context: Context,
    private val listaEtapas: List<Etapa>
) : BaseAdapter() {

    // Devuelve el número total de elementos en la lista
    override fun getCount(): Int = listaEtapas.size

    // Devuelve el objeto Etapa en la posición indicada
    override fun getItem(position: Int): Any = listaEtapas[position]

    // Devuelve un identificador único para la fila (normalmente su ID o posición)
    override fun getItemId(position: Int): Long = listaEtapas[position].id.toLong()

    /**
     * El parámetro convertView es la clave de la optimización en Android:
     * Si es nulo, inflamos el layout por primera vez.
     * Si no es nulo, reutilizamos la vista que ya ha salido de pantalla al hacer scroll.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista: View
        val holder: ViewHolder

        if (convertView == null) {
            // Primera vez: inflamos el archivo XML del ítem
            val inflater = LayoutInflater.from(context)
            vista = inflater.inflate(R.layout.item_etapa, parent, false)

            // Cacheamos las referencias a los TextViews e ImageView
            holder = ViewHolder(
                ivTerreno = vista.findViewById(R.id.ivTerreno),
                tvNombre = vista.findViewById(R.id.tvNombreEtapa),
                tvPeligro = vista.findViewById(R.id.tvPeligrosidad),
                tvTerreno = vista.findViewById(R.id.tvTipoTerreno)
            )
            vista.tag = holder
        } else {
            // Reutilización: recuperamos el holder ya existente
            vista = convertView
            holder = vista.tag as ViewHolder
        }

        // Obtener la etapa actual y pintar los datos
        val etapaActual = listaEtapas[position]
        holder.tvNombre.text = etapaActual.nombre
        holder.tvPeligro.text = "Peligro: ${etapaActual.peligrosidad}"
        holder.tvTerreno.text = etapaActual.tipoTerreno
        holder.ivTerreno.setImageResource(etapaActual.imagenResId)

        // Asignar color temático al badge según el nivel de peligrosidad
        when (etapaActual.peligrosidad) {
            "Baja" -> holder.tvPeligro.setTextColor(ContextCompat.getColor(context, R.color.peligro_bajo))
            "Media" -> holder.tvPeligro.setTextColor(ContextCompat.getColor(context, R.color.peligro_medio))
            "Alta" -> holder.tvPeligro.setTextColor(ContextCompat.getColor(context, R.color.peligro_alto))
            "Extrema" -> holder.tvPeligro.setTextColor(ContextCompat.getColor(context, R.color.peligro_extremo))
            else -> holder.tvPeligro.setTextColor(Color.WHITE)
        }

        return vista
    }

    /**
     * Clase estática ViewHolder para evitar llamadas repetitivas a findViewById()
     */
    private class ViewHolder(
        val ivTerreno: ImageView,
        val tvNombre: TextView,
        val tvPeligro: TextView,
        val tvTerreno: TextView
    )
}