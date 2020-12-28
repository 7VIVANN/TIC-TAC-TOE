package com.example.tic_tac_toe.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.tic_tac_toe.R;
import com.example.tic_tac_toe.data.PlayerData;
import com.example.tic_tac_toe.databinding.ActivityGameBinding;
import com.example.tic_tac_toe.models.Player;
import com.example.tic_tac_toe.viewmodels.GameViewModel;

public class GameActivity extends AppCompatActivity {
    private String player1;
    private String player2;

    public static final String GAME_BEGIN_DIALOG_TAG = "game_dialog_tag";
    public static final String GAME_END_DIALOG_TAG = "game_ending_dialog_tag";

    public static final String NO_WINNER = "No ONE";
    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promptForPlayers();
    }

    public void onPlayerSet(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        initDataBinding(player1, player2);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.init(player1, player2);
        activityGameBinding.setGameViewModel(gameViewModel);
        setUpOnGameEndListener();
    }

    public void saveWinners(Player winner)
    {
        //insert the data into repository
        gameViewModel.insert(player1, player2, winner.name);
    }

    public void promptForPlayers() {
        GameBeginDialog dialog =GameBeginDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_BEGIN_DIALOG_TAG);
    }

    private void setUpOnGameEndListener(){
        gameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    @VisibleForTesting
    private void onGameWinnerChanged(Player winner) {
        String winnerName = winner==null || winner.name.compareTo("")==0 ? NO_WINNER : winner.name;
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), GAME_END_DIALOG_TAG);
        saveWinners(winner);
    }
}
