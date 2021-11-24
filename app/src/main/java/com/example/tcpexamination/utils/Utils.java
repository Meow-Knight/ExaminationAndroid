package com.example.tcpexamination.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Choice;
import entity.Question;

public class Utils {
    private Utils() {}

    public static <T> List<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static String formatTimer(Integer minutes, Integer seconds) {
        return (minutes < 10 ? "0" + minutes : minutes) + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }

    public static String formatTimer(long milliseconds) {
        int seconds = (int) (milliseconds / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return formatTimer(minutes, seconds);
    }

    public static int calculateScore(List<Question> questions) {
        int correctChoiceAmount = getCorrectAnswerAmount(questions);
        int totalQuestionAmount = questions.size();

        return Math.round((float)correctChoiceAmount * 10 / totalQuestionAmount);
    }

    public static int getCorrectAnswerAmount(List<Question> questions) {
        int correctChoiceAmount = 0;

        for (Question question: questions) {
            List<Choice> choices = question.getChoices();
            Choice checkedChoice = null;
            for (Choice choice : choices) {
                if (choice.isChecked()) {
                    checkedChoice = choice;
                    break;
                }
            }

            if (checkedChoice != null && checkedChoice.getIsAnswer()) {
                correctChoiceAmount++;
            }
        }

        return correctChoiceAmount;
    }
}
