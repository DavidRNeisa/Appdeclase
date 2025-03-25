package com.example.appdeclase

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdeclase.databinding.ActivityListviewBinding
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class Listview : AppCompatActivity() {
    private lateinit var binding: ActivityListviewBinding
    var arreglo = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initArray()
        val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, arreglo)
        binding.listView.adapter = adapter
        val json = JSONObject(loadJSONFromAsset())
        val paisesJson = json.getJSONArray("paises")
        for (i in 0 until paisesJson.length()){
            val jsonObject = paisesJson.getJSONObject(i)
            arreglo.add(jsonObject.getString("capital"))
        }
    }

    private fun initArray(){
        for (i in arreglo.indices){
            arreglo[i] = if (i % 2 == 0) "hola" else "mundo"
        }
    }
    fun loadJSONFromAsset(): String?{
        var json: String?=null
        try {
            val isStream: InputStream = assets.open("paises.json")
            val size: Int = isStream.available()
            val buffer = ByteArray(size)
            isStream.read(buffer)
            isStream.close()
            json = String(buffer, Charsets.UTF_8)
        }catch (ex: IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }
}