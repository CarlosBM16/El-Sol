package com.example.elsol

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val datos = mutableListOf(
            ItemData("Corona solar", R.drawable.corona_solar),
            ItemData("Erupción solar", R.drawable.erupcionsolar),
            ItemData("Espículas", R.drawable.espiculas),
            ItemData("Filamentos", R.drawable.filamentos),
            ItemData("Magnetosfera", R.drawable.magnetosfera),
            ItemData("Mancha Solar", R.drawable.manchasolar)
        )

        lateinit var adapter : TablaAdapter
        adapter = TablaAdapter(datos) { item, actionId ->
            when (actionId) {
                R.id.action_rename -> Toast.makeText(this, "Renombrar ${item.texto}", Toast.LENGTH_SHORT).show()
                R.id.action_copy -> {
                    val newItem = ItemData("${item.texto} COPIA", item.imagenRes)
                    datos.add(newItem)
                    adapter.notifyItemInserted(datos.size - 1)
                }
                R.id.action_cut -> Toast.makeText(this, "Cortar ${item.texto}", Toast.LENGTH_SHORT).show()
                R.id.action_delete -> {
                    datos.remove(item)
                    adapter.notifyItemRemoved(datos.indexOf(item))
                }
                R.id.action_move -> Toast.makeText(this, "Mover ${item.texto}", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.adapter = adapter
    }
}