package com.miniproject.miniproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherLoginPage extends AppCompatActivity {
    EditText teacherIDenter,teacherPassword;
    Button teacherLoginConfirmBtn;
    TextView teacherCreateNewAccountBtn;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login_page);
        teacherIDenter = findViewById(R.id.teacherIDenter);
        teacherPassword = findViewById(R.id.teacherPassword);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(TeacherLoginPage.this);
        teacherLoginConfirmBtn = findViewById(R.id.teacherLoginConfirmBtn);
        teacherCreateNewAccountBtn = findViewById(R.id.teacherCreateNewAccountBtn);
        teacherCreateNewAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TeacherCreateAccount.class);
                startActivity(i);
            }
        });
        teacherLoginConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String teacherID = teacherIDenter.getText().toString();
        String password = teacherPassword.getText().toString();
        if(teacherID.equals("")){
            Toast.makeText(getApplicationContext(), "Username Cannot Be Empty !!", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(teacherID) >= 1901001 && Integer.parseInt(teacherID) <= 2001000){
            Toast.makeText(getApplicationContext(), "Database Not Accessible", Toast.LENGTH_LONG).show();
            teacherIDenter.setText(null);
            teacherPassword.setText(null);
        }
        else if(password.isEmpty() || password.length()<6){
            Toast.makeText(getApplicationContext(), "Password should be longer than 6 characters! !!", Toast.LENGTH_LONG).show();
            teacherPassword.setText(null);
        }
        else {
            progressDialog.setMessage("Logging in...");
            progressDialog.setTitle("Log in");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(teacherID+".teacher@gmail.com", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(TeacherLoginPage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(TeacherLoginPage.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        teacherIDenter.setText(null);
                        teacherPassword.setText(null);
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent i = new Intent(this, TeacherInfoSelectionPage.class);
        progressDialog.dismiss();
        startActivity(i);
    }
}