package com.example.moviesplus.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.example.moviesplus.databinding.ActivityHomeBinding
import com.example.moviesplus.databinding.ActivityMainBinding

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}