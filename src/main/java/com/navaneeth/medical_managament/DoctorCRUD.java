package com.navaneeth.medical_managament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorCRUD {
    public static void CreateDoctor(Doctor d) {
        Connection conn = ConnectionDB.getConnection();
        final String tablesql = "CREATE TABLE IF NOT EXISTS doctors  (Doctor_id INT  PRIMARY KEY,Doctor_Name VARCHAR (50) NOT NULL,Specialist varchar(50) NOT NULL,yearsOfExperience int NOT NULL,Qualification VARCHAR (50) NOT NULL,Salary int NOT NULL,roomNo INT NOT NULL)";

        try {
            java.sql.Statement sta = conn.createStatement();
            sta.execute(tablesql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final String createsql = "Insert into doctors values(?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(createsql)) {
            stmt.setInt(1, d.getId());
            stmt.setString(2, d.getName());
            stmt.setString(3, d.getSpecialist());
            stmt.setInt(4, d.getYearsOfExperience());
            stmt.setString(5, d.getQualification());
            stmt.setInt(6, d.getSalary());
            stmt.setInt(7, d.getRoomNo());
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " doctor rows created.");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static ObservableList<Doctor> ReadDoctor() {
        ObservableList<Doctor> d= FXCollections.observableArrayList();
        final String sql1 = "Select * from doctors";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7));

                d.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;

    }

    public static ObservableList<Department> ReadDepartments() {
        ObservableList<Department> d= FXCollections.observableArrayList();
        final String sql1 = "Select * from specialists_departments";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doctor=DoctorCRUD.getDoctorByid(String.valueOf(rs.getInt(1)));

               SpecialistDepartments doc = new SpecialistDepartments(rs.getInt(1), rs.getString(2),doctor.getSpecialist(),doctor.getYearsOfExperience(),doctor.getQualification(),doctor.getSalary(),doctor.getRoomNo(), rs.getString(3), rs.getInt(4), rs.getString(5));
                Department dept=new Department(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
                d.add(dept);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;

    }
    public static Doctor getDoctorByid(String id) throws SQLException {
        final String sql ="select * from doctors where Doctor_id=?";
        Connection conn =ConnectionDB.getConnection();
        Doctor d=null;
        try(PreparedStatement st=conn.prepareStatement(sql)){
            st.setInt(1,Integer.valueOf(id));
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                /*int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
            int roomNo*/
                d = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7));
            }
        }catch (SQLException e){
            e.getLocalizedMessage();
        }
        return d;
        }
    public static Department getDeptByid(String id) throws SQLException {
        final String sql ="select * from specialists_Departments where Doctor_id=?";
        Connection conn =ConnectionDB.getConnection();
        Department d=null;
        try(PreparedStatement st=conn.prepareStatement(sql)){
            st.setInt(1,Integer.valueOf(id));
            ResultSet rs=st.executeQuery();
            while (rs.next()){

                d = new Department(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5));
            }
        }catch (SQLException e){
            e.getLocalizedMessage();
        }
        return d;
    }

    public static ObservableList<Doctor> ListDoctorBySpeciality(String speciality) {
        ObservableList<Doctor> specialists = FXCollections.observableArrayList();
        final String sql1 = "Select * from doctors where Specialist=?";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {
            stmt.setString(1, speciality);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7));

                specialists.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialists;

    }
    public static ObservableList<Doctor> ListDoctorByExperience(String exp) {
        ObservableList<Doctor> specialists = FXCollections.observableArrayList();
        final String sql1 = "Select * from doctors where yearsOfExperience" + exp+";";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7));

                specialists.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialists;

    }
    public static ObservableList<Doctor> ListDoctorBySalary(String sal) {
        ObservableList<Doctor> specialists = FXCollections.observableArrayList();
        final String sql1 = "Select * from doctors where Salary"+sal+";";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getInt(7));

                specialists.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialists;

    }
    public static ObservableList<Department> ListDoctorByDepartment(String dept) {
        ObservableList<Department> specialists = FXCollections.observableArrayList();
        final String sql1 = "Select * from specialists_departments where Department=?";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {
            stmt.setString(1,dept);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Department doc = new Department(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));

                specialists.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialists;

    }
    public static ObservableList<Department> ListDoctorByDeptNo(String deptNo) {
        ObservableList<Department> specialists = FXCollections.observableArrayList();
        final String sql1 = "Select * from specialists_departments where DeptNO=?";
        Connection con = ConnectionDB.getConnection();
        try (PreparedStatement stmt = con.prepareStatement(sql1)) {
            stmt.setInt(1,Integer.parseInt(deptNo));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Department doc = new Department(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));

                specialists.add(doc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return specialists;

    }

    public static void CreateSpecialists(SpecialistDepartments s) {
        Connection conn = ConnectionDB.getConnection();
        final String SQL2 = "CREATE TABLE IF NOT EXISTS Specialists_departments (Doctor_id INT ,Doctor_Name VARCHAR (50) NOT NULL,Department varchar(50) NOT NULL,DeptNO int NOT NULL,Position varchar(50) NOT NULL,FOREIGN KEY (Doctor_id) REFERENCES doctors(Doctor_id))";
        try {
            java.sql.Statement sta = conn.createStatement();
            sta.execute(SQL2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final String createsql = "Insert into Specialists_departments values(?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(createsql)) {
            stmt.setInt(1, s.getId());
            stmt.setString(2, (s).getName());
            stmt.setString(3, (s).getDepartment());
            stmt.setInt(4, (s).getDepartmentNo());
            stmt.setString(5, s.getPosition());
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " doctor rows created.");
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public static void updateDoctor(Doctor d) {
        Connection con = ConnectionDB.getConnection();
        String sql = "UPDATE doctors set roomNo=? where Name=? And Doctor_id=?";
       // String sql2 = "UPDATE " + d.getSpecialist() + " set roomNo=? where Name=? And Specialist=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, d.getRoomNo());
            stmt.setString(2, d.getName());
            stmt.setString(3, d.getSpecialist());

            int rowsAffected = stmt.executeUpdate();

            System.out.println(rowsAffected + " rows updated in doctors table");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
           int roomNo*/
    public static boolean updateDoctor(int id,String name,String sp,int yoe,String q,int sl,int rn) {
        Connection con = ConnectionDB.getConnection();
        String sql = "UPDATE doctors set Doctor_Name=?,Specialist=?,yearsOfExperience=?,Qualification=?Salary=?,roomNo=? where Doctor_id=?";

        try (PreparedStatement stmt = con.prepareStatement(sql); PreparedStatement stmt2 = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2,sp);
            stmt.setInt(3,yoe);
            stmt.setString(4, q);
            stmt.setInt(5, sl);
            stmt.setInt(6, rn);
            stmt.setInt(7, id);

            int rowsAffected = stmt.executeUpdate();

            System.out.println(rowsAffected + " rows updated in doctors table");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
    public static boolean updateDepartment(int id,String name,String dept,int deptNo,String position) {
        Connection con = ConnectionDB.getConnection();
        String sql = "UPDATE specialists_Departments set Doctor_Name=?,Department=?,DeptNO=?,Position=? where Doctor_id=?";
/*Integer id, String name, String department, Integer deptNo, String position*/
        try (PreparedStatement stmt = con.prepareStatement(sql); PreparedStatement stmt2 = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2,dept);
            stmt.setInt(3,deptNo);
            stmt.setString(4, position);
            stmt.setInt(5, id);


            int rowsAffected = stmt.executeUpdate();

            System.out.println(rowsAffected + " rows updated in specialists_departments table");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
    public static boolean deleteDoctor(String id) {
        Connection con = ConnectionDB.getConnection();
        String sql = "delete from doctors where Doctor_id=?";
        String sql2="delete from specialists_departments where Doctor_id=?";
        /*Integer id, String name, String department, Integer deptNo, String position*/
        try (PreparedStatement stmt2 = con.prepareStatement(sql2); PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt2.setInt(1, Integer.parseInt(id));
            stmt.setInt(1, Integer.parseInt(id));




            int rowsAffected = stmt2.executeUpdate();
            int rowsAffected2 = stmt.executeUpdate();

            System.out.println(rowsAffected + " rows updated in doctors table");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }
    public static boolean deleteDepartment(String id) {
        Connection con = ConnectionDB.getConnection();

        String sql2="delete from specialists_departments where Doctor_id=?";
        /*Integer id, String name, String department, Integer deptNo, String position*/
        try (PreparedStatement stmt = con.prepareStatement(sql2); ) {
            stmt.setInt(1, Integer.parseInt(id));


            int rowsAffected = stmt.executeUpdate();


            System.out.println(rowsAffected + " rows updated in department table");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }


    public static void meetDoctor(patientList p) {
        if (p.getSetPrescription().equals("No")) {
            prescription.addPrescription(p);
            patientscrud.updatePatient(p);
        } else {
            prescription.updatePrescription(p);
        }
    }

}
