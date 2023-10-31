package com.navaneeth.medical_managament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDoctorController {
    @FXML
    private TextField namefield;
    @FXML
    private TextField doctorsIdfield;
    @FXML
    private TextField yoefield;
    @FXML
    private TextField qualificationfield;
    @FXML
    private TextField salaryfield;
    @FXML
    private Button addButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label messagelabel;
    @FXML
    private TextField specialistfield;
    @FXML
    private TextField roomfield;
    @FXML
    private TextField departmentfield;
    @FXML
    private TextField deptNofield;
    @FXML
    private TextField positionfield;

    public void addButtonOnAction(ActionEvent event) throws Exception {
        if(!namefield.getText().isBlank() && !doctorsIdfield.getText().isBlank() && !yoefield.getText().isBlank() && !qualificationfield.getText().isBlank() && !salaryfield.getText().isBlank()
                && !specialistfield.getText().isBlank()  && !roomfield.getText().isBlank() && !deptNofield.getText().isBlank() && !positionfield.getText().isBlank()){

                //1,Dr.Soundappan,Anasthesiologist,20,MBBS|DA,100000,10,physician,2,member
                String str=doctorsIdfield.getText().toString()+","+namefield.getText().toString()+","+specialistfield.getText().toString()+","+Integer.parseInt(yoefield.getText())+","+qualificationfield.getText()+","+Integer.parseInt(salaryfield.getText().toString())+","+roomfield.getText().toString()+","+departmentfield.getText().toString()+","+deptNofield.getText().toString()+","+positionfield.getText().toString()+"\n";


               DoctorCRUD.CreateDoctor(new Doctor(Integer.parseInt(doctorsIdfield.getText().toString()),namefield.getText().toString(),specialistfield.getText().toString(),Integer.parseInt(yoefield.getText()),qualificationfield.getText(),Integer.parseInt(salaryfield.getText().toString()),Integer.parseInt(roomfield.getText().toLowerCase())));
               DoctorCRUD.CreateSpecialists(new SpecialistDepartments(Integer.parseInt(doctorsIdfield.getText().toString()),namefield.getText().toString(),specialistfield.getText().toString(),Integer.parseInt(yoefield.getText()),qualificationfield.getText(),Integer.parseInt(salaryfield.getText().toString()),Integer.parseInt(roomfield.getText().toLowerCase()),departmentfield.getText().toString(),Integer.parseInt(deptNofield.getText().toString()),positionfield.getText().toString()));





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
