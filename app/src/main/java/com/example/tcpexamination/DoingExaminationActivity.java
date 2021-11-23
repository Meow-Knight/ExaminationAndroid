package com.example.tcpexamination;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.adapter.ListQuestionAdapter;
import com.example.tcpexamination.common.CustomLinearLayoutManager;
import com.example.tcpexamination.utils.SocketUtil;

import java.util.List;

import entity.Question;

public class DoingExaminationActivity extends AppCompatActivity {
    private Long examinationId;
    private List<Question> mQuestions;

    private LinearLayout btCancelExamination;
    private LinearLayout btFinishExamination;
    private TextView tvExaminationTimer;
    private TextView btBackQuestion;
    private TextView btNextQuestion;

    private RecyclerView rvQuestions;
    private CustomLinearLayoutManager layoutManager;
    private ListQuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing_examination);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Toast.makeText(this, "Error examination id is missing in intent", Toast.LENGTH_SHORT).show();
            return;
        }

        examinationId = bundle.getLong("examination_id");

        mapComponents();
        initEvents();
        layoutManager = new CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setScrollHorizontalEnabled(true);
        rvQuestions.setLayoutManager(layoutManager);
        FetchQuestionTask fetchQuestionTask = new FetchQuestionTask();
        fetchQuestionTask.execute();
    }

    private void initEvents() {
        btNextQuestion.setOnClickListener(v -> {
            layoutManager.setScrollHorizontalEnabled(true);

            rvQuestions.smoothScrollToPosition(questionAdapter.increaseItemPosition());
            if (questionAdapter.isReachedLastItem()) {
                btNextQuestion.setVisibility(View.GONE);
            }
            if (btBackQuestion.getVisibility() == View.GONE) {
                btBackQuestion.setVisibility(View.VISIBLE);
            }

            disableScrollQuestions();
        });

        btBackQuestion.setOnClickListener(v -> {
            layoutManager.setScrollHorizontalEnabled(true);

            rvQuestions.smoothScrollToPosition(questionAdapter.decreaseItemPosition());
            if (questionAdapter.isReachedFirstItem()) {
                btBackQuestion.setVisibility(View.GONE);
            }
            if (btNextQuestion.getVisibility() == View.GONE) {
                btNextQuestion.setVisibility(View.VISIBLE);
            }

            disableScrollQuestions();
        });
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
    }


    private class FetchQuestionTask extends AsyncTask<String, String, List<Question>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected List<Question> doInBackground(String... params) {
            return SocketUtil.getInstance().getQuestionByExamId(examinationId);
        }

        @Override
        protected void onPostExecute(List<Question> examinations) {
            mQuestions = examinations;
            questionAdapter = new ListQuestionAdapter(getApplicationContext(), mQuestions);
            rvQuestions.setAdapter(questionAdapter);
        }
    }
}
