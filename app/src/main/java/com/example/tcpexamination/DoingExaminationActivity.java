package com.example.tcpexamination;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.adapter.ListQuestionAdapter;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvQuestions.setLayoutManager(layoutManager);
        FetchQuestionTask fetchQuestionTask = new FetchQuestionTask();
        fetchQuestionTask.execute();
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
//            examinationAdapter = new ListExaminationAdapter(getContext(), mQuestions);
//            rvExamination.setAdapter(examinationAdapter);
        }
    }
}
