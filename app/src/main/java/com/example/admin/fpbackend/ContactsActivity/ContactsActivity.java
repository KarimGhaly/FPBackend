package com.example.admin.fpbackend.ContactsActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.fpbackend.CompletedActivity;
import com.example.admin.fpbackend.DataObjects.ContactInfoClass;
import com.example.admin.fpbackend.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity {
    public static final String TAG = "ContactActivityTAG";
    String Consultant_Ref;
    String Housing_Ref;
    String Team_Ref;
    String TM_Refrence;
    String IN_Refrence;
    String MK_Refrnces;
    DatabaseReference Managers_Info;
    @BindView(R.id.SpinnerSetTrainManger)
    Spinner SpinnerSetTrainManger;
    @BindView(R.id.SpinnerSetMarketManger)
    Spinner SpinnerSetMarketManger;
    @BindView(R.id.SpinnerSetTrainInstructor)
    Spinner SpinnerSetTrainInstructor;
    private Dialog dialog;
    private ArrayAdapter<String> TMAdapter;
    private ArrayAdapter<String> INAdapter;
    private ArrayAdapter<String> MKAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Consultant_Ref = intent.getStringExtra(getString(R.string.consultant_Refrence));
        Housing_Ref = intent.getStringExtra(getString(R.string.Housing_Refrence));
        Team_Ref = intent.getStringExtra(getString(R.string.Team_Refrence));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Managers_Info = database.getReference("Managers_Info");
        TMAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        INAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        MKAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        getTraingManager();
        getInstructors();
        getMarketingManager();
    }

    public void getTraingManager() {
        Managers_Info.orderByChild("position").equalTo("Training Manager").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TMAdapter.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    TMAdapter.add(s.getValue(ContactInfoClass.class).getName());
                }
                TMAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpinnerSetTrainManger.setAdapter(TMAdapter);
                TMAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getInstructors(){
        Managers_Info.orderByChild("position").equalTo("Instructor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                INAdapter.clear();
                for(DataSnapshot s: dataSnapshot.getChildren())
                {
                    INAdapter.add(s.getValue(ContactInfoClass.class).getName());
                }
                INAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpinnerSetTrainInstructor.setAdapter(INAdapter);
                INAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void getMarketingManager(){
        Managers_Info.orderByChild("position").equalTo("Marketing Manager").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                MKAdapter.clear();
                for(DataSnapshot s: dataSnapshot.getChildren())
                {
                    MKAdapter.add(s.getValue(ContactInfoClass.class).getName());
                }
                MKAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpinnerSetMarketManger.setAdapter(MKAdapter);
                MKAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void AddNewManager(View view) {
        dialog = new Dialog(this);
        dialog.setTitle("Add New Manager");
        dialog.setContentView(R.layout.add_manager);
        dialog.show();
    }

    public void SetContacts(View view) {
        getTrainingManagerRefrence();
    }

    public void getTrainingManagerRefrence(){
        Managers_Info.orderByChild("name").equalTo(SpinnerSetTrainManger.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()) {
                    TM_Refrence = d.getKey();
                }
                getTrainintInstructorRefrnce();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getTrainintInstructorRefrnce() {
        Managers_Info.orderByChild("name").equalTo(SpinnerSetTrainInstructor.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()) {
                    IN_Refrence = d.getKey();
                }
                getMarketingManagerRefrence();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getMarketingManagerRefrence(){
        Managers_Info.orderByChild("name").equalTo(SpinnerSetMarketManger.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren()) {
                    MK_Refrnces = d.getKey();
                }
               Intent intent = new Intent(ContactsActivity.this, CompletedActivity.class);
                intent.putExtra(getString(R.string.consultant_Refrence),Consultant_Ref);
                intent.putExtra(getString(R.string.Housing_Refrence),Housing_Ref);
                intent.putExtra(getString(R.string.Team_Refrence),Team_Ref);
                intent.putExtra(getString(R.string.TrainMaanger_Refrence),TM_Refrence);
                intent.putExtra(getString(R.string.Instructor_Refrence),IN_Refrence);
                intent.putExtra(getString(R.string.MarktingManager_Refrence),MK_Refrnces);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void AddManagertoDB(View view) {
        EditText txtAddContactID = dialog.findViewById(R.id.txtAddContact_ID);
        EditText txtAddContactName = dialog.findViewById(R.id.txtAddContact_Name);
        EditText txtAddContactSkype = dialog.findViewById(R.id.txtAddContact_Skype);
        EditText txtAddContactPhone = dialog.findViewById(R.id.txtAddContact_Phone);
        EditText txtAddContactEmail = dialog.findViewById(R.id.txtAddContact_Email);
        Spinner spAddContactPosition = dialog.findViewById(R.id.spAddContact_Position);

        ContactInfoClass contact = new ContactInfoClass();
        contact.setId(Integer.parseInt(txtAddContactID.getText().toString()));
        contact.setName(txtAddContactName.getText().toString());
        contact.setEmail(txtAddContactEmail.getText().toString());
        contact.setSkype(txtAddContactSkype.getText().toString());
        contact.setPhone(txtAddContactPhone.getText().toString());
        contact.setPosition(spAddContactPosition.getSelectedItem().toString());
        Managers_Info.push().setValue(contact, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(ContactsActivity.this, "Add Succfully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
