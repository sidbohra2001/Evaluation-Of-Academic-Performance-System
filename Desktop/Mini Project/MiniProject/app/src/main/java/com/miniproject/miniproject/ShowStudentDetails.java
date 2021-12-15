package com.miniproject.miniproject;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ShowStudentDetails extends AppCompatActivity {

    //Declaring Data-types.

    TextView dispUnivRoll, dispStudName, dispFname, dispYear, dispSec, dispRollNo, dispSmobile, dispFmobile, MathsMarks, EnglishMarks, CSEMarks, ChemistryMarks, PhysicsMarks, ElectronicsMarks, ElectricalMarks;
    TextView MathsPercentage, EnglishPercentage, CSEPercentage, ChemistryPercentage, PhysicsPercentage, ElectronicsPercentage, ElectricalPercentage;
    TextView MathsGrade, EnglishGrade, CSEGrade, ChemistryGrade, PhysicsGrade, ElectronicsGrade, ElectricalGrade;
    TextView CallStudent, CallParent, MessageParent, MessageStudent, contacts;
    SQLConvert sqlc; //SQLConvert object.
    TextView greenColourInfo, greyColourInfo, redColourInfo, percentageOutput, GradeOutput;
    TextView sameSec, sameYear, sameSecDisplay, sameYearDisplay, yearAverage, secAverage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Page that shows a selected student's details.

        super.onCreate(savedInstanceState);
        String univRollNo = getIntent().getExtras().getString("uniroll"); //Fetching university roll no. from previous page.
        String prevIntent = getIntent().getExtras().getString("from"); //Fetching calling page identity.
        setContentView(R.layout.activity_show_student_details);
        greenColourInfo = findViewById(R.id.greenColourInfo);
        greyColourInfo = findViewById(R.id.greyColourInfo);
        redColourInfo = findViewById(R.id.redColourInfo);
        greenColourInfo.setTextColor(Color.rgb(0,200,0));
        redColourInfo.setTextColor(Color.rgb(200,0,0));
        dispUnivRoll = findViewById(R.id.dispUnivRoll);
        dispStudName = findViewById(R.id.dispStudName);
        dispFname = findViewById(R.id.dispFname);
        dispYear = findViewById(R.id.dispYear);
        dispSec = findViewById(R.id.dispSec);
        dispRollNo = findViewById(R.id.dispRollNo);
        dispSmobile = findViewById(R.id.dispSmobile);
        dispFmobile = findViewById(R.id.dispFmobile);
        MathsMarks = findViewById(R.id.MathsMarks);
        EnglishMarks = findViewById(R.id.EnglishMarks);
        CSEMarks = findViewById(R.id.CSEMarks);
        ChemistryMarks = findViewById(R.id.ChemistryMarks);
        PhysicsMarks = findViewById(R.id.PhysicsMarks);
        ElectronicsMarks = findViewById(R.id.ElectronicsMarks);
        ElectricalMarks = findViewById(R.id.ElectricalMarks);
        MathsPercentage = findViewById(R.id.MathsPercentage);
        EnglishPercentage = findViewById(R.id.EnglishPercentage);
        CSEPercentage = findViewById(R.id.CSEPercentage);
        ChemistryPercentage = findViewById(R.id.ChemistryPercentage);
        PhysicsPercentage = findViewById(R.id.PhysicsPercentage);
        ElectronicsPercentage = findViewById(R.id.ElectronicsPercentage);
        ElectricalPercentage = findViewById(R.id.ElectricalPercentage);
        MathsGrade = findViewById(R.id.MathsGrade);
        EnglishGrade = findViewById(R.id.EnglishGrade);
        CSEGrade = findViewById(R.id.CSEGrade);
        ChemistryGrade = findViewById(R.id.ChemistryGrade);
        PhysicsGrade = findViewById(R.id.PhysicsGrade);
        ElectronicsGrade = findViewById(R.id.ElectronicsGrade);
        ElectricalGrade = findViewById(R.id.ElectricalGrade);
        percentageOutput = findViewById(R.id.PercentageOutput);
        GradeOutput = findViewById(R.id.GradeOutput);
        contacts = findViewById(R.id.contacts);
        CallStudent = findViewById(R.id.CallStudent);
        CallParent = findViewById(R.id.CallParent);
        MessageStudent = findViewById(R.id.MessageStudent);
        MessageParent = findViewById(R.id.MessageParent);
        sameSec = findViewById(R.id.sameSec);
        sameYear = findViewById(R.id.sameYear);
        sameSecDisplay = findViewById(R.id.sameSecDisp);
        sameYearDisplay = findViewById(R.id.sameYearDisp);
        secAverage = findViewById(R.id.secAverage);
        yearAverage = findViewById(R.id.yearAverage);
        CallStudent.setVisibility(View.INVISIBLE);
        CallParent.setVisibility(View.INVISIBLE);
        MessageParent.setVisibility(View.INVISIBLE);
        MessageStudent.setVisibility(View.INVISIBLE);
        contacts.setVisibility(View.INVISIBLE);
        if(prevIntent.equals("Teacher")){

            //Shows some additional options if calling page is a Teacher usable page.

            CallStudent.setVisibility(View.VISIBLE);
            CallParent.setVisibility(View.VISIBLE);
            MessageParent.setVisibility(View.VISIBLE);
            MessageStudent.setVisibility(View.VISIBLE);
            contacts.setVisibility(View.VISIBLE);
        }
        readStudentDetails();
        setValues(univRollNo);
    }

    private void setValues(String univRollNo) {

        //Sets value to all the text views regarding a particular student.

        SQLConvert setDetails = new SQLConvert(ShowStudentDetails.this, "studDatabase", null, 1);
        StudentInfo info = setDetails.sinfo1(univRollNo);
        int maths = Integer.parseInt(info.getMaths());
        int english = Integer.parseInt(info.getEnglish());
        int cse = Integer.parseInt(info.getCse());
        int chemistry = Integer.parseInt(info.getChemistry());
        int physics = Integer.parseInt(info.getPhysics());
        int electronics = Integer.parseInt(info.getElectronics());
        int electrical = Integer.parseInt(info.getElectrical());
        int percentage = (maths+english+cse+chemistry+physics+electronics+electrical)/7;
        dispUnivRoll.setText(info.getUnivRollNo());
        dispStudName.setText(info.getName());
        dispFname.setText(info.getFname());
        dispYear.setText(info.getYear());
        dispSec.setText(info.getSec());
        dispRollNo.setText(info.getRoll());
        dispSmobile.setText(info.getSmobile());
        dispFmobile.setText(info.getFmobile());
        setMarksColor(maths,MathsMarks);
        setMarksColor(english,EnglishMarks);
        setMarksColor(cse,CSEMarks);
        setMarksColor(chemistry,ChemistryMarks);
        setMarksColor(physics,PhysicsMarks);
        setMarksColor(electronics,ElectronicsMarks);
        setMarksColor(electrical,ElectricalMarks);
        setPercentageColor(english,EnglishPercentage);
        setPercentageColor(maths,MathsPercentage);
        setPercentageColor(cse,CSEPercentage);
        setPercentageColor(chemistry,ChemistryPercentage);
        setPercentageColor(physics,PhysicsPercentage);
        setPercentageColor(electronics,ElectronicsPercentage);
        setPercentageColor(electrical,ElectricalPercentage);
        setGrade(maths,MathsGrade);
        setGrade(english,EnglishGrade);
        setGrade(cse,CSEGrade);
        setGrade(chemistry,ChemistryGrade);
        setGrade(physics,PhysicsGrade);
        setGrade(electronics,ElectronicsGrade);
        setGrade(electrical,ElectricalGrade);
        setSameSecDisplay(info.getYear(), info.getSec(), percentage, sameSecDisplay);
        setSameYearDisplay(info.getYear(), percentage, sameYearDisplay);
        percentageOutput.setText(getPercentage(percentage));
        setGrade(percentage,GradeOutput);
        CallStudent.setOnClickListener(new View.OnClickListener() {

            //Activity when teacher wants to call the selected student.

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + info.getSmobile()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        CallParent.setOnClickListener(new View.OnClickListener() {

            //Activity when teacher wants to call the parent of the selected student.

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + info.getFmobile()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        MessageStudent.setOnClickListener(new View.OnClickListener() {

            //Activity when teacher wants to text the selected student.

            @Override
            public void onClick(View v) {
                String[] email1 = {info.getSmobile()+"@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL,email1);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        MessageParent.setOnClickListener(new View.OnClickListener() {

            //Activity when teacher wants to text the parent of the selected student.

            @Override
            public void onClick(View v) {
                String[] email1 = {info.getFmobile()+"@gmail.com"};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("*/*");
                intent.putExtra(Intent.EXTRA_EMAIL,email1);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    private void setSameYearDisplay(String year, int n, TextView r){

        //Shows student's performance as compared to the other student of same year.

        String bA = "Below Average";
        String A = "Average";
        String aA = "Better than Average";
        if(year.equals("1")){
            double av = 65.79;
            String b = yearAverage.getText().toString()+av;
            yearAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("2")){
            double av = 65.24;
            String b = yearAverage.getText().toString()+av;
            yearAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("3")){
            double av = 63.74;
            String b = yearAverage.getText().toString()+av;
            yearAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("4")){
            double av = 63.95;
            String b = yearAverage.getText().toString()+av;
            yearAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("5")){
            double av = 62.71;
            String b = yearAverage.getText().toString()+av;
            yearAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
    }

    private void setSameSecDisplay(String year, String sec, int n, TextView r){

        //Shows student's performance as compared to the other student of same section.

        String bA = "Below Average";
        String A = "Average";
        String aA = "Better than Average";
        if(year.equals("1") && sec.equals("A")){
            double av = 65.59;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){ 
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("1") && sec.equals("B")){
            double av = 65.99;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("2") && sec.equals("A")){
            double av = 64.76;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("2") && sec.equals("B")){
            double av = 65.73;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("3") && sec.equals("A")){
            double av = 63.41;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("3") && sec.equals("B")){
            double av = 64.07;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("4") && sec.equals("A")){
            double av = 64.84;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("4") && sec.equals("B")){
            double av = 63.05;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("5") && sec.equals("A")){
            double av = 62.10;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
        if(year.equals("5") && sec.equals("B")){
            double av = 63.32;
            String b = secAverage.getText().toString()+av;
            secAverage.setText(b);
            if(n < av - 10){
                r.setText(bA);
                r.setTextColor(Color.rgb(200,0,0));
            }
            else if(n >= av - 10 && n <= av + 10){
                r.setText(A);
            }
            else if(n > av + 10){
                r.setText(aA);
                r.setTextColor(Color.rgb(0,200,0));
            }
        }
    }

    private void setGrade(int n, TextView r) {

        //Sets grades of students.

        if(n > 90){
            r.setText("O");
            r.setTextColor(Color.rgb(0,0,200));
        }
        else if(n > 80){
            r.setText("A");
            r.setTextColor(Color.rgb(0,200,0));
        }
        else if(n > 65){
            r.setText("B");
        }
        else if(n > 50){
            r.setText("C");
        }
        else if(n > 33){
            r.setText("D");
            r.setTextColor(Color.rgb(176,0,32));
        }
        else{
            String fail = "Fail";
            r.setText(fail);
            r.setTextColor(Color.rgb(176,0,32));
        }
    }

    private String getPercentage(int n) {

        //Sets percentage of student.

        return n+"%";
    }

    private void setMarksColor(int n, TextView r){

        //Gives color to the marks.

        if(n > 80){
            r.setText(String.valueOf(n));
            r.setTextColor(Color.rgb(0,200,0));
        }
        else if(n > 50){
            r.setText(String.valueOf(n));
        }
        else{
            r.setText(String.valueOf(n));
            r.setTextColor(Color.rgb(176,0, 32));
        }
    }

    private void setPercentageColor(int n, TextView r){

        //Gives color to the percentage text.

        if(n > 80){
            r.setText(getPercentage(n));
            r.setTextColor(Color.rgb(0,200,0));
        }
        else if(n > 50){
            r.setText(getPercentage(n));
        }
        else{
            r.setText(getPercentage(n));
            r.setTextColor(Color.rgb(176,0, 32));
        }
    }

    protected void readStudentDetails() {

        //Reads student details from and forms a database from student.csv file.

        InputStream is = getResources().openRawResource(R.raw.students);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        sqlc = new SQLConvert(ShowStudentDetails.this, "studDatabase", null, 1);
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