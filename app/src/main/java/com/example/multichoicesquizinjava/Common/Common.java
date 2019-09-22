package com.example.multichoicesquizinjava.Common;

import android.os.CountDownTimer;

import com.example.multichoicesquizinjava.Model.Category;
import com.example.multichoicesquizinjava.Model.CurrentQuestion;
import com.example.multichoicesquizinjava.Model.Question;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

public class Common {
    public static final int TOTAL_TIME = 20*60*1000; //20 min
    public static Category selectedCategory = new Category();
    public static List<Question> questionList = new ArrayList<>();
    public static List<CurrentQuestion> answerSheetList = new ArrayList<>();

    public static CountDownTimer countDownTimer;
    public static int right_answer_count = 0;
    public static int wrong_answer_count = 0;


    public enum ANSWER_TYPE {
       NO_ANSWER,
       WRONG_ANSWER,
       RIGHT_ANSWER
    }
}
