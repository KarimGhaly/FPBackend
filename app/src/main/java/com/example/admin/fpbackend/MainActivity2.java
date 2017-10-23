package com.example.admin.fpbackend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.fpbackend.Benfits.Benfits;
import com.example.admin.fpbackend.Training.AddAssignment;
import com.example.admin.fpbackend.Training.GradeAssignment;
import com.example.admin.fpbackend.marketing.AddInterviews;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //deleteBranch();
    }

    public void AddConsultant(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void AddPaySlip(View view) {
        Intent intent = new Intent(this,FinanceActivity.class);
        startActivity(intent);
    }

    void deleteBranch(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Consultants_Records")
                .child("7TI8mD1R0aTiB5duvvlxBuOvXot1").child("Training Phase").child("Finance").child("Pay Slips");
        ref.removeValue();
    }

    public void AddBenfits(View view) {
        Intent intent = new Intent(this, Benfits.class);
        startActivity(intent);
    }

    public void AddAssignemnt(View view) {
        Intent intent = new Intent(this, AddAssignment.class);
        startActivity(intent);
    }

    public void GradeAssignemnt(View view) {
        Intent intent = new Intent(this, GradeAssignment.class);
        startActivity(intent);
    }

    public void AddInterview(View view) {
        Intent intent = new Intent(this, AddInterviews.class);
        startActivity(intent);
    }
}
