package com.example.admin.fpbackend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.fpbackend.DataObjects.ConsultantInfoClass;
import com.example.admin.fpbackend.DataObjects.PaySlibInfoClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FinanceActivity extends AppCompatActivity {

    public static final String TAG = "FinanceActivityTAG";

    FirebaseDatabase database;
    DatabaseReference Consultant_Information;
    List<String> consultantsList = new ArrayList<>();
    @BindView(R.id.spSetSpinnerConsultant)
    Spinner spSetSpinnerConsultant;
    @BindView(R.id.etAddFinance_totalHours)
    EditText etAddFinanceTotalHours;
    @BindView(R.id.etAddFinance_holidayHours)
    EditText etAddFinanceHolidayHours;
    @BindView(R.id.etAddFinance_pto)
    EditText etAddFinancePto;
    @BindView(R.id.etAddFinance_overtimeHours)
    EditText etAddFinanceOvertimeHours;
    @BindView(R.id.etAddFinance_hourlyRate)
    EditText etAddFinanceHourlyRate;
    @BindView(R.id.etAddFinance_overtimeRate)
    EditText etAddFinanceOvertimeRate;
    @BindView(R.id.etAddFinance_federalTax)
    EditText etAddFinanceFederalTax;
    @BindView(R.id.etAddFinance_stateTax)
    EditText etAddFinanceStateTax;
    @BindView(R.id.etAddFinance_socialSecurityTax)
    EditText etAddFinanceSocialSecurityTax;
    @BindView(R.id.etAddFinance_medicareTax)
    EditText etAddFinanceMedicareTax;
    @BindView(R.id.etAddFinance_medicalInsurance)
    EditText etAddFinanceMedicalInsurance;
    @BindView(R.id.etAddFinance_visionInsurance)
    EditText etAddFinanceVisionInsurance;
    @BindView(R.id.etAddFinance_dentalInsurance)
    EditText etAddFinanceDentalInsurance;
    @BindView(R.id.etAddFinance_shortTermDisabilityInsurance)
    EditText etAddFinanceShortTermDisabilityInsurance;
    @BindView(R.id.etAddFinance_longTermDisabilityInsurance)
    EditText etAddFinanceLongTermDisabilityInsurance;
    @BindView(R.id.etAddFinance_lifeInsurance)
    EditText etAddFinanceLifeInsurance;
    @BindView(R.id.etAddFinance_expensePay)
    EditText etAddFinanceExpensePay;
    @BindView(R.id.etAddFinance_advancePay)
    EditText etAddFinanceAdvancePay;
    @BindView(R.id.etAddFinance_advanceDeduction)
    EditText etAddFinanceAdvanceDeduction;
    @BindView(R.id.etAddFinance_dateFrom)
    EditText etAddFinanceDateFrom;
    @BindView(R.id.etAddFinance_dateTo)
    EditText etAddFinanceDateTo;
    @BindView(R.id.etAddFinance_payDate)
    EditText etAddFinancePayDate;

    ArrayAdapter<String> adapter;

    HashMap<String ,String> ConsultantUID = new HashMap<String,String>();
    private String uid;

    private float lastGrossYTD = 0;
    private float lastNetYTD = 0;
    private float lastfederalTaxYTD = 0;
    private float laststateTaxYTD = 0;
    private float lastsocialSecurityTaxYTD = 0;
    private float lastmedicareTaxYTD = 0;

    private float lastmedicalInsuranceYTD = 0;
    private float lastvisionInsuranceYTD = 0;
    private float lastdentalInsuranceYTD = 0;
    private float lastshortTermDisabilityInsuranceYTD = 0;
    private float lastlongTermDisabilityInsuranceYTD = 0;
    private float lastlifeInsuranceYTD = 0;

    private float lastadvancesDeductionYTD = 0;
    private DatabaseReference payslibRefrence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        Consultant_Information = database.getReference("Consultant_Information");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        getConsultants();

    }
    ValueEventListener getlastPaySlip = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            PaySlibInfoClass lastPaySlip = null;
            for(DataSnapshot D: dataSnapshot.getChildren()) {
               lastPaySlip = D.getValue(PaySlibInfoClass.class);
            }
            if (lastPaySlip!=null) {
                    lastGrossYTD = lastPaySlip.getGrosspayYTD();
                    lastNetYTD = lastPaySlip.getNetpayYTD();
                    lastfederalTaxYTD = lastPaySlip.getFederalTaxYTD();
                    laststateTaxYTD = lastPaySlip.getStateTaxYTD();
                    lastsocialSecurityTaxYTD = lastPaySlip.getSocialSecurityTaxYTD();
                    lastmedicareTaxYTD = lastPaySlip.getMedicareTaxYTD();
                    lastmedicalInsuranceYTD = lastPaySlip.getMedicalInsuranceYTD();
                    lastvisionInsuranceYTD = lastPaySlip.getVisionInsuranceYTD();
                    lastdentalInsuranceYTD = lastPaySlip.getDentalInsuranceYTD();
                    lastshortTermDisabilityInsuranceYTD = lastPaySlip.getShortTermDisabilityInsuranceYTD();
                    lastlongTermDisabilityInsuranceYTD = lastPaySlip.getLongTermDisabilityInsuranceYTD();
                    lastlifeInsuranceYTD = lastPaySlip.getLifeInsuranceYTD();
                    lastadvancesDeductionYTD = lastPaySlip.getAdvancesDeductionYTD();
                     AddPayslib();
                    payslibRefrence.orderByKey().limitToLast(1).removeEventListener(this);
            }
            else
            {
                Log.d(TAG, "onDataChange: First Time");
                AddPayslib();
                payslibRefrence.orderByKey().limitToLast(1).removeEventListener(this);
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void getlastpayslib(){
        payslibRefrence.orderByKey().limitToLast(1).addValueEventListener(getlastPaySlip);

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
                    ConsultantUID.put(Full_Name,D.child("uid").getValue(String.class));
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

    public void addFinance(View view) {
        uid = ConsultantUID.get(spSetSpinnerConsultant.getSelectedItem().toString());
        payslibRefrence = database.getReference("Consultants_Records")
                .child(uid).child("Training Phase").child("Finance").child("Pay Slips");
        getlastpayslib();
    }

    public void AddPayslib(){
        PaySlibInfoClass payslibe = new PaySlibInfoClass();
        payslibe.setTotalHours(Float.valueOf(etAddFinanceTotalHours.getText().toString()));
        payslibe.setHolidayHours(Float.valueOf(etAddFinanceHolidayHours.getText().toString()));
        payslibe.setPtoUsedHours(Float.valueOf(etAddFinancePto.getText().toString()));
        payslibe.setOvertimeHours(Float.valueOf(etAddFinanceOvertimeHours.getText().toString()));
        payslibe.setHourlyRate(Float.valueOf(etAddFinanceHourlyRate.getText().toString()));
        payslibe.setOvertimeRate(Float.valueOf(etAddFinanceOvertimeRate.getText().toString()));
        payslibe.setFederalTax(Float.valueOf(etAddFinanceFederalTax.getText().toString()));
        payslibe.setStateTax(Float.valueOf(etAddFinanceStateTax.getText().toString()));
        payslibe.setSocialSecurityTax(Float.valueOf(etAddFinanceSocialSecurityTax.getText().toString()));
        payslibe.setMedicareTax(Float.valueOf(etAddFinanceMedicareTax.getText().toString()));
        payslibe.setMedicalInsurance(Float.valueOf(etAddFinanceMedicalInsurance.getText().toString()));
        payslibe.setVisionInsurance(Float.valueOf(etAddFinanceVisionInsurance.getText().toString()));
        payslibe.setDentalInsurance(Float.valueOf(etAddFinanceDentalInsurance.getText().toString()));
        payslibe.setShortTermDisabilityInsurance(Float.valueOf(etAddFinanceShortTermDisabilityInsurance.getText().toString()));
        payslibe.setLongTermDisabilityInsurance(Float.valueOf(etAddFinanceLongTermDisabilityInsurance.getText().toString()));
        payslibe.setLifeInsurance(Float.valueOf(etAddFinanceLifeInsurance.getText().toString()));
        payslibe.setExpensesPay(Float.valueOf(etAddFinanceExpensePay.getText().toString()));
        payslibe.setAdvancesPay(Float.valueOf(etAddFinanceAdvancePay.getText().toString()));
        payslibe.setAdvancesDeduction(Float.valueOf(etAddFinanceAdvanceDeduction.getText().toString()));

        SimpleDateFormat df = new SimpleDateFormat("mm/dd/yyyy");
        try {
            payslibe.setFrom(df.parse(etAddFinanceDateFrom.getText().toString()));
        } catch (ParseException e) {

            e.printStackTrace();
        }
        try {
            payslibe.setTo(df.parse(etAddFinanceDateTo.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            payslibe.setPayDate(df.parse(etAddFinancePayDate.getText().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        payslibe.calcTotalGrossPay();
        payslibe.calcDeductions();
        payslibe.calcTotalGrossHours();
        payslibe.calcTotalNetPay();
        payslibe.setGrosspayYTD(payslibe.getTotalPay() + lastGrossYTD);
        payslibe.setNetpayYTD(payslibe.getTotalNetPay()+ lastNetYTD);
        payslibe.setFederalTaxYTD(payslibe.getFederalTax() + lastfederalTaxYTD);
        payslibe.setStateTaxYTD(payslibe.getStateTax() + laststateTaxYTD);
        payslibe.setSocialSecurityTaxYTD(payslibe.getSocialSecurityTax() + lastsocialSecurityTaxYTD);
        payslibe.setMedicareTaxYTD(payslibe.getMedicareTax() + lastmedicareTaxYTD);
        payslibe.setMedicalInsuranceYTD(payslibe.getMedicalInsurance() + lastmedicalInsuranceYTD);
        payslibe.setVisionInsuranceYTD(payslibe.getVisionInsurance() + lastvisionInsuranceYTD);
        payslibe.setDentalInsuranceYTD(payslibe.getDentalInsurance() + lastdentalInsuranceYTD);
        payslibe.setShortTermDisabilityInsuranceYTD(payslibe.getShortTermDisabilityInsurance()+lastshortTermDisabilityInsuranceYTD);
        payslibe.setLongTermDisabilityInsuranceYTD(payslibe.getLongTermDisabilityInsurance()+lastlongTermDisabilityInsuranceYTD);
        payslibe.setLifeInsuranceYTD(payslibe.getLifeInsurance()+lastlifeInsuranceYTD);
        payslibe.setAdvancesDeductionYTD(payslibe.getAdvancesDeduction() + lastadvancesDeductionYTD);
        payslibe.calcTotalDedYTD();
        payslibRefrence.push().setValue(payslibe, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                Toast.makeText(FinanceActivity.this, "PaySlib Added Succefully", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
