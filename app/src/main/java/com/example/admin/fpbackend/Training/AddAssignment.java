package com.example.admin.fpbackend.Training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.DataObjects.TeamBindingClass;
import com.example.admin.fpbackend.DataObjects.TodayAssigmentInfoClass;
import com.example.admin.fpbackend.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAssignment extends AppCompatActivity {

    public static final String TAG = "AddAssignmentTAG";

    @BindView(R.id.spTodayAssignment_SetTeam)
    Spinner spTodayAssignmentSetTeam;
    @BindView(R.id.btnTodayAssignment_SelectTeam)
    Button btnTodayAssignmentSelectTeam;
    @BindView(R.id.etTodayAssignment_title)
    EditText etTodayAssignmentTitle;
    @BindView(R.id.etTodayAssignment_description)
    EditText etTodayAssignmentDescription;
    @BindView(R.id.etTodayAssignment_dueDate)
    EditText etTodayAssignmentDueDate;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    FirebaseDatabase database;
    DatabaseReference TeamRef;
    DatabaseReference TeamBinding;
    DatabaseReference ConsultantRef;
    DatabaseReference TrainingRef;

    List<String> consultantList = new ArrayList<>();
    Map<String, String> teamKey = new HashMap<>();

    ArrayAdapter<String> spAdapter;
    @BindView(R.id.etTodayAssignment_SylbusLINK)
    EditText etTodayAssignmentSylbusLINK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        TeamRef = database.getReference("Teams_Info");
        TeamBinding = database.getReference("Data_Binding").child("Teams_Consult");
        ConsultantRef = database.getReference("Consultant_Information");
        TrainingRef = database.getReference("Consultants_Records");
        spAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        getTeams();
    }

    public void getTeams() {
        TeamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                spAdapter.clear();
                teamKey.clear();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    spAdapter.add(D.getValue(String.class));
                    teamKey.put(D.getValue(String.class), D.getKey());
                }
                spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTodayAssignmentSetTeam.setAdapter(spAdapter);
                spAdapter.notifyDataSetChanged();
                btnTodayAssignmentSelectTeam.setEnabled(true);
                TeamRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @OnClick(R.id.btnTodayAssignment_SelectTeam)
    public void onBtnTodayAssignmentSelectTeamClicked() {
        consultantList.clear();
        String team_key = teamKey.get(spTodayAssignmentSetTeam.getSelectedItem().toString());
        TeamBinding.orderByChild("teamRefrence").equalTo(team_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot D : dataSnapshot.getChildren()) {
                    ConsultantRef.child(D.getValue(TeamBindingClass.class).getConsultantRefrence()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            consultantList.add(dataSnapshot.getValue(ConsultantInfoClass.class).getUid());
                            ConsultantRef.child(D.getValue(TeamBindingClass.class).getConsultantRefrence()).removeEventListener(this);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                btnSubmit.setEnabled(true);
                btnTodayAssignmentSelectTeam.setEnabled(false);
                TeamBinding.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @OnClick(R.id.btnSubmit)
    public void onBtnSubmitClicked() {

        if (!etTodayAssignmentTitle.getText().toString().isEmpty() && !etTodayAssignmentDescription.getText().toString().isEmpty()) {
            TodayAssigmentInfoClass asignment = new TodayAssigmentInfoClass();
            asignment.setTitle(etTodayAssignmentTitle.getText().toString());
            asignment.setDescription(etTodayAssignmentDescription.getText().toString());
            asignment.setSylbusLink(etTodayAssignmentSylbusLINK.getText().toString());
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                asignment.setDueDate(sdf.parse(etTodayAssignmentDueDate.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (String uid : consultantList) {
                TrainingRef.child(uid).child("Training Phase").child("Training").child("Assignments").push().setValue(asignment);

            }
            Toast.makeText(this, "Assignment Added Succesfully to the Team", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Please Insert Assignmet Title & Description", Toast.LENGTH_SHORT).show();
        }
    }
}
