package com.universitaskuningan.yamaha_dealer_app;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.universitaskuningan.yamaha_dealer_app.databinding.ListItemBinding;

import java.util.ArrayList;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ViewHolder> {
    private ArrayList<ItemModel> itemModels;

    public AdapterRecycleView(ArrayList<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    // ViewHolder Class menggunakan View Binding
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

    @NonNull
    @Override
    public AdapterRecycleView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleView.ViewHolder holder, int position) {
        ItemModel item = itemModels.get(position);
        holder.bind(item, currentItem -> {
            Intent intent = new Intent(holder.itemView.getContext(), activity_detail.class);
            intent.putExtra("MOTOR_NAME", currentItem.getMotorType());
            intent.putExtra("MOTOR_IMAGE", currentItem.getMotorImage());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    // Listener untuk menangani klik item
    public interface ViewHolderClickListener {
        void onClick(ItemModel item);
    }
}
