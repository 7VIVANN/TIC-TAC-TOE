package com.example.tic_tac_toe.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.tic_tac_toe.models.Player;

@Entity(tableName = "player_table")
public class PlayerData {

    @PrimaryKey(autoGenerate = true)
    int matchNumber;

    @ColumnInfo(name = "player1")
    String player1_name;

    @Ignore
    public Player player1;

    @ColumnInfo(name = "player2")
    String player2_name;

    @Ignore
    public Player player2;

    @ColumnInfo(name = "Winner")
    public String Winner;

    public PlayerData(String player1_name, String player2_name, String Winner) {
        this.player1_name = player1_name;
        this.player2_name = player2_name;

        this.player1=new Player(player1_name, "X");
        this.player2=new Player(player2_name, "O");

        this.Winner = Winner;
    }
}
