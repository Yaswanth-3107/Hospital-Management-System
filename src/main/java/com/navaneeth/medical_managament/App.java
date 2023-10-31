package com.navaneeth.medical_managament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class App {




        public static boolean CheckPassword(int userid, String Password){
            Connection conn = ConnectionDB.getConnection();

            String pass = new String();
            final String check1 = "Select password from password where id=?";

            try (PreparedStatement st = conn.prepareStatement(check1)) {
                st.setInt(1, Integer.valueOf(userid));
                ResultSet rs = st.executeQuery();

                while (rs.next())
                    pass = rs.getString(1);
                // System.out.println(rs.getString(1).equals(password));

            } catch (Exception e) {
                // TODO: handle exception
                e.getLocalizedMessage();
            }

            if (Password.equals(pass)) {
                return true;
            }

            return false;
        }

        public static void SearchDoctor () {
            Connection conn = ConnectionDB.getConnection();
            String query = "select Doctor_id, Doctor_name, Specialist, yearsOfExperience, Qualification, Salary, roomNo from doctors where Doctor_name = 'Dr.Srinivasan'";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                List<Doctor> lis = new ArrayList<>();
                while (rs.next()) {
                    Doctor d = new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                            rs.getInt(6), rs.getInt(7));
                    lis.add(d);

                }
                lis.forEach(System.out::println);
            } catch (Exception e) {
                System.out.println("there was a problem in executing" + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void patientfee () {
            Connection conn = ConnectionDB.getConnection();
            String query = "select Pid, Name, FEE from Prescriptions where Pid = 20";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int Pid = rs.getInt(1);
                    String Name = rs.getString(2);
                    int FEE = rs.getInt(3);
                    System.out.println(Pid + ", " + Name + ", " + FEE + ", ");
                }
            } catch (Exception e) {
                System.out.println("there was a problem in executing" + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void doctordetails () {
            Connection conn = ConnectionDB.getConnection();
            String query = "select p.pid,d.Doctor_id,d.Doctor_Name,d.Qualification from patients p,doctors d where p.pid=8;";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int Pid = rs.getInt(1);
                    int Doctor_id = rs.getInt(2);
                    String Doctor_name = rs.getString(3);
                    String Qualification = rs.getString(4);
                    System.out.println(Pid + ", " + Doctor_id + ", " + Doctor_name + ", " + Qualification + ",");
                }
            } catch (Exception e) {
                System.out.println("there was a problem in executing" + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void count () {
            Connection conn = ConnectionDB.getConnection();
            String query = "select count(Department) from specialists_departments where Department = 'physician'";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Number of physicians in hospital are : " + count);
                }
            } catch (Exception e) {
                System.out.println("there was a problem in executing" + e.getMessage());
                e.printStackTrace();
            }
        }

        public static void printHelp () {
            System.out.println("a.meet");
        }




}


