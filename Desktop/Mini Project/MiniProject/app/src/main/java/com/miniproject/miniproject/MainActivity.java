package com.miniproject.miniproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

//Launch Page or we can say page that appears when app opens(launches).

public class MainActivity extends AppCompatActivity {
    Button studentLoginBtn, teacherLoginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentLoginBtn = findViewById(R.id.studentLoginBtn);
        teacherLoginBtn = findViewById(R.id.teacherLoginBtn);
        studentLoginBtn.setOnClickListener(new View.OnClickListener() {

            //Button Activity when student needs to login.

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StudentLoginPage.class);
                startActivity(i);
            }
        });
        teacherLoginBtn.setOnClickListener(new View.OnClickListener() {

            //Button activity when teacher needs to login.

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TeacherLoginPage.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        deleteDatabase("studDatabase");
    }
}