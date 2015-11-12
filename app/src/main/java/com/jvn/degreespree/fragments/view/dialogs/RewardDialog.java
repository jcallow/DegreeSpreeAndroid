package com.jvn.degreespree.fragments.view.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.jvn.degreespree.R;
import com.jvn.degreespree.controllers.GameController;

/**
 * Created by john on 11/7/15.
 */
public class RewardDialog extends DialogFragment {
    private GameController controller;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.start_fragment, null));


        return builder.create();
    }

    public void bind(GameController controller) {
        this.controller = controller;
    }

}