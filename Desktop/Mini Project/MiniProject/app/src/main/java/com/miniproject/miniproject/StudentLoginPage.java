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

public class StudentLoginPage extends AppCompatActivity {

    //Declaring Data-types.

    EditText univRollInput,passwordInput;
    TextView studentCreateAccountBtn;
    Button studentLoginConfirmBtn;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth; //Google Console Based Data-type.
    FirebaseUser mUser; //Google Console Based Data-type.
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Student Login Page

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login_page);
        univRollInput = findViewById(R.id.univRollInput);
        passwordInput = findViewById(R.id.passwordInput);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(StudentLoginPage.this);
        studentCreateAccountBtn = findViewById(R.id.studentCreateAccountBtn);
        studentLoginConfirmBtn = findViewById(R.id.studentLoginConfirmBtn);
        studentCreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            //Button Activity when student needs to create a new account.

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),StudentCreateAccount.class);
                startActivity(i);
            }
        });
        studentLoginConfirmBtn.setOnClickListener(new View.OnClickListener() {

            //Button Activity when student enters username and password and presses login.

            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String univRollNo = univRollInput.getText().toString();
        String password = passwordInput.getText().toString();
        if(univRollNo.equals("")){

            //Shows error when username is empty.

            Toast.makeText(getApplicationContext(), "Username Cannot Be Empty !!", Toast.LENGTH_SHORT).show();
        }
        else if(Integer.parseInt(univRollNo) < 1901001 || Integer.parseInt(univRollNo) > 1901500){

            //Shows error if username entered is not available in database.

            Toast.makeText(getApplicationContext(), "University Roll No. does not belongs to any student !!", Toast.LENGTH_LONG).show();
            univRollInput.setText(null);
            passwordInput.setText(null);
        }
        else if(password.isEmpty() || password.length()<6){

            //Shows error if password is empty or is of less than 6 digits.

            Toast.makeText(getApplicationContext(), "Password should be longer than 6 characters !!", Toast.LENGTH_LONG).show();
            passwordInput.setText(null);
        }
        else {
            progressDialog.setMessage("Logging in...");
            progressDialog.setTitle("Log in");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(univRollNo+".student@gmail.com", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                //Passing username and password to Google Console for checking login validity.

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        //Sends user to the next activity if login is successful.

                        Toast.makeText(StudentLoginPage.this, "Login Successful", Toast.LENGTH_LONG).show();
                        sendUserToNextActivity();
                    }
                    else{

                        //Shows the appropriate error if login is unsuccessful.

                        progressDialog.dismiss();
                        Toast.makeText(StudentLoginPage.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        univRollInput.setText(null);
                        passwordInput.setText(null);
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent i = new Intent(this, ShowStudentDetails.class);
        String univRollNo = univRollInput.getText().toString();
        i.putExtra("uniroll",univRollNo); //University Roll No. is passed along the intent to the next activity.
        i.putExtra("from","Student"); //value passed to dignify the identity of parent page of the upcoming activity.
        progressDialog.dismiss();
        startActivity(i);
    }

}