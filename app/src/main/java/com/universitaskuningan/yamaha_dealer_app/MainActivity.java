package com.universitaskuningan.yamaha_dealer_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.universitaskuningan.yamaha_dealer_app.databinding.ActivityMainBinding;  // Import the binding class
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;  // Declare the binding object
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private ArrayList<ItemModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the ViewBinding object
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  // Set the root view using binding

        // Inisialisasi RecyclerView menggunakan ViewBinding
        binding.recyclerView.setHasFixedSize(true);

        // Mengatur LayoutManager
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(recyclerViewLayoutManager);

        // Menambahkan garis pembatas
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        // Mengisi data
        data = new ArrayList<>();
        for (int i = 0; i < Myitem.motorTypes.length; i++) {
            data.add(new ItemModel(Myitem.motorTypes[i], Myitem.motorImages[i]));
        }

        // Inisialisasi Adapter
        adapter = new AdapterRecycleView(data);
        binding.recyclerView.setAdapter(adapter);

        setupBottomNavigation();
    }


    @SuppressLint("NonConstantResourceId")
    private void setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId(); // Ambil item ID
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.navigation_library) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                return true;
            } else {
                return false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;  // Prevent memory leaks
    }
}
