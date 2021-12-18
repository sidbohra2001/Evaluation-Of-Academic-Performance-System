package com.miniproject.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TeacherInfoSelectionPage extends AppCompatActivity {

    //Declaring Data-types.

    Spinner yearSelection, secSelection; //Drop down box data-type.
    TextView allStudent;
    Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Page to selected which student details are to be viewed to the teacher.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info_selection_page);
        yearSelection = findViewById(R.id.yearSelection);
        secSelection = findViewById(R.id.secSelection);
        allStudent = findViewById(R.id.allStudent);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {

            //Button Activity when user selects the required year and section and presses SUBMIT.

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TeacherInfoSelectionPage.this, TeacherStudentView.class);
                intent.putExtra("year",yearSelection.getSelectedItem().toString()); //Year is passed to the next page.
                intent.putExtra("sec",secSelection.getSelectedItem().toString()); //Section is passed to the next page.
                startActivity(intent);
            }

        });

        allStudent.setOnClickListener(new View.OnClickListener() {

            //Button Activity when user wants to see List of all the students at once.

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TeacherInfoSelectionPage.this, TeacherStudentView.class);
                intent.putExtra("year",""); //Year is passed to the next page.
                intent.putExtra("sec",""); //Section is passed to the next page.
                startActivity(intent);
            }

        });
    }
}