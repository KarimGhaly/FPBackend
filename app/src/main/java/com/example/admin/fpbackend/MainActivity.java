package com.example.admin.fpbackend;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.HousingActivity.HousingActivity;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Add Consultant TAG";

    @BindView(R.id.txtAdd_ID)
    EditText txtAddID;
    @BindView(R.id.txtAdd_firstName)
    EditText txtAddFirstName;
    @BindView(R.id.txtAdd_lastName)
    EditText txtAddLastName;
    @BindView(R.id.txtAdd_email)
    EditText txtAddEmail;
    @BindView(R.id.txtAdd_address)
    EditText txtAddAddress;
    @BindView(R.id.txtAdd_phoneNo)
    EditText txtAddPhoneNo;
    @BindView(R.id.txtAdd_emergencyName)
    EditText txtAddEmergencyName;
    @BindView(R.id.txtAdd_emergencyPhone)
    EditText txtAddEmergencyPhone;
    @BindView(R.id.txtAdd_joinDate)
    EditText txtAddJoinDate;
    @BindView(R.id.txtAdd_Technology)
    EditText txtAddTechnology;
    @BindView(R.id.txtAdd_Skype)
    EditText txtAddSkype;
    private DatabaseReference consultant_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        consultant_information = database.getReference("Consultant_Information");
    }

    String Refrence;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void AddtoDB(View view) {
        final ConsultantInfoClass consultant = new ConsultantInfoClass();
        consultant.setId(Integer.parseInt(txtAddID.getText().toString()));
        consultant.setFirstName(txtAddFirstName.getText().toString());
        consultant.setLastName(txtAddLastName.getText().toString());
        consultant.setEmail(txtAddEmail.getText().toString());
        consultant.setAddress(txtAddAddress.getText().toString());
        consultant.setPhoneNo(txtAddPhoneNo.getText().toString());
        consultant.setEmergencyName(txtAddEmergencyName.getText().toString());
        consultant.setEmergencyPhone(txtAddEmergencyPhone.getText().toString());
        consultant.setJoinDate(converttoDate(txtAddJoinDate.getText().toString()));
        consultant.setTechnology(txtAddTechnology.getText().toString());
        consultant.setSkype(txtAddSkype.getText().toString());
        consultant_information.push().setValue(consultant, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Refrence = databaseReference.getKey();
                    Toast.makeText(MainActivity.this, "Addes Succefully Refrence: " + Refrence, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HousingActivity.class);
                    intent.putExtra(getString(R.string.consultant_Refrence), Refrence);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Error \n Please Check Logs", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onComplete: " + databaseError.getMessage());
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Date converttoDate(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(str);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }
}
