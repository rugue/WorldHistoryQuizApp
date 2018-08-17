package com.example.android.worldhistoryquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    int percentageScore;
    private boolean isSubmited = false;

    /**
     * This method calculates the scores for each questions.
     * For each question answered 20 percent is awarded
     *
     * @return total of the scores as "percentageScore"
     */
    public int calculatePercentage() {
        //Radio Group for Question 1
        RadioGroup question1 = findViewById(R.id.radio_group_1);
        if (question1.getCheckedRadioButtonId() == R.id.answer1_3_correct) {
            percentageScore += 20;
        }

        //CheckBox for Question 2
        CheckBox CheckBoxQuestion2 = findViewById(R.id.answer2_2_correct);
        CheckBox CheckBoxQuestion4 = findViewById(R.id.answer2_4_correct);
        if (CheckBoxQuestion2.isChecked() && CheckBoxQuestion4.isChecked()) {
            percentageScore += 20;
        } else if (CheckBoxQuestion2.isChecked() || CheckBoxQuestion4.isChecked()) {
            percentageScore += 10;
        }

        //Radio Group for Question 3
        RadioGroup question3 = findViewById(R.id.radio_group_3);
        if (question3.getCheckedRadioButtonId() == R.id.answer3_2_correct) {
            percentageScore += 20;
        }

        // EditText for question 4. If the answered typed is equal to the right answer, it means 20 more percent to the percentageScore
        EditText answerQuestionFour = findViewById(R.id.answer4);
        String answerForQuestionFour = answerQuestionFour.getText().toString();
        if (answerForQuestionFour.equals("Egypt")) {
            percentageScore += 20;
        }

        //Radio Group for Question 5
        RadioGroup question5 = findViewById(R.id.radio_group_5);
        if (question5.getCheckedRadioButtonId() == R.id.answer5_4_correct) {
            percentageScore += 20;
        }

        return percentageScore;
    }


    /**
     * This method is called when the submit button is clicked
     */
    public void submit(View view) {

        //Inputs calculatePercentage method into calculatePercentage variable
        int calculatePercentage = calculatePercentage();
        //Inputs the Phrase "Answer: Egypt" into the variable correctNameOfCountry for question 4
        String correctNameOfCountry = "Answer: Egypt";

        //Retrieves the value of name typed by the user in the nameField
        EditText nameField = findViewById(R.id.your_name);
        String valueOfName = nameField.getText().toString();

        //Makes the Submit button disappears when the button is clicked
        Button submit = findViewById(R.id.view_results);
        submit.setVisibility(View.GONE);

        //Makes the Reset button display when the Submit button is clicked, hence the Submit button automatically changes to Reset button
        Button reset = findViewById(R.id.reset);
        reset.setVisibility(View.VISIBLE);

        //This if-statement prevents the Submit button from updating the score when it is clicked more than once.
        if (isSubmited) {
            return;
        }
        isSubmited = true;


        // This helps indicates the correct answers by changing their color to green
        TextView TxtColorChange1 = findViewById(R.id.answer1_3_correct);
        TextView TxtColorChange2a = findViewById(R.id.answer2_2_correct);
        TextView TxtColorChange2b = findViewById(R.id.answer2_4_correct);
        TextView TxtColorChange3 = findViewById(R.id.answer3_2_correct);
        TextView TxtColorChange5 = findViewById(R.id.answer5_4_correct);
        TxtColorChange1.setTextColor(Color.GREEN);
        TxtColorChange2a.setTextColor(Color.GREEN);
        TxtColorChange2b.setTextColor(Color.GREEN);
        TxtColorChange3.setTextColor(Color.GREEN);
        TxtColorChange5.setTextColor(Color.GREEN);


        // Displays scores and grading message as a toast
        if (calculatePercentage >= 90) {
            Toast.makeText(this, "EXCELENTE! \nYour Score is " + calculatePercentage + "%", Toast.LENGTH_LONG).show();
        } else if (calculatePercentage >= 60) {
            Toast.makeText(this, "VERY GOOD! \nYour Score is " + calculatePercentage + "%", Toast.LENGTH_LONG).show();
        } else if (calculatePercentage >= 40) {
            Toast.makeText(this, "OK! \nYour Score is " + calculatePercentage + "%", Toast.LENGTH_LONG).show();
        } else if (calculatePercentage >= 20) {
            Toast.makeText(this, "NOT GOOD, TRY AGAIN! \nYour Score is " + calculatePercentage + "%", Toast.LENGTH_LONG).show();
        } else if (calculatePercentage >= 0) {
            Toast.makeText(this, "VERY POOR, TRY AGAIN! \nYour Score is " + calculatePercentage + "%", Toast.LENGTH_LONG).show();
        }

        //Shows the value of name and displays message to the user indicating the right answers are now coloured green
        displayMessage("Hello " + valueOfName + ". Check for the correct answers, they are now coloured green");

        //Calls the displayEgypt method to display the Correct answer for Question 4
        //If the correct answer "Egypt" is typed by the user, it displays the text "You are right!"
        // else it displays the value of correctNameOfCountry which is "Answer: Egypt"
        EditText answerQuestionFour = findViewById(R.id.answer4);
        String answerOfQuestionFour = answerQuestionFour.getText().toString();
        if (answerOfQuestionFour.equals("Egypt")) {
            displayEgypt("You were correct!");
        } else {
            displayEgypt(correctNameOfCountry);
        }

    }

    /**
     * This method displays the correct answer to Question 4
     */
    private void displayEgypt(String countryName) {
        TextView displayEgypt = findViewById(R.id.display_egypt);
        displayEgypt.setText(countryName);
    }

    /**
     * This method displays a message through a TextView on top of the Submit/Reset button
     */
    private void displayMessage(String message) {
        TextView scoreTotal = findViewById(R.id.score);
        scoreTotal.setText(message);
    }

    /**
     * This method takes us again to the MainActivity class when the reset button is clicked
     */
    public void reset_button(View view) {
        finish();
    }
}
