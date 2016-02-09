package edu.uco.weddingcrashers.hitched;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.webkit.WebView;

public class Pictures extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictures);
        Intent intent = getIntent();
    }
    //comment here

    public void viewPhotographers(View view){
        Intent intent = new Intent(Pictures.this, PicturesWeb.class);
        startActivity(intent);
    }



}
