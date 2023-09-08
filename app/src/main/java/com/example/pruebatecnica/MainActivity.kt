package com.example.pruebatecnica

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebatecnica.adapters.ComercioAdapter
import com.example.pruebatecnica.databinding.ActivityMainBinding
import com.example.pruebatecnica.models.Comercio
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var commerceList =
        mutableListOf<Comercio>() // Declarar una lista mutable para almacenar los comercios
    private lateinit var adapter: ComercioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        commerceList = mutableListOf(
            Comercio(
                "Supermercado A",
                "Alimentos",
                "1234567890",
                R.drawable.ic_launcher_background
            ),
            Comercio(
                "ElectroHogar",
                "Electrónicos",
                "9876543210",
                R.drawable.ic_launcher_background
            ),
            Comercio("Librería ABC", "Libros", "5678901234", R.drawable.ic_launcher_background),
            Comercio("ModaFashion", "Ropa", "2345678901", R.drawable.ic_launcher_background),
            Comercio(
                "Cafetería Delicia",
                "Alimentos",
                "6789012345",
                R.drawable.ic_launcher_background
            )
        )

        val recyclerView: RecyclerView = binding.companiesRV
        binding.companiesRV.layoutManager = LinearLayoutManager(this)
        adapter = ComercioAdapter(commerceList)
        recyclerView.adapter = adapter

        binding.addCompaniesBTN.setOnClickListener {
            showdialogAddCompany()

        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showdialogAddCompany() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_commerce, null)

        builder.setView(dialogView)
        builder.setTitle("Agregar Comercio")
        builder.setPositiveButton("Agregar") { dialogInterface: DialogInterface, _: Int ->
            val nameET = dialogView.findViewById<EditText>(R.id.nameET)
            val departamentET = dialogView.findViewById<EditText>(R.id.departamentET)
            val documentET = dialogView.findViewById<EditText>(R.id.documentET)

            val name = nameET.text.toString()
            val depto = departamentET.text.toString()
            val document = documentET.text.toString()

            if (name.isNotEmpty() && depto.isNotEmpty() && document.isNotEmpty()) {
                val nuevoComercio =
                    Comercio(name, depto, document, R.drawable.ic_launcher_background)
                commerceList.add(nuevoComercio)
                adapter.notifyDataSetChanged()
            }

            dialogInterface.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}