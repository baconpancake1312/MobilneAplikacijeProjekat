package com.sr_17_2021.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sr_17_2021.R;

import java.util.Arrays;
import java.util.Random;
public class SkockoFragment extends Fragment implements View.OnClickListener {
    //TODO:
    // add 2 player compability
    //  add 2 rounds
    //
    //{'💎', '⬛', '⚫', '♥','🔺', '⭐'};
    private final char[] characters = {'1', '2', '3', '4', '5', '6'};
    //six combinations
    private char[] combination = new char[4];
    private char[] guessedCombination = new char[4];
    private int guessedCombinationIndex = -1;
    private int tries;
    private int score;
    private boolean gameOver;
    private boolean playerOneTurn;
    private ImageButton[] buttons;
    private ImageButton[][] rows;
    private View[][] hints;
    private TextView timerTextView;
    private TextView scoreTextView;
    private CountDownTimer timer;
    private ProgressBar progressBar;
    private int round=1;
    ImageButton springButton;
    ImageButton heartButton;
    ImageButton circleButton;
    ImageButton squareButton;
    ImageButton triangleButton;
    ImageButton starButton;
    ImageButton solution1;
    ImageButton solution2;
    ImageButton solution3;
    ImageButton solution4;

    ImageButton row11;
    ImageButton row12;
    ImageButton row13;
    ImageButton row14;

    ImageButton row21;
    ImageButton row22;
    ImageButton row23;
    ImageButton row24;

    ImageButton row31;
    ImageButton row32;
    ImageButton row33;
    ImageButton row34;

    ImageButton row41;
    ImageButton row42;
    ImageButton row43;
    ImageButton row44;

    ImageButton row51;
    ImageButton row52;
    ImageButton row53;
    ImageButton row54;

    ImageButton row61;
    ImageButton row62;
    ImageButton row63;
    ImageButton row64;

    View row1H1;
    View row1H2;
    View row1H3;
    View row1H4;

    View row2H1;
    View row2H2;
    View row2H3;
    View row2H4;

    View row3H1;
    View row3H2;
    View row3H3;
    View row3H4;

    View row4H1;
    View row4H2;
    View row4H3;
    View row4H4;

    View row5H1;
    View row5H2;
    View row5H3;
    View row5H4;

    View row6H1;
    View row6H2;
    View row6H3;
    View row6H4;



    int displayRow;
    int displayColumn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skocko, container, false);
        displayRow = 0;
        displayColumn = -1;
        score=0;
        guessedCombination = new char[4];
        progressBar = view.findViewById(R.id.skockoProgressBar);
        springButton = view.findViewById(R.id.spring);
        heartButton = view.findViewById(R.id.heart);
        circleButton = view.findViewById(R.id.circle);
        squareButton = view.findViewById(R.id.square);
        triangleButton = view.findViewById(R.id.triangle);
        starButton = view.findViewById(R.id.star);

        solution1 = view.findViewById(R.id.solution1);
        solution2 = view.findViewById(R.id.solution2);
        solution3 = view.findViewById(R.id.solution3);
        solution4 = view.findViewById(R.id.solution4);

        row11 = view.findViewById(R.id.row11);
        row12 = view.findViewById(R.id.row12);
        row13 = view.findViewById(R.id.row13);
        row14 = view.findViewById(R.id.row14);

        row21 = view.findViewById(R.id.row21);
        row22 = view.findViewById(R.id.row22);
        row23 = view.findViewById(R.id.row23);
        row24 = view.findViewById(R.id.row24);

        row31 = view.findViewById(R.id.row31);
        row32 = view.findViewById(R.id.row32);
        row33 = view.findViewById(R.id.row33);
        row34 = view.findViewById(R.id.row34);

        row41 = view.findViewById(R.id.row41);
        row42 = view.findViewById(R.id.row42);
        row43 = view.findViewById(R.id.row43);
        row44 = view.findViewById(R.id.row44);

        row51 = view.findViewById(R.id.row51);
        row52 = view.findViewById(R.id.row52);
        row53 = view.findViewById(R.id.row53);
        row54 = view.findViewById(R.id.row54);

        row61 = view.findViewById(R.id.row61);
        row62 = view.findViewById(R.id.row62);
        row63 = view.findViewById(R.id.row63);
        row64 = view.findViewById(R.id.row64);

        rows = new ImageButton[6][4];
        rows[0][0] = row11;
        rows[0][1] = row12;
        rows[0][2] = row13;
        rows[0][3] = row14;
        rows[1][0] = row21;
        rows[1][1] = row22;
        rows[1][2] = row23;
        rows[1][3] = row24;
        rows[2][0] = row31;
        rows[2][1] = row32;
        rows[2][2] = row33;
        rows[2][3] = row34;
        rows[3][0] = row41;
        rows[3][1] = row42;
        rows[3][2] = row43;
        rows[3][3] = row44;
        rows[4][0] = row51;
        rows[4][1] = row52;
        rows[4][2] = row53;
        rows[4][3] = row54;
        rows[5][0] = row61;
        rows[5][1] = row62;
        rows[5][2] = row63;
        rows[5][3] = row64;

        row1H1 = view.findViewById(R.id.row1Hint1);
        row1H2 = view.findViewById(R.id.row1Hint2);
        row1H3 = view.findViewById(R.id.row1Hint3);
        row1H4 = view.findViewById(R.id.row1Hint4);

        row2H1 = view.findViewById(R.id.row2Hint1);
        row2H2 = view.findViewById(R.id.row2Hint2);
        row2H3 = view.findViewById(R.id.row2Hint3);
        row2H4 = view.findViewById(R.id.row2Hint4);

        row3H1 = view.findViewById(R.id.row3Hint1);
        row3H2 = view.findViewById(R.id.row3Hint2);
        row3H3 = view.findViewById(R.id.row3Hint3);
        row3H4 = view.findViewById(R.id.row3Hint4);

        row4H1 = view.findViewById(R.id.row4Hint1);
        row4H2 = view.findViewById(R.id.row4Hint2);
        row4H3 = view.findViewById(R.id.row4Hint3);
        row4H4 = view.findViewById(R.id.row4Hint4);

        row5H1 = view.findViewById(R.id.row5Hint1);
        row5H2 = view.findViewById(R.id.row5Hint2);
        row5H3 = view.findViewById(R.id.row5Hint3);
        row5H4 = view.findViewById(R.id.row5Hint4);

        row6H1 = view.findViewById(R.id.row6Hint1);
        row6H2 = view.findViewById(R.id.row6Hint2);
        row6H3 = view.findViewById(R.id.row6Hint3);
        row6H4 = view.findViewById(R.id.row6Hint4);

        hints = new View[6][4];
        hints[0][0] = row1H1;
        hints[0][1] = row1H2;
        hints[0][2] = row1H3;
        hints[0][3] = row1H4;
        hints[1][0] = row2H1;
        hints[1][1] = row2H2;
        hints[1][2] = row2H3;
        hints[1][3] = row2H4;
        hints[2][0] = row3H1;
        hints[2][1] = row3H2;
        hints[2][2] = row3H3;
        hints[2][3] = row3H4;
        hints[3][0] = row4H1;
        hints[3][1] = row4H2;
        hints[3][2] = row4H3;
        hints[3][3] = row4H4;
        hints[4][0] = row5H1;
        hints[4][1] = row5H2;
        hints[4][2] = row5H3;
        hints[4][3] = row5H4;
        hints[5][0] = row6H1;
        hints[5][1] = row6H2;
        hints[5][2] = row6H3;
        hints[5][3] = row6H4;

        buttons = new ImageButton[6];
        buttons[0] = springButton;
        buttons[1] = heartButton;
        buttons[2] = circleButton;
        buttons[3] = squareButton;
        buttons[4] = triangleButton;
        buttons[5] = starButton;

        for (ImageButton button : buttons) {
            button.setOnClickListener(this);
        }
        timerTextView = view.findViewById(R.id.skockoTime);
        scoreTextView = view.findViewById(R.id.skockoP1Points);
        startGame();
        return view;
    }

    private void startGame() {
        for (ImageButton button : buttons) {
            button.setClickable(false);
        }

        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Start:"+(millisUntilFinished / 1000));
                progressBar.setMax(5);
                long progress = millisUntilFinished / 1000;
                progressBar.setProgress((int) progress);
            }

            @Override
            public void onFinish() {
                //enable buttons
                for (ImageButton button : buttons) {
                    button.setClickable(true);
                }
                displayColumn = -1;
                displayRow = 0;
                guessedCombinationIndex = -1;
                tries = 0;
                score = 0;
                gameOver = false;
                playerOneTurn = true;

                Random random = new Random();
                for (int i = 0; i < combination.length; i++) {
                    int index = random.nextInt(characters.length);
                    combination[i] = characters[index];
                }
                //set image of all rows to null
                for (int i = 0; i < rows.length; i++) {
                    for (int j = 0; j < rows[i].length; j++) {
                        rows[i][j].setImageResource(0);
                    }
                }
                //set image of all hints to black circle
                for (int i = 0; i < hints.length; i++) {
                    for (int j = 0; j < hints[i].length; j++) {
                        hints[i][j].setBackgroundResource(R.drawable.black_circle);
                    }
                }
                solution1.setImageResource(0);
                solution2.setImageResource(0);
                solution3.setImageResource(0);
                solution4.setImageResource(0);

                timer = new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerTextView.setText("Time: " + millisUntilFinished / 1000);
                        progressBar.setMax(30);
                        long progress = millisUntilFinished / 1000;
                        progressBar.setProgress((int) progress);
                    }

                    @Override
                    public void onFinish() {
                        round++;
                        if (round > 2) {
                            gameOver = true;
                            timer.cancel();
                        } else {
                            timer.cancel();
                            startGame();
                        }
                    }
                }.start();
            }
        }.start();
    }

    private void endRound() {
//        if (playerOneTurn) {
//            playerOneTurn = false;
            timer = new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerTextView.setText("Time: " + millisUntilFinished / 1000);
                    progressBar.setMax(30);
                    long progress = millisUntilFinished / 1000;
                    progressBar.setProgress((int) progress);
                }

                @Override
                public void onFinish() {
                    timerTextView.setText("Time's up!");
                    if (!gameOver) {
                        endRound();
                    }
                }
            }.start();
//        } else {
//            if (tries <= 2) {
//                score = 20;
//            } else if (tries <= 4) {
//                score = 15;
//            } else {
//                score = 10;
//            }
//            scoreTextView.setText("Score: " + score);
//            timer.cancel();
//        }
    }

    private void displayInputCombination(View v) {
        ImageButton tempButton = (ImageButton) v;
        displayColumn++;
        if (displayColumn == 4) {
            displayRow++;
            displayColumn = 0;
        }
        if (displayRow == 6) {
            displayRow = 0;
            for (int i = 0; i < 5; i++) {
                rows[i][0].setImageDrawable(null);
                rows[i][1].setImageDrawable(null);
                rows[i][2].setImageDrawable(null);
                rows[i][3].setImageDrawable(null);
            }
        }
        rows[displayRow][displayColumn].setImageDrawable(tempButton.getBackground());
    }

    private void displaySolution() {
        solution1.setImageDrawable(numberToImage(combination[0]));
        solution2.setImageDrawable(numberToImage(combination[1]));
        solution3.setImageDrawable(numberToImage(combination[2]));
        solution4.setImageDrawable(numberToImage(combination[3]));
    }
    private void displayHint(int row) {
        for (int i = 0; i < combination.length; i++) {

                if (combination[i] == guessedCombination[i]) {

                    // same element in same place
                    hints[row][i].setBackgroundResource(R.drawable.red_circle);

                } else {
                    for (int j = 0; j < guessedCombination.length; j++) {
                    // same element in different place
                    if (combination[i] == guessedCombination[j] && i != j)
                        hints[row][i].setBackgroundResource(R.drawable.yellow_circle);
                }
            }
        }
    }


    private Drawable numberToImage(char number) {
        switch (number) {
            case '1':                          //{'💎', '⬛', '⚫', '♥','🔺', '⭐'};
                return getResources().getDrawable(R.drawable.spring, null);
            case '2':
                return getResources().getDrawable(R.drawable.square, null);
            case '3':
                return getResources().getDrawable(R.drawable.circle, null);
            case '4':
                return getResources().getDrawable(R.drawable.heart, null);
            case '5':
                return getResources().getDrawable(R.drawable.triangle, null);
            case '6':
                return getResources().getDrawable(R.drawable.star, null);

        }
        return null;
    }


    private void guessCombination(String guess) {
        if (gameOver) {
            return;
        }
        guessedCombinationIndex++;

        guessedCombination[guessedCombinationIndex] =guess.charAt(0);
        if (guessedCombinationIndex != 3) {
            return;
        }
        else {
            tries++;
            displayHint(displayRow);
            if (Arrays.equals(guessedCombination,combination)) {
                if (tries <= 2) {
                    score = 20;
                } else if (tries <= 4) {
                    score = 15;
                } else {
                    score = 10;
                }
                displaySolution();

                scoreTextView.setText(String.valueOf(score));
                    round++;
                if (round > 2) {
                    gameOver=true;
                    return;
                }
                else{
                    timer.cancel();
                    startGame();
                }
            }
            if (tries == 6) {
                displaySolution();
            }
            guessedCombinationIndex=-1;
            for (int i = 0; i < guessedCombination.length; i++) {
                guessedCombination[i] = '\0';
            }

            }

        }

    //            } else {
//                if (playerOneTurn) {
//                    playerOneTurn = false;
//                    scoreTextView.setText("Score: 0");
//                    timer = new CountDownTimer(30000, 1000) {
//                        @Override
//                        public void onTick(long millisUntilFinished) {
//                            timerTextView.setText("Time: " + millisUntilFinished / 1000);
//                        }
//
//                        @Override
//                        public void onFinish() {
//                            timerTextView.setText("Time's up!");
//                            if (!gameOver) {
//                                endRound();
//                            }
//                        }
//                    }.start();
//                } else {
//                    score = 10;
//                    scoreTextView.setText("Score: " + score);
//                    gameOver = true;
//                    timer.cancel();
//                }
    @Override
    public void onClick(@NonNull View v) {

        displayInputCombination(v);
        if (v.getId()==R.id.spring) {  //{'💎', '⬛', '⚫', '♥','🔺', '⭐'};
            guessCombination("1");
        }
        if (v.getId()==R.id.square) {
            guessCombination("2");
        }
        if (v.getId()==R.id.circle) {
            guessCombination("3");
        }
        if (v.getId()==R.id.heart) {
            guessCombination("4");
        }
        if (v.getId()==R.id.triangle) {
            guessCombination("5");
        }
        if (v.getId()==R.id.star) {
            guessCombination("6");

        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        timer.cancel();
    }
}