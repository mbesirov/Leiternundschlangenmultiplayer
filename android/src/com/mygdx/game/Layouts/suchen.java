package com.mygdx.game.Layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mygdx.game.NetworkConnection;
import com.mygdx.game.R;

public class suchen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suchen);
        startSearching();
    }

    private void startSearching() {
        //this.connect(false);
    }

    public void onButtonClickSuchenZurück(View v){
       // this.disconnect();
        Intent intent = new Intent(getApplicationContext(), startscreen.class);
        startActivity(intent);
    }
}
