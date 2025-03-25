package com.example.appdeclase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdeclase.databinding.ActivityFrameLayoutBinding

class FrameLayout : AppCompatActivity() {
    private lateinit var binding: ActivityFrameLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var login = intent.getStringExtra("login")
        var spinner = intent.getStringExtra("spinner")
        var textView = binding.fiboTextView

        textView.text = "Bienvenido " + login + " tu nivel educativo es " + spinner

    }
}