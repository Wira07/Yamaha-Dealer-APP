package com.universitaskuningan.yamaha_dealer_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.universitaskuningan.yamaha_dealer_app.databinding.ActivityLoginBinding;

public class activity_login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi View Binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Menambahkan klik listener untuk tombol login
        binding.btnLogin.setOnClickListener(v -> validateLogin());

        // Menambahkan klik listener untuk tombol daftar
        binding.btnRegis.setOnClickListener(v -> {
            // Berpindah ke halaman register
            Intent intent = new Intent(activity_login.this, activity_register.class);
            startActivity(intent);
        });
    }

    private void validateLogin() {
        // Mendapatkan input dari user
        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // Validasi username dan password
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username atau password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cek apakah pengguna sudah terdaftar menggunakan SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String storedUsername = sharedPreferences.getString("USERNAME", null);
        String storedPassword = sharedPreferences.getString("PASSWORD", null);

        // Jika username dan password cocok dengan yang tersimpan
        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            // Berhasil login, pindah ke halaman utama
            Intent intent = new Intent(activity_login.this, MainActivity.class);
            startActivity(intent);
            finish(); // Mengakhiri aktivitas login
        } else {
            // Gagal login
            Toast.makeText(this, "Username atau password salah!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Menghindari memory leak
    }
}
