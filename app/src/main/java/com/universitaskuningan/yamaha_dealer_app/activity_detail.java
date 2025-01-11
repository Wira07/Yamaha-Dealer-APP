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
        String name = getIntent().getStringExtra("MOTOR_NAME");
        int image = getIntent().getIntExtra("MOTOR_IMAGE", 0);

        // Menampilkan data
        binding.motorName.setText(name);
        binding.motorImage.setImageResource(image);
        binding.motorDescription.setText("Detail deskripsi motor.");
        binding.motorSpecifications.setText("- Mesin: 150cc\n- Tenaga: 15HP\n- Torsi: 13Nm");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null; // Menghindari memory leak
    }
}
