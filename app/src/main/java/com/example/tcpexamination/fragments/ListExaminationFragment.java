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
    private RecyclerView rvExamination;
    private ListExaminationAdapter examinationAdapter;

    private List<Examination> mExaminations;

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
        FetchExaminationTask task = new FetchExaminationTask();
        task.execute();
    }

    private void mapComponents(View view) {
        rvExamination = view.findViewById(R.id.rv_examinations);
    }

    class FetchExaminationTask extends AsyncTask<String, String, List<Examination>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected List<Examination> doInBackground(String... params) {
            return SocketUtil.getInstance().getAllExaminations();
        }

        @Override
        protected void onPostExecute(List<Examination> examinations) {
            mExaminations = examinations;
            examinationAdapter = new ListExaminationAdapter(view.getContext(), mExaminations);
            rvExamination.setAdapter(examinationAdapter);
        }
    }
}