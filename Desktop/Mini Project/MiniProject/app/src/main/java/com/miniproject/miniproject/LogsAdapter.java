package com.miniproject.miniproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {

    //Support Class for Recycler View.

    //Declaring Data-types.

    private final ArrayList<StudentInfo> localDataSet;
    private TextView allUnivRoll, allStudName, allStudYear, allStudSec, allStudRoll;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView allUnivRoll, allStudName, allStudYear, allStudSec, allStudRoll;
        public TextView getAllUnivRoll() {
            return allUnivRoll;
        }

        public TextView getAllStudName() {
            return allStudName;
        }

        public TextView getAllStudYear() {
            return allStudYear;
        }

        public TextView getAllStudSec() {
            return allStudSec;
        }

        public TextView getAllStudRoll() {
            return allStudRoll;
        }

        public ViewHolder(View view) {
            super(view);

            // Define click listener for the ViewHolder's View.

            allUnivRoll = view.findViewById(R.id.allUnivRoll);
            allStudName = view.findViewById(R.id.allStudName);
            allStudYear = view.findViewById(R.id.allStudYear);
            allStudSec = view.findViewById(R.id.allStudSec);
            allStudRoll = view.findViewById(R.id.allStudRoll);
        }


    }

    public LogsAdapter(ArrayList<StudentInfo> dataSet) {

        // Create new views (invoked by the layout manager).

        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        // Create a new view, which defines the UI of the list item.

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.all_student_details, viewGroup, false);
        allUnivRoll = view.findViewById(R.id.allUnivRoll);
        allStudName = view.findViewById(R.id.allStudName);
        allStudYear = view.findViewById(R.id.allStudYear);
        allStudSec = view.findViewById(R.id.allStudSec);
        allStudRoll = view.findViewById(R.id.allStudRoll);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        // Replace the contents of a view (invoked by the layout manager).

        viewHolder.setIsRecyclable(false);
        allUnivRoll.setText(localDataSet.get(position).getUnivRollNo());
        allStudName.setText(localDataSet.get(position).getName());
        allStudYear.setText(localDataSet.get(position).getYear());
        allStudSec.setText(localDataSet.get(position).getSec());
        allStudRoll.setText(localDataSet.get(position).getRoll());
        allUnivRoll.setOnClickListener(new View.OnClickListener() {

            //Button Activity when university roll no.is clicked on the student list page.

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Loading...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(),ShowStudentDetails.class);
                intent.putExtra("uniroll",localDataSet.get(position).getUnivRollNo()); //University Roll No. is passed to the next page.
                intent.putExtra("from","Teacher"); //value passed to dignify the identity of parent page of the upcoming activity.
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        // Return the size of your dataset (invoked by the layout manager).

        return localDataSet.size();
    }
}