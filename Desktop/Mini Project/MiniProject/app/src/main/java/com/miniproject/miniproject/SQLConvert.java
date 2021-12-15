package com.miniproject.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLConvert extends SQLiteOpenHelper {

    //Helper class helps to deal with SQL Queries.

    public SQLConvert(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating table studDatabase.

        String create = "CREATE TABLE studDatabase(UnivRollNo varchar2(10), Year varchar2(2), RollNo varchar2(3), Section varchar2(1), FatherName varchar2(50), StudentName varchar2(50), FatherMobile varchar2(12), StudentMobile varchar2(12), Maths varchar2(3), English varchar2(3), Cse varchar2(3), Chemistry varchar2(3), Physics varchar2(3), Electronics varchar2(3), Electrical varchar2(3))";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Action taken if an existing table is being remade.

        db.execSQL("DROP TABLE IF EXISTS studDatabase");
        onCreate(db);
    }

    public void addInfo(StudentInfo sinfo){

        //Adds information to the database.

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UnivRollNo", sinfo.getUnivRollNo());
        values.put("Year", sinfo.getYear());
        values.put("RollNo", sinfo.getRoll());
        values.put("Section", sinfo.getSec());
        values.put("FatherName", sinfo.getFname());
        values.put("StudentName", sinfo.getName());
        values.put("FatherMobile", sinfo.getFmobile());
        values.put("StudentMobile", sinfo.getSmobile());
        values.put("Maths", sinfo.getMaths());
        values.put("English", sinfo.getEnglish());
        values.put("Cse", sinfo.getCse());
        values.put("Chemistry", sinfo.getChemistry());
        values.put("Physics", sinfo.getPhysics());
        values.put("Electronics", sinfo.getElectronics());
        values.put("Electrical", sinfo.getElectrical());
        long k = db.insert("studDatabase", null, values);
        db.close();
    }

    public StudentInfo sinfo1(String UnivRollno){

        //Handles SQL Queries when user provides University Roll No. to access the data.

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM studDatabase WHERE UnivRollNo =?";
        Cursor cursor = db.rawQuery(query, new String[]{UnivRollno});
        String univRollNo1 = "", Year = "", RollNo = "", Sec = "", Fname = "", Name = "", Fmobile = "", Smobile = "", Maths = "", English = "", Cse = "", Chemistry = "", Physics = "", Electronics = "", Electricals = "";
        if(cursor.getCount() > 0 && cursor.moveToFirst()){
            univRollNo1 = cursor.getString(0);
            Year = cursor.getString(1);
            RollNo = cursor.getString(2);
            Sec = cursor.getString(3);
            Fname = cursor.getString(4);
            Name = cursor.getString(5);
            Fmobile = cursor.getString(6);
            Smobile = cursor.getString(7);
            Maths = cursor.getString(8);
            English = cursor.getString(9);
            Cse = cursor.getString(10);
            Chemistry = cursor.getString(11);
            Physics = cursor.getString(12);
            Electronics = cursor.getString(13);
            Electricals = cursor.getString(14);
        }
        else
            Log.e("mytag", "Some Error Occured");
        StudentInfo data = new StudentInfo(univRollNo1, Year, RollNo, Sec, Fname, Name, Fmobile, Smobile, Maths, English, Cse, Chemistry, Physics, Electronics, Electricals);
        cursor.close();
        return data;
    }

    public ArrayList<StudentInfo> sinfo2(String year,String sec){

        //Handles SQL Queries if Year and section is used to access data.

        ArrayList<StudentInfo> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM studDatabase WHERE Year =? AND Section =?", new String[]{year,sec});
        String univRollNo1 = "", Year = "", RollNo = "", Sec = "", Fname = "", Name = "", Fmobile = "", Smobile = "", Maths = "", English = "", Cse = "", Chemistry = "", Physics = "", Electronics = "", Electricals = "";
        if(cursor.getCount() > 0 && cursor.moveToFirst()) {
            {
                do {
                    univRollNo1 = cursor.getString(0);
                    Year = cursor.getString(1);
                    RollNo = cursor.getString(2);
                    Sec = cursor.getString(3);
                    Fname = cursor.getString(4);
                    Name = cursor.getString(5);
                    Fmobile = cursor.getString(6);
                    Smobile = cursor.getString(7);
                    Maths = cursor.getString(8);
                    English = cursor.getString(9);
                    Cse = cursor.getString(10);
                    Chemistry = cursor.getString(11);
                    Physics = cursor.getString(12);
                    Electronics = cursor.getString(13);
                    Electricals = cursor.getString(14);
                    StudentInfo temp = new StudentInfo(univRollNo1, Year, RollNo, Sec, Fname, Name, Fmobile, Smobile, Maths, English, Cse, Chemistry, Physics, Electronics, Electricals);
                    data.add(temp);
                } while (cursor.moveToNext());
            }
        }
        else
            Log.e("mytag", "Some Error Occured");
        cursor.close();
        return data;
    }

    public ArrayList<StudentInfo> sinfo3(){

        //Handles SQL Queries when all data is required to be seen.

        ArrayList<StudentInfo> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM studDatabase", new String[]{});
        String univRollNo1 = "", Year = "", RollNo = "", Sec = "", Fname = "", Name = "", Fmobile = "", Smobile = "", Maths = "", English = "", Cse = "", Chemistry = "", Physics = "", Electronics = "", Electricals = "";
        if(cursor.getCount() > 0 && cursor.moveToFirst()) {
            {
                do {
                    univRollNo1 = cursor.getString(0);
                    Year = cursor.getString(1);
                    RollNo = cursor.getString(2);
                    Sec = cursor.getString(3);
                    Fname = cursor.getString(4);
                    Name = cursor.getString(5);
                    Fmobile = cursor.getString(6);
                    Smobile = cursor.getString(7);
                    Maths = cursor.getString(8);
                    English = cursor.getString(9);
                    Cse = cursor.getString(10);
                    Chemistry = cursor.getString(11);
                    Physics = cursor.getString(12);
                    Electronics = cursor.getString(13);
                    Electricals = cursor.getString(14);
                    StudentInfo temp = new StudentInfo(univRollNo1, Year, RollNo, Sec, Fname, Name, Fmobile, Smobile, Maths, English, Cse, Chemistry, Physics, Electronics, Electricals);
                    data.add(temp);
                } while (cursor.moveToNext());
            }
        }
        else
            Log.e("mytag", "Some Error Occured");
        cursor.close();
        return data;
    }

}
