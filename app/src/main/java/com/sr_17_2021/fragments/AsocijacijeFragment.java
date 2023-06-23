package com.sr_17_2021.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sr_17_2021.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AsocijacijeFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AsocijacijeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AsocijacijeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AsocijacijeFragment newInstance() {
        AsocijacijeFragment fragment = new AsocijacijeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    public AsocijacijeFragment() {
        // Required empty public constructor
    }
        private String[] columnSolutions={"Column 1", "Column 2", "Column 3", "Column 4"};
        private String[] columnSolutionsRound1 = {"Column 1", "Column 2", "Column 3", "Column 4"};

        private String[] columnSolutionsRound2 = {"Column 11", "Column 22", "Column 33", "Column 44"};
        private String finalSolutionStringRound1 = "Final Solution 1";
        private String finalSolutionStringRound2 = "Final Solution 2";
        private String finalSolutionString = "";
        private List<List<String>> currentFields = new ArrayList<>();
        private List<List<String>> fieldsRound1 = new ArrayList<>();
        private List<List<String>> fieldsRound2 = new ArrayList<>();
        private boolean[][] openedFields = new boolean[4][4];
        private int score = 0;

        private TextView playerScoreText;
        private TextView fieldA1, fieldB1, fieldC1, fieldD1, fieldA2, fieldB2, fieldC2, fieldD2, fieldA3, fieldB3, fieldC3, fieldD3, fieldA4, fieldB4, fieldC4, fieldD4;
        private List <TextView> fieldList = new ArrayList<>();
        private TextView solutionA, solutionB, solutionC, solutionD;

        private TextView finalSolution;
        private TextView time;
        private TextView p1Score;
        private ProgressBar progressBar;
        private int round=1;
        private CountDownTimer timer;

    public void assignClickListener(View.OnClickListener textViewClickListener){
        fieldA1.setOnClickListener(textViewClickListener);
        fieldB1.setOnClickListener(textViewClickListener);
        fieldC1.setOnClickListener(textViewClickListener);
        fieldD1.setOnClickListener(textViewClickListener);
        fieldA2.setOnClickListener(textViewClickListener);
        fieldB2.setOnClickListener(textViewClickListener);
        fieldC2.setOnClickListener(textViewClickListener);
        fieldD2.setOnClickListener(textViewClickListener);
        fieldA3.setOnClickListener(textViewClickListener);
        fieldB3.setOnClickListener(textViewClickListener);
        fieldC3.setOnClickListener(textViewClickListener);
        fieldD3.setOnClickListener(textViewClickListener);
        fieldA4.setOnClickListener(textViewClickListener);
        fieldB4.setOnClickListener(textViewClickListener);
        fieldC4.setOnClickListener(textViewClickListener);
        fieldD4.setOnClickListener(textViewClickListener);
    }
    public void assignEditorActionListener(TextView.OnEditorActionListener onEditorActionListener){
        solutionA.setOnEditorActionListener(onEditorActionListener);
        solutionB.setOnEditorActionListener(onEditorActionListener);
        solutionC.setOnEditorActionListener(onEditorActionListener);
        solutionD.setOnEditorActionListener(onEditorActionListener);
        finalSolution.setOnEditorActionListener(onEditorActionListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_asocijacije, container, false);
            fieldsRound1.add(List.of("word 1", "word 2", "word 3", "word 4"));
            fieldsRound1.add(List.of("word 5", "word 6", "word 7", "word 8"));
            fieldsRound1.add(List.of("word 9", "word 10", "word 11", "word 12"));
            fieldsRound1.add(List.of("word 13", "word 14", "word 15", "word 16"));

            fieldsRound2.add(List.of("word 11", "word 22", "word 33", "word 44"));
            fieldsRound2.add(List.of("word 55", "word 66", "word 77", "word 88"));
            fieldsRound2.add(List.of("word 99", "word 110", "word 111", "word 112"));
            fieldsRound2.add(List.of("word 113", "word 114", "word 115", "word 116"));

            p1Score = view.findViewById(R.id.asocijacijeP1Points);

            fieldA1 = view.findViewById(R.id.fieldA1);
            fieldB1 = view.findViewById(R.id.fieldB1);
            fieldC1 = view.findViewById(R.id.fieldC1);
            fieldD1 = view.findViewById(R.id.fieldD1);
            fieldA2 = view.findViewById(R.id.fieldA2);
            fieldB2 = view.findViewById(R.id.fieldB2);
            fieldC2 = view.findViewById(R.id.fieldC2);
            fieldD2 = view.findViewById(R.id.fieldD2);
            fieldA3 = view.findViewById(R.id.fieldA3);
            fieldB3 = view.findViewById(R.id.fieldB3);
            fieldC3 = view.findViewById(R.id.fieldC3);
            fieldD3 = view.findViewById(R.id.fieldD3);
            fieldA4 = view.findViewById(R.id.fieldA4);
            fieldB4 = view.findViewById(R.id.fieldB4);
            fieldC4 = view.findViewById(R.id.fieldC4);
            fieldD4 = view.findViewById(R.id.fieldD4);

            fieldList.add(fieldA1);
            fieldList.add(fieldB1);
            fieldList.add(fieldC1);
            fieldList.add(fieldD1);
            fieldList.add(fieldA2);
            fieldList.add(fieldB2);
            fieldList.add(fieldC2);
            fieldList.add(fieldD2);
            fieldList.add(fieldA3);
            fieldList.add(fieldB3);
            fieldList.add(fieldC3);
            fieldList.add(fieldD3);
            fieldList.add(fieldA4);
            fieldList.add(fieldB4);
            fieldList.add(fieldC4);
            fieldList.add(fieldD4);

        solutionA = view.findViewById(R.id.solutionA);
            solutionB = view.findViewById(R.id.solutionB);
            solutionC = view.findViewById(R.id.solutionC);
            solutionD = view.findViewById(R.id.solutionD);




            finalSolution = view.findViewById(R.id.AsocijacjeSolution);


            solutionA.setEnabled(false);
            solutionB.setEnabled(false);
            solutionC.setEnabled(false);
            solutionD.setEnabled(false);
            progressBar = view.findViewById(R.id.asocijacijeProgressBar);
            time=view.findViewById(R.id.asocijacijeTime);



        View.OnClickListener textViewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView textView = (TextView) v;
                String id= getResources().getResourceName(textView.getId());
                char secondToLast = id.charAt(id.length() - 2);
                char last = id.charAt(id.length() - 1);
                int lastInt = Character.getNumericValue(last);
                lastInt--;

                    switch(secondToLast) {
                        case 'A':
                            secondToLast = '0';
                            break;
                        case 'B':
                            secondToLast = '1';
                            break;
                        case 'C':
                            secondToLast = '2';
                            break;
                        case 'D':
                            secondToLast = '3';
                            break;
                }

                int secondToLastInt = Character.getNumericValue(secondToLast);

                openField(secondToLastInt, lastInt,textView);
                //textView.setOnClickListener(null);
                if (secondToLast >= '0' && secondToLast <= '3') {
                    List<Integer> openColumnsList = getOpenColumns();
                    if (openColumnsList.contains(0) && !solutionA.getText().toString().equalsIgnoreCase(columnSolutions[0]))
                        solutionA.setEnabled(true);
                    if (openColumnsList.contains(1) && !solutionB.getText().toString().equalsIgnoreCase(columnSolutions[1]))
                        solutionB.setEnabled(true);
                    if (openColumnsList.contains(2) && !solutionC.getText().toString().equalsIgnoreCase(columnSolutions[2]))
                        solutionC.setEnabled(true);
                    if (openColumnsList.contains(3) && !solutionD.getText().toString().equalsIgnoreCase(columnSolutions[3]))
                        solutionD.setEnabled(true);

                }
            }

        };
        TextView.OnEditorActionListener onEditorActionListener = (new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE) {
                    TextView textView = (TextView) v;
                    String id= getResources().getResourceName(textView.getId());
                    char last = id.charAt(id.length() - 1);

                    switch(last) {
                        case 'A':
                            last = '0';
                            break;
                        case 'B':
                            last = '1';
                            break;
                        case 'C':
                            last = '2';
                            break;
                        case 'D':
                            last = '3';
                            break;
                        default:
                            guessFinalSolution(textView.getText().toString());
                            break;
                    }
                    if(Character.isDigit(last)) {
                        int lastInt = Character.getNumericValue(last);
                        guessColumnSolution(lastInt,textView.getText().toString());
                    }
                }
                return false;
            }

        });
                assignClickListener(textViewClickListener);
                assignEditorActionListener(onEditorActionListener);


            StartRound();
            return view;

        }

        private void StartRound(){
            if(round==1) {
                finalSolutionString=finalSolutionStringRound1;
                currentFields.add(fieldsRound1.get(0));
                currentFields.add(fieldsRound1.get(1));
                currentFields.add(fieldsRound1.get(2));
                currentFields.add(fieldsRound1.get(3));
                for (int i = 0; i < columnSolutionsRound1.length; i++) {
                    columnSolutions[i] = columnSolutionsRound1[i];
                }
            }
            else if(round==2){
                currentFields.clear();
                finalSolutionString=finalSolutionStringRound2;
                currentFields.add(fieldsRound2.get(0));
                currentFields.add(fieldsRound2.get(1));
                currentFields.add(fieldsRound2.get(2));
                currentFields.add(fieldsRound2.get(3));
                for (int i = 0; i <columnSolutionsRound2.length; i++) {
                    columnSolutions[i] = columnSolutionsRound2[i];
                }
                //close all fields
                for (int i = 0; i < openedFields.length; i++) {
                    for (int j = 0; j < openedFields[i].length; j++) {
                        openedFields[i][j] = false;
                    }
                }

            }
            for(TextView field :fieldList){
                field.setText("");
                field.setEnabled(true);
            }
            solutionA.setText("");
            solutionB.setText("");
            solutionC.setText("");
            solutionD.setText("");
            finalSolution.setText("");



        timer = new CountDownTimer(120000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                time.setText(String.valueOf(secondsRemaining));
                long progress = secondsRemaining;
                progressBar.setMax(120);
                progressBar.setProgress((int) progress);
                setColors();
            }

            public void onFinish() {
                roundOver();

                }

        }.start();
        }

        private void roundOver() {
        if(round!=2) {
            round++;
            timer.cancel();
            StartRound();
        }
        else{
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.gameContainer, new SkockoFragment())
                    .addToBackStack(null)
                    .commit();
        }
        }


        public void openField(int column, int field, TextView textView) {
            openedFields[column][field] = true;
            textView.setEnabled(false);
            textView.setText(currentFields.get(column).get(field));

        }
        private boolean areAllFieldsOpen() {
            for (int i = 0; i < openedFields.length; i++) {
                for (int j = 0; j < openedFields[i].length; j++) {
                    if (!openedFields[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        private boolean isColumnOpen(int column) {
            for (int i = 0; i < openedFields.length; i++) {
                if (!openedFields[i][column]) {
                    return false;
                }
            }
            return true;
        }

    private List<Integer> getOpenColumns() {
        List<Integer> openColumns = new ArrayList<>();
        for (int column = 0; column < openedFields[0].length; column++) {
            boolean isColumnOpen = false;
            for (int row = 0; row < openedFields.length; row++) {
                if (openedFields[column][row]) {
                    isColumnOpen = true;
                    break;
                }
            }
            if (isColumnOpen) {
                openColumns.add(column);
            }
        }
        return openColumns;
    }

    public void guessColumnSolution(int column, String guess) {
            if (columnSolutions[column].equalsIgnoreCase(guess)) {
                int columnScore = 2;
                for (int i = 0; i < 4; i++) {
                    if (!openedFields[column][i]) {
                        columnScore++;
                    }

                    p1Score.setText(String.valueOf(score += columnScore));
                    finalSolution.setEnabled(true);
                    switch (column) {
                        case 0:
                            openField(column, 0, fieldA1);
                            openField(column, 1, fieldA2);
                            openField(column, 2, fieldA3);
                            openField(column, 3, fieldA4);
                            fieldA1.setEnabled(false);
                            fieldA2.setEnabled(false);
                            fieldA3.setEnabled(false);
                            fieldA4.setEnabled(false);


                            break;
                        case 1:
                            openField(column, 0, fieldB1);
                            openField(column, 1, fieldB2);
                            openField(column, 2, fieldB3);
                            openField(column, 3, fieldB4);
                            fieldB1.setEnabled(false);
                            fieldB2.setEnabled(false);
                            fieldB3.setEnabled(false);
                            fieldB4.setEnabled(false);


                            break;
                        case 2:
                            openField(column, 0, fieldC1);
                            openField(column, 1, fieldC2);
                            openField(column, 2, fieldC3);
                            openField(column, 3, fieldC4);
                            fieldC1.setEnabled(false);
                            fieldC2.setEnabled(false);
                            fieldC3.setEnabled(false);
                            fieldC4.setEnabled(false);

                            break;
                        case 3:
                            openField(column, 0, fieldD1);
                            openField(column, 1, fieldD2);
                            openField(column, 2, fieldD3);
                            openField(column, 3, fieldD4);
                            fieldD1.setEnabled(false);
                            fieldD2.setEnabled(false);
                            fieldD3.setEnabled(false);
                            fieldD4.setEnabled(false);


                            break;
                    }
                    solutionA.setEnabled(false);
                    solutionB.setEnabled(false);
                    solutionC.setEnabled(false);
                    solutionD.setEnabled(false);
                }
                Context context = getContext();
                finalSolution.setEnabled(true);


            } else {
                if(areAllFieldsOpen()==false) {
                    if(isColumnOpen(column)==true) {
                        if(column==0)
                            solutionA.setEnabled(true);
                        if(column==1)
                            solutionB.setEnabled(true);
                        if(column==2)
                            solutionC.setEnabled(true);
                        if(column==3)
                            solutionD.setEnabled(true);
                    }
                    else {
                        solutionA.setEnabled(false);
                        solutionB.setEnabled(false);
                        solutionC.setEnabled(false);
                        solutionD.setEnabled(false);
                    }
                }
                finalSolution.setEnabled(false);
            }
        }
        public void guessFinalSolution(String guess) {
            if (finalSolutionString.equalsIgnoreCase(guess)) {
                int finalScore = 7;
                for (int i = 0; i < 4; i++) {
                    int columnScore = 2;
                    for (int j = 0; j < 4; j++) {
                        if (!openedFields[i][j]) {
                            columnScore++;
                        }
                    }
                    finalScore += columnScore;

                }
                score += finalScore;
                p1Score.setText(String.valueOf(score));
                roundOver();
            } else {

                solutionA.setEnabled(false);
                solutionB.setEnabled(false);
                solutionC.setEnabled(false);
                solutionD.setEnabled(false);
                if(areAllFieldsOpen()==false)
                    finalSolution.setEnabled(false);
            }
        }
        public void setColors(){
        if(solutionA.isEnabled()==true)
        solutionA.setBackgroundColor(getResources().getColor(R.color.buttonAsocijacijeWriteNow, getContext().getTheme()));
        else
            solutionA.setBackgroundColor(getResources().getColor(R.color.buttonPrimary, getContext().getTheme()));
        if(solutionB.isEnabled()==true)
        solutionB.setBackgroundColor(getResources().getColor(R.color.buttonAsocijacijeWriteNow,getContext().getTheme()));
        else
            solutionB.setBackgroundColor(getResources().getColor(R.color.buttonPrimary, getContext().getTheme()));
        if(solutionC.isEnabled()==true)
        solutionC.setBackgroundColor(getResources().getColor(R.color.buttonAsocijacijeWriteNow,getContext().getTheme()));
        else
            solutionC.setBackgroundColor(getResources().getColor(R.color.buttonPrimary, getContext().getTheme()));
        if(solutionD.isEnabled()==true)
        solutionD.setBackgroundColor(getResources().getColor(R.color.buttonAsocijacijeWriteNow,getContext().getTheme()));
        else
            solutionD.setBackgroundColor(getResources().getColor(R.color.buttonPrimary, getContext().getTheme()));
        if(finalSolution.isEnabled()==true)
        finalSolution.setBackgroundColor(getResources().getColor(R.color.buttonAsocijacijeWriteNow,getContext().getTheme()));
        else
            finalSolution.setBackgroundColor(getResources().getColor(R.color.buttonPrimary, getContext().getTheme()));

        }
        public int getScore() {
            return score;
        }
        public void reset() {
            score = 0;
            openedFields = new boolean[4][4];
        }

    }

