package com.mygdx.game.Layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View.OnClickListener;


import com.mygdx.game.Spiel.GameActivity;
import com.mygdx.game.R;
import com.mygdx.game.Spiel.GameView;
import com.mygdx.game.Spiel.TokenColor;

public class nickname extends Activity {
    private EditText editTextNickname;
    private TextView textViewNick;
    private TextView textView2;
    private RadioButton radioButtonSchwarz;
    private RadioButton radioButtonBlau;
    private RadioButton radioButtonRot;
    private RadioButton radioButtonLila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
        textViewNick = (TextView) findViewById(R.id.textViewNick);
        textView2 = (TextView) findViewById(R.id.textView2);
        editTextNickname = (EditText) findViewById(R.id.editTextNickname);
        radioButtonSchwarz = (RadioButton) findViewById(R.id.radioButtonSchwarz);
        radioButtonLila = (RadioButton) findViewById(R.id.radioButtonLila);
        radioButtonBlau = (RadioButton) findViewById(R.id.radioButtonBlau);
        radioButtonRot = (RadioButton) findViewById(R.id.radioButtonRot);

        radioButtonSchwarz.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                radioButtonSchwarz.setChecked(true);
                radioButtonLila.setChecked(false);
                radioButtonBlau.setChecked(false);
                radioButtonRot.setChecked(false);
            }
        });

        radioButtonLila.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                radioButtonSchwarz.setChecked(false);
                radioButtonLila.setChecked(true);
                radioButtonBlau.setChecked(false);
                radioButtonRot.setChecked(false);
            }
        });

        radioButtonBlau.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                radioButtonSchwarz.setChecked(false);
                radioButtonLila.setChecked(false);
                radioButtonBlau.setChecked(true);
                radioButtonRot.setChecked(false);
            }
        });

        radioButtonRot.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                radioButtonSchwarz.setChecked(false);
                radioButtonLila.setChecked(false);
                radioButtonBlau.setChecked(false);
                radioButtonRot.setChecked(true);
            }
        });


    }

    public void onButtonClickNickZurück(View v) {
        Intent intent = new Intent(getApplicationContext(), startscreen.class);
        startActivity(intent);
    }


    public void onButtonClickOk(View v)
    {
        if (editTextNickname.getText().toString().equals("")) {
            editTextNickname.setHint("Nicknamen auswählen!!");
        }

        else if (!radioButtonBlau.isChecked() && !radioButtonLila.isChecked() && !radioButtonSchwarz.isChecked() && !radioButtonRot.isChecked()) {
            textView2.setText("Farbe auswählen");
        }

        else if (radioButtonBlau.isChecked()) {
            textView2.setText("");
            /*radioButtonRot.setChecked(false);
            radioButtonSchwarz.setChecked(false);
            radioButtonLila.setChecked(false);*/
            TokenColor.color=0;
        }

        else if ( radioButtonLila.isChecked()) {
            textView2.setText("");
           /* radioButtonRot.setChecked(false);
            radioButtonSchwarz.setChecked(false);
            radioButtonBlau.setChecked(false);*/
            TokenColor.color=1;
        }

        else if (radioButtonSchwarz.isChecked()) {
            textView2.setText("");
            /*radioButtonRot.setChecked(false);
            radioButtonBlau.setChecked(false);
            radioButtonLila.setChecked(false);*/
            TokenColor.color=2;
        }

        else if (radioButtonRot.isChecked()) {
            textView2.setText("");
            /*radioButtonBlau.setChecked(false);
            radioButtonSchwarz.setChecked(false);
            radioButtonLila.setChecked(false);*/
            TokenColor.color=3;

        }

        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }
}


