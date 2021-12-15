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

public class StudentCreateAccount extends AppCompatActivity {

    //Declaring Data-types.

    FirebaseAuth mAuth; // Google Console Based Data-type.
    FirebaseUser mUser; // Google Console Based Data-type.
    EditText createunivroll,newpassword,newpasswordconfirm;
    Button createAccountConfirmBtn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Create Student Account Page.

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_create_account);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(StudentCreateAccount.this);
        progressDialog.setMessage("Registering Student...");
        progressDialog.setTitle("Register");
        progressDialog.setCanceledOnTouchOutside(false);
        createunivroll = findViewById(R.id.teacherID);
        newpassword = findViewById(R.id.teacherNewPassword);
        newpasswordconfirm = findViewById(R.id.newpasswordconfirm);
        createAccountConfirmBtn = findViewById(R.id.createAccountConfirmBtn);
        createAccountConfirmBtn.setOnClickListener(new View.OnClickListener() {

            //Button Activity when user enters all the details and presses Create Account.

            @Override
            public void onClick(View v) {
                registerStudent();
            }
        });
    }

    private void registerStudent() {
        String newUnivRoll = createunivroll.getText().toString();
        String newPasswordvar = newpassword.getText().toString();
        String newPasswordConfirmvar = newpasswordconfirm.getText().toString();
        if(newUnivRoll.equals("")){

            //Shows error if username is empty.

            Toast.makeText(getApplicationContext(), "Username Cannot Be Empty !!", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(newUnivRoll) < 1901001 || Integer.parseInt(newUnivRoll) > 1901500){

            //Shows error if username is not in database.

            Toast.makeText(getApplicationContext(), "University Roll No. Not Assigned To Any Student !!", Toast.LENGTH_LONG).show();
            createunivroll.setText(null);
        }
        else if(newPasswordvar.isEmpty() || newPasswordvar.length()<6){

            //Shows error if new password field is empty or has less than 6 characters.

            Toast.makeText(getApplicationContext(), "Password Should Be Longer Than 6 Characters !!", Toast.LENGTH_LONG).show();
            newpassword.setText(null);
            newpasswordconfirm.setText(null);
        }
        else if(!newPasswordvar.equals(newPasswordConfirmvar)){

            //Shows error if new password is not equal to confirm password field.

            Toast.makeText(getApplicationContext(), "Password mismatch !!", Toast.LENGTH_LONG).show();
            newpasswordconfirm.setText(null);
            newpassword.setText(null);
        }
        else{
            mAuth.createUserWithEmailAndPassword(newUnivRoll+".student@gmail.com", newPasswordvar).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                //Passing credentials for user registration.

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.show();
                    if(task.isSuccessful()){

                        //Sends user to login page if account creation is successful.

                        Log.e("mytag","working");
                        Toast.makeText(StudentCreateAccount.this, "Student Registered", Toast.LENGTH_LONG).show();
                        sendUserToLoginActivity();
                    }
                    else{

                        //Shows appropriate error if account creation is unsuccessful.

                        Toast.makeText(StudentCreateAccount.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        createunivroll.setText(null);
                        newpassword.setText(null);
                        newpasswordconfirm.setText(null);
                    }
                }
            });
        }
    }

    private void sendUserToLoginActivity() {
        Intent i = new Intent(getApplicationContext(), com.miniproject.miniproject.StudentLoginPage.class);
        progressDialog.dismiss();
        startActivity(i);
    }
}