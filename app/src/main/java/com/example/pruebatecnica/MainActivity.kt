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

class MainActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var listaDeComercios =
        mutableListOf<Comercio>() // Declarar una lista mutable para almacenar los comercios
    private lateinit var adapter: ComercioAdapter
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el menú lateral
        drawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.menu, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listaDeComercios = mutableListOf(
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
        adapter = ComercioAdapter(listaDeComercios)
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
                listaDeComercios.add(nuevoComercio)
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_agregar_comercio -> {
                showdialogAddCompany()
            }
            // Agrega más opciones de menú aquí si es necesario
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}