package com.example.tcpexamination.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.R;

import java.util.List;

import entity.Question;

public class ListQuestionAdapter extends RecyclerView.Adapter<ListQuestionAdapter.ViewHolder> {
    private Context context;
    private List<Question> questions;

    public ListQuestionAdapter(Context context, List<Question> questions) {
        this.context = context;
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.tvQuestionContent.setText(question.getContent());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvChoices.setLayoutManager(layoutManager);

        Log.d("hehe", question.getChoices().size() + "");
        ListChoiceAdapter choiceAdapter = new ListChoiceAdapter(context, question.getChoices());
        holder.rvChoices.setAdapter(choiceAdapter);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestionContent;
        RecyclerView rvChoices;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapComponents(itemView);
            initEvents(itemView);


        }

        private void mapComponents(View itemView) {
            tvQuestionContent = itemView.findViewById(R.id.tv_question_content);
            rvChoices = itemView.findViewById(R.id.rv_choices);
        }

        private void initEvents(View itemView) {
        }
    }
}
