package com.example.pruebatecnica.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.R
import com.example.pruebatecnica.models.Comercio

class ComercioAdapter(private val comercios: List<Comercio>) :
    RecyclerView.Adapter<ComercioAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_comercio, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comercio = comercios[position]
        holder.iconImageView.setImageResource(comercio.icono)
        holder.nombreTextView.text = comercio.nombre
    }

    override fun getItemCount(): Int {
        return comercios.size
    }
}
