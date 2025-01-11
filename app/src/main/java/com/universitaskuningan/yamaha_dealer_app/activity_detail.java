package com.universitaskuningan.yamaha_dealer_app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.universitaskuningan.yamaha_dealer_app.databinding.ActivityDetailBinding;

public class activity_detail extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi View Binding
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Mendapatkan data dari Intent
        String motorName = getIntent().getStringExtra("MOTOR_NAME");
        int motorImage = getIntent().getIntExtra("MOTOR_IMAGE", 0);
        String motorDescription = getIntent().getStringExtra("MOTOR_DESCRIPTION");
        String motorSpecifications = getIntent().getStringExtra("MOTOR_SPECIFICATIONS");

        // Menampilkan data motor
        if (motorName != null) {
            binding.motorName.setText(motorName); // Menampilkan nama motor
        }

        if (motorImage != 0) {
            binding.motorImage.setImageResource(motorImage); // Menampilkan gambar motor
        }

        if (motorDescription != null) {
            binding.motorDescription.setText(motorDescription); // Menampilkan deskripsi motor
        } else {
            binding.motorDescription.setText("Deskripsi tidak tersedia."); // Default deskripsi jika kosong
        }

        if (motorSpecifications != null) {
            binding.motorSpecifications.setText(motorSpecifications); // Menampilkan spesifikasi motor
        } else {
            binding.motorSpecifications.setText("Spesifikasi tidak tersedia."); // Default spesifikasi jika kosong
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Menghindari memory leak
    }
}
