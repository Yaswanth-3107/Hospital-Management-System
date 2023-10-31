package com.navaneeth.medical_managament;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class patientList {
    private Integer pid=-1;
    private String pname;
    private Integer Did;
    private String symptoms;
    private String gender;
    private String setPrescription;
    private String phoneNumber;


    //System.out.println("Today's date : " + todaysdate);
    public patientList(int pid, String pname, int did, String symptoms, String gender, String setPrescription,
                       String phoneNumber) {
        this.pid = pid;
        this.pname = pname;
        Did = did;
        this.symptoms = symptoms;
        this.gender = gender;
        this.setPrescription = setPrescription;
        this.phoneNumber = phoneNumber;
    }

    public patientList(String pname, int did, String symptoms, String gender, String setPrescription, String phoneNumber) {
        this.pname = pname;
        Did = did;
        this.symptoms = symptoms;
        this.gender = gender;
        this.setPrescription = setPrescription;
        this.phoneNumber = phoneNumber;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getDid() {
        return Did;
    }

    public void setDid(int did) {
        Did = did;
    }

    public String getGender() {
        return gender;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSetPrescription() {
        return setPrescription;
    }

    public void setSetPrescription(String setPrescription) {
        this.setPrescription = setPrescription;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "patientList{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", Did=" + Did +
                ", symptoms='" + symptoms + '\'' +
                ", gender='" + gender + '\'' +
                ", setPrescription='" + setPrescription + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +

                '}';
    }

    public patientList(String studLine) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String todaysdate = dateFormat.format(date);
            String values[] = studLine.split(",");
            if (todaysdate.equals(values[0])) {
                this.pname = values[1];
                this.Did = Integer.parseInt(values[2]);
                this.symptoms = values[3];
                this.gender = values[4];
                this.setPrescription = values[5];
                this.phoneNumber = values[6];
            }



    }

}
