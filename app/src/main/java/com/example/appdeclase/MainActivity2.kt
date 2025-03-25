package com.example.appdeclase

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdeclase.databinding.ActivityMain2Binding
import com.example.appdeclase.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var login = intent.getStringExtra("login")
        binding.login2.text = login

        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.onItemSelectedListener = this

        binding.web.setOnClickListener{
           val intent = Intent(this, Web::class.java)
            startActivity(intent)
        }
        binding.frame.setOnClickListener{
            val intent = Intent(this, FrameLayout::class.java)
            intent.putExtra("login", login.toString())
            val nivel = spinner.selectedItem
            intent.putExtra("spinner", nivel.toString())
            startActivity(intent)
        }
        binding.listab.setOnClickListener{
            val intent = Intent(this, Listview::class.java)
            startActivity(intent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val nivel = parent?.getItemAtPosition(position).toString()
        Toast.makeText(baseContext, "El nivel educativo seleccionado es " + nivel , Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}