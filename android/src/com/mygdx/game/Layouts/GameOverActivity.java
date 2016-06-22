package com.mygdx.game.Layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mygdx.game.R;
import com.mygdx.game.Spiel.GameActivity;

public class GameOverActivity extends Activity {


    private Button buttonExit;
    private Button buttonNewGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        buttonExit = (Button) findViewById(R.id.buttonExit);
        buttonNewGame = (Button) findViewById(R.id.buttonNewGame);
    }

    public void onButtonClickExit(View v) {
        Intent intent = new Intent(getApplicationContext(), startscreen.class);
        startActivity(intent);
    }
    public void onButtonClickNewGame(View v) {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        startActivity(intent);
    }
}
