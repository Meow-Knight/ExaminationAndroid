package com.example.tcpexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class FinishExamActivity extends AppCompatActivity {

    private CircularProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_exam);

        mapComponents();

        progressBar.setProgress(60f);
        progressBar.setProgressWithAnimation(60f, 2000L);
        progressBar.setRoundBorder(true);
        progressBar.setProgressBarColorStart(getResources().getColor(R.color.red_pink));
        progressBar.setProgressBarColorEnd(getResources().getColor(R.color.purple));
        progressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);
        progressBar.setBackgroundProgressBarColor(getResources().getColor(R.color.white_gray));
    }

    private void mapComponents() {
        progressBar = findViewById(R.id.circularProgressBar);
    }
}