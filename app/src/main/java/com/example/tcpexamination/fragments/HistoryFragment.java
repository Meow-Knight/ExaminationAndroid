package com.example.tcpexamination.fragments;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.R;
import com.example.tcpexamination.adapter.ListExaminationAdapter;
import com.example.tcpexamination.adapter.ListHistoryAdapter;
import com.example.tcpexamination.utils.SocketUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import entity.Examination;
import entity.History;

public class HistoryFragment extends Fragment {

    private FirebaseUser currentUser;

    public static final String FRAGMENT_TAG = "HISTORY_FRAGMENT";

    private View view;

    private RecyclerView rvExaminationHistory;
    private ListHistoryAdapter historyAdapter;
    private List<History> mHistories;

    public HistoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_history, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapComponents(view);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        LinearLayoutManager layout = new LinearLayoutManager(view.getContext());
        rvExaminationHistory.setLayoutManager(layout);
        fetchData();
    }

    private void fetchData() {
        FetchHistoryTask task = new FetchHistoryTask(currentUser.getEmail());
        task.execute();
    }

    private void mapComponents(View view) {
        rvExaminationHistory = view.findViewById(R.id.rv_examination_history);
    }

    class FetchHistoryTask extends AsyncTask<String, String, List<History>> {
        private final String accountEmail;

        FetchHistoryTask(String accountEmail) {
            this.accountEmail = accountEmail;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        protected List<History> doInBackground(String... params) {
            return SocketUtil.getInstance().getHistoryByAccountEmail(accountEmail);
        }

        @Override
        protected void onPostExecute(List<History> histories) {
            mHistories = histories;
            historyAdapter = new ListHistoryAdapter(view.getContext(), mHistories);
            rvExaminationHistory.setAdapter(historyAdapter);
        }
    }
}