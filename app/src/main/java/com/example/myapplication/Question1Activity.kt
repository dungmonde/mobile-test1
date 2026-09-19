package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityQuestion1Binding

class Question1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestion1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestion1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up click listener for Mendaftar button to make the UI interactive
        binding.btnMendaftar.setOnClickListener {
            val name = binding.etNamaLengkap.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val phone = binding.etNomorHP.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etKonfirmasiPassword.text.toString()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Harap isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password dan Konfirmasi Password tidak cocok!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Pendaftaran Berhasil untuk $name!", Toast.LENGTH_LONG).show()
        }
    }
}
