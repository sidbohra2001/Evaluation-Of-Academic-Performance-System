package com.miniproject.miniproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TeacherCreateAccount extends AppCompatActivity {

    //Declaring Data-types.

    EditText teacherID,teacherNewPassword,teacherNewPasswordConfirm;
    FirebaseAuth mAuth; //Google Console Based Data-type.
    FirebaseUser mUser; //Google Console Based Data-type.
    Button teacherCreateAccountBtn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create Teacher Account Page.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create_account);
        teacherID = findViewById(R.id.teacherID);
        teacherNewPassword = findViewById(R.id.teacherNewPassword);
        teacherNewPasswordConfirm = findViewById(R.id.teacherNewPasswordConfirm);
        teacherCreateAccountBtn = findViewById(R.id.teacherCreateAccountBtn);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(TeacherCreateAccount.this);
        progressDialog.setMessage("Registering Student...");
        progressDialog.setTitle("Register");
        progressDialog.setCanceledOnTouchOutside(false);
        teacherCreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            //Button Activity when user fills all credentials and presses create account.

            @Override
            public void onClick(View v) {
                registerTeacher();
            }
        });
    }

    private void registerTeacher() {
        String newTeacherId = teacherID.getText().toString();
        String newTeacherNewPassword = teacherNewPassword.getText().toString();
        String newTeacherNewPasswordConfirm = teacherNewPasswordConfirm.getText().toString();
        if(newTeacherId.equals("")){

            //Shows error if username is empty.

            Toast.makeText(getApplicationContext(), "Username Cannot Be Empty !!", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(newTeacherId) < 2001001){

            //Shows error if teacher ID entered is less than 2001000.

            Toast.makeText(getApplicationContext(), "Teacher ID Should Not Be Below 2001000 !!", Toast.LENGTH_LONG).show();
            teacherID.setText(null);
        }
        else if(newTeacherNewPassword.isEmpty() || newTeacherNewPassword.length()<6){

            //Shows error if new password field is either empty or has less than 6 characters.

            Toast.makeText(getApplicationContext(), "Password should be longer than 6 characters !!", Toast.LENGTH_LONG).show();
            teacherNewPassword.setText(null);
            teacherNewPasswordConfirm.setText(null);
        }
        else if(!newTeacherNewPassword.equals(newTeacherNewPasswordConfirm)){

            //Shows error if confirm password is not equal to new password.

            Toast.makeText(getApplicationContext(), "Confirm Password Mismatch", Toast.LENGTH_SHORT).show();
            teacherNewPasswordConfirm.setText(null);
            teacherNewPassword.setText(null);
        }
        else{
            mAuth.createUserWithEmailAndPassword(newTeacherId+".teacher@gmail.com", newTeacherNewPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.show();
                    if(task.isSuccessful()){

                        //Takes user to login page if account creation is successful.

                        Log.e("mytag","working");
                        Toast.makeText(TeacherCreateAccount.this, "Teacher Registered", Toast.LENGTH_LONG).show();
                        sendUserToLoginActivity();
                    }
                    else{

                        //Shows appropriate message if account creation is unsuccessful.

                        Toast.makeText(TeacherCreateAccount.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        teacherID.setText(null);
                        teacherNewPassword.setText(null);
                        teacherNewPasswordConfirm.setText(null);
                    }
                }
            });
        }
    }

    private void sendUserToLoginActivity() {
        Intent i = new Intent(getApplicationContext(), com.miniproject.miniproject.TeacherLoginPage.class);
        progressDialog.dismiss();
        startActivity(i);
    }
}