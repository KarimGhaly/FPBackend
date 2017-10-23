package com.example.admin.fpbackend.Training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.DataObjects.GradedAssignmentInfoClass;
import com.example.admin.fpbackend.DataObjects.TodayAssigmentInfoClass;
import com.example.admin.fpbackend.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GradeAssignment extends AppCompatActivity {
    public static final String TAG = "GradeAssignmentTAG";


    @BindView(R.id.spAssignmentGrade_SetConsultant)
    Spinner spAssignmentGradeSetConsultant;
    @BindView(R.id.btnASsignmentGrade_SelectConsultant)
    Button btnASsignmentGradeSelectConsultant;
    @BindView(R.id.spAssignmentGrade_SetAssignment)
    Spinner spAssignmentGradeSetAssignment;
    @BindView(R.id.etAssignment_title)
    EditText etAssignmentTitle;
    @BindView(R.id.etAssignment_grade)
    EditText etAssignmentGrade;
    @BindView(R.id.etAssignment_totalGrade)
    EditText etAssignmentTotalGrade;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.etAssignment_feedback)
    EditText etAssignmentFeedback;
    @BindView(R.id.btnAssignment_submit)
    Button btnAssignmentSubmit;

    FirebaseDatabase database;
    DatabaseReference ConsultantRef;
    DatabaseReference TrainingRef;

    ArrayAdapter<String> consultantAdapter;
    ArrayAdapter<String> assignmentsAdapter;
    HashMap<String, String> ConsultantUID = new HashMap<String, String>();
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_assignment);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        ConsultantRef = database.getReference("Consultant_Information");
        TrainingRef = database.getReference("Consultants_Records");

        consultantAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        assignmentsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        getConsultants();

        spAssignmentGradeSetAssignment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etAssignmentTitle.setText(spAssignmentGradeSetAssignment.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                etAssignmentTitle.setText("");
            }
        });
    }

    private void getConsultants() {
        consultantAdapter.clear();
        ConsultantUID.clear();
        ConsultantRef.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    ConsultantInfoClass c = D.getValue(ConsultantInfoClass.class);
                    String Full_Name = c.getFirstName() + " " + c.getLastName();
                    ConsultantUID.put(Full_Name, D.child("uid").getValue(String.class));
                    consultantAdapter.add(Full_Name);
                }
                consultantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAssignmentGradeSetConsultant.setAdapter(consultantAdapter);
                consultantAdapter.notifyDataSetChanged();
                btnASsignmentGradeSelectConsultant.setEnabled(true);
                ConsultantRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.btnASsignmentGrade_SelectConsultant)
    public void onBtnASsignmentGradeSelectConsultantClicked() {
        uid = ConsultantUID.get(spAssignmentGradeSetConsultant.getSelectedItem().toString());
        getAssignments(uid);
        btnAssignmentSubmit.setEnabled(true);
    }

    private void getAssignments(final String uid) {
        assignmentsAdapter.clear();
        assignmentsAdapter.add("");
        TrainingRef.child(uid).child("Training Phase").child("Training").child("Assignments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    assignmentsAdapter.add(D.getValue(TodayAssigmentInfoClass.class).getTitle());
                }
                assignmentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAssignmentGradeSetAssignment.setAdapter(assignmentsAdapter);
                assignmentsAdapter.notifyDataSetChanged();
                TrainingRef.child(uid).child("Training Phase").child("Training").child("Assignments").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @OnClick(R.id.btnAssignment_submit)
    public void onBtnAssignmentSubmitClicked() {
        insertGradedAssignemt();
    }

    public void insertGradedAssignemt() {
        if (!etAssignmentTitle.getText().toString().isEmpty() && !etAssignmentGrade.getText().toString().isEmpty() && !etAssignmentTotalGrade.getText().toString().isEmpty())

        {
            GradedAssignmentInfoClass grade = new GradedAssignmentInfoClass();

            grade.setTitleAssignment(etAssignmentTitle.getText().toString());
            grade.setFeedback(etAssignmentFeedback.getText().toString());
            float gradeScore = Float.parseFloat(etAssignmentGrade.getText().toString()) / Float.parseFloat(etAssignmentTotalGrade.getText().toString());
            grade.setGrade(gradeScore);
            TrainingRef.child(uid).child("Training Phase").child("Training").child("Grades").push().setValue(grade, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null) {
                        Toast.makeText(GradeAssignment.this, "Graded Submitted Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GradeAssignment.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onComplete: " + databaseError.getMessage());
                    }
                }
            });
        } else {
            Toast.makeText(this, "Please Fill the Data First", Toast.LENGTH_SHORT).show();
        }

    }


}
