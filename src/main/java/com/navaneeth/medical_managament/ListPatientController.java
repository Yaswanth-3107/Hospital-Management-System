package com.navaneeth.medical_managament;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ListPatientController implements Initializable{

    @FXML
    private TableView<patientList> patientsTableview;
    @FXML
    private TableColumn<patientList,Integer> pidColumn;
    @FXML
    private TableColumn<patientList,String> nameColumn;
    @FXML
    private TableColumn<patientList,Integer> didColumn;
    @FXML
    private TableColumn<patientList,String> symptomsColumn;
    @FXML
    private TableColumn<patientList,String> genderColumn;
    @FXML
    private TableColumn<patientList,String> prescriptionColumn;
    @FXML
    private TableColumn<patientList,String> phoneColumn;
    @FXML
    private TextField searchPatientfield;
    @FXML
    private TextField doctoridfield;
    @FXML
    private Button appointments;
    @FXML
    private TextField pidField;
    @FXML
    private TextField pNameField;
    @FXML
    private TextField didField;
    @FXML
    private TextArea symptomsField;
    @FXML
    private TextField genderField;
    @FXML
    private TextField prescriptionField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button pidSearchButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;


    String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        doctoridfield.setText(doctorId);
        this.doctorId = doctorId;
    }
    ObservableList<patientList>patients= FXCollections.observableArrayList();
    ObservableList<patientList>patients2= FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

          patients=patientscrud.ReadPatients();

          formingTable(patients);



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void formingTable(ObservableList<patientList>patients){
        pidColumn.setCellValueFactory(new PropertyValueFactory<patientList,Integer>("pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("pname"));
        didColumn.setCellValueFactory(new PropertyValueFactory<>("Did"));
        symptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        prescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("setPrescription"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        patients.stream().forEach(System.out::println);


        patientsTableview.setItems(patients);
        System.out.println(patientsTableview.getItems());
        FilteredList<patientList> filteredList=new FilteredList<>(patients,p->true);
        searchPatientfield.textProperty().addListener((observable,newValue,oldValue)->{
            filteredList.setPredicate(patientList -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }
                String searchKeyword=newValue.toString().toLowerCase();
                if(patientList.getPname().toLowerCase().contains(searchKeyword)){
                    return  true;
                }else if (patientList.getSymptoms().toLowerCase().contains(searchKeyword))
                {
                    return  true;
                }else if (patientList.getGender().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else if (patientList.getSetPrescription().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else if (patientList.getPhoneNumber().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else
                    return false;
            });
        });
        SortedList<patientList>sortedList=new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(patientsTableview.comparatorProperty());
        patientsTableview.setItems(sortedList);



    }

    public void appointmentsOnAction(ActionEvent event){
        patients2=patientscrud.getPatientByDid(doctoridfield.getText());
        patients2.stream().forEach(System.out::println);
        formingTable(patients2);
    }

    public void pidSearchButtonOnAction(ActionEvent event) throws SQLException {
        if(!pidField.getText().isBlank()){
            patientList p=patientscrud.getPatientById(pidField.getText());
            if(p!=null){
                pNameField.setText(p.getPname());
                didField.setText(String.valueOf(p.getDid()));
                symptomsField.setText(p.getSymptoms());
                genderField.setText(p.getGender());
                prescriptionField.setText(p.getSetPrescription());
                phoneField.setText(p.getPhoneNumber());
            }
            else
            {
                pNameField.setText("");
                didField.setText(String.valueOf(""));
                symptomsField.setText("");
                genderField.setText("");
                prescriptionField.setText("");
                phoneField.setText("");
            }


        }
    }
    public void updateButtonOnAction(ActionEvent event) throws SQLException {
        if(!pidField.getText().isBlank()){

            patientList p=patientscrud.getPatientById(pidField.getText());
            System.out.println(p);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            if(p!=null){
                    boolean a=patientscrud.updatePatientbyid(p.getPid(),pNameField.getText(),Integer.parseInt(didField.getText()),symptomsField.getText(),genderField.getText(),prescriptionField.getText(),phoneField.getText());
                    if(a==true){

                        alert.setContentText("updated successfully!!");
                        patients=patientscrud.ReadPatients();
                        formingTable(patients);
                        pNameField.setText("");
                        didField.setText(String.valueOf(""));
                        symptomsField.setText("");
                        genderField.setText("");
                        prescriptionField.setText("");
                        phoneField.setText("");
                    }
                    else
                        alert.setContentText("Invalid access!!!");

            }
            else
                alert.setContentText("Not found Patient!!");
        }
    }
    public void deleteButtonOnAction(ActionEvent event) throws SQLException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        if(!pidField.getText().isBlank()){

            patientList p=patientscrud.getPatientById(pidField.getText());
            System.out.println(p);

            if(p!=null){
                boolean a=patientscrud.deletePatient(pidField.getText());
                if(a==true)
                    alert.setContentText("deleted successfully!!");
                    patients=patientscrud.ReadPatients();
                    formingTable(patients);
                pNameField.setText("");
                didField.setText(String.valueOf(""));
                symptomsField.setText("");
                genderField.setText("");
                prescriptionField.setText("");
                phoneField.setText("");
            }
                else
                    alert.setContentText("Invalid access!!!");

            }
            else
                alert.setContentText("Not found Patient!!");
        }
    }


