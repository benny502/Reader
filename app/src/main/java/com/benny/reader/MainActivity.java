package com.benny.reader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;

import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Typeface iconTypeface;

    private static MainActivity instance;

    public Typeface getIconTypeface(){
        return iconTypeface;
    }

    public static synchronized MainActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        iconTypeface = Typeface.createFromAsset(getAssets(),"fonts/Material-Design-Iconic-Font.ttf");
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fabBtn);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle(getString(R.string.app_name));
//        getSupportActionBar().set


        TextDrawable icon = TextDrawable.builder().beginConfig().useFont(iconTypeface).fontSize(60).endConfig().buildRound(getResources().getString(R.string.zmdi_plus), Color.TRANSPARENT);
        fab.setImageDrawable(icon);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult( Intent.createChooser(intent, "Select a File to Upload"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
            Snackbar.make(v, "Please install a File Manager.", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                String path = FileUtils.getPath(MainActivity.this, uri);
                try {
                    ZipHelper.ZipDecode(MainActivity.this, path);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

