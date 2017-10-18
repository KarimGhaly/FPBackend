package com.example.admin.fpbackend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.DataObjects.ContactInfoClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompletedActivity extends AppCompatActivity {

    String Consultant_Refrence;
    String Housing_Refrence;
    String Team_Refrence;
    String TrainingManager_Refrence;
    String MarketingManager_Refrence;
    String Insturctor_Refrence;
    @BindView(R.id.consultant_refrence)
    TextView consultantRefrence;
    @BindView(R.id.Housing_refrence)
    TextView HousingRefrence;
    @BindView(R.id.team_refrence)
    TextView teamRefrence;
    @BindView(R.id.tm_refrence)
    TextView tmRefrence;
    @BindView(R.id.IN_refrence)
    TextView INRefrence;
    @BindView(R.id.MK_refrence)
    TextView MKRefrence;

    ConsultantInfoClass consultant;
    String UID;

    public static final String TAG = "CompleteActivityTAG";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Consultant_Refrence = intent.getStringExtra(getString(R.string.consultant_Refrence));
        Housing_Refrence = intent.getStringExtra(getString(R.string.Housing_Refrence));
        Team_Refrence = intent.getStringExtra(getString(R.string.Team_Refrence));
        TrainingManager_Refrence = intent.getStringExtra(getString(R.string.TrainMaanger_Refrence));
        Insturctor_Refrence = intent.getStringExtra(getString(R.string.Instructor_Refrence));
        MarketingManager_Refrence = intent.getStringExtra(getString(R.string.MarktingManager_Refrence));
        consultantRefrence.setText(Consultant_Refrence);
        HousingRefrence.setText(Housing_Refrence);
        teamRefrence.setText(Team_Refrence);
        tmRefrence.setText(TrainingManager_Refrence);
        INRefrence.setText(Insturctor_Refrence);
        MKRefrence.setText(MarketingManager_Refrence);
    }

    public void Submit(View view) {
        getConsultan();
    }
    private void getConsultan()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference Consultant_Info = database.getReference("Consultant_Information");
        Consultant_Info.orderByKey().equalTo(Consultant_Refrence).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.getChildren())
                {
                    consultant = d.getValue(ConsultantInfoClass.class);
                    CreateUser();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void CreateUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        if(consultant!= null) {
            firebaseAuth.createUserWithEmailAndPassword(consultant.getEmail(), "Welcome2BB").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(CompletedActivity.this, "Consultant Addes Succefully", Toast.LENGTH_SHORT).show();
                        CreateConsultantinDB();
                    }
                    else
                    {
                        Toast.makeText(CompletedActivity.this, "Failed to Create User", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onComplete: "+task.getException().toString());
                    }

                }
            });
        }
        else {
            Toast.makeText(this, "Please Check Consultant Email First", Toast.LENGTH_SHORT).show();
        }
    }

    private void CreateConsultantinDB() {
        firebaseAuth.signInWithEmailAndPassword(consultant.getEmail(),"Welcome2BB").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if(user!= null)
                    {
                        UID = user.getUid();
                        AddConsultantData();
                    }
                }
                else
                {
                    Toast.makeText(CompletedActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void AddConsultantData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("Consultants_Records").child(UID).child("Phase").setValue("Training Phase");
        DatabaseReference Ref = database.getReference("Consultants_Records").child(UID).child("Training Phase").child("Account");
        Ref.child("Consultant_Refrence").setValue(Consultant_Refrence);
        Ref.child("Housing_Refrence").setValue(Housing_Refrence);
        Ref.child("Team_Refrence").setValue(Team_Refrence);
        Ref.child("TrainingManager_Refrence").setValue(TrainingManager_Refrence);
        Ref.child("Instructor_Refrence").setValue(Insturctor_Refrence);
        Ref.child("MarketingManager_Refrence").setValue(MarketingManager_Refrence);
        Map<String, Object> userUpdates = new HashMap<String, Object>();
        userUpdates.put("uid",UID);
        database.getReference("Consultant_Information").child(Consultant_Refrence).updateChildren(userUpdates);
    }


}
