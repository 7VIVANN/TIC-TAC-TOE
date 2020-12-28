package com.example.tic_tac_toe.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tic_tac_toe.R;
import com.example.tic_tac_toe.data.PlayerData;

public class PlayerDataHolder extends RecyclerView.ViewHolder {

    private final TextView textView;

    public PlayerDataHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bind(PlayerData data){
        String res=data.player1.name +" "+ data.player2.name +" "+ data.Winner;
        textView.setText(res);
    }

    static PlayerDataHolder create(ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PlayerDataHolder(view);
    }

}
