package com.example.tcpexamination.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tcpexamination.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import entity.Choice;
import entity.Question;

public class ListChoiceAdapter extends RecyclerView.Adapter<ListChoiceAdapter.ViewHolder> {
    private Context context;
    private List<Choice> choices;

    private Choice checkedChoice;

    private static final String[] ALPHABET_INDEX = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    public ListChoiceAdapter(Context context, List<Choice> choices) {
        this.context = context;
        this.choices = choices;

        for (Choice choice: choices) {
            if (choice.isChecked()) {
                checkedChoice = choice;
                break;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        if (choice.isChecked()) {
            holder.llContainer.setBackgroundColor(context.getResources().getColor(R.color.lightest_gray));
        }

        setCheckedChoiceBG(holder.llContainer, choice);
    }

    @Override
    public int getItemCount() {
        return choices.size();
    }

    private void setCheckedChoiceBG(LinearLayout llContainer, Choice choice) {
        boolean isChecked = choice.isChecked();
        int background = context.getResources().getColor(isChecked ? R.color.lightest_gray : R.color.white);
        llContainer.setBackgroundColor(background);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvChoiceIndex;
        TextView tvChoiceContent;
        LinearLayout llContainer;

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapComponents(itemView);
            initEvents(itemView);
        }

        private void mapComponents(View itemView) {
            tvChoiceIndex = itemView.findViewById(R.id.tv_choice_index);
            tvChoiceContent = itemView.findViewById(R.id.tv_choice_content);
            llContainer = itemView.findViewById(R.id.layout_choice_container);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private void initEvents(View itemView) {
            llContainer.setOnClickListener(v -> {
                int index = getLayoutPosition();
                choices.forEach(choice -> choice.setChecked(false));
                Choice curChoice = choices.get(index);
                if (checkedChoice != null) {
                    checkedChoice.setChecked(false);
                }
                curChoice.setChecked(true);
                checkedChoice = curChoice;
                notifyDataSetChanged();
            });
        }
    }
}
