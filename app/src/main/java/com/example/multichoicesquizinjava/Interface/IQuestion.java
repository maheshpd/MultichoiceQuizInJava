package com.example.multichoicesquizinjava.Interface;

import com.example.multichoicesquizinjava.Model.CurrentQuestion;

public interface IQuestion {
    CurrentQuestion getSelectedAnswer(); //get selected Answer from user select
    void showCorrectAnswer(); //Bold correct Answer text
    void disableAnswer(); //Disable all check box
    void resetQuestion(); //Reset all function on question
}
