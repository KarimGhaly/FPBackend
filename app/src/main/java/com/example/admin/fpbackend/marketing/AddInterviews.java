package com.example.admin.fpbackend.marketing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.DataObjects.InterviewInfoClass;
import com.example.admin.fpbackend.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddInterviews extends AppCompatActivity {

    public static final String TAG = "AddInterviewTAG";

    @BindView(R.id.spInterview_SetConsultant)
    Spinner spInterviewSetConsultant;
    @BindView(R.id.etInterview_title)
    EditText etInterviewTitle;
    @BindView(R.id.etInterview_date)
    EditText etInterviewDate;
    @BindView(R.id.etInterview_time)
    EditText etInterviewTime;
    @BindView(R.id.etInterview_interviewerNames)
    EditText etInterviewInterviewerNames;
    @BindView(R.id.etInterview_clientName)
    EditText etInterviewClientName;
    @BindView(R.id.etInterview_vendorName)
    EditText etInterviewVendorName;
    @BindView(R.id.etInterview_projectCity)
    EditText etInterviewProjectCity;
    @BindView(R.id.etInterview_projectState)
    EditText etInterviewProjectState;
    @BindView(R.id.etInterview_projectDuration)
    EditText etInterviewProjectDuration;
    @BindView(R.id.etInterview_availabilityDate)
    EditText etInterviewAvailabilityDate;
    @BindView(R.id.etInterview_clientWebsite)
    EditText etInterviewClientWebsite;
    @BindView(R.id.btnInputInterviewInfo)
    Button btnInputInterviewInfo;

    FirebaseDatabase database;
    DatabaseReference ConsultantRef;
    DatabaseReference InterviewRef;
    String UID;
    HashMap<String, String> ConsultantUID = new HashMap<String, String>();
    ArrayAdapter<String> consultantAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_interviews);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        ConsultantRef = database.getReference("Consultant_Information");
        InterviewRef = database.getReference("Consultants_Records");
        consultantAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        getConsultants();
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
                spInterviewSetConsultant.setAdapter(consultantAdapter);
                consultantAdapter.notifyDataSetChanged();
                ConsultantRef.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @OnClick(R.id.btnInputInterviewInfo)
    public void onViewClicked() {
        UID = ConsultantUID.get(spInterviewSetConsultant.getSelectedItem().toString());
        insertInterviewToDB();

    }
    public void insertInterviewToDB(){

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        boolean flag = true;
        Calendar interviewCalendar = null;

        try {

            interviewCalendar = Calendar.getInstance();
            interviewCalendar.setTime(df.parse(etInterviewDate.getText().toString() + " " + etInterviewTime.getText().toString()));

        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Date", Toast.LENGTH_SHORT).show();
            flag = false;
        }

        if(flag) {
            InterviewInfoClass interview = new InterviewInfoClass(etInterviewTitle.getText().toString(),
                    interviewCalendar.getTime(), etInterviewInterviewerNames.getText().toString(),
                    etInterviewVendorName.getText().toString(), etInterviewVendorName.getText().toString(),
                    etInterviewProjectCity.getText().toString() + ", " + etInterviewProjectState.getText().toString(),
                    etInterviewProjectDuration.getText().toString(), etInterviewAvailabilityDate.getText().toString(),
                    etInterviewClientWebsite.getText().toString());
            InterviewRef.child(UID).child("Training Phase").child("Interviews").child("Upcoming Interviews").push().setValue(interview, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if(databaseError == null)
                    {
                        Toast.makeText(AddInterviews.this, "Interview Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AddInterviews.this, "Error", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onComplete: "+databaseError.getMessage());
                    }
                }
            });
        }

    }
}
