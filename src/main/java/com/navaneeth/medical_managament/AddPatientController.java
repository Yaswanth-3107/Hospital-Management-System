package com.navaneeth.medical_managament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddPatientController {
    @FXML
    private TextField dateField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField doctorIdField;
    @FXML
    private TextField genderField;
    @FXML
    private TextArea symptomsField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField setPrescriptionField;
    @FXML
    private Label messagelabel;

    public void addButtonOnAction(ActionEvent event) throws Exception
    {
       if(!dateField.getText().isBlank() && nameField.getText().isBlank()==false && !doctorIdField.getText().isBlank() && !genderField.getText().isBlank() && !symptomsField.getText().isBlank()
       && !phoneNumberField.getText().isBlank()){
           try {
               File log = new File(
                       "C:\\Users\\navan\\IdeaProjects\\Medical Managament\\src\\main\\java\\com\\navaneeth\\medical_managament\\patients.csv");
               FileWriter fileWriter = new FileWriter(log, true);
               BufferedWriter writer = new BufferedWriter(fileWriter);
               writer.newLine();
               String str=dateField.getText().toString()+","+nameField.getText().toString()+","+Integer.parseInt(doctorIdField.getText().toString())+","+symptomsField.getText().toString()+","+genderField.getText().toString()+","+setPrescriptionField.getText().toString()+","+phoneNumberField.getText().toString()+"\n";
               writer.write(str);

               patientscrud.AddPatient(new patientList(nameField.getText().toString(),Integer.parseInt(doctorIdField.getText().toString()),symptomsField.getText().toString(),genderField.getText().toString(),setPrescriptionField.getText().toString(),phoneNumberField.getText().toString()));
               writer.close();
           } catch (IOException e) {
               e.printStackTrace();
           }


       }
       else{
           messagelabel.setText( "Please provide all fields to add patient!!!");
       }
    }

    public void cancelButtonOnAction(ActionEvent event)
    {
        Stage stage;
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



}
