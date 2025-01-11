package com.universitaskuningan.yamaha_dealer_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.universitaskuningan.yamaha_dealer_app.databinding.ActivityRegisterBinding;

// activity_register.java remains as it is
public class activity_register extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi View Binding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Klik tombol register
        binding.btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Mendapatkan input
        String username = binding.etUsername.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();
        String confirmPassword = binding.etConfirmPassword.getText().toString().trim();

        // Validasi input
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password dan Konfirmasi Password tidak cocok!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simpan data user
        saveUser(username, password);

        // Berpindah ke halaman login
        Intent intent = new Intent(activity_register.this, activity_login.class);
        startActivity(intent);
        finish(); // Mengakhiri aktivitas register
    }

    private void saveUser(String username, String password) {
        // Menyimpan data user menggunakan SharedPreferences
        getSharedPreferences("UserPrefs", MODE_PRIVATE)
                .edit()
                .putString("USERNAME", username)
                .putString("PASSWORD", password)
                .apply();

        Toast.makeText(this, "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Menghindari memory leak
    }
}
