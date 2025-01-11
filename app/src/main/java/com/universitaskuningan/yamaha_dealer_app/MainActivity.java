package com.universitaskuningan.yamaha_dealer_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.universitaskuningan.yamaha_dealer_app.databinding.ActivityMainBinding;
import com.universitaskuningan.yamaha_dealer_app.databinding.ListItemBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private ArrayList<ItemModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the ViewBinding object
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
            // Mengambil nama motor dari string resource
            String motorName = Myitem.motorTypes[i];
            int motorImage = Myitem.motorImages[i];

            // Menambahkan motor dan deskripsinya
            data.add(new ItemModel(motorName, motorImage));
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

    // Menambahkan logika untuk mengirimkan data ke activity_detail saat item di RecyclerView diklik
    private class AdapterRecycleView extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<ItemModel> itemModels;

        public AdapterRecycleView(ArrayList<ItemModel> itemModels) {
            this.itemModels = itemModels;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ItemModel item = itemModels.get(position);
            holder.bind(item, currentItem -> {
                // Mengirimkan data motor ke activity_detail
                Intent intent = new Intent(holder.itemView.getContext(), activity_detail.class);
                intent.putExtra("MOTOR_NAME", currentItem.getMotorType());
                intent.putExtra("MOTOR_IMAGE", currentItem.getMotorImage());
                intent.putExtra("MOTOR_DESCRIPTION", getMotorDescription(currentItem.getMotorType())); // Deskripsi motor
                intent.putExtra("MOTOR_SPECIFICATIONS", getMotorSpecifications(currentItem.getMotorType())); // Spesifikasi motor
                holder.itemView.getContext().startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return itemModels.size();
        }

        // Mendapatkan deskripsi motor
        private String getMotorDescription(String motorType) {
            switch (motorType) {
                case "MX KING":
                    return "MX King adalah motor bebek sport dengan performa tinggi dan tampilan dinamis.";
                case "Aerox":
                    return "Yamaha Aerox adalah skuter matik sporty dengan performa dan desain agresif.";
                case "Mio":
                    return "Yamaha Mio adalah skuter matik yang praktis dan ekonomis, cocok untuk sehari-hari.";
                case "Nmax":
                    return "Yamaha NMAX menawarkan kenyamanan maksimal dengan mesin berteknologi Blue Core.";
                case "YZ":
                    return "Yamaha YZ adalah motor trail untuk petualangan off-road dengan performa tinggi.";
                case "R6":
                    return "Yamaha R6 adalah motor supersport yang dirancang untuk pengalaman balap jalanan.";
                case "RX King":
                    return "RX King adalah motor legendaris dengan desain klasik dan performa bertenaga.";
                default:
                    return "Deskripsi tidak tersedia.";
            }
        }

        // Mendapatkan spesifikasi motor
        private String getMotorSpecifications(String motorType) {
            switch (motorType) {
                case "MX KING":
                    return "- Mesin: 150 cc, SOHC\n- Tenaga Maksimum: 11,5 kW @ 8500 rpm\n- Torsi Maksimum: 13,8 Nm @ 7000 rpm\n- Berat: 115 kg";
                case "Aerox":
                    return "- Mesin: 155 cc, VVA\n- Tenaga Maksimum: 15,1 PS @ 8000 rpm\n- Kapasitas Tangki: 5,5 liter";
                case "Mio":
                    return "- Mesin: 125 cc\n- Sistem Pendingin: Udara\n- Transmisi: CVT\n- Kapasitas Tangki: 4,2 liter";
                case "Nmax":
                    return "- Mesin: 155 cc, VVA\n- Sistem Pendingin: Cair\n- Fitur: ABS, Y-Connect\n- Kapasitas Tangki: 7,1 liter";
                case "YZ":
                    return "- Mesin: 250 cc\n- Transmisi: 5 percepatan\n- Suspensi: Upside-down\n- Berat: 106 kg";
                case "R6":
                    return "- Mesin: 600 cc, DOHC, 4-silinder\n- Tenaga Maksimum: 123 PS @ 14500 rpm\n- Berat: 190 kg\n- Kapasitas Tangki: 17 liter";
                case "RX King":
                    return "- Mesin: 135 cc, 2-tak\n- Sistem Pendingin: Udara\n- Transmisi: 5 percepatan\n- Berat: 100 kg";
                default:
                    return "Spesifikasi tidak tersedia.";
            }
        }
    }

    // ViewHolder sebagai kelas terpisah
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ItemModel item, ViewHolderClickListener clickListener) {
            binding.motorType.setText(item.getMotorType());
            binding.motorImage.setImageResource(item.getMotorImage());

            // Klik untuk membuka activity_detail
            binding.getRoot().setOnClickListener(v -> clickListener.onClick(item));
        }
    }

    // Listener untuk menangani klik item
    public interface ViewHolderClickListener {
        void onClick(ItemModel item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;  // Prevent memory leaks
    }
}
