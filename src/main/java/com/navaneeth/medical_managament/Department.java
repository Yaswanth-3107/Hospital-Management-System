package com.navaneeth.medical_managament;

public class Department {
    private Integer id;
    private  String Name;
    private String Department;
    private Integer deptNo;
    private String Position;

    public Department(Integer id, String name, String department, Integer deptNo, String position) {
        this.id = id;
        Name = name;
        Department = department;
        this.deptNo = deptNo;
        Position = position;
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

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public Integer getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Integer deptNo) {
        this.deptNo = deptNo;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }
}
