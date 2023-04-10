package com.example.basicquizdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class QuizPage extends AppCompatActivity {
    //VARIABLES DECLARED HERE FOR SCOPE REASONS BUT ASSIGNED IN ON CREATE SO THEY ARE ALWAYS INSTANCED ON ACTIVITY CREATION

    //elements which are accessed by function outside of onCreate class scope
    Button answerOne;
    Button answerTwo;
    Button answerThree;


    //booleans for first correct answer (will be updated by method as quiz progresses)
    boolean oneCorrect;
    boolean twoCorrect;
    boolean threeCorrect;
    //booleans for which answer is selected (user changes by clicking answer)
    //My reasoning for this implementation is that it allows me to dynamically update a single activity for the whole quiz process, rather than create an activity for each question
    boolean answer1;
    boolean answer2;
    boolean answer3;

    //Booleans for behaviour of submit/continue button
    boolean ansSelected;
    boolean ansSubmitted;

    //variables for tracking quiz progress
    int question;
    int progressUpdater;
    int score;

    Button continueBtn;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);
        Intent getFromFirst = getIntent();
        String username = getFromFirst.getStringExtra("username");

        //Declared variables assigned within OnCreate
        oneCorrect = true;
        twoCorrect = false;
        threeCorrect = false;

        answer1 = false;
        answer2= false;
        answer3 = false;

        ansSelected = false;
        ansSubmitted = false;

        question = 1;
        progressUpdater = 20;
        score = 0;

        answerOne = findViewById(R.id.answerOne);
        answerTwo = findViewById(R.id.answerTwo);
        answerThree = findViewById(R.id.answerThree);

        continueBtn = findViewById(R.id.continueButton);

        progressBar = findViewById(R.id.progressBar);

        //Intent for end of quiz
        Intent goToScore = new Intent(this, ResultPage.class);

        //Method sets default button colour and resets it during quiz
        setButtonColour();



        //all on click listeners for answer buttons toggle relevant booleans to assess answer, and to indicate an answer has been selected
        answerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
               answer1 = true;
               answer2 = false;
               answer3 = false;
               ansSelected = true;
            }
        });

        answerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                answer1 = false;
                answer2 = true;
                answer3 = false;
                ansSelected = true;
            }
        });


        answerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                answer1 = false;
                answer2 = false;
                answer3 = true;
                ansSelected = true;
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if an answer has been selected
                if(ansSelected)
                {
                    //if answer has already been submitted
                    if(ansSubmitted)
                    {
                        //condition for all questions other than last
                        if(question < 5)
                        {
                            question++;
                            updateAnswer();
                            continueBtn.setText("Submit");
                            setButtonColour();
                        }
                        else
                        {

                            goToScore.putExtra("username", username);
                            goToScore.putExtra("score", score);
                            startActivity(goToScore);
                        }
                    }
                    //if an answer is selected but not yet submitted
                    else
                    {
                        //call check answers method, then change button from submit to continue, and update the progress bar
                        checkAnswers();
                        continueBtn.setText("Continue");
                        progressBar.setProgress(progressUpdater);
                        progressUpdater += 20;
                        ansSubmitted = true;

                    }
                }
                //Nothing happens if so answer is selected for submission
                else{}
            }
        });


    }

    //method to check users answer against actual answer
    public void checkAnswers()
    {
        // Disable all buttons to prevent answer changing after submission
        answerOne.setEnabled(false);
        answerTwo.setEnabled(false);
        answerThree.setEnabled(false);

        // Check if the selected answer is correct and colour buttons accordingly (iterates over each button), and update score
        if (oneCorrect)
        {
            answerOne.setBackgroundColor(Color.GREEN);
            if(answer1)
            {
                score++;
            }
        }
        else if(answer1)
        {
            answerOne.setBackgroundColor(Color.RED);
        }
        if (twoCorrect)
        {
            answerTwo.setBackgroundColor(Color.GREEN);
            if(answer2)
            {
                score++;
            }
        }
        else if(answer2)
        {
            answerTwo.setBackgroundColor(Color.RED);
        }

        if (threeCorrect)
        {
            answerThree.setBackgroundColor(Color.GREEN);
            if(answer3)
            {
                score++;
            }
        }
        else if(answer3)
        {
            answerThree.setBackgroundColor(Color.RED);
        }

    }

    //method to progress the quiz
    public void updateAnswer()
    {
        //resets booleans and restores button functionality
        ansSelected = false;
        ansSubmitted = false;
        answerOne.setEnabled(true);
        answerTwo.setEnabled(true);
        answerThree.setEnabled(true);


        //different answer associated with each question #
        if (question == 2)
        {
            oneCorrect = false;
            twoCorrect = true;
            threeCorrect = false;
        }
        else if (question == 3)
        {
            oneCorrect = false;
            twoCorrect = false;
            threeCorrect = true;
        }
        else if (question == 4)
        {
            oneCorrect = false;
            twoCorrect = true;
            threeCorrect = false;
        }
        else if (question == 5)
        {
            oneCorrect = true;
            twoCorrect = false;
            threeCorrect = false;
        }
    }

    public void setButtonColour()
    {
        answerOne.setBackgroundColor(Color.rgb(255,255,255));
        answerOne.setTextColor(Color.BLACK);
        answerTwo.setBackgroundColor(Color.rgb(255,255,255));
        answerTwo.setTextColor(Color.BLACK);
        answerThree.setBackgroundColor(Color.rgb(255,255,255));
        answerThree.setTextColor(Color.BLACK);
    }



}
