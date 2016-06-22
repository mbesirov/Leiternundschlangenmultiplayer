package com.mygdx.game.Layouts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mygdx.game.R;

public class spielregeln extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spielregeln);
    }
    public void onButtonClickRegelnZurück(View v){
        Intent intent = new Intent(getApplicationContext(), startscreen.class);
        startActivity(intent);
    }
}
