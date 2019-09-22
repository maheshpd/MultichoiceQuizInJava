package com.example.multichoicesquizinjava.activity;

import android.os.Bundle;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.multichoicesquizinjava.Adapter.AnswerSheetAdapter;
import com.example.multichoicesquizinjava.Common.Common;
import com.example.multichoicesquizinjava.DBHelper.DBHelper;
import com.example.multichoicesquizinjava.Model.CurrentQuestion;
import com.example.multichoicesquizinjava.Model.Question;
import com.example.multichoicesquizinjava.R;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import android.os.CountDownTimer;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class QuestionsActivity extends AppCompatActivity {

   int time_play = Common.TOTAL_TIME;
    boolean isAnswerModeView = false;


    TextView txt_right_answer,txt_timer;


    RecyclerView answer_sheet_view;
    AnswerSheetAdapter answerSheetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Common.selectedCategory.getName());
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //First, we need take question from DB
         takeQuestion();

        if (Common.questionList.size() > 0) {

            //Show textView right and Text View Timer
            txt_right_answer = findViewById(R.id.txt_question_right);
            txt_timer = findViewById(R.id.txt_timer);

            txt_timer.setVisibility(View.VISIBLE);
            txt_right_answer.setVisibility(View.VISIBLE);
            txt_right_answer.setText(new StringBuilder(String.format("%d/%d",Common.right_answer_count,Common.questionList.size())));
            counterTimer();



            answer_sheet_view = findViewById(R.id.grid_answer);
            answer_sheet_view.setHasFixedSize(true);
            if (Common.questionList.size() > 5) // If question List have size > 5 , we will seprate 2 row
                answer_sheet_view.setLayoutManager(new GridLayoutManager(this, Common.questionList.size() / 2));
            answerSheetAdapter = new AnswerSheetAdapter(this, Common.answerSheetList);
            answer_sheet_view.setAdapter(answerSheetAdapter);
        }
    }


    private void counterTimer() {
        if (Common.countDownTimer == null)
        {
            Common.countDownTimer = new CountDownTimer(Common.TOTAL_TIME,1000)   {
                @Override
                public void onTick(long l) {
                    txt_timer.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(l),
                            TimeUnit.MILLISECONDS.toSeconds(l) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));
                    time_play -=1000;
                }

                @Override
                public void onFinish() {
        //Finish Game
                }
            }.start();
        }
        else {
            Common.countDownTimer.cancel();
            Common.countDownTimer = new CountDownTimer(Common.TOTAL_TIME,1000)   {
                @Override
                public void onTick(long l) {
                    txt_timer.setText(String.format("%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(1),
                            TimeUnit.MILLISECONDS.toSeconds(1) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(1))));
                    time_play -=1000;
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }

    private void takeQuestion() {

        Common.questionList = DBHelper.getInstance(this).getQuestionByCategory(Common.selectedCategory.getId());
        if (Common.questionList.size() == 0)
        {
            //If no question
            new MaterialStyledDialog.Builder(this)
                    .setTitle("Opps")
                    .setIcon(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
                    .setDescription("We don't have any question in this " + Common.selectedCategory.getName()+" category")
                    .setPositiveText("OK")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).show();

        }
        else {


            if (Common.answerSheetList.size() > 0)
                Common.answerSheetList.clear();

            //Gen answerSheet item from question
            //30 question = 30 answer sheet item
            //1 question = 1 answer sheet item
            for (int i=0;i<Common.questionList.size();i++)
            {
                Common.answerSheetList.add(new CurrentQuestion(i,Common.ANSWER_TYPE.NO_ANSWER)); //Default all answer is no answer
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    protected void onDestroy() {
        if (Common.countDownTimer != null)
            Common.countDownTimer.cancel();
        super.onDestroy();
    }
}
