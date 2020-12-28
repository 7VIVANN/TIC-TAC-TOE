package com.example.tic_tac_toe.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tic_tac_toe.models.Player;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PlayerData.class}, version = 1, exportSchema = false)
public abstract class PlayerDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();

    public static volatile PlayerDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlayerDatabase getDatabase(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (PlayerDatabase.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PlayerDatabase.class, "player_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            dataBaseWriteExecutor.execute(()->{
                PlayerDao dao = INSTANCE.playerDao();
                dao.deleteAll();

                dao.insert(new PlayerData("PLAYER 1","PLAYER 2", "WINNER"));
            });
        }
    };
}
