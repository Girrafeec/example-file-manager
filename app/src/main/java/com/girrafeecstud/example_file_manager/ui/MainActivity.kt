package com.girrafeecstud.example_file_manager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.girrafeecstud.example_file_manager.R
import com.girrafeecstud.example_file_manager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}