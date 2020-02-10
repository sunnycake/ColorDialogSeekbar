package com.mynuex.colordialogseekbar;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorPickerDialogFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {


    SeekBar red, green, blue;

    //    int values
    int mRed = 0;
    int mGreen = 0;
    int mBlue = 0;

    //Interface for the listener
    interface ColorDialogSelectionListener {
        void colorSelected(int color);
    }

    private ColorDialogSelectionListener mListener;

    //New instance
    public static ColorPickerDialogFragment newInstance() {
        ColorPickerDialogFragment fragment = new ColorPickerDialogFragment();
        return fragment;
    }

    @Override // Override onAttach to configure listener
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ColorDialogSelectionListener) {
            mListener = (ColorDialogSelectionListener) activity;
        } else {
            throw new RuntimeException(activity.toString() + " must implement ColorDialogSelectionListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Creating dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflate the custom dialog
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.color_picker_seekbar, null);

        red = view.findViewById(R.id.seekbar_red);
        green = view.findViewById(R.id.seekbar_green);
        blue = view.findViewById(R.id.seekbar_blue);

        // Displaying dialog and setting view
        builder.setTitle("Choose a background color")
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        mListener.colorSelected(Color.rgb(red.getProgress(), green.getProgress(), blue.getProgress()));
                    }
                });

        return builder.create();
    }
    // Tips from https://www.programcreek.com/java-api-examples/android.widget.SeekBar
    @Override // Determine changes of SeekBar
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (!fromUser) {
            return;
        }
        switch (seekBar.getId()) {
            case R.id.seekbar_red:
                mRed = seekBar.getProgress();
                break;
            case R.id.seekbar_green:
                mGreen = seekBar.getProgress();
                break;
            case R.id.seekbar_blue:
                mBlue = seekBar.getProgress();
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

