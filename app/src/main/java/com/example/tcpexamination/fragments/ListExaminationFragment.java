package com.example.tcpexamination.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.R;

import java.util.List;

import entity.Examination;

public class ListExaminationFragment extends Fragment {

    public static final String FRAGMENT_TAG = "SHOW_SCHEDULE_FRAGMENT";

    private View view;
    private RecyclerView rvExamination;

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
            view = inflater.inflate(R.layout.fragment_history, container, false);
        }
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void mapComponents(View view) {
        rvExamination = view.findViewById(R.id.rv_examinations);
    }

}