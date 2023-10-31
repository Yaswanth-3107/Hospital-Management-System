package com.navaneeth.medical_managament;

public class SpecialistDepartments extends Doctor {

    public SpecialistDepartments(int id, String name, String specialist, int yearsOfExperience, String qualification,
            int salary, int roomNo) {
        super(id, name, specialist, yearsOfExperience, qualification, salary, roomNo);
        // TODO Auto-generated constructor stub
    }

    // private String Specialist;
    private String Department;
    private int DepartmentNo;
    private String position;

    public SpecialistDepartments(int id, String name, String specialist, int yearsOfExperience, String qualification,
            int salary, int roomNo, String department, int departmentNo, String position) {
        super(id, name, specialist, yearsOfExperience, qualification, salary, roomNo);
        // Specialist = specialist;
        Department = department;
        DepartmentNo = departmentNo;
        this.position = position;
    }

    public SpecialistDepartments(String studLine, String department, int departmentNo, String position) {
        super(studLine);
        Department = department;
        DepartmentNo = departmentNo;
        this.position = position;
    }



    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public int getDepartmentNo() {
        return DepartmentNo;
    }

    public void setDepartmentNo(int departmentNo) {
        DepartmentNo = departmentNo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
