package com.example.tic_tac_toe.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tic_tac_toe.data.PlayerData;
import com.example.tic_tac_toe.data.PlayerRepository;
import com.example.tic_tac_toe.models.Cell;
import com.example.tic_tac_toe.models.Game;
import com.example.tic_tac_toe.models.Player;

import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class GameViewModel extends AndroidViewModel {
    public ObservableArrayMap<String,String> cells;
    private Game game;

    private PlayerRepository playerRepository;
    private final LiveData<List<PlayerData>> playersList;

    public GameViewModel(@NonNull Application application) {
        super(application);
        playerRepository = new PlayerRepository(application);
        playersList = playerRepository.getPlayersList();
    }

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

    public LiveData<List<PlayerData>> getPlayersList(){
        return playersList;
    }

    public void insert(String player1, String player2, String winner)
    {
        if(player1!=null && player2!=null && winner!=null)
        {
            try
            {
                PlayerData playerData = new PlayerData(player1, player2, winner);
                playerRepository.insert(playerData);
            }

            catch (NullPointerException e)
            {
                (Toast.makeText(getApplication(),"NO WINNER HAS BEEN DECLARED",Toast.LENGTH_SHORT)).show();
            }
        }
    }

}
