package com.example.basicquizdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        Intent getFromQuiz = getIntent();
        String username = getFromQuiz.getStringExtra("username");
        int score = getFromQuiz.getIntExtra("score", 0);

        TextView yourName = findViewById(R.id.yourName);
        TextView yourScore = findViewById(R.id.yourScore);

        Button playAgain = findViewById(R.id.playAgain);
        Button exit = findViewById(R.id.exit);

        yourName.setText("Congratulations " + username);

        String scoreString = Integer.toString(score);
        yourScore.setText(scoreString +"/5");

        //exit quiz goes back to main activity, no data needs to be carried over
        Intent exitQuiz = new Intent(this, MainActivity.class);

        //bring the username back to quiz if play game is selected
        Intent playQuiz = new Intent(this, QuizPage.class);
        playQuiz.putExtra("username", username);


        //on click listeners that call the relevant intent
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(playQuiz);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(exitQuiz);
            }
        });

    }
}