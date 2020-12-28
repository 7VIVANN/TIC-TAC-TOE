package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tic_tac_toe.viewmodels.GameViewModel;
import com.example.tic_tac_toe.views.GameActivity;

public class MainActivity extends AppCompatActivity {

    public Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener((View view)->{
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            MainActivity.this.startActivity(intent);
        });
    }

}
