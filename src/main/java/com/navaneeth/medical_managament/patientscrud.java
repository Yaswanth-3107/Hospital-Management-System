package com.navaneeth.medical_managament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class patientscrud {
    public static void AddPatient(patientList p) {
        if(p.getPname()!=null) {
            Connection conn = ConnectionDB.getConnection();
            final String tablesql = "CREATE TABLE IF NOT EXISTS patients  (Pid INT PRIMARY KEY AUTO_INCREMENT,Name VARCHAR (50) NOT NULL,Did VARCHAR (50) NOT NULL,symptoms TEXT NOT NULL,gender VARCHAR(50) NOT NULL,setPrescription VARCHAR(10) NOT NULL,phoneNumber varchar(50))";
            try {
                java.sql.Statement sta = conn.createStatement();
                sta.execute(tablesql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            final String createsql = "Insert into patients(Name,Did,symptoms,gender,setPrescription,phoneNumber) values(?,?,?,?,?,?)";
            try (PreparedStatement stmt = conn.prepareStatement(createsql)) {
                // stmt.setInt(1, p.getPid());
                stmt.setString(1, p.getPname());
                stmt.setInt(2, p.getDid());
                stmt.setString(3, p.getSymptoms());
                stmt.setString(4, p.getGender());
                stmt.setString(5, p.getSetPrescription());
                stmt.setString(6, p.getPhoneNumber());
                int rowsAffected = stmt.executeUpdate();
                System.out.println(rowsAffected + " patients rows created.");
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public static ObservableList<patientList> ReadPatients() {
        ObservableList<patientList> p = FXCollections.observableArrayList();
        Connection con = ConnectionDB.getConnection();
        final String sql = "select * from patients";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patientList pat = new patientList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
                p.add(pat);
            }
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
        return p;
    }

    public static void updatePatient(patientList p) {
        Connection con = ConnectionDB.getConnection();

        final String sql = "UPDATE patients set setPrescription='Yes' where Pid=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, p.getPid());
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " paients rows was updated");

        } catch (Exception e) {
            System.out.println("there was a problem in updating patient record " + e.getLocalizedMessage());
        }
    }
    public static boolean deletePatient(String id) {
        Connection con = ConnectionDB.getConnection();

        final String sql = "Delete from patients where Pid=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(id));
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " patient rows was deleted successfully");
            return  true;

        } catch (Exception e) {
            System.out.println("there was a problem in deleting record " + e.getLocalizedMessage());
        }
        return false;
    }

    public static boolean updatePatientbyid(Integer id,String Name,Integer did,String sym,String g,String pre,String pn) {
        Connection con = ConnectionDB.getConnection();
        patientList patient;
        final String sql = "UPDATE patients set Name=?,Did=?,symptoms=?,gender=?,setPrescription=?,phoneNumber=? where Pid=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, Name);
            stmt.setInt(2,did);
            stmt.setString(3,sym);
            stmt.setString(4,g);
            stmt.setString(5,pre);
            stmt.setString(6,pn);
            stmt.setInt(7,id);
            int rowsAffected=stmt.executeUpdate();
            System.out.println(rowsAffected + " paients rows was updated by setting prescription to yes");
            return  true;

        } catch (Exception e) {
            System.out.println("there was a problem in setting prescription " + e.getLocalizedMessage());
            return false;
        }
    }

    public static ObservableList<patientList> getPatientByDid(String id) {
        ObservableList<patientList> patients = FXCollections.observableArrayList();
        Connection con = ConnectionDB.getConnection();
        final String sql = "Select * from patients where Did=?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, Integer.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                patients.add(new patientList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7)));
            }

        } catch (Exception e) {
            System.out.println("there was a problem in getting patient information " + e.getLocalizedMessage());
        }
        return patients;
    }

    public static int getPatientcount(String id) {
        Connection con = ConnectionDB.getConnection();
        final String sql = "Select count(Pid) from patients where Did=?";
        int count = 0;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, Integer.valueOf(id));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                System.out.println(rs.getInt(1) + " patients have booked an appointment today");
            }

        } catch (Exception e) {
            System.out.println("there was a problem in getting patient count" + e.getLocalizedMessage());
        }
        return count;
    }
    public static patientList getPatientById(String id) throws SQLException {
        Connection conn=ConnectionDB.getConnection();
        patientList p = null;
        final String query="select * from patients where Pid=?";
        try(PreparedStatement st=conn.prepareStatement(query)){
            st.setInt(1,Integer.valueOf(id));
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                p = new patientList(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
        }catch (SQLException e){
            e.getLocalizedMessage();
        }
        return p;
    }
}
// java -cp C:/path/to/external.jar MyApp
/*
 * java MyApp java -jar MyExecutable.jar java -jar MyExecutable.jar data.csv
 */