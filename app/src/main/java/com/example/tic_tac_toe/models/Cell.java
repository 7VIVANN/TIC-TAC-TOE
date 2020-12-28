package com.example.tic_tac_toe.models;

public class Cell {
    public  Player player;

    public Cell(Player player) {
        this.player = player;
    }

    boolean isEmpty()
    {
        return player==null || player.value.compareTo("")==0;
    }
}
