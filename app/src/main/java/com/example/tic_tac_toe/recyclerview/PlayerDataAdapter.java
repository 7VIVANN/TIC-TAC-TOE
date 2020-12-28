package com.example.tic_tac_toe.recyclerview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.tic_tac_toe.data.PlayerData;

public class PlayerDataAdapter  extends ListAdapter<PlayerData, PlayerDataHolder> {

    public PlayerDataAdapter(@NonNull DiffUtil.ItemCallback<PlayerData> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PlayerDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return PlayerDataHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerDataHolder holder, int position) {
        PlayerData current  = getItem(position);
        holder.bind(current);
    }

    public static class PlayerDiff extends DiffUtil.ItemCallback<PlayerData>
    {

        @Override
        public boolean areItemsTheSame(@NonNull PlayerData oldItem, @NonNull PlayerData newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlayerData oldItem, @NonNull PlayerData newItem) {
            return oldItem.Winner.equals(newItem.Winner);
        }
    }
}
