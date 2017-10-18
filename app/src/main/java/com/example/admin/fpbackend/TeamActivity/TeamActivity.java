package com.example.admin.fpbackend.TeamActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.fpbackend.ContactsActivity.ContactsActivity;
import com.example.admin.fpbackend.DataObjects.TeamBindingClass;
import com.example.admin.fpbackend.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamActivity extends AppCompatActivity {
    String consultant_ref;
    String Housing_ref;
    @BindView(R.id.spinnerSetTeam)
    Spinner spinnerSetTeam;
    private Dialog dialog;
    DatabaseReference Teams_Info;
    DatabaseReference Teams_Consul;
    private ArrayAdapter<String> adapter;
    public static final String TAG = "TeamActivityTAG";
    String TeamRefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        Intent intent = getIntent();
        consultant_ref = intent.getStringExtra(getString(R.string.consultant_Refrence));
        Housing_ref = intent.getStringExtra(getString(R.string.Housing_Refrence));
        ButterKnife.bind(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Teams_Info = database.getReference("Teams_Info");
        Teams_Consul = database.getReference("Data_Binding").child("Teams_Consult");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        setSpinnerItems();
        Log.d(TAG, "onCreate: "+Housing_ref);


    }

    public void setSpinnerItems() {
        Teams_Info.orderByKey().limitToLast(10).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    adapter.add(d.getValue().toString());
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSetTeam.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void SetTeam(View view) {
        Teams_Info.orderByValue().equalTo(spinnerSetTeam.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    TeamRefrence = d.getKey();
                }
                SetDataBinding(TeamRefrence);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void SetDataBinding(String Refrence)
    {
        TeamBindingClass team = new TeamBindingClass(TeamRefrence,consultant_ref);
        Teams_Consul.push().setValue(team, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(TeamActivity.this, "Member Added to the Team Succefully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TeamActivity.this, ContactsActivity.class);
                intent.putExtra(getString(R.string.consultant_Refrence),consultant_ref);
                intent.putExtra(getString(R.string.Housing_Refrence),Housing_ref);
                intent.putExtra(getString(R.string.Team_Refrence),TeamRefrence);
                startActivity(intent);
            }
        });
    }

    public void AddNewTeam(View view) {
        dialog = new Dialog(this);
        dialog.setTitle("Add new Team");
        dialog.setContentView(R.layout.add_teaminfo);
        dialog.show();

    }

    public void AddTeamtoDB(View view) {
        EditText txtTeamName = dialog.findViewById(R.id.txtAddTeam_name);
        Teams_Info.push().setValue(txtTeamName.getText().toString(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(TeamActivity.this, "Addes Succefully", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }
}
