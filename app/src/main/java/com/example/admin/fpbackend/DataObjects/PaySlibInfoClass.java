package com.example.admin.fpbackend.DataObjects;

import java.util.Date;

/**
 * Created by Admin on 10/16/2017.
 */

public class PaySlibInfoClass {

    private float totalHours;
    private float holidayHours;
    private float ptoUsedHours;
    private float overtimeHours;

    private float hourlyRate;
    private float overtimeRate;

    private float federalTax;
    private float stateTax;
    private float socialSecurityTax;
    private float medicareTax;

    private float medicalInsurance;
    private float visionInsurance;
    private float dentalInsurance;
    private float shortTermDisabilityInsurance;
    private float longTermDisabilityInsurance;
    private float lifeInsurance;

    private Date from;
    private Date to;
    private Date payDate;

    private float expensesPay;
    private float advancesPay;
    private float advancesDeduction;

    private float totalPay;
    private float totalGrossHours;
    private float totalNetPay;
    private float totalYTDDeductions;
    private float totalDeductions;


    private float netpayYTD;
    private float grosspayYTD;

    private float federalTaxYTD;
    private float stateTaxYTD;
    private float socialSecurityTaxYTD;
    private float medicareTaxYTD;

    private float medicalInsuranceYTD;
    private float visionInsuranceYTD;
    private float dentalInsuranceYTD;
    private float shortTermDisabilityInsuranceYTD;
    private float longTermDisabilityInsuranceYTD;
    private float lifeInsuranceYTD;

    private float advancesDeductionYTD;

    public PaySlibInfoClass() {
    }

    public PaySlibInfoClass(float totalHours, float holidayHours, float ptoUsedHours, float overtimeHours, float hourlyRate, float overtimeRate, float federalTax, float stateTax, float socialSecurityTax, float medicareTax, float medicalInsurance, float visionInsurance, float dentalInsurance, float shortTermDisabilityInsurance, float longTermDisabilityInsurance, float lifeInsurance, Date from, Date to, Date payDate, float expensesPay, float advancesPay, float advancesDeduction, float totalPay, float totalGrossHours, float totalNetPay, float totalYTDDeductions, float totalDeductions, float netpayYTD, float grosspayYTD, float federalTaxYTD, float stateTaxYTD, float socialSecurityTaxYTD, float medicareTaxYTD, float medicalInsuranceYTD, float visionInsuranceYTD, float dentalInsuranceYTD, float shortTermDisabilityInsuranceYTD, float longTermDisabilityInsuranceYTD, float lifeInsuranceYTD, float advancesDeductionYTD) {
        this.totalHours = totalHours;
        this.holidayHours = holidayHours;
        this.ptoUsedHours = ptoUsedHours;
        this.overtimeHours = overtimeHours;
        this.hourlyRate = hourlyRate;
        this.overtimeRate = overtimeRate;
        this.federalTax = federalTax;
        this.stateTax = stateTax;
        this.socialSecurityTax = socialSecurityTax;
        this.medicareTax = medicareTax;
        this.medicalInsurance = medicalInsurance;
        this.visionInsurance = visionInsurance;
        this.dentalInsurance = dentalInsurance;
        this.shortTermDisabilityInsurance = shortTermDisabilityInsurance;
        this.longTermDisabilityInsurance = longTermDisabilityInsurance;
        this.lifeInsurance = lifeInsurance;
        this.from = from;
        this.to = to;
        this.payDate = payDate;
        this.expensesPay = expensesPay;
        this.advancesPay = advancesPay;
        this.advancesDeduction = advancesDeduction;
        this.totalPay = totalPay;
        this.totalGrossHours = totalGrossHours;
        this.totalNetPay = totalNetPay;
        this.totalYTDDeductions = totalYTDDeductions;
        this.totalDeductions = totalDeductions;
        this.netpayYTD = netpayYTD;
        this.grosspayYTD = grosspayYTD;
        this.federalTaxYTD = federalTaxYTD;
        this.stateTaxYTD = stateTaxYTD;
        this.socialSecurityTaxYTD = socialSecurityTaxYTD;
        this.medicareTaxYTD = medicareTaxYTD;
        this.medicalInsuranceYTD = medicalInsuranceYTD;
        this.visionInsuranceYTD = visionInsuranceYTD;
        this.dentalInsuranceYTD = dentalInsuranceYTD;
        this.shortTermDisabilityInsuranceYTD = shortTermDisabilityInsuranceYTD;
        this.longTermDisabilityInsuranceYTD = longTermDisabilityInsuranceYTD;
        this.lifeInsuranceYTD = lifeInsuranceYTD;
        this.advancesDeductionYTD = advancesDeductionYTD;
    }

    public float getNetpayYTD() {
        return netpayYTD;
    }

    public void setNetpayYTD(float netpayYTD) {
        this.netpayYTD = netpayYTD;
    }

    public float getGrosspayYTD() {
        return grosspayYTD;
    }

    public void setGrosspayYTD(float grosspayYTD) {
        this.grosspayYTD = grosspayYTD;
    }

    public float getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(float totalHours) {
        this.totalHours = totalHours;
    }

    public float getHolidayHours() {
        return holidayHours;
    }

    public void setHolidayHours(float holidayHours) {
        this.holidayHours = holidayHours;
    }

    public float getPtoUsedHours() {
        return ptoUsedHours;
    }

    public void setPtoUsedHours(float ptoUsedHours) {
        this.ptoUsedHours = ptoUsedHours;
    }

    public float getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(float overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public float getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(float overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public float getFederalTax() {
        return federalTax;
    }

    public void setFederalTax(float federalTax) {
        this.federalTax = federalTax;
    }

    public float getStateTax() {
        return stateTax;
    }

    public void setStateTax(float stateTax) {
        this.stateTax = stateTax;
    }

    public float getSocialSecurityTax() {
        return socialSecurityTax;
    }

    public void setSocialSecurityTax(float socialSecurityTax) {
        this.socialSecurityTax = socialSecurityTax;
    }

    public float getMedicareTax() {
        return medicareTax;
    }

    public void setMedicareTax(float medicareTax) {
        this.medicareTax = medicareTax;
    }

    public float getMedicalInsurance() {
        return medicalInsurance;
    }

    public void setMedicalInsurance(float medicalInsurance) {
        this.medicalInsurance = medicalInsurance;
    }

    public float getVisionInsurance() {
        return visionInsurance;
    }

    public void setVisionInsurance(float visionInsurance) {
        this.visionInsurance = visionInsurance;
    }

    public float getDentalInsurance() {
        return dentalInsurance;
    }

    public void setDentalInsurance(float dentalInsurance) {
        this.dentalInsurance = dentalInsurance;
    }

    public float getShortTermDisabilityInsurance() {
        return shortTermDisabilityInsurance;
    }

    public void setShortTermDisabilityInsurance(float shortTermDisabilityInsurance) {
        this.shortTermDisabilityInsurance = shortTermDisabilityInsurance;
    }

    public float getLongTermDisabilityInsurance() {
        return longTermDisabilityInsurance;
    }

    public void setLongTermDisabilityInsurance(float longTermDisabilityInsurance) {
        this.longTermDisabilityInsurance = longTermDisabilityInsurance;
    }

    public float getLifeInsurance() {
        return lifeInsurance;
    }

    public void setLifeInsurance(float lifeInsurance) {
        this.lifeInsurance = lifeInsurance;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public float getExpensesPay() {
        return expensesPay;
    }

    public void setExpensesPay(float expensesPay) {
        this.expensesPay = expensesPay;
    }

    public float getAdvancesPay() {
        return advancesPay;
    }

    public void setAdvancesPay(float advancesPay) {
        this.advancesPay = advancesPay;
    }

    public float getAdvancesDeduction() {
        return advancesDeduction;
    }

    public void setAdvancesDeduction(float advancesDeduction) {
        this.advancesDeduction = advancesDeduction;
    }

    public float getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(float totalPay) {
        this.totalPay = totalPay;
    }

    public float getTotalGrossHours() {
        return totalGrossHours;
    }

    public void setTotalGrossHours(float totalGrossHours) {
        this.totalGrossHours = totalGrossHours;
    }

    public float getTotalNetPay() {
        return totalNetPay;
    }

    public void setTotalNetPay(float totalNetPay) {
        this.totalNetPay = totalNetPay;
    }

    public float getTotalYTDDeductions() {
        return totalYTDDeductions;
    }

    public void setTotalYTDDeductions(float totalYTDDeductions) {
        this.totalYTDDeductions = totalYTDDeductions;
    }

    public float getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(float totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public float getFederalTaxYTD() {
        return federalTaxYTD;
    }

    public void setFederalTaxYTD(float federalTaxYTD) {
        this.federalTaxYTD = federalTaxYTD;
    }

    public float getStateTaxYTD() {
        return stateTaxYTD;
    }

    public void setStateTaxYTD(float stateTaxYTD) {
        this.stateTaxYTD = stateTaxYTD;
    }

    public float getSocialSecurityTaxYTD() {
        return socialSecurityTaxYTD;
    }

    public void setSocialSecurityTaxYTD(float socialSecurityTaxYTD) {
        this.socialSecurityTaxYTD = socialSecurityTaxYTD;
    }

    public float getMedicareTaxYTD() {
        return medicareTaxYTD;
    }

    public void setMedicareTaxYTD(float medicareTaxYTD) {
        this.medicareTaxYTD = medicareTaxYTD;
    }

    public float getMedicalInsuranceYTD() {
        return medicalInsuranceYTD;
    }

    public void setMedicalInsuranceYTD(float medicalInsuranceYTD) {
        this.medicalInsuranceYTD = medicalInsuranceYTD;
    }

    public float getVisionInsuranceYTD() {
        return visionInsuranceYTD;
    }

    public void setVisionInsuranceYTD(float visionInsuranceYTD) {
        this.visionInsuranceYTD = visionInsuranceYTD;
    }

    public float getDentalInsuranceYTD() {
        return dentalInsuranceYTD;
    }

    public void setDentalInsuranceYTD(float dentalInsuranceYTD) {
        this.dentalInsuranceYTD = dentalInsuranceYTD;
    }

    public float getShortTermDisabilityInsuranceYTD() {
        return shortTermDisabilityInsuranceYTD;
    }

    public void setShortTermDisabilityInsuranceYTD(float shortTermDisabilityInsuranceYTD) {
        this.shortTermDisabilityInsuranceYTD = shortTermDisabilityInsuranceYTD;
    }

    public float getLongTermDisabilityInsuranceYTD() {
        return longTermDisabilityInsuranceYTD;
    }

    public void setLongTermDisabilityInsuranceYTD(float longTermDisabilityInsuranceYTD) {
        this.longTermDisabilityInsuranceYTD = longTermDisabilityInsuranceYTD;
    }

    public float getLifeInsuranceYTD() {
        return lifeInsuranceYTD;
    }

    public void setLifeInsuranceYTD(float lifeInsuranceYTD) {
        this.lifeInsuranceYTD = lifeInsuranceYTD;
    }

    public float getAdvancesDeductionYTD() {
        return advancesDeductionYTD;
    }

    public void setAdvancesDeductionYTD(float advancesDeductionYTD) {
        this.advancesDeductionYTD = advancesDeductionYTD;
    }

    public void calcTotalGrossHours(){
        float totalhours;
        totalhours = getTotalHours() + getHolidayHours() + getPtoUsedHours() + getOvertimeHours();
        setTotalGrossHours(totalhours);
    }
    public void calcTotalGrossPay(){
        float totalGrosspay;
        totalGrosspay = (getHourlyRate() * (getTotalHours() + getHolidayHours() + getPtoUsedHours()) ) + (getOvertimeHours() * getOvertimeRate()) + getExpensesPay() + getAdvancesPay();
        setTotalPay(totalGrosspay);
    }
    public void calcDeductions(){
        float deductions;
        deductions = getFederalTax() + getStateTax() + getSocialSecurityTax() + getMedicareTax()
                + getMedicalInsurance() + getVisionInsurance() + getDentalInsurance() + getLifeInsurance()
                +getLongTermDisabilityInsurance() + getShortTermDisabilityInsurance() + getAdvancesDeduction();
        setTotalDeductions(deductions);
    }
    public void calcTotalNetPay(){
        setTotalNetPay(getTotalPay() - getTotalDeductions());
    }

    public void calcTotalDedYTD(){
        float TotalDecYTD;
        TotalDecYTD = getFederalTaxYTD() + getStateTaxYTD() + getSocialSecurityTaxYTD() + getMedicareTaxYTD() + getMedicalInsuranceYTD()
                +getVisionInsuranceYTD() + getDentalInsuranceYTD() + getShortTermDisabilityInsuranceYTD() + getLongTermDisabilityInsuranceYTD()
                +getLifeInsuranceYTD() + getAdvancesDeductionYTD();
        setTotalYTDDeductions(TotalDecYTD);
    }
}
