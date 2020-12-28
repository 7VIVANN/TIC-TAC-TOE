package com.example.tic_tac_toe.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.tic_tac_toe.R;

public class GameEndDialog extends DialogFragment {

    private View rootView;
    private GameActivity activity;
    private String winnerName;

    public static GameEndDialog newInstance(GameActivity activity, String winnerName)
    {
        GameEndDialog dialog = new GameEndDialog();
        dialog.activity = activity;
        dialog.winnerName = winnerName;

        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setCancelable(false)
                .setPositiveButton(R.string.done, ((dialog, which)->onNewGame()))
                .setNegativeButton(R.string.cancel,((dialog, which) -> dismiss()))
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
//        alertDialog.setCancelable(false);

        return alertDialog;
    }

    private void onNewGame() {
        dismiss();
        activity.promptForPlayers();
    }


    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);

        ((TextView) rootView.findViewById(R.id.tv_winner)).setText(winnerName);
    }
}
