package com.sila.kelimegezmece.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.sila.kelimegezmece.R;
import com.sila.kelimegezmece.controller.CreatePuzzle;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Level1 extends AppCompatActivity {


    public CreatePuzzle createPuzzle = new CreatePuzzle();
    public Timer timer;
    public TimerTask timerTask;
    final Handler handler = new Handler();
    CountDownTimer countDownTimer;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView writeWord,chapter,point;
    Button lvl1b1, lvl1b2, lvl1b3, shuffle, leader;
    public static List<List<String>> level1_Words;
    public List<String> allPoints;
    int level_chapter;
    String dynamicArea[][] = new String[6][6];
    String dynamicArea2[][] = new String[6][6];

    ConstraintLayout constraintLayout;
    GridLayout gl;
    TextView[]  text = new TextView[25];
    int say=0;
    int points=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1);
        getSupportActionBar().setTitle("Level 1");

        init();
        getWordsList();
        shuffle();
        createGame();

        test();

        startPointTimer();


        lvl1b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl1b1.getText());
                lvl1b1.setClickable(false);
                lvl1b1.setEnabled(false);
                lvl1b1.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });


        lvl1b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl1b2.getText());
                lvl1b2.setClickable(false);
                lvl1b2.setEnabled(false);
                lvl1b2.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });

        lvl1b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl1b3.getText());
                lvl1b3.setClickable(false);
                lvl1b3.setEnabled(false);
                lvl1b3.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });

        shuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuffle();
                stoptimertask();
            }
        });

        leader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopUpClass popUpClass = new PopUpClass();
                popUpClass.pointsText = getAllPoints();
                popUpClass.showPopupWindow(v);
            }
        });

    }


    private void startPointTimer() {

        countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                points=points-5;
            }
        }.start();
    }


    private String getAllPoints() {

        String textt = new String();

        for(int i=7;i>1;i--){
            textt += "Level-"+1+" Bölüm-"+(i-1)+" "+ getLevelChapterPointsUsername(1,(i-1),getUsername())+ " " +getLevelChapterPoints(1,(i-1));
            textt += "                                                                                      ";
        }
        return textt;
    }

    private void test() {

        for(int i=0;i<25;i++)
        {
            text[i] = new TextView(Level1.this);
            text[i].setLayoutParams(new LayoutParams
                    (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            text[i].setText(String.valueOf(i));
            text[i].setTextSize(25);

            text[i].setPadding(50, 25, 10, 25);
            int satir = (i/5);
            int sutun = (i%5);
            if(dynamicArea[satir][sutun] != null){
                text[i].setText(dynamicArea2[satir][sutun]);
            }else{
                text[i].setVisibility(View.GONE);
            }
            gl.addView(text[i]);
        }

        constraintLayout.addView(gl);

    }

    private void createGame() {

        List<String> letters = createPuzzle.getLetter(level1_Words.get(level_chapter).get(0));

        for (int i = 0; i < letters.size(); i++) {
            dynamicArea[1][i + 1] = letters.get(i);
        }
        List<String> letters2 = createPuzzle.getLetter(level1_Words.get(level_chapter).get(1));

        for (int i = 0; i < letters2.size(); i++) {
            if (dynamicArea[1][i + 1].equals(letters2.get(0))) {
                dynamicArea[2][i + 1] = letters2.get(1);
                dynamicArea[3][i + 1] = letters2.get(2);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                    if(dynamicArea[i][j] != null){
                        dynamicArea2[i][j]="*";
                    }
            }
        }
    }

    private void getWordsList() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        level_chapter = sh.getInt("levelChapter", 1); //Eğer level boş ise default olarak 1 ver

        if (level_chapter == 1) {
            level1_Words = createPuzzle.chooseWords(1);
        }
    }

    private void setLevelChapter(int levelChapter) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putInt("levelChapter", levelChapter);
        editor.commit();
        chapter.setText("Bölüm "+levelChapter);

    }

    private void setLevelChapterPoints(int level, int chapterr, int ppoints, String name) {

        if(ppoints > getLevelChapterPoints(level,chapterr)){
        String levelChapterPoints = "level"+level+"chapter"+chapterr;
        String levelChapterPointsUsername = "level"+level+"chapter"+chapterr+"name"+name;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putInt(levelChapterPoints, ppoints);
        editor.commit();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putString(levelChapterPointsUsername, name);
        editor.commit();
        }
    }

    private int getLevelChapterPoints(int level,int cchapter){
        String levelChapterPoints = "level"+level+"chapter"+cchapter;
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        return sh.getInt(levelChapterPoints, 0);
    }

    private String getLevelChapterPointsUsername(int level,int cchapter, String name){
        String levelChapterPointsUsername = "level"+level+"chapter"+cchapter+"name"+name;
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        return sh.getString(levelChapterPointsUsername, "admin");
    }

    private String getUsername(){
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        return sh.getString("username", "null");
    }

    private void setSharedPreferencesLevel(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putInt("level",2); //Yeni seviye ekle
        editor.commit(); //Eklediğin seviyeyi kaydet
    }

    // Shuffle Button
    private void shuffle() {
        List<String> letters = createPuzzle.getLetter(level1_Words.get(level_chapter).get(0).toString());
        Collections.shuffle(letters);

        lvl1b1.setText(letters.get(0).toString());
        lvl1b2.setText(letters.get(1).toString());
        lvl1b3.setText(letters.get(2).toString());
    }

    private void init() {
        writeWord = findViewById(R.id.writeWord);
        chapter = findViewById(R.id.chapter);
        point = findViewById(R.id.point);
        lvl1b1 = findViewById(R.id.lvl1b1);
        lvl1b2 = findViewById(R.id.lvl1b2);
        lvl1b3 = findViewById(R.id.lvl1b3);
        shuffle = findViewById(R.id.shuffle);
        leader = findViewById(R.id.leader);
        constraintLayout = findViewById(R.id.constraintlayout);
        gl = new GridLayout(Level1.this);
        gl.setLayoutParams(new LayoutParams
                (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        gl.setOrientation(GridLayout.HORIZONTAL);
        gl.setColumnCount(5);
        gl.setRowCount(5);
        gl.setPadding(360,150,0,0);

    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 1500ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1500, 10000); //
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        //get the current timeStamp

                        control();

                        lvl1b1.setEnabled(true);
                        lvl1b2.setEnabled(true);
                        lvl1b3.setEnabled(true);

                        lvl1b1.setClickable(true);
                        lvl1b2.setClickable(true);
                        lvl1b3.setClickable(true);

                        lvl1b1.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        lvl1b2.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        lvl1b3.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        writeWord.setText("");

                    }
                });
            }
        };
    }
    int lock=0;
    private void control() {
        // Toast.makeText(Level1.this, writeWord.getText().toString() + i, Toast.LENGTH_SHORT).show();
        
            if(writeWord.getText().equals(level1_Words.get(level_chapter).get(0)) && lock != 1){
                
                List<String> letters = createPuzzle.getLetter(level1_Words.get(level_chapter).get(0));

                for (int i = 0; i < letters.size(); i++) {
                    dynamicArea2[1][i + 1] = letters.get(i);
                }
                say++;
                lock = 1;
                points=points+50;
                sayControl();
                constraintLayout.removeView(gl);
                gl.removeAllViewsInLayout();
                test();
            }else if(writeWord.getText().equals(level1_Words.get(level_chapter).get(1)) && lock != 2){

                List<String> letters2 = createPuzzle.getLetter(level1_Words.get(level_chapter).get(1));

                for (int i = 0; i < letters2.size(); i++) {
                    if (dynamicArea[1][i + 1].equals(letters2.get(0))) {
                        dynamicArea2[1][i + 1] = letters2.get(0);
                        dynamicArea2[2][i + 1] = letters2.get(1);
                        dynamicArea2[3][i + 1] = letters2.get(2);
                    }
                }
                say++;
                lock = 2;
                points=points+50;
                sayControl();
                constraintLayout.removeView(gl);
                gl.removeAllViewsInLayout();
                test();
            }else{
                points = points-10;
            }

        stoptimertask();

    }

    private void sayControl(){
        if(say == 2){
            Toast.makeText(this, "Bölüm Puanınız : "+points, Toast.LENGTH_SHORT).show();
            constraintLayout.removeView(gl);
            gl.removeAllViewsInLayout();
            lock=5;
            say=0;


            setLevelChapterPoints(1,level_chapter,points,getUsername());

            points=0;
            point.setText("Puan "+points);

            level_chapter = level_chapter+1;

            if(level_chapter == 7){
                level_chapter =1;
                setLevelChapter(1);
                setSharedPreferencesLevel();
                Intent i = new Intent(Level1.this, Level2.class);
                startActivity(i);
                finish();
            }
            setLevelChapter(level_chapter);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dynamicArea[i][j] = null;
                    dynamicArea2[i][j]= null;

                }
            }
            createGame();

            shuffle();
            test();
            countDownTimer.cancel();
            startPointTimer();
        }
        point.setText("Puan "+points);
    }
}


///https://medium.com/@evan.x.liu/using-ascending-timers-in-android-studio-650268348c9d

//Kelimeler yapısı kurulmalı gelen kelimeler görülmeli sonra aşağıdakilere bakılmalı
//Levellar tasarlanmalı

