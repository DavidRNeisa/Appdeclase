package com.example.appdeclase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdeclase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        var login = binding.editTextText
        var button1 = binding.button1
        var numero = binding.Numeroint
        var porcentaje= binding.Porcentaje
        var button2= binding.button2

        button1.setOnClickListener{
            Toast.makeText(applicationContext, "Bienvenido " + login.text, Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("login", login.text.toString())
            startActivity(intent)
        }
        button2.setOnClickListener{
            val numero = numero.text.toString().toInt()
            val porcentaje = porcentaje.text.toString().toInt()
            val resultado = (numero * porcentaje) / 100
            Toast.makeText(applicationContext, " El " + porcentaje + "% de " + numero + " es " + resultado, Toast.LENGTH_LONG).show()
        }

        binding.Pedirpermiso.setOnClickListener{
            val intent = Intent(this, Permisos::class.java)
            startActivity(intent)

        }

    }
}