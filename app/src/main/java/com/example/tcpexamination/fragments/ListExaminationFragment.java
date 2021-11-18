package com.example.tcpexamination.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.MainActivity;
import com.example.tcpexamination.R;
import com.example.tcpexamination.adapter.ListExaminationAdapter;
import com.example.tcpexamination.utils.SocketUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Account;
import entity.Examination;

public class ListExaminationFragment extends Fragment {

    public static final String FRAGMENT_TAG = "LIST_EXAMINATION_FRAGMENT";

    private View view;
    private MainActivity parentActivity;
    private RecyclerView rvExamination;
    private ListExaminationAdapter examinationAdapter;

    private List<Examination> examinations;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_examinations, container, false);
        }
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapComponents(view);
        LinearLayoutManager layout = new LinearLayoutManager(view.getContext());
        rvExamination.setLayoutManager(layout);
        fetchData();
    }

    private void fetchData() {
        Log.d("hehe", "in fetch");
        FetchExaminationTask task = new FetchExaminationTask();
        task.execute();
//        MainActivity mainActivity = (MainActivity) view.getContext();
//        mainActivity.runOnUiThread(new Runnable() {
//            @Override
////            public void run() {
//                examinations = SocketUtil.getInstance().getAllExaminations();
//                Log.d("hehe", "dddd");
//                examinationAdapter = new ListExaminationAdapter(view.getContext(), examinations);
//                rvExamination.setAdapter(examinationAdapter);
//            }
//        });
    }

    private void mapComponents(View view) {
        rvExamination = view.findViewById(R.id.rv_examinations);
    }

    class FetchExaminationTask extends AsyncTask<String, String, Examination[]> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Examination[] doInBackground(String... params) {
            Log.d("hehe", "doInBackground");
            Examination[] examinations = SocketUtil.getInstance().getAllExaminations();
            Log.d("hehe", examinations.length + "");
//            Log.d("hehe", examinations.size() + "");

//            ((MainActivity) view.getContext()).runOnUiThread(new Runnable() {
//                public void run() {
//
//                }
//            });

            return examinations;
        }

        @Override
        protected void onPostExecute(Examination[] examinations) {
            List<Examination> hehe = Arrays.asList(examinations);
            examinationAdapter = new ListExaminationAdapter(view.getContext(), hehe);
            rvExamination.setAdapter(examinationAdapter);
        }
    }
}