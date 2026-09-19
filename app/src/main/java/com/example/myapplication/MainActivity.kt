package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnQuestion1.setOnClickListener {
            startActivity(Intent(this, Question1Activity::class.java))
        }

        binding.btnQuestion2.setOnClickListener {
            startActivity(Intent(this, Question2Activity::class.java))
        }
    }
}
