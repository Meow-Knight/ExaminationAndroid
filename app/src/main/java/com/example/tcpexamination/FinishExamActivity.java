package com.example.tcpexamination;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class FinishExamActivity extends AppCompatActivity {

    private CircularProgressBar progressBar;
    private TextView tvPercentQuestion;
    private TextView tvGrade;
    private Button btBackHome;

    private int correctAnswerAmount;
    private int questionAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_exam);

        mapComponents();
        initEvents();

        Intent intent = getIntent();
        correctAnswerAmount = intent.getIntExtra("correct_answer_amount", 0);
        questionAmount = intent.getIntExtra("question_amount", 1);
        float correctPercent = (float) correctAnswerAmount / questionAmount;
        setUpProgressBar(correctPercent * 100);

        tvPercentQuestion.setText(correctAnswerAmount + "/" + questionAmount);
        tvGrade.setText("" + Math.round(correctPercent * 10));
    }

    private void initEvents() {
        btBackHome.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setUpProgressBar(float correctPercent) {
        progressBar.setProgress(correctPercent);
        progressBar.setProgressWithAnimation(correctPercent, 2000L);
        progressBar.setRoundBorder(true);
        progressBar.setProgressBarColorStart(getResources().getColor(R.color.red_pink));
        progressBar.setProgressBarColorEnd(getResources().getColor(R.color.purple));
        progressBar.setProgressBarColorDirection(CircularProgressBar.GradientDirection.TOP_TO_BOTTOM);
        progressBar.setBackgroundProgressBarColor(getResources().getColor(R.color.white_gray));
    }

    private void mapComponents() {
        progressBar = findViewById(R.id.circularProgressBar);
        tvPercentQuestion = findViewById(R.id.tv_percent_question);
        tvGrade = findViewById(R.id.tv_grade);
        btBackHome = findViewById(R.id.bt_back_to_home);
    }
}