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
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import entity.Examination;

public class ListExaminationAdapter extends RecyclerView.Adapter<ListExaminationAdapter.ViewHolder> {
    private Context context;
    private List<Examination> examinations;

    public ListExaminationAdapter(Context context, List<Examination> examinations) {
        this.context = context;
        this.examinations = examinations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_examination, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Examination examination = examinations.get(position);

        holder.tvFromTime.setText(DateUtil.formatDate(examination.getStartTime()));
        holder.tvToTime.setText(DateUtil.formatDate(examination.getEndTime()));
        holder.tvExamTitle.setText(examination.getTitle());
        holder.tvExamDescription.setText(examination.getDescription());
        holder.tvCreatedBy.setText(examination.getCreatedByAccountName());
        holder.tvExamDuration.setText(examination.getDuration() + "");
        holder.tvQuestionAmount.setText(examination.getQuestionAmount() + "");
    }

    @Override
    public int getItemCount() {
        return examinations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFromTime;
        TextView tvToTime;
        TextView tvExamTitle;
        TextView tvExamDescription;
        TextView tvCreatedBy;
        TextView tvExamDuration;
        TextView tvQuestionAmount;
        TextView btStartTest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapComponents(itemView);
            initEvents(itemView);
        }

        private void mapComponents(View itemView) {
            tvFromTime = itemView.findViewById(R.id.tv_from_time);
            tvToTime = itemView.findViewById(R.id.tv_to_time);
            tvExamTitle = itemView.findViewById(R.id.tv_exam_title);
            tvExamDescription = itemView.findViewById(R.id.tv_exam_description);
            tvCreatedBy = itemView.findViewById(R.id.tv_exam_created_by);
            tvExamDuration = itemView.findViewById(R.id.tv_exam_duration);
            tvQuestionAmount = itemView.findViewById(R.id.tv_exam_question_amount);
            btStartTest = itemView.findViewById(R.id.bt_start_test);
        }

        private void initEvents(View itemView) {
            btStartTest.setOnClickListener(v -> {
                Examination currentExamination = examinations.get(getLayoutPosition());
//                Long examinationId = currentExamination.getId();
                Intent intent = new Intent(itemView.getContext(), DoingExaminationActivity.class);
                intent.putExtra("current_examination", currentExamination);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
