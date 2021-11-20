package com.example.tcpexamination.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.R;

import java.util.List;

import entity.Choice;
import entity.Question;

public class ListChoiceAdapter extends RecyclerView.Adapter<ListChoiceAdapter.ViewHolder> {
    private Context context;
    private List<Choice> choices;

    private static final String[] ALPHABET_INDEX = {"A", "B", "C", "D", "E", "F"};

    public ListChoiceAdapter(Context context, List<Choice> choices) {
        this.context = context;
        this.choices = choices;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_choice, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Choice choice = choices.get(position);

        holder.tvChoiceIndex.setText(ALPHABET_INDEX[position]);
        holder.tvChoiceContent.setText(choice.getContent());
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChoiceIndex;
        TextView tvChoiceContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapComponents(itemView);
            initEvents(itemView);
        }

        private void mapComponents(View itemView) {
            tvChoiceIndex = itemView.findViewById(R.id.tv_choice_index);
            tvChoiceContent = itemView.findViewById(R.id.tv_choice_content);
        }

        private void initEvents(View itemView) {
        }
    }
}
