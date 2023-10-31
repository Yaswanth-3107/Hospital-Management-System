package com.navaneeth.medical_managament;

public class Doctor {

    private Integer id;
    private String Name;
    private String specialist;
    private Integer yearsOfExperience;
    private String Qualification;
    private Integer Salary;
    private Integer roomNo;

    public Doctor(int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
            int roomNo) {
        this.id = id;
        Name = name;
        this.specialist = specialist;
        this.yearsOfExperience = yearsOfExperience;
        Qualification = qualification;
        Salary = salary;
        this.roomNo = roomNo;
    }

    public Doctor(String studLine) {
        String values[] = studLine.split(",");
        this.id = Integer.valueOf(values[0]);
        this.Name = values[1];
        this.specialist = values[2];
        this.yearsOfExperience = Integer.valueOf(values[3]);
        this.Qualification = values[4];
        this.Salary = Integer.valueOf(values[5]);
        this.roomNo = Integer.valueOf(values[6]);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public Integer getSalary() {
        return Salary;
    }

    public void setSalary(Integer salary) {
        Salary = salary;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "Doctor [Name=" + Name + ", Qualification=" + Qualification + ", Salary=" + Salary + ", id=" + id
                + ", roomNo=" + roomNo + ", yearsOfExperience=" + yearsOfExperience + "]";
    }

    // abstract void specification();
}
