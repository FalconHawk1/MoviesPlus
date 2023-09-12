package com.example.moviesplus.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesplus.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        goToHomeActivity()
    }

    private fun goToHomeActivity(){
        startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        finish()
    }
}