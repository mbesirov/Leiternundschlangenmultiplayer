package com.mygdx.game.Layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mygdx.game.CallbackInterface;
import com.mygdx.game.NetworkConnection;
import com.mygdx.game.R;
import com.mygdx.game.Spiel.GameActivity;
import com.mygdx.game.Spiel.GameView;


public class startscreen extends Activity {

    private Button buttonOk;
    private Button buttonNickZurück;
    private Button buttonRegelnzurück;
    private Button buttonSuchenZurück;
    private Button buttonBeitreten;
    private Button buttonNeuStart;
    private Button buttonNeuSuchen;
    private Button buttonSpielregeln;
    private TextView textViewNick;
    private EditText editTextNickname;
    private NetworkConnection networkConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);

        buttonOk = (Button) findViewById(R.id.buttonOK);
        buttonRegelnzurück = (Button) findViewById(R.id.buttonRegelnZurück);
        buttonSuchenZurück = (Button) findViewById(R.id.buttonSuchenZurück);
        buttonBeitreten = (Button) findViewById(R.id.buttonBeitreten);
        buttonNeuStart = (Button) findViewById(R.id.buttonNeuStart);
        buttonNeuSuchen = (Button) findViewById(R.id.buttonNeuSuchen);
        buttonSpielregeln = (Button) findViewById(R.id.buttonSpielregeln);
        textViewNick = (TextView) findViewById(R.id.textViewNick);
        editTextNickname = (EditText) findViewById(R.id.editTextNickname);
        buttonNickZurück = (Button) findViewById(R.id.buttonNickZurück);
    }

    public void onButtonClickSpielregeln(View v) {


        Intent intent = new Intent(getApplicationContext(), spielregeln.class);
        startActivity(intent);
    }
    public void onButtonClickNeuSuchen(View v) {
        NetworkConnection.getInstance().setHost(false);
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
    public void onButtonClickNeuStart(View v) {

        NetworkConnection.getInstance().setHost(true);
            Intent intent = new Intent(this, GameActivity.class);
          startActivity(intent);
    }
}