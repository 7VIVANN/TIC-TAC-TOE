package com.example.tic_tac_toe.viewmodels;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tic_tac_toe.models.Cell;
import com.example.tic_tac_toe.models.Game;
import com.example.tic_tac_toe.models.Player;

import java.util.Observable;

public class GameViewModel extends ViewModel {
    public ObservableArrayMap<String,String> cells;
    private Game game;

    public void init(String player1, String player2)
    {
        game = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    /*
    REFACTOR SUCH THAT this class has no access to the cells 2d array
    abstract the setter and getter for the cells 2d array
    * */
    public void onClickedCellAt(int row, int column)
    {
        if(game.cells[row][column] == null)
        {
            game.cells[row][column]= new Cell(game.currentPlayer);
            cells.put(stringFromNumbers(row, column), game.currentPlayer.value);

            if(game.hasGameEnded())
                game.reset();
            else
                game.switchPlayer();
        }
    }

    public LiveData<Player> getWinner()
    {
        return game.winner;
    }

    @org.jetbrains.annotations.NotNull
    private String stringFromNumbers(int row, int column) {
        return Integer.toString(row)+Integer.toString(column);
    }
}
