package com.mynuex.colordialogseekbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ColorDialogSeekbar extends AppCompatActivity
        implements ColorPickerDialogFragment.ColorDialogSelectionListener {

    Button mChooseBackgroundColor;
    RelativeLayout mBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_dialog_seekbar);

        mBackground = findViewById(R.id.background);
        mChooseBackgroundColor = findViewById(R.id.show_color_dialog_button);

        mChooseBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show dialog
                ColorPickerDialogFragment dialog = ColorPickerDialogFragment.newInstance();
                dialog.show(getSupportFragmentManager(), "Color Dialog");
            }
        });

    }

    @Override
    public void colorSelected(int color) {
        mBackground.setBackgroundColor(color);
    }
}
