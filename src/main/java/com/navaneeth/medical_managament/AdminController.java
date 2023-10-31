package com.navaneeth.medical_managament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements  Initializable {
    @FXML
    private TextField Namefield;
    @FXML
    private TextField didfield;
    @FXML
    private TextField genderfield;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button didSearchButton;
    @FXML
    private TextField qualificationfield;
    @FXML
    private TextField roomfield;
    @FXML
    private TextField specialistsfield;
    @FXML
    private TextField salaryfield;
    @FXML
    private Button addButton;
    @FXML
    private TableView<Doctor> doctorTableview;
    @FXML
    private TableColumn<Doctor, Integer> didcolumn;
    @FXML
    private TableColumn<Doctor, String> namecolumn;
    @FXML
    private TableColumn<Doctor, String> specialistcolumn;
    @FXML
    private TableColumn<Doctor, Integer> experiencecolumn;
    @FXML
    private TableColumn<Doctor, String> qualificationcolumn;
    @FXML
    private TableColumn<Doctor, Integer> salarycolumn;
    @FXML
    private TableColumn<Doctor, Integer> roomcolumn;
    @FXML
    private TextField searchDoctorfield;
    @FXML
    private TextField experiencefield;
    @FXML
    private Button departments;
    @FXML
    private Button anasthesiologistButton;
    @FXML
    private Button cardioButton;
    @FXML
    private Button generalButton;
    @FXML
    private Button nephroButton;
    @FXML
    private TextField efield;
    @FXML
    private TextField sfield;
    @FXML
    private Button allDoctorsButton;
    @FXML
    private Button expgtButton;
    @FXML
    private Button salgtButton;


    ObservableList<Doctor> doctors = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            doctors = DoctorCRUD.ReadDoctor();

            formingTable(doctors);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void allDoctorsButtonOnAction() {
        doctors = DoctorCRUD.ReadDoctor();
        formingTable(doctors);
    }

    public void formingTable(ObservableList<Doctor> doctors) {
        /*private Integer id;
    private String Name;
    private String specialist;
    private Integer yearsOfExperience;
    private String Qualification;
    private Integer Salary;
    private Integer roomNo;*/
        didcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        specialistcolumn.setCellValueFactory(new PropertyValueFactory<>("specialist"));
        experiencecolumn.setCellValueFactory(new PropertyValueFactory<>("yearsOfExperience"));
        qualificationcolumn.setCellValueFactory(new PropertyValueFactory<>("Qualification"));
        salarycolumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        roomcolumn.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        doctors.stream().forEach(System.out::println);


        doctorTableview.setItems(doctors);
        System.out.println(doctorTableview.getItems());
        FilteredList<Doctor> filteredList = new FilteredList<>(doctors, p -> true);
        searchDoctorfield.textProperty().addListener((observable, newValue, oldValue) -> {
            filteredList.setPredicate(Doctor -> {
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }

                String searchKeyword = newValue.toString().toLowerCase();
                if (Doctor.getName().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Doctor.getSpecialist().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (Doctor.getQualification().toLowerCase().indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Doctor.getId()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Doctor.getRoomNo()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Doctor.getSalary()).indexOf(searchKeyword) > -1) {
                    return true;
                } else if (String.valueOf(Doctor.getYearsOfExperience()).indexOf(searchKeyword) > -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<Doctor> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(doctorTableview.comparatorProperty());
        doctorTableview.setItems(sortedList);


    }

    public void didSearchButtonOnAction(ActionEvent event) throws SQLException {
        if (!didfield.getText().isBlank()) {
            Doctor p = DoctorCRUD.getDoctorByid(didfield.getText());
            if (p != null) {
               /* int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
                int roomNo*/
                Namefield.setText(p.getName());
                specialistsfield.setText(String.valueOf(p.getSpecialist()));
                experiencefield.setText(String.valueOf(p.getYearsOfExperience()));
                qualificationfield.setText(p.getQualification());
                salaryfield.setText(String.valueOf(p.getSalary()));
                roomfield.setText(String.valueOf(p.getRoomNo()));
            } else {
                Namefield.setText("");
                specialistsfield.setText(String.valueOf(""));
                experiencefield.setText(String.valueOf(""));
                qualificationfield.setText("");
                salaryfield.setText(String.valueOf(""));
                roomfield.setText(String.valueOf(""));
            }


        }
    }

    public void updateButtonOnAction(ActionEvent event) throws SQLException {
        if (!didfield.getText().isBlank()) {

            Doctor p = DoctorCRUD.getDoctorByid(didfield.getText());
            System.out.println(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            if (p != null) {
 /*int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
           int roomNo*/
                boolean a = DoctorCRUD.updateDoctor(Integer.valueOf(didfield.getText()), Namefield.getText(), specialistsfield.getText(), Integer.parseInt(experiencefield.getText()), qualificationfield.getText(), Integer.parseInt(salaryfield.getText()), Integer.parseInt(roomfield.getText()));
                if (a == true) {

                    alert.setContentText("updated successfully!!");
                    doctors = DoctorCRUD.ReadDoctor();
                    formingTable(doctors);
                    Namefield.setText("");
                    specialistsfield.setText(String.valueOf(""));
                    experiencefield.setText(String.valueOf(""));
                    qualificationfield.setText("");
                    salaryfield.setText(String.valueOf(""));
                    roomfield.setText(String.valueOf(""));
                } else
                    alert.setContentText("Invalid access!!!");

            } else
                alert.setContentText("Not found Doctor!!");
        }
    }

    public void deleteButtonOnAction(ActionEvent event) throws SQLException {
        if (!didfield.getText().isBlank()) {

            Doctor p = DoctorCRUD.getDoctorByid(didfield.getText());
            System.out.println(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            if (p != null) {
 /*int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
           int roomNo*/
                boolean a = DoctorCRUD.deleteDoctor(didfield.getText());
                if (a == true) {

                    alert.setContentText("deleted successfully!!");
                    doctors = DoctorCRUD.ReadDoctor();
                    formingTable(doctors);
                    Namefield.setText("");
                    specialistsfield.setText(String.valueOf(""));
                    experiencefield.setText(String.valueOf(""));
                    qualificationfield.setText("");
                    salaryfield.setText(String.valueOf(""));
                    roomfield.setText(String.valueOf(""));
                } else
                    alert.setContentText("Invalid access!!!");

            }

        }
    }

    public void anasthesiologistButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorBySpeciality("Anasthesiologist");
        formingTable(doctors);
    }

    public void cardioButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorBySpeciality("CardioSurgeon");
        formingTable(doctors);
    }

    public void generalButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorBySpeciality("GeneralMedicin");
        formingTable(doctors);
    }

    public void nephroButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorBySpeciality("Nephrology");
        formingTable(doctors);
    }

    public void expgtButtonOnAction(ActionEvent event) {
        if (efield.getText().isBlank() == false) {
            doctors = DoctorCRUD.ListDoctorByExperience(efield.getText());
            formingTable(doctors);
        }
    }

    public void salgtButtonOnAction(ActionEvent event) {
        if (sfield.getText().isBlank() == false) {
            doctors = DoctorCRUD.ListDoctorBySalary(sfield.getText());
            formingTable(doctors);
        }
    }
    public void departmentsOnAction(ActionEvent event){
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("departments.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1317.2, 821.6);

        Stage stage = new Stage();
        stage.setTitle("Departments");
        stage.setScene(scene);
        stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addButtonOnAction(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddDoctor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1114,784);

            Stage stage = new Stage();
            stage.setTitle("Add Doctor");
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}











