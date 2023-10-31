package com.navaneeth.medical_managament;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Button signInButton;
    @FXML
    private ImageView loginImageview;
    @FXML
    private TextField userIdtext;
    @FXML
    private PasswordField passwordtext;
    @FXML
    private Label invalidlogin;


    private  Stage stage;
    private Scene scene;
    private Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createTables.Drop();
        File loginFile=new File("images/LoginPage.png");
        Image loginImage=new Image(loginFile.toURI().toString());
        loginImageview.setImage(loginImage);
        createTables.tables();

    }

    private void extracted() {
        createTables.tables();
    }


    public void SignInButtonOnAction(ActionEvent event) throws Exception {

        if(!userIdtext.getText().isBlank() && passwordtext.getText().isBlank()==false){

            if(App.CheckPassword(Integer.parseInt(userIdtext.getText()),passwordtext.getText())){

                try{

                    String doctorid= userIdtext.getText();
                    System.out.println(doctorid);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("MainPage.fxml"));
                    root=loader.load();
                    MainPageController mainPageController=loader.getController();
                    mainPageController.displayUser(doctorid);
                    //Parent root = FXMLLoader.load(Main.class.getResource("Mainpage.fxml"));
                    stage =(Stage)((Node)event.getSource()).getScene().getWindow();
                    scene =new Scene(root,977, 651);


                    stage.setTitle("HomePage");
                    stage.setScene(scene);
                    stage.show();

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                invalidlogin.setText("Invalid Login!!!");
            }
        }
        else
        {
            invalidlogin.setText("Enter userid and password!!!");
        }
    }
    public void cancelButtonOnAction(ActionEvent event)
    {
        Stage stage;
        stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



}
