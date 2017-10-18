package com.example.admin.fpbackend.HousingActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.HousingInfoClass;
import com.example.admin.fpbackend.MainActivity;
import com.example.admin.fpbackend.R;
import com.example.admin.fpbackend.TeamActivity.TeamActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HousingActivity extends AppCompatActivity {


    DatabaseReference Housing_Information;
    Spinner spinnerSetHousing;

    private List<String> houseList = new ArrayList<>();
    private EditText txtAddHousingLng;
    private EditText txtAddHousingLat;
    private EditText txtAddHousingGateCode;
    private EditText txtAddHousingBuildingManagerPhone;
    private EditText txtAddHousingNumBathroom;
    private EditText txtAddHousingNumBedroom;
    private EditText txtAddHousingAddress;
    private EditText txtAddHousingName;
    private EditText txtAddHousingId;
    private EditText txtAddHousingLockCode;
    private EditText txtAddHousingBuildingManager;
    private Dialog addHousingDialog;
    public static final String TAG = "HousingActivityTAG";
    private ArrayAdapter<String> dataAdapter;
    private String consultant_ref;
    String Housing_Refrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing);
        Intent intent = getIntent();
        consultant_ref = intent.getStringExtra(getString(R.string.consultant_Refrence));
        ButterKnife.bind(this);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Housing_Information = database.getReference("Housing_Information");
        UpdateSpinnerList();
        HousingList();


    }

    public void SaveHousing(View view) {
        HousingInfoClass house = new HousingInfoClass();

        house.setId(Integer.parseInt(txtAddHousingId.getText().toString()));
        house.setName(txtAddHousingName.getText().toString());
        house.setAddress(txtAddHousingAddress.getText().toString());
        house.setNumBathroom(Integer.parseInt(txtAddHousingNumBathroom.getText().toString()));
        house.setNumBedroom(Integer.parseInt(txtAddHousingNumBedroom.getText().toString()));
        house.setBuildingManagerPhone(txtAddHousingBuildingManagerPhone.getText().toString());
        house.setLockCode(txtAddHousingLockCode.getText().toString());
        house.setGateCode(txtAddHousingGateCode.getText().toString());
        house.setLat(Double.parseDouble(txtAddHousingLat.getText().toString()));
        house.setLng(Double.parseDouble(txtAddHousingLng.getText().toString()));
        house.setHouseManager(txtAddHousingBuildingManager.getText().toString());
        Housing_Information.push().setValue(house, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(HousingActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                HousingList();
                addHousingDialog.dismiss();

            }
        });


    }

    public void AddNewHousing(View view) {
        addHousingDialog = new Dialog(this);
        addHousingDialog.setTitle("Add New Housing");
        addHousingDialog.setContentView(R.layout.add_housinginfo);
        txtAddHousingId = addHousingDialog.findViewById(R.id.txtAddHousing_id);
        txtAddHousingName = addHousingDialog.findViewById(R.id.txtAddHousing_name);
        txtAddHousingAddress = addHousingDialog.findViewById(R.id.txtAddHousing_address);
        txtAddHousingNumBedroom = addHousingDialog.findViewById(R.id.txtAddHousing_numBedroom);
        txtAddHousingNumBathroom = addHousingDialog.findViewById(R.id.txtAddHousing_numBathroom);
        txtAddHousingBuildingManagerPhone = addHousingDialog.findViewById(R.id.txtAddHousing_buildingManagerPhone);
        txtAddHousingBuildingManager = addHousingDialog.findViewById(R.id.txtAddHousing_buildingManager);
        txtAddHousingGateCode = addHousingDialog.findViewById(R.id.txtAddHousing_gateCode);
        txtAddHousingLockCode = addHousingDialog.findViewById(R.id.txtAddHousing_lockCode);
        txtAddHousingLat = addHousingDialog.findViewById(R.id.txtAddHousing_lat);
        txtAddHousingLng = addHousingDialog.findViewById(R.id.txtAddHousing_lng);
        addHousingDialog.show();
    }

    public void HousingList() {
        houseList.clear();
        Housing_Information.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {
                    houseList.add(s.getValue(HousingInfoClass.class).getName());
                }
                UpdateSpinnerList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void UpdateSpinnerList() {
        spinnerSetHousing = (Spinner) findViewById(R.id.spinnerSetHousing);
        if(dataAdapter != null) {
            dataAdapter.clear();
            dataAdapter.addAll(houseList);
            dataAdapter.notifyDataSetChanged();

        }
        else
        {
            dataAdapter = new ArrayAdapter<String>(HousingActivity.this, android.R.layout.simple_list_item_1);
            dataAdapter.addAll(houseList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerSetHousing.setAdapter(dataAdapter);
            dataAdapter.notifyDataSetChanged();
        }


    }

    public void SetHousing(View view) {
        Log.d(TAG, "SetHousing: "+spinnerSetHousing.getSelectedItem().toString());

        Housing_Information.orderByChild("name").equalTo(spinnerSetHousing.getSelectedItem().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getChildren();
                for(DataSnapshot s: dataSnapshot.getChildren())
                {
                    Housing_Refrence = s.getKey();
                }
                Toast.makeText(HousingActivity.this, "Housing Set Succeffully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HousingActivity.this, TeamActivity.class);
                intent.putExtra(getString(R.string.Housing_Refrence),Housing_Refrence);
                intent.putExtra(getString(R.string.consultant_Refrence),consultant_ref);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
