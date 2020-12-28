package com.example.tic_tac_toe.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tic_tac_toe.models.Player;

import java.util.List;

public class PlayerRepository {
    private PlayerDao playerDao;
    private LiveData<List<PlayerData>> playersList;

    public PlayerRepository(Application application)
    {
        PlayerDatabase db = PlayerDatabase.getDatabase(application);
        playerDao = db.playerDao();
        playersList = playerDao.getPlayersList();
    }

    public LiveData<List<PlayerData>> getPlayersList(){ return playersList; }

    public void insert(PlayerData playerData)
    {
        PlayerDatabase.dataBaseWriteExecutor.execute(()->{
            playerDao.insert(playerData);
        });
    }
}
