package com.example.tic_tac_toe.models;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

public class Game {
    private static final String TAG = Game.class.getSimpleName();
    private static  final int BOARD_SIZE = 3;

    Player player1;
    Player player2;

    public Player currentPlayer;
    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();

    public Game(String playerOne, String playerTwo) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "X");
        player2 = new Player(playerTwo, "O");
        currentPlayer = player1;
    }

    public void switchPlayer()
    {
        currentPlayer = currentPlayer==player1?player2:player1;
    }
    public boolean hasGameEnded()
    {
        if(hasThreeHorizontalCells() || hasThreeVerticalCells() || hasThreeDiagonalCells())
        {
            winner.setValue(currentPlayer);
            return true;
        }

        if(isBoardFull())
        {
            winner.setValue(null);
            return true;
        }

        return false;


    }
    public void reset()
    {
        player1=null;
        player2=null;
        currentPlayer=null;
        cells=null;
    }
    private boolean hasThreeDiagonalCells() {
        for (int i=0; i<BOARD_SIZE; i++)
            if(areEqual(cells[i][0], cells[i][1], cells[i][2]))
                return true;
        return false;
    }

    private boolean areEqual(Cell... cells) {
        if(cells == null || cells.length == 0 )
            return false;
        for(Cell cell: cells)
            if( cell == null || cell.player == null || cell.player.value.length() == 0)
                return false;

        Cell comparisionBase = cells[0];
        for(int i=1; i<BOARD_SIZE; i++)
            if(!comparisionBase.player.value.equals(cells[i].player.value))
                return false;

        return true;
    }

    private boolean hasThreeVerticalCells() {
        try{
            for(int i=0; i<BOARD_SIZE; i++)
                if(areEqual(cells[0][i], cells[1][i], cells[2][i]))
                    return true;
            return false;
        }
        catch (NullPointerException e)
        {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private boolean hasThreeHorizontalCells() {
        try{
            for(int i=0; i<BOARD_SIZE; i++)
                if(areEqual(cells[i][0], cells[i][1], cells[i][2]))
                    return true;
            return false;
        }
        catch (NullPointerException e)
        {
            Log.e(TAG, e.getMessage());
            return false;
        }

    }

    private boolean isBoardFull() {
        for(Cell[] row: cells)
            for(Cell cell: row)
                if(cell == null || cell.isEmpty())
                    return false;
        return true;
    }
}
