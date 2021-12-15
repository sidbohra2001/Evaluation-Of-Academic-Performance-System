package com.miniproject.miniproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TeacherStudentView extends AppCompatActivity {

    //Declaring Data-types.

    RecyclerView recyclerView; //A list view which has a specific number of rows which refreshes and updates itself whenever the screen is scrolled.
    SQLConvert sqlc; //Object of class SQLConvert.

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Page to show list of students.

        super.onCreate(savedInstanceState);
        String sec, year;
        ArrayList<StudentInfo> arrayList; //Declaring an ArrayList.
        sec = getIntent().getExtras().getString("sec"); //Fetching section from the previous page.
        year = getIntent().getExtras().getString("year"); //Fetching year from the previous page.
        setContentView(R.layout.activity_teacher_student_view);
        recyclerView = findViewById(R.id.recycler_view);
        readStudentDetails();
        SQLConvert teachersql = new SQLConvert(TeacherStudentView.this, "studDatabase", null, 1);
        if(sec.equals("") && year.equals("")){
            arrayList = teachersql.sinfo3();
        }
        else{
            arrayList = teachersql.sinfo2(year,sec);
        }
        Log.e("mytag1", String.valueOf(arrayList.size()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LogsAdapter logsAdapter = new LogsAdapter(arrayList);
        recyclerView.setAdapter(logsAdapter);
    }
    protected void readStudentDetails() {

        //Reads and sends data to the database from students.csv file

        InputStream is = getResources().openRawResource(R.raw.students);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        sqlc = new SQLConvert(TeacherStudentView.this, "studDatabase", null, 1);
        String line = "";
        try{
            while((line = reader.readLine())!=null){
                String[] tokens = line.split(",");
                StudentInfo sinfo = new StudentInfo();
                sinfo.setUnivRollNo(tokens[0]);
                sinfo.setYear(tokens[1]);
                sinfo.setRoll(tokens[2]);
                sinfo.setSec(tokens[3]);
                sinfo.setFname(tokens[4]);
                sinfo.setName(tokens[5]);
                sinfo.setFmobile(tokens[6]);
                sinfo.setSmobile(tokens[7]);
                sinfo.setMaths(tokens[8]);
                sinfo.setEnglish(tokens[9]);
                sinfo.setCse(tokens[10]);
                sinfo.setChemistry(tokens[11]);
                sinfo.setPhysics(tokens[12]);
                sinfo.setElectronics(tokens[13]);
                sinfo.setElectrical(tokens[14]);
                sqlc.addInfo(sinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlc.close();
    }
}