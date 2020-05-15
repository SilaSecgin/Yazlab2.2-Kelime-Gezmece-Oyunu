package com.sila.kelimegezmece.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
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

public class Level3 extends AppCompatActivity {

    public CreatePuzzle createPuzzle = new CreatePuzzle();
    public Timer timer;
    public TimerTask timerTask;
    final Handler handler = new Handler();
    CountDownTimer countDownTimer;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView writeWord,chapter,point;
    Button lvl3b1, lvl3b2, lvl3b3, lvl3b4, lvl3b5, shuffle, leader;
    public static List<List<String>> level3_Words;
    public List<String> allPoints;
    int level_chapter;
    String dynamicArea[][] = new String[9][9];
    String dynamicArea2[][] = new String[9][9];

    ConstraintLayout constraintLayout;
    GridLayout gl;
    TextView[]  text = new TextView[81];
    int say=0;
    int points=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level3);
        getSupportActionBar().setTitle("Level 3");

        init();
        getWordsList();
        shuffle();
        createGame();
        test();

        startPointTimer();

        lvl3b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl3b1.getText());
                lvl3b1.setClickable(false);
                lvl3b1.setEnabled(false);
                lvl3b1.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });


        lvl3b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl3b2.getText());
                lvl3b2.setClickable(false);
                lvl3b2.setEnabled(false);
                lvl3b2.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });

        lvl3b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl3b3.getText());
                lvl3b3.setClickable(false);
                lvl3b3.setEnabled(false);
                lvl3b3.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });

        lvl3b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl3b4.getText());
                lvl3b4.setClickable(false);
                lvl3b4.setEnabled(false);
                lvl3b4.setBackgroundColor(Color.rgb(255, 111, 156));

                startTimer();
            }
        });

        lvl3b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stoptimertask();

                writeWord.setText(writeWord.getText() + "" + lvl3b5.getText());
                lvl3b5.setClickable(false);
                lvl3b5.setEnabled(false);
                lvl3b5.setBackgroundColor(Color.rgb(255, 111, 156));

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

    private void init() {

        writeWord = findViewById(R.id.writeWord);
        chapter = findViewById(R.id.chapter);
        point = findViewById(R.id.point);
        lvl3b1 = findViewById(R.id.lvl3b1);
        lvl3b2 = findViewById(R.id.lvl3b2);
        lvl3b3 = findViewById(R.id.lvl3b3);
        lvl3b3 = findViewById(R.id.lvl3b3);
        lvl3b4 = findViewById(R.id.lvl3b4);
        lvl3b5 = findViewById(R.id.lvl3b5);
        shuffle = findViewById(R.id.shuffle);
        leader = findViewById(R.id.leader);
        constraintLayout = findViewById(R.id.constraintlayout);
        gl = new GridLayout(Level3.this);
        gl.setLayoutParams(new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        gl.setOrientation(GridLayout.HORIZONTAL);
        gl.setColumnCount(8);
        gl.setRowCount(8);
        gl.setPadding(150,20,0,0);
    }

    private void getWordsList() {
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        level_chapter = sh.getInt("levelChapter", 1); //Eğer level boş ise default olarak 1 ver

        if (level_chapter == 1) {
            level3_Words = createPuzzle.chooseWords(3);
        }
    }

    private void shuffle() {
        List<String> letters = createPuzzle.getLetter(level3_Words.get(level_chapter).get(0).toString());
        Collections.shuffle(letters);

        lvl3b1.setText(letters.get(0).toString());
        lvl3b2.setText(letters.get(1).toString());
        lvl3b3.setText(letters.get(2).toString());
        lvl3b4.setText(letters.get(3).toString());
        lvl3b5.setText(letters.get(4).toString());
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

                        lvl3b1.setEnabled(true);
                        lvl3b2.setEnabled(true);
                        lvl3b3.setEnabled(true);
                        lvl3b4.setEnabled(true);
                        lvl3b5.setEnabled(true);

                        lvl3b1.setClickable(true);
                        lvl3b2.setClickable(true);
                        lvl3b3.setClickable(true);
                        lvl3b4.setClickable(true);
                        lvl3b5.setClickable(true);

                        lvl3b1.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        lvl3b2.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        lvl3b3.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        lvl3b4.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        lvl3b5.setBackground(getResources().getDrawable(R.drawable.button_selector));
                        writeWord.setText("");

                    }
                });
            }
        };
    }

    private String getAllPoints() {

        String textt = new String();

        for(int i=7;i>1;i--){
            textt += "Level-"+3+" Bölüm-"+(i-1)+" "+ getLevelChapterPointsUsername(3,(i-1),getUsername())+ " " +getLevelChapterPoints(3,(i-1));
            textt += "                                                                                      ";
        }
        return textt;
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

    private void setLevelChapter(int levelChapter) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putInt("levelChapter", levelChapter);
        editor.commit();
        chapter.setText("Bölüm "+levelChapter);

    }

    private void setSharedPreferencesLevel(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPreferences.edit();
        editor.putInt("level",4); //Yeni seviye ekle
        editor.commit(); //Eklediğin seviyeyi kaydet
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

    private void startPointTimer() {

        countDownTimer = new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                points=points-5;
            }
        }.start();
    }

    private void test() {

        for(int i=0;i<64;i++)
        {
            text[i] = new TextView(Level3.this);
            text[i].setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            text[i].setText(String.valueOf(i));
            text[i].setTextSize(25);

            text[i].setPadding(50, 25, 10, 25);
            int satir = (i/8);
            int sutun = (i%8);

            text[i].setText(dynamicArea2[satir][sutun]);

            gl.addView(text[i]);
        }

        constraintLayout.addView(gl);

    }

    private void createGame() {
        int tutucu=0;
        List<String> letters = createPuzzle.getLetter(level3_Words.get(level_chapter).get(0));


        for (int i = 0; i < letters.size(); i++) {
            dynamicArea[1][i + 1] = letters.get(i);
        }
        List<String> letters2 = createPuzzle.getLetter(level3_Words.get(level_chapter).get(1));

        for (int i = 0; i < letters2.size(); i++) {
            if (dynamicArea[1][i + 1].equals(letters2.get(0))) {
                dynamicArea[2][i + 1] = letters2.get(1);
                dynamicArea[3][i + 1] = letters2.get(2);
                tutucu=i+1;
            }
        }

        List<String> letters3 = createPuzzle.getLetter(level3_Words.get(level_chapter).get(2));

        if (letters2.get(2).equals(letters3.get(0))) {
            dynamicArea[3][tutucu+1] = letters3.get(1);
            dynamicArea[3][tutucu+2] = letters3.get(2);
        } else if (letters2.get(2).equals(letters3.get(1))) {
            dynamicArea[3][tutucu-1] = letters3.get(0);
            dynamicArea[3][tutucu+1] = letters3.get(2);
        } else if (letters2.get(2).equals(letters3.get(2))) {
            dynamicArea[3][tutucu-2] = letters3.get(0);
            dynamicArea[3][tutucu-1] = letters3.get(1);
        }

        List<String> letters4 = createPuzzle.getLetter(level3_Words.get(level_chapter).get(3));

            dynamicArea[5][1] = letters4.get(0);
            dynamicArea[5][2] = letters4.get(1);
            dynamicArea[5][3] = letters4.get(2);
            dynamicArea[5][4] = letters4.get(3);



        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(dynamicArea[i][j] != null){
                    dynamicArea2[i][j]="*";
                }
            }
        }

    }

    int lock=0;
    private void control() {
        // Toast.makeText(Level1.this, writeWord.getText().toString() + i, Toast.LENGTH_SHORT).show();

        if(writeWord.getText().equals(level3_Words.get(level_chapter).get(0)) && lock != 1){

            List<String> letters = createPuzzle.getLetter(level3_Words.get(level_chapter).get(0));

            for (int i = 0; i < letters.size(); i++) {
                dynamicArea2[1][i + 1] = letters.get(i);
            }
            say++;
            lock = 1;
            points=points+25;
            sayControl();
            constraintLayout.removeView(gl);
            gl.removeAllViewsInLayout();
            test();
        }else if(writeWord.getText().equals(level3_Words.get(level_chapter).get(1)) && lock != 2){

            List<String> letters2 = createPuzzle.getLetter(level3_Words.get(level_chapter).get(1));

            for (int i = 0; i < letters2.size(); i++) {
                if (dynamicArea[1][i + 1].equals(letters2.get(0))) {
                    dynamicArea2[1][i + 1] = letters2.get(0);
                    dynamicArea2[2][i + 1] = letters2.get(1);
                    dynamicArea2[3][i + 1] = letters2.get(2);
                }
            }
            say++;
            lock = 2;
            points=points+25;
            sayControl();
            constraintLayout.removeView(gl);
            gl.removeAllViewsInLayout();
            test();
        }else if(writeWord.getText().equals(level3_Words.get(level_chapter).get(2)) && lock != 3){

            List<String> letters3 = createPuzzle.getLetter(level3_Words.get(level_chapter).get(2));

            for (int i = 0; i < 6; i++) {

                dynamicArea2[3][i] = dynamicArea[3][i];

            }
            say++;
            lock = 3;
            points=points+25;
            sayControl();
            constraintLayout.removeView(gl);
            gl.removeAllViewsInLayout();
            test();
        }else if(writeWord.getText().equals(level3_Words.get(level_chapter).get(3)) && lock != 4){

            List<String> letters4 = createPuzzle.getLetter(level3_Words.get(level_chapter).get(3));

            for (int i = 0; i < 6; i++) {

                dynamicArea2[5][i] = dynamicArea[5][i];

            }
            say++;
            lock = 4;
            points=points+25;
            sayControl();
            constraintLayout.removeView(gl);
            gl.removeAllViewsInLayout();
            test();
        }else{
            points = points-5;
        }

        stoptimertask();

    }

    private void sayControl(){
        if(say == 4){
            Toast.makeText(this, "Bölüm Puanınız : "+points, Toast.LENGTH_SHORT).show();
            constraintLayout.removeView(gl);
            gl.removeAllViewsInLayout();
            lock=5;
            say=0;


            setLevelChapterPoints(3,level_chapter,points,getUsername());

            points=0;
            point.setText("Puan "+points);

            level_chapter = level_chapter+1;

            if(level_chapter == 7){
                Toast.makeText(Level3.this, "Oyunu Tamamladınız. Tebrikler..", Toast.LENGTH_SHORT).show();
                setSharedPreferencesLevel();
                Intent i = new Intent(Level3.this, Homepage.class);
                startActivity(i);
                finish();
            }
            setLevelChapter(level_chapter);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    dynamicArea[i][j] = null;
                    dynamicArea2[i][j] = null;
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