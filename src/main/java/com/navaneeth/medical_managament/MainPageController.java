package com.navaneeth.medical_managament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private ImageView mainPage;
    @FXML
    private Button listPatientsButton;
    @FXML
    private Button AdminLogin;
    @FXML
    private Button prescriptionButton;
    @FXML
    private Button addPatientButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TextField doctoridfield;
    String doctorId;
    private  Stage stage;
    private Scene scene;
    private Parent root;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File MainFile=new File("C:\\Users\\navan\\IdeaProjects\\Medical Managament\\images\\MainPage.jpg");
        Image MainImage=new Image(MainFile.toURI().toString());
        mainPage.setImage(MainImage);
        System.out.println(getDoctorId());
        doctoridfield.setText(getDoctorId());
    }
    public void displayUser(String id)
    {
        doctoridfield.setText(id);
        System.out.println("field"+doctoridfield.getText());
    }
    public void addPatientButtonOnAction(ActionEvent event) throws Exception
    {
        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("addPatient.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(root, 602.4, 823);
            stage.setTitle("AddPatient");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void listPatientsButtonOnAction(ActionEvent event) throws Exception
    {
        try {
            String doctorid=doctoridfield.getText();
            System.out.println("filll"+doctorid);
           // FXMLLoader loader=new FXMLLoader(getClass().getResource("ListPatient.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("patientslist.fxml"));
            //root = fxmlLoader.load();
            Scene scene = new Scene(fxmlLoader.load(),1477.4 , 835);

            ListPatientController listPatientController=fxmlLoader.getController();
            listPatientController.setDoctorId(doctorid);
            //Parent root = FXMLLoader.load(Main.class.getResource("Mainpage.fxml"));
           // stage =(Stage)((Node)event.getSource()).getScene().getWindow();
           // scene =new Scene(root ,1493, 822);
            Stage stage = new Stage();
            stage.setTitle("ListPatient");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void prescriptionButtonOnAction(ActionEvent event){
        try {
            String doctorid=doctoridfield.getText();
            System.out.println("fil"+doctorid);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("prescriptionController.fxml"));
            Scene scene = new Scene(fxmlLoader.load(),1484 , 822);
            PrescriptionController prescriptionController=fxmlLoader.getController();
            prescriptionController.setDoctorId(doctorid);
            Stage stage = new Stage();
            stage.setTitle("Diagnosis");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void AdminLoginOnAction(ActionEvent event){
        try {

                String doctorid = doctoridfield.getText();
                System.out.println("fil" + doctorid);
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("adminPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1477.4, 821.6);

                Stage stage = new Stage();
                stage.setTitle("Admin");
                stage.setScene(scene);
                stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
