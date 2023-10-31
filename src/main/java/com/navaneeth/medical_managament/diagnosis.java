package com.navaneeth.medical_managament;

public class diagnosis {
    Integer Pid;
    String PatientName;
    Integer Did;
    String symptoms;
    String gender;
    String disease;
    String prescription;
    String medicine;
    Integer fee;

    public diagnosis(Integer pid, String patientName, Integer did, String symptoms, String gender, String disease, String prescription, String medicine, Integer fee) {
        Pid = pid;
        PatientName = patientName;
        Did = did;
        this.symptoms = symptoms;
        this.gender = gender;
        this.disease = disease;
        this.prescription = prescription;
        this.medicine = medicine;
        this.fee = fee;
    }

    public Integer getPid() {
        return Pid;
    }

    public void setPid(Integer pid) {
        Pid = pid;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public Integer getDid() {
        return Did;
    }

    public void setDid(Integer did) {
        Did = did;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        fee = fee;
    }
}
