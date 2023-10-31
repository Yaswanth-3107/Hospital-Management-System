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
public class PrescriptionController implements Initializable {
    @FXML
    private TableView<diagnosis> prescriptionTableview;
    @FXML
    private TableColumn<diagnosis,Integer> pidColumn;
    @FXML
    private TableColumn<diagnosis,String> nameColumn;
    @FXML
    private TableColumn<diagnosis,Integer> didColumn;
    @FXML
    private TableColumn<diagnosis,String> symptomsColumn;
    @FXML
    private TableColumn<diagnosis,String> genderColumn;
    @FXML
    private TableColumn<diagnosis,String> diseasecolumn;
    @FXML
    private TableColumn<diagnosis,String> prescriptioncolumn;
    @FXML
    private TableColumn<diagnosis,String> medicinecolumn;
    @FXML
    private TableColumn<diagnosis,Integer> feecolumn;
    @FXML
    private TextField searchPatientfield;
    @FXML
    private TextField doctoridfield;
    @FXML
    private Button appointments;
    @FXML
    private TextField pidfield;
    @FXML
    private TextField diseasefield;
    @FXML
    private TextField pNamefield;
    @FXML
    private TextField didfield;
    @FXML
    private TextArea symptomsfield;
    @FXML
    private TextField genderfield;
    @FXML
    private TextField medicinefield;
    @FXML
    private TextField feefield;

    @FXML
    private TextArea prescriptionfield;
    @FXML
    private TextField phonefield;
    @FXML
    private Button pidSearchButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button applications;

    ObservableList<patientList> patients= FXCollections.observableArrayList();
    ObservableList<diagnosis> Diagnosis=FXCollections.observableArrayList();
    String doctorId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        doctoridfield.setText(doctorId);
        this.doctorId = doctorId;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        patients=patientscrud.ReadPatients();


    }
    public void appointmentOnAction(ActionEvent event){
        System.out.println("hello");
        patients=patientscrud.getPatientByDid(doctoridfield.getText());
        for(patientList p:patients){
          prescription.addPrescription(p);
        }
        Diagnosis=prescription.ReadPrescription(doctoridfield.getText());
        Diagnosis.stream().forEach(System.out::println);
        formingTable(Diagnosis);


    }
    public void formingTable(ObservableList<diagnosis>Diagnosis){
        pidColumn.setCellValueFactory(new PropertyValueFactory<diagnosis,Integer>("Pid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("PatientName"));
        didColumn.setCellValueFactory(new PropertyValueFactory<>("Did"));
        symptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        diseasecolumn.setCellValueFactory(new PropertyValueFactory<>("disease"));
        prescriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("prescription"));
        medicinecolumn.setCellValueFactory(new PropertyValueFactory<>("medicine"));
        feecolumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
        Diagnosis.stream().forEach(System.out::println);


        prescriptionTableview.setItems(Diagnosis);
        System.out.println(prescriptionTableview.getItems());
        FilteredList<diagnosis> filteredList=new FilteredList<>(Diagnosis, d->true);
        searchPatientfield.textProperty().addListener((observable,newValue,oldValue)->{
            filteredList.setPredicate(diagnosis -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }
                String searchKeyword=newValue.toString().toLowerCase();
                if(diagnosis.getPatientName().toLowerCase().contains(searchKeyword)){
                    return  true;
                }else if (diagnosis.getSymptoms().toLowerCase().contains(searchKeyword))
                {
                    return  true;
                }else if (diagnosis.getGender().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else if (diagnosis.getPrescription().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else if (diagnosis.getMedicine().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else if (diagnosis.getDisease().toLowerCase().indexOf(searchKeyword)>-1) {
                    return true;
                }else if (String.valueOf(diagnosis.getFee()).toLowerCase().indexOf(searchKeyword)>-1) {
                    return true;
                }
                else
                    return false;
            });
        });
        SortedList<diagnosis> sortedList=new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(prescriptionTableview.comparatorProperty());
        prescriptionTableview.setItems(sortedList);



    }
    public void pidSearchButtonOnAction(ActionEvent event) throws SQLException {
        if(!pidfield.getText().isBlank()){
            diagnosis p=prescription.getPatientById(pidfield.getText());
            if(p!=null){
                pNamefield.setText(p.getPatientName());
                didfield.setText(String.valueOf(p.getDid()));
                symptomsfield.setText(p.getSymptoms());
                genderfield.setText(p.getGender());
                diseasefield.setText(p.getDisease());
                prescriptionfield.setText(p.getPrescription());
                medicinefield.setText(p.getMedicine());
                feefield.setText(String.valueOf(p.getFee()));


            }
            else{
                pNamefield.setText("");
                didfield.setText("");
                symptomsfield.setText("");
                genderfield.setText("");
                diseasefield.setText("");
                prescriptionfield.setText("");
                medicinefield.setText("");
                feefield.setText("");
            }


        }
    }
    public void updateButtonOnAction(ActionEvent e) throws SQLException {
        if(!pidfield.getText().isBlank()){

            patientList p=patientscrud.getPatientById(pidfield.getText());
            System.out.println(p);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            if(p!=null){

                    boolean a = prescription.updatePrescriptionById(pidfield.getText(), pNamefield.getText(), didfield.getText(), symptomsfield.getText(), genderfield.getText(), diseasefield.getText(), prescriptionfield.getText(), medicinefield.getText(), feefield.getText());

                    if (a == true) {

                        alert.setContentText("updated successfully!!");
                        Diagnosis = prescription.ReadPrescription(doctoridfield.getText());
                        formingTable(Diagnosis);

                        pNamefield.setText("");
                        didfield.setText("");
                        symptomsfield.setText("");
                        genderfield.setText("");
                        diseasefield.setText("");
                        prescriptionfield.setText("");
                        medicinefield.setText("");
                        feefield.setText("");

                    } else
                        alert.setContentText("Invalid access!!!");
                }

            }

        }

    public void deleteButtonOnAction(ActionEvent event) throws SQLException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        if(!pidfield.getText().isBlank()){

            diagnosis p=prescription.getPatientById(pidfield.getText());
            System.out.println(p);

            if(p!=null){
                boolean a=prescription.deletePrescription(pidfield.getText());
                if(a==true){
                    alert.setContentText("deleted successfully!!");
                    Diagnosis=prescription.ReadPrescription(doctoridfield.getText());
                formingTable(Diagnosis);
                    pNamefield.setText("");
                    didfield.setText("");
                    symptomsfield.setText("");
                    genderfield.setText("");
                    diseasefield.setText("");
                    prescriptionfield.setText("");
                    medicinefield.setText("");
                    feefield.setText("");
            }
            else
                alert.setContentText("Invalid access!!!");

        }
        else
            alert.setContentText("Not found Patient!!");
    }
    }


    public void applicationOnAction(ActionEvent actionEvent) {
    }
}
