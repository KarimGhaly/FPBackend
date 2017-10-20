package com.example.admin.fpbackend.Benfits;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.DataObjects.InsuranceInfoClass;
import com.example.admin.fpbackend.DataObjects.PTOInfoClass;
import com.example.admin.fpbackend.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Benfits extends AppCompatActivity {
    public static final String TAG = "AddBenfeitsTAG";

    @BindView(R.id.spSetSpinnerConsultant)
    Spinner spSetSpinnerConsultant;

    FirebaseDatabase database;
    DatabaseReference Consultant_Information;
    ArrayAdapter adapter;
    HashMap<String, String> ConsultantUID = new HashMap<String, String>();
    @BindView(R.id.etPtoInput_totalPto)
    EditText etPtoInputTotalPto;
    @BindView(R.id.etPtoInput_usedPto)
    EditText etPtoInputUsedPto;
    @BindView(R.id.etPtoInput_remainingPto)
    EditText etPtoInputRemainingPto;
    @BindView(R.id.btnSubmitPtoInfo)
    Button btnSubmitPtoInfo;
    @BindView(R.id.spinnerInsuranceInput_type)
    Spinner spinnerInsuranceInputType;
    @BindView(R.id.etInsuranceInput_companyName)
    EditText etInsuranceInputCompanyName;
    @BindView(R.id.etInsuranceInput_packageName)
    EditText etInsuranceInputPackageName;
    @BindView(R.id.etInsuranceInput_packageDetailsUrl)
    EditText etInsuranceInputPackageDetailsUrl;
    @BindView(R.id.btnInsertInsurance_submitInsurance)
    Button btnInsertInsuranceSubmitInsurance;
    @BindView(R.id.btnSelectConsultant)
    Button btnSelectConsultant;
    @BindView(R.id.BenfitsRV)
    RecyclerView BenfitsRV;
    private String uid;
    private DatabaseReference benfits_refrence;

    List<InsuranceInfoClass> insuranceList = new ArrayList<>();
    private RecyclerViewAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benfits);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        Consultant_Information = database.getReference("Consultant_Information");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        getConsultants();
    }

    private void getConsultants() {
        adapter.clear();
        ConsultantUID.clear();
        Consultant_Information.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    ConsultantInfoClass c = D.getValue(ConsultantInfoClass.class);
                    String Full_Name = c.getFirstName() + " " + c.getLastName();
                    ConsultantUID.put(Full_Name, D.child("uid").getValue(String.class));
                    adapter.add(Full_Name);
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spSetSpinnerConsultant.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void SelectConsultant(View view) {
        btnSubmitPtoInfo.setEnabled(true);
        uid = ConsultantUID.get(spSetSpinnerConsultant.getSelectedItem().toString());
        benfits_refrence = database.getReference("Consultants_Records").child(uid).child("Training Phase").child("Benefits");
        benfits_refrence.child("PTO").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PTOInfoClass ptoInfoClass = null;
                ptoInfoClass = dataSnapshot.getValue(PTOInfoClass.class);
                updatePtoUI(ptoInfoClass);
                benfits_refrence.child("PTO").removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        benfits_refrence.child("Insurance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                insuranceList.clear();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    insuranceList.add(D.getValue(InsuranceInfoClass.class));
                }
                updateRecyclerView();
                btnInsertInsuranceSubmitInsurance.setEnabled(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void updatePtoUI(PTOInfoClass ptoInfoClass) {
        if (ptoInfoClass != null) {
            etPtoInputTotalPto.setText(String.valueOf(ptoInfoClass.getTotalPTO()));
            etPtoInputRemainingPto.setText(String.valueOf(ptoInfoClass.getRemainingPTO()));
            etPtoInputUsedPto.setText(String.valueOf(ptoInfoClass.getUsedPTO()));
            btnSubmitPtoInfo.setEnabled(true);

        } else {
            btnSubmitPtoInfo.setEnabled(true);
        }

    }

   public void updateRecyclerView()
    {
        Log.d(TAG, "updateRecyclerView: "+insuranceList.size());
        rvAdapter = new RecyclerViewAdapter(insuranceList);
        BenfitsRV.setAdapter(rvAdapter);
        BenfitsRV.setLayoutManager(new LinearLayoutManager(Benfits.this));
        BenfitsRV.setItemAnimator(new DefaultItemAnimator());
    }

    public void SavePTOs(View view) {
        PTOInfoClass ptoInfoClass = new PTOInfoClass();
        ptoInfoClass.setTotalPTO(Integer.valueOf(etPtoInputTotalPto.getText().toString()));
        ptoInfoClass.setRemainingPTO(Integer.valueOf(etPtoInputRemainingPto.getText().toString()));
        ptoInfoClass.setUsedPTO(Integer.valueOf(etPtoInputUsedPto.getText().toString()));
        benfits_refrence.child("PTO").setValue(ptoInfoClass, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(Benfits.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Benfits.this, "Error", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onComplete: " + databaseError.getMessage());
                }
            }
        });
    }

    public void addToList(View view) {
        boolean found = false;
        if (insuranceList.size() > 0) {
            for (InsuranceInfoClass insurance : insuranceList) {
                if (insurance.getType().equals(spinnerInsuranceInputType.getSelectedItem().toString())) {
                    insurance.setCompanyName(etInsuranceInputCompanyName.getText().toString());
                    insurance.setPackageName(etInsuranceInputPackageName.getText().toString());
                    insurance.setPackageDetailsUrl("www.google.com");
                    found = true;
                    rvAdapter.notifyDataSetChanged();
                }
            }
            if (!found) {
                InsuranceInfoClass insurance = new InsuranceInfoClass();
                insurance.setType(spinnerInsuranceInputType.getSelectedItem().toString());
                insurance.setOrder(spinnerInsuranceInputType.getSelectedItemPosition());
                insurance.setCompanyName(etInsuranceInputCompanyName.getText().toString());
                insurance.setPackageName(etInsuranceInputPackageName.getText().toString());
                insurance.setPackageDetailsUrl("www.google.com");
                insuranceList.add(insurance);
                rvAdapter.notifyDataSetChanged();
            }
        } else {
            InsuranceInfoClass insurance = new InsuranceInfoClass();
            insurance.setType(spinnerInsuranceInputType.getSelectedItem().toString());
            insurance.setOrder(spinnerInsuranceInputType.getSelectedItemPosition());
            insurance.setCompanyName(etInsuranceInputCompanyName.getText().toString());
            insurance.setPackageName(etInsuranceInputPackageName.getText().toString());
            insurance.setPackageDetailsUrl("www.google.com");
            insuranceList.add(insurance);
            rvAdapter.notifyDataSetChanged();
        }
    }

    public void SaveInsuranceinDB(View view) {
        benfits_refrence.child("Insurance").setValue(insuranceList, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(Benfits.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Benfits.this, "Failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onComplete: " + databaseError.getMessage());
                }
            }
        });
    }
}
