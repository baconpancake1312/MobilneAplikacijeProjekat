package com.sr_17_2021.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sr_17_2021.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KorakPoKorakFragment} factory method to
 * create an instance of this fragment.
 */
public class KorakPoKorakFragment extends Fragment {
    public KorakPoKorakFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     */
    // TODO: Rename and change types and number of parameters

    private int currentStep; // current step of the game
    String currentTerm;
    EditText currentTermText;
    private int playerScore;
    private TextView playerScoreText;
    private int opponentScore;
    private TextView opponentScoreText;
    private CountDownTimer timer;

    private ArrayList<TextView> hintObjects;


    private int currentRound =1;
    private final String[][] termsAndHints = {
            {"term1", "hint1", "hint2", "hint3", "hint4", "hint5", "hint6", "hint7"},
            {"term2", "hint11", "hint22", "hint33", "hint44", "hint55", "hint66", "hint77"}
    };

    private TextView hint1;
    private TextView hint2;
    private TextView hint3;
    private TextView hint4;
    private TextView hint5;
    private TextView hint6;
    private TextView hint7;
    private TextView time;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (container == null) {
            return null;
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_korak_po_korak, container, false);
        view.setFocusable(true);
        view.setClickable(true);
        // initialize variables
        playerScore=0;
        opponentScore=0;
        playerScoreText = view.findViewById(R.id.asocijacijeP1Points);
        opponentScoreText = view.findViewById(R.id.asocijacijeP2Points);
        playerScoreText.setText(String.valueOf(playerScore));
        opponentScoreText.setText(String.valueOf(opponentScore));

        currentTermText = view.findViewById(R.id.solution);
        currentTermText.setText("");



        hint1 = view.findViewById(R.id.hint1);
        hint2 = view.findViewById(R.id.hint2);
        hint3 = view.findViewById(R.id.hint3);
        hint4 = view.findViewById(R.id.hint4);
        hint5 = view.findViewById(R.id.hint5);
        hint6 = view.findViewById(R.id.hint6);
        hint7 = view.findViewById(R.id.hint7);

        hintObjects = new ArrayList<>();
        hintObjects.add(hint1);
        hintObjects.add(hint2);
        hintObjects.add(hint3);
        hintObjects.add(hint4);
        hintObjects.add(hint5);
        hintObjects.add(hint6);
        hintObjects.add(hint7);

        for(TextView hint : hintObjects){
            if(hint != null)
                hint.setVisibility(View.INVISIBLE);
        }


        progressBar = view.findViewById(R.id.asocijacijeProgressBar);
        time = view.findViewById(R.id.asocijacijeTime);
        currentStep = 1;
        playerScore = 0;
        opponentScore = 0;
        // start the game
        startStep();
        return view;
    }
    private void startStep() {

        currentTerm = termsAndHints[currentRound - 1][0];
        for(int j = 1; j < termsAndHints[currentRound - 1].length; j++) {
            hintObjects.get(j-1).setText(termsAndHints[currentRound - 1][j]);
        }

        hintObjects.get(currentStep - 1).setVisibility(View.VISIBLE);
        timer = new CountDownTimer(11000, 1000) {
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                time.setText(String.valueOf(secondsRemaining));
                progressBar.setMax(10);
                long progress = secondsRemaining;
                progressBar.setProgress((int) progress);
            }

            public void onFinish() {
                // times up
                if(Guess(currentTermText.getText().toString())==false) {

                    if (currentStep == 7) {
                        if (currentRound < 2) {
                            currentRound++;
                            startStep();
                        }
                        else
                        {
                            endGame();
                        }
                    } else {
                        currentStep++;
                        startStep();
                    }
                }
            }
        }.start();

        currentTermText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    v.setFocusable(true);
                    v.setClickable(true);
                    if(Guess(currentTermText.getText().toString())==true);
                    return true;
                }
                return false;
            }
        });
    }

    public boolean Guess(String guess) {
        if (guess.equalsIgnoreCase(currentTerm)) {

            playerScore += 20 - 2 * (currentStep - 1);
            playerScoreText.setText(String.valueOf(playerScore));

            hintObjects.get(1).setVisibility(View.INVISIBLE);
            hintObjects.get(2).setVisibility(View.INVISIBLE);
            hintObjects.get(3).setVisibility(View.INVISIBLE);
            hintObjects.get(4).setVisibility(View.INVISIBLE);
            hintObjects.get(5).setVisibility(View.INVISIBLE);
            hintObjects.get(6).setVisibility(View.INVISIBLE);

            timer.cancel();
            if(currentRound == 2){
                endGame();
            }
            else {
                currentRound++;
                currentStep = 1;
                startStep();
                return true;
            }
           return false;
        }

        return false;
    }
    public void endGame(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.gameContainer, new AsocijacijeFragment()).commit();
    }
}