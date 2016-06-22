package com.mygdx.game.Layouts;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.mygdx.game.NetworkConnection;
import com.mygdx.game.R;
import com.mygdx.game.Spiel.GameView;

public class MyStartedGame extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_started_game);
        startServer();
    }

    private void startServer() {
        //this.connect(true);
    }

    public void onClickBack(View v){
      //  this.disconnect();
        Intent intent = new Intent(getApplicationContext(), GameView.class);
        startActivity(intent);
    }

}
