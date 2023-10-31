package com.navaneeth.medical_managament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

class diseasePrescription {
    String dis;
    String pres;
    String medicine;
    Integer fee;

    @Override
    public String toString() {
        return "diseasePrescription [dis=" + dis + ", pres=" + pres + "]";
    }

}

abstract class prescriptions {
    private Integer pid;
    private Integer did;
    protected String symptoms;
    Map<String, Integer> medicineList = diseasefile();
    Map<String, diseasePrescription> symptomsmap = symptomsfile();

    abstract Map<String, Integer> diseasefile();

    abstract Map<String, diseasePrescription> symptomsfile();

    public prescriptions(int pid, int did, String symptoms) {
        this.pid = pid;
        this.did = did;
        this.symptoms = symptoms;

    }

    abstract diseasePrescription searchdisease();

}

public class prescription extends prescriptions {

    public prescription(int pid, int did, String symptoms) {
        super(pid, did, symptoms);

    }



    @Override
    Map<String, diseasePrescription> symptomsfile() {
        Map<String, diseasePrescription> map = new HashMap<String, diseasePrescription>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "C:\\Users\\krish\\Downloads\\MedicalProjectJavaFX\\src\\main\\java\\com\\navaneeth\\medicalsystem\\symptoms.csv"));
            String studLine;
            while ((studLine = reader.readLine()) != null) {

                String parts[] = studLine.split(":");
                String diag = parts[0].trim();
                diseasePrescription d = new diseasePrescription();
                d.dis = parts[1].trim();
                d.pres = parts[2].trim();
                d.medicine = parts[3].trim();
                d.fee = 0;
                String medlist[] = d.medicine.split(",");
                for (int i = 0; i < medlist.length; i++) {
                    d.fee += medicineList.get(medlist[i]);

                }

                if (!diag.equals("") && !d.dis.equals("") && !d.pres.equals("") && !d.medicine.equals("") && d.fee != 0)
                    map.put(diag, d);

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }

    @Override
    diseasePrescription searchdisease() {

        diseasePrescription disease = new diseasePrescription();
        disease = symptomsmap.get(this.symptoms);
        return disease;
    }

    public static void addPrescription(patientList p) {
        Connection con = ConnectionDB.getConnection();
        final String sql = "CREATE TABLE IF NOT EXISTS Prescriptions  (Pid INT Not NULL,Name VARCHAR (50) NOT NULL,Did VARCHAR (50) NOT NULL,symptoms TEXT NOT NULL,gender VARCHAR(50) NOT NULL,disease TEXT,prescription TEXT,Medicine TEXT,FEE INT NOT NULL)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected + " prescription rows created.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String sql2 = "insert into Prescriptions values(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement st = con.prepareStatement(sql2)) {
            st.setInt(1, p.getPid());
            st.setString(2, p.getPname());
            st.setInt(3, p.getDid());
            st.setString(4, p.getSymptoms());
            st.setString(5, p.getGender());
            prescription presc = new prescription(p.getPid(), p.getDid(), p.getSymptoms());
            diseasePrescription d = new diseasePrescription();
            d = presc.searchdisease();
            if ( d!=null) {
                System.out.println(d.dis);
                st.setString(6, d.dis);
                st.setString(7, d.pres);
                st.setString(8, d.medicine);
                System.out.println(d.fee);
                st.setInt(9, d.fee);
            } else {
                st.setString(6, "Add disease");
                st.setString(7, "Add Prescription");
                st.setString(8, "Add medicine");
                st.setInt(9, 5);
            }
            int rowsAffected = st.executeUpdate();
            System.out.println(rowsAffected + " Prescriptions rows created.");

        } catch (Exception e) {
            e.printStackTrace();
        }

           }

    public static void updatePrescription(patientList p) {
        Connection con = ConnectionDB.getConnection();
        final String sql = "CREATE TABLE IF NOT EXISTS Prescriptions  (Pid INT PRIMARY KEY,Name VARCHAR (50) NOT NULL,Did VARCHAR (50) NOT NULL,symptoms TEXT NOT NULL,gender VARCHAR(50) NOT NULL,disease TEXT,prescription TEXT,Medicine TEXT,FEE INT NOT NULL)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String sql2 = "update Prescriptions set Name=?,Did=?,symptoms=?,gender=?,disease=?,prescription=?,Medicine=?,FEE=? where Pid=?";
        try (PreparedStatement st = con.prepareStatement(sql2)) {

            prescription presc = new prescription(p.getPid(), p.getDid(), p.getSymptoms());
            diseasePrescription d = new diseasePrescription();
            d = presc.searchdisease();
            st.setString(1, d.medicine);
            st.setInt(2, d.fee);
            st.setString(3, d.dis);
            st.setString(4, d.pres);

            st.setInt(5, p.getPid());
            int rowsAffected = st.executeUpdate();
            System.out
                    .println(rowsAffected + " " + p.getPid() + "and " + p.getPname() + " Prescription row was updated");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean updatePrescriptionById(String pid, String Name, String Did, String symp, String g, String d, String p, String m, String f){
        Connection con = ConnectionDB.getConnection();
        final String sql2 = "update Prescriptions set Name=?,Did=?,symptoms=?,gender=?,disease=?,prescription=?,Medicine=?,FEE=? where Pid=?";
        try (PreparedStatement st = con.prepareStatement(sql2)) {

            prescription presc = new prescription(Integer.valueOf(pid), Integer.valueOf(Did), symp);
            diseasePrescription dis = new diseasePrescription();
            dis = presc.searchdisease();
            st.setString(1,Name);
           // st.setString(2,Name);
            st.setInt(2,Integer.valueOf(Did));
            st.setString(3,symp);
            st.setString(4,g);
            st.setString(5,d);
            st.setString(6,p);
            st.setString(7,m);
            st.setInt(8,Integer.valueOf(f));
            st.setInt(9,Integer.valueOf(pid));

            int rowsAffected = st.executeUpdate();
           return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean deletePrescription(String pid){
        Connection con = ConnectionDB.getConnection();
        final String sql2 = "delete from Prescriptions  where Pid=?";
        try (PreparedStatement st = con.prepareStatement(sql2)) {


            st.setInt(1,Integer.valueOf(pid));

            int rowsAffected = st.executeUpdate();
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ObservableList<diagnosis> ReadPrescription(String id) {
        Connection con = ConnectionDB.getConnection();
        ObservableList<diagnosis> d1 = FXCollections.observableArrayList();
        final String sql2 = "select * from Prescriptions where Did=?";
        try (PreparedStatement st = con.prepareStatement(sql2)) {

            st.setInt(1, Integer.valueOf(id));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               diagnosis d= new diagnosis(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7) ,rs.getString(8),rs.getInt(9));
               d1.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return d1;
    }
    public static diagnosis getPatientById(String id) throws SQLException {
        Connection conn=ConnectionDB.getConnection();
        diagnosis d = null;
        final String query="select * from prescriptions where Pid=?";
        try(PreparedStatement st=conn.prepareStatement(query)){
            st.setInt(1,Integer.valueOf(id));
            ResultSet rs=st.executeQuery();
            while (rs.next()){
                d = new diagnosis(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8),rs.getInt(9));
            }
        }catch (SQLException e){
            e.getLocalizedMessage();
        }
        return d;
    }

    @Override
    Map<String, Integer> diseasefile() {
        Map<String, Integer> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "C:\\Users\\krish\\Downloads\\MedicalProjectJavaFX\\src\\main\\java\\com\\navaneeth\\medicalsystem\\medicine.csv"));
            String studLine;
            while ((studLine = reader.readLine()) != null) {
                String parts[] = studLine.split(":");
                map.put(parts[0], Integer.valueOf(parts[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
