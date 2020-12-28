package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tic_tac_toe.data.PlayerData;
import com.example.tic_tac_toe.models.Player;
import com.example.tic_tac_toe.recyclerview.PlayerDataAdapter;
import com.example.tic_tac_toe.recyclerview.PlayerDataHolder;
import com.example.tic_tac_toe.viewmodels.GameViewModel;
import com.example.tic_tac_toe.views.GameActivity;

public class MainActivity extends AppCompatActivity {

    public Button button ;
    GameViewModel gameViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener((View view)->{
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            MainActivity.this.startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerVieww);
        final PlayerDataAdapter adapter = new PlayerDataAdapter(new PlayerDataAdapter.PlayerDiff());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        gameViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(GameViewModel.class);

        gameViewModel.getPlayersList().observe(this, adapter::submitList);
    }

}
