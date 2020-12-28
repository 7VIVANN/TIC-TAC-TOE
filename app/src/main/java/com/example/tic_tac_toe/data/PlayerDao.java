package com.example.tic_tac_toe.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tic_tac_toe.models.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    //conflict resolve by ignoring

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(PlayerData playerData);

    @Query("SELECT * FROM player_table")
    LiveData<List<PlayerData>> getPlayersList();

    @Query("DELETE FROM player_table")
    void deleteAll();
}
