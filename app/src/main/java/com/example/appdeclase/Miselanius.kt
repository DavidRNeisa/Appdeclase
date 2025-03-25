package com.example.appdeclase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.io.InputStream

class Miselanius: AppCompatActivity() {
    companion object{
        const val PERMISSION_READ_CONTACTS = 1
        const val PERMISSION_CAMERA = 2

        fun loadJSONFromAsset(context: Context): String?{
            var json: String?=null
            try {
                val isStream: InputStream = context.assets.open("paises.json")
                val size: Int = isStream.available()
                val buffer = ByteArray(size)
                isStream.read(buffer)
                isStream.close()
                json = String(buffer, Charsets.UTF_8)
            }catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }
    }
}