package com.example.appdeclase

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdeclase.Miselanius.Companion.PERMISSION_CAMERA
import com.example.appdeclase.Miselanius.Companion.PERMISSION_READ_CONTACTS
import com.example.appdeclase.databinding.ActivityPermisosBinding

class Permisos : AppCompatActivity() {
    private lateinit var binding: ActivityPermisosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermisosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.PermisoContacto.setOnClickListener {
            Toast.makeText(applicationContext, "Contactos", Toast.LENGTH_SHORT).show()
            when {
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED -> {
                    Toast.makeText(this, "Permiso de contactos concedido", Toast.LENGTH_SHORT).show()
                }

                ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_CONTACTS) -> {
                    Toast.makeText(this, "Explicación necesaria para permisos", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    PedirPermiso(this, android.Manifest.permission.READ_CONTACTS, "Permiso para leer contactos", PERMISSION_READ_CONTACTS)
                }
            }

        }
        binding.PermisoCamara.setOnClickListener{
            Toast.makeText(applicationContext, "Camara", Toast.LENGTH_SHORT).show()
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.CAMERA
                ) -> {
                }

                else -> {
                    PedirPermiso(
                        this,
                    android.Manifest.permission.CAMERA,"Permiso para leer contactos", PERMISSION_CAMERA)

                }
            }
        }
    }

    private fun PedirPermiso(
        context: Activity,
        permiso: String,
        Justificacion: String,
        IdCode: Int
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                permiso
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(permiso), IdCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso de contactos concedido", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permiso de contactos denegado", Toast.LENGTH_LONG).show()
                }
            }

            PERMISSION_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso de cámara concedido", Toast.LENGTH_SHORT).show()
                    val pickImage = Intent(Intent.ACTION_PICK)
                    pickImage.type = "image/*"
                    startActivityForResult(pickImage, 100)
                } else {
                    Toast.makeText(this, "Permiso de cámara denegado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, 1)
        }catch (e: ActivityNotFoundException) {
            e.message?.let { Log.e("Permisos", it) } }
        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == RESULT_OK) {
            val extras: Bundle? = data?.extras
            val imageBitmap = extras?.get("data") as android.graphics.Bitmap
            binding.PermisoCamara.setImageBitmap(imageBitmap)
        }
    }


}
