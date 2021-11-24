package com.example.tcpexamination;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.adapter.ListQuestionAdapter;
import com.example.tcpexamination.common.CustomLinearLayoutManager;
import com.example.tcpexamination.utils.SocketUtil;
import com.example.tcpexamination.utils.Utils;

import java.util.List;

import entity.Examination;
import entity.Question;

public class DoingExaminationActivity extends AppCompatActivity {
    private Examination currentExamination;
    private List<Question> mQuestions;
    private boolean isFinished = false;

    private LinearLayout btCancelExamination;
    private LinearLayout btFinishExamination;
    private TextView tvExaminationTimer;
    private TextView btBackQuestion;
    private TextView btNextQuestion;

    private RecyclerView rvQuestions;
    private CustomLinearLayoutManager layoutManager;
    private ListQuestionAdapter questionAdapter;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_examination);

        Intent intent = getIntent();
        currentExamination = (Examination) intent.getSerializableExtra("current_examination");

        mapComponents();
        initEvents();
        layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setScrollHorizontalEnabled(false);
        rvQuestions.setLayoutManager(layoutManager);
        FetchQuestionTask fetchQuestionTask = new FetchQuestionTask();
        fetchQuestionTask.execute();
    }

    private void initEvents() {
        btNextQuestion.setOnClickListener(v -> {
            if (!questionAdapter.isReachedLastItem()) {
                if (questionAdapter.isReachedFirstItem()) {
                    btBackQuestion.setBackground(getDrawable(R.drawable.rounded_back_button));
                    btBackQuestion.setTextColor(getResources().getColor(R.color.black));
                }

                layoutManager.setScrollHorizontalEnabled(true);

                rvQuestions.smoothScrollToPosition(questionAdapter.increaseItemPosition());
                if (questionAdapter.isReachedLastItem()) {
                    btNextQuestion.setBackground(getDrawable(R.drawable.disable_next_button));
                }

                disableScrollQuestions();
            }
        });

        btBackQuestion.setOnClickListener(v -> {
            if (!questionAdapter.isReachedFirstItem()) {
                if (questionAdapter.isReachedLastItem()) {
                    btNextQuestion.setBackground(getDrawable(R.drawable.rounded_next_button));
                }

                layoutManager.setScrollHorizontalEnabled(true);

                rvQuestions.smoothScrollToPosition(questionAdapter.decreaseItemPosition());
                if (questionAdapter.isReachedFirstItem()) {
                    btBackQuestion.setBackground(getDrawable(R.drawable.disable_back_button));
                    btBackQuestion.setTextColor(getResources().getColor(R.color.greycolor));
                }

                disableScrollQuestions();
            }
        });

        btFinishExamination.setOnClickListener(v -> new AlertDialog.Builder(this)
            .setIcon(getResources().getDrawable(R.drawable.ic_baseline_warning_24))
            .setTitle("Finish Examination")
            .setMessage("Are you sure you want to finish current examination?")
            .setPositiveButton("Yes", (dialog, which) -> countDownTimer.onFinish())
            .setNegativeButton("No", null)
            .show());
    }

    public void disableScrollQuestions() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                layoutManager.setScrollHorizontalEnabled(false);
            }
        };
        thread.start();
    }

    private void mapComponents() {
        btCancelExamination = findViewById(R.id.bt_cancel_examination);
        btFinishExamination = findViewById(R.id.bt_finish_examination);
        tvExaminationTimer = findViewById(R.id.tv_examination_timer);
        btBackQuestion = findViewById(R.id.bt_back_question);
        btNextQuestion = findViewById(R.id.bt_next_question);
        rvQuestions = findViewById(R.id.rv_questions);

        tvExaminationTimer.setText(Utils.formatTimer(currentExamination.getDuration(), 0));
    }


    private class FetchQuestionTask extends AsyncTask<String, String, List<Question>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected List<Question> doInBackground(String... params) {
            return SocketUtil.getInstance().getQuestionByExamId(currentExamination.getId());
        }

        @Override
        protected void onPostExecute(List<Question> examinations) {
            mQuestions = examinations;
            questionAdapter = new ListQuestionAdapter(getApplicationContext(), mQuestions);
            rvQuestions.setAdapter(questionAdapter);

            countDownTimer = new CountDownTimer(currentExamination.getDuration() * 60 * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvExaminationTimer.setText(Utils.formatTimer(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    if (!isFinished) {
                        isFinished = true;
                    }

                    Intent intent = new Intent(DoingExaminationActivity.this, FinishExamActivity.class);
                    intent.putExtra("correct_answer_amount", Utils.getCorrectAnswerAmount(mQuestions));
                    intent.putExtra("question_amount", mQuestions.size());
                    startActivity(intent);
                }
            };
            countDownTimer.start();
        }
    }
}
