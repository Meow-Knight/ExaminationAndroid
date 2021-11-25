package com.example.tcpexamination.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.DoingExaminationActivity;
import com.example.tcpexamination.R;
import com.example.tcpexamination.utils.DateUtil;

import java.util.List;

import entity.Examination;
import entity.History;

public class ListHistoryAdapter extends RecyclerView.Adapter<ListHistoryAdapter.ViewHolder> {
    private Context context;
    private List<History> histories;

    public ListHistoryAdapter(Context context, List<History> histories) {
        this.context = context;
        this.histories = histories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = histories.get(position);
        int correctAnswerAmount = history.getCorrectAnswerAmount();
        int totalQuestionAmount = history.getTotalQuestionAmount();
        int grade = Math.round((float) correctAnswerAmount * 10 / totalQuestionAmount);

        holder.tvFinishedAt.setText(DateUtil.formatDate(history.getCreatedAt()));
        holder.tvExamTitle.setText(history.getExamination().getTitle());
        holder.tvExamCreatedBy.setText(history.getExamination().getCreatedByAccountName());
        holder.tvGrade.setText("" + grade);
        holder.tvPercentCorrectAnswer.setText(correctAnswerAmount + "/" + totalQuestionAmount);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFinishedAt;
        TextView tvExamTitle;
        TextView tvExamCreatedBy;
        TextView tvGrade;
        TextView tvPercentCorrectAnswer;
        TextView btTryAgain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapComponents(itemView);
            initEvents(itemView);
        }

        private void mapComponents(View itemView) {
            tvFinishedAt = itemView.findViewById(R.id.tv_finished_at);
            tvExamTitle = itemView.findViewById(R.id.tv_exam_title);
            tvExamCreatedBy = itemView.findViewById(R.id.tv_exam_created_by);
            tvGrade = itemView.findViewById(R.id.tv_grade);
            tvPercentCorrectAnswer = itemView.findViewById(R.id.tv_percent_correct);
            btTryAgain = itemView.findViewById(R.id.bt_try_again);
        }

        private void initEvents(View itemView) {
            btTryAgain.setOnClickListener(v -> {
                Examination currentExamination = histories.get(getLayoutPosition()).getExamination();
                Intent intent = new Intent(itemView.getContext(), DoingExaminationActivity.class);
                intent.putExtra("current_examination", currentExamination);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
