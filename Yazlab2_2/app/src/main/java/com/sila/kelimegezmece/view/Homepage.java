package com.sila.kelimegezmece.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sila.kelimegezmece.R;
import com.sila.kelimegezmece.controller.CreatePuzzle;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public TextView username ;
    public Button start, attendance;
    public CreatePuzzle createPuzzle = new CreatePuzzle();
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        getSupportActionBar().setTitle("Anasayfa");

        init();
        setSharedPreferencesLevelChapter();

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        level = sh.getInt("level", 0); //Eğer level boş ise default olarak 0 ver

        if(level != 0){
            attendance.setVisibility(View.VISIBLE);
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!username.getText().toString().isEmpty()){

                    setSharedPreferencesLevel();
                    setSharedPreferencesUsername(username.getText().toString());

                    Toast.makeText(Homepage.this, "Hoşgeldin "+username.getText(), Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Homepage.this, Level1.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(Homepage.this, "Oyuncu adı boş geçilemez!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level == 1){
                    Intent i = new Intent(Homepage.this, Level1.class);
                    startActivity(i);
                    finish();
                }
                else if(level == 2){
                    Intent i = new Intent(Homepage.this, Level2.class);
                    startActivity(i);
                    finish();
                }
                else if(level == 3){
                    Intent i = new Intent(Homepage.this, Level3.class);
                    startActivity(i);
                    finish();
                }else if(level == 4){
                    Toast.makeText(Homepage.this, "Oyunu Tamamladınız. Tebrikler..", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Homepage.this, "Oyun Yüklenirken Bir Sorun Oluştu..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setSharedPreferencesLevel(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putInt("level",1); //Yeni seviye ekle
        editor.commit(); //Eklediğin seviyeyi kaydet
    }

    private void setSharedPreferencesUsername(String username){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putString("username",username); //Yeni Kullanıcı ekle
        editor.commit(); //Eklediğin kullanıcıyı kaydet
    }

    private void setSharedPreferencesLevelChapter(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("levelChapter", 1); //Her zaman bölüm 1 den başlanır.
        editor.commit();
    }

    private void init() {
        username = findViewById(R.id.username);
        start = findViewById(R.id.start);
        attendance = findViewById(R.id.attendance);
    }

}