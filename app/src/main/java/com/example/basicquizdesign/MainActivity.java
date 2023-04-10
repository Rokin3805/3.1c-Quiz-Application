package com.example.basicquizdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //Declare the two relevant elements on this page (Other elements cant be interacted with)
    Button startButton;
    EditText name;

    String enteredName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign the relevant values into these declared values
        startButton = findViewById(R.id.button);
        name = findViewById(R.id.name_input);
    }


    public void startQuiz(View view) {
        //get the inputted value and store in a string
        enteredName = name.getText().toString();
        //goes to next activity if name has been entered
        if(enteredName.length()>0)
        {
            //intent to move from this to quiz page
            Intent goToQuiz = new Intent(this, QuizPage.class);
            //put extra to bring the users inputted name (not necessary in quiz but must be carried forward to results page)
            goToQuiz.putExtra("username",enteredName);
            startActivity(goToQuiz);
        }
        //does nothing if name field is empty
        else {}

    }



}