package com.example.multichoicesquizinjava.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multichoicesquizinjava.Common.Common;
import com.example.multichoicesquizinjava.Model.CurrentQuestion;
import com.example.multichoicesquizinjava.R;

import java.util.List;

public class AnswerSheetAdapter extends RecyclerView.Adapter<AnswerSheetAdapter.AnswerViewHolder> {

    Context context;
    List<CurrentQuestion> currentQuestionList;

    public AnswerSheetAdapter(Context context, List<CurrentQuestion> currentQuestionList) {
        this.context = context;
        this.currentQuestionList = currentQuestionList;
    }


    @NonNull
    @Override
    public AnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_grid_answer_sheet_item,parent,false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerViewHolder holder, int position) {
        if (currentQuestionList.get(position).getType() == Common.ANSWER_TYPE.RIGHT_ANSWER)
            holder.question_item.setBackgroundResource(R.drawable.grid_question_right_answer);
        else if (currentQuestionList.get(position).getType() == Common.ANSWER_TYPE.WRONG_ANSWER)
            holder.question_item.setBackgroundResource(R.drawable.grid_question_right_answer);
        else
            holder.question_item.setBackgroundResource(R.drawable.grid_question_no_answer);
    }

    @Override
    public int getItemCount() {
        return currentQuestionList.size();
    }

    public class AnswerViewHolder extends RecyclerView.ViewHolder {
        View question_item;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);

            question_item = itemView.findViewById(R.id.question_item);
        }
    }
}
