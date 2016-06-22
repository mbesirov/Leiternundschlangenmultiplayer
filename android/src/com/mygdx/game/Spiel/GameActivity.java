package com.mygdx.game.Spiel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mygdx.game.Layouts.GameOverActivity;
import com.mygdx.game.NetworkConnection;


public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        NetworkConnection.getInstance().connect(this);
    }


    public void onGameOver() {
        Intent theNextIntent = new Intent(getApplicationContext(), GameOverActivity.class);
        startActivity(theNextIntent);
        this.finish();
    }
}
