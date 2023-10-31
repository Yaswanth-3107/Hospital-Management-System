package com.navaneeth.medical_managament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class createTables {
    public static void Drop() {

            Connection conn=ConnectionDB.getConnection();
            final String query1="Drop Table if exists doctors,patients,specialists_departments,password,prescriptions;";
            try(PreparedStatement stmt= conn.prepareStatement(query1))
            {
                stmt.executeUpdate();
            }catch(SQLException e)
            {
                System.out.println("there was a problem in deleting older database");
                e.getLocalizedMessage();
            }

    }
    public static void tables(){
        //C:\\Users\\krish\\Downloads\\MedicalProjectJavaFX\\src\\main\\java\\com\\navaneeth\\medical_managament\\doctors.csv"
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\navan\\IdeaProjects\\Medical Managament\\src\\main\\java\\com\\navaneeth\\medical_managament\\doctors.csv"));
            String studLine;
            while ((studLine = reader.readLine()) != null) {

                DoctorCRUD.CreateDoctor(new Doctor(studLine));

                String[] parts = studLine.split(",");

                DoctorCRUD.CreateSpecialists(
                        new SpecialistDepartments(studLine, parts[7], Integer.valueOf(parts[8]), parts[9]));
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "C:\\Users\\navan\\IdeaProjects\\Medical Managament\\src\\main\\java\\com\\navaneeth\\medical_managament\\password.csv"));
            String studLine;
            Connection conn = ConnectionDB.getConnection();
            final String create = "CREATE TABLE IF NOT EXISTS password (id int primary key,Name varchar(20),password varchar(50))";
            try (PreparedStatement Stmt = conn.prepareStatement(create)) {
                Stmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while ((studLine = reader.readLine()) != null) {

                String parts[] = studLine.split(":");

                final String insert = "insert into password values (?,?,?)";
                try (PreparedStatement st = conn.prepareStatement(insert)) {
                    st.setInt(1, Integer.valueOf(parts[0]));
                    st.setString(2, parts[1]);
                    st.setString(3, parts[2]);
                    st.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader reader = new BufferedReader(new FileReader(
                    "C:\\Users\\navan\\IdeaProjects\\Medical Managament\\src\\main\\java\\com\\navaneeth\\medical_managament\\patients.csv"));
            String stdLine;
            while ((stdLine = reader.readLine()) != null) {
                patientList p=new patientList(stdLine);
                if(p!=null)
                patientscrud.AddPatient(p);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
