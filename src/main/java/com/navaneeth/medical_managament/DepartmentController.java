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
public class DepartmentController implements  Initializable{
    @FXML
            private TextField Namefield;
    @FXML
            private TextField didfield;
    @FXML
    private TextField deptNofield;
    @FXML
    private TextField departmentfield;
    @FXML
    private TextField positionfield;
    @FXML
    private TextField dfield;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button didSearchButton;
    @FXML
    private Button physicianButton;
    @FXML
    private Button surgeryButton;
    @FXML
    private Button generalButton;
    @FXML
    private Button nephroButton;
    @FXML
    private Button departmentNoButton;
    @FXML
    private Button allDoctorsButton;
    @FXML
    private  TableView<Department> doctorTableview;
    @FXML
    private TableColumn<Department,Integer> didcolumn;
    @FXML
    private TableColumn<Department,String> namecolumn;
    @FXML
    private TableColumn<Department,String> departmentcolumn;
    @FXML
    private TableColumn<Department,Integer> deptNocolumn;
    @FXML
    private TableColumn<Department,String> positioncolumn;
    @FXML
            private TextField searchDoctorfield;



    ObservableList<Department> doctors= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            doctors=DoctorCRUD.ReadDepartments();

            formingTable(doctors);



        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void formingTable(ObservableList<Department>doctors){
        /*   private Integer id;
    private  String Name;
    private String Department;
    private Integer deptNo;
    private String Position;;*/
        didcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        departmentcolumn.setCellValueFactory(new PropertyValueFactory<>("Department"));
        deptNocolumn.setCellValueFactory(new PropertyValueFactory<>("deptNo"));
        positioncolumn.setCellValueFactory(new PropertyValueFactory<>("Position"));

        doctors.stream().forEach(System.out::println);


        doctorTableview.setItems(doctors);
        System.out.println(doctorTableview.getItems());
        FilteredList<Department> filteredList=new FilteredList<>(doctors, p->true);
        searchDoctorfield.textProperty().addListener((observable,newValue,oldValue)->{
            filteredList.setPredicate(Department -> {
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }

                String searchKeyword=newValue.toString().toLowerCase();
                if(Department.getName().toLowerCase().contains(searchKeyword)){
                    return  true;
                }else if (Department.getDepartment().toLowerCase().contains(searchKeyword))
                {
                    return  true;
                }else if (String.valueOf(Department.getDeptNo()).toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }else if (Department.getPosition().toLowerCase().indexOf(searchKeyword)>-1)
                {
                    return  true;
                }
                else
                    return false;
            });
        });
        SortedList<Department> sortedList=new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(doctorTableview.comparatorProperty());
        doctorTableview.setItems(sortedList);



    }

    public void allDoctorsButtonOnAction(){
        doctors=DoctorCRUD.ReadDepartments();
        formingTable(doctors);
    }
    public void didSearchButtonOnAction(ActionEvent event) throws SQLException {
        if(!didfield.getText().isBlank()){
            Department p=DoctorCRUD.getDeptByid(didfield.getText());
            if(p!=null){

                Namefield.setText(p.getName());
                departmentfield.setText(String.valueOf(p.getDepartment()));
                deptNofield.setText(String.valueOf(p.getDeptNo()));
                positionfield.setText(p.getPosition());

            }
            else
            {
                Namefield.setText("");
                departmentfield.setText("");
                deptNofield.setText("");
                positionfield.setText("");
            }


        }
    }
    public void updateButtonOnAction(ActionEvent event) throws SQLException {
        if(!didfield.getText().isBlank()){

            Department p=DoctorCRUD.getDeptByid(didfield.getText());
            System.out.println(p);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            if(p!=null){
 /*int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
           int roomNo*/
                boolean a=DoctorCRUD.updateDepartment(Integer.valueOf(didfield.getText()),Namefield.getText(),departmentfield.getText(),Integer.parseInt(deptNofield.getText()),positionfield.getText());
                if(a==true){

                    alert.setContentText("updated successfully!!");
                    doctors=DoctorCRUD.ReadDepartments();
                    formingTable(doctors);
                    Namefield.setText("");
                    Namefield.setText("");
                    departmentfield.setText("");
                    deptNofield.setText("");
                    positionfield.setText("");
                }
                else
                    alert.setContentText("Invalid access!!!");

            }
            else
                alert.setContentText("Not found Doctor!!");
        }
    }
    public void deleteButtonOnAction(ActionEvent event)  throws SQLException{
        if(!didfield.getText().isBlank()){

            Department p=DoctorCRUD.getDeptByid(didfield.getText());
            System.out.println(p);
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            if(p!=null){
 /*int id, String name, String specialist, int yearsOfExperience, String qualification, int salary,
           int roomNo*/
                boolean a=DoctorCRUD.deleteDepartment(didfield.getText());
                if(a==true){

                    alert.setContentText("deleted successfully!!");
                    doctors=DoctorCRUD.ReadDepartments();
                    formingTable(doctors);
                    Namefield.setText("");
                    Namefield.setText("");
                    departmentfield.setText("");
                    deptNofield.setText("");
                    positionfield.setText("");
                }
                else
                    alert.setContentText("Invalid access!!!");

            }
            else
                alert.setContentText("Not found Doctor!!");
        }
    }
    public void physicianButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorByDepartment("physician");
        formingTable(doctors);
    }

    public void surgeryButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorByDepartment("surgery");
        formingTable(doctors);
    }

    public void generalButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorByDepartment("GeneralMedicin");
        formingTable(doctors);
    }

    public void nephroButtonOnAction(ActionEvent event) {
        doctors = DoctorCRUD.ListDoctorByDepartment("Nephrology");
        formingTable(doctors);
    }

    public void departmentNoButtonOnAction(ActionEvent event) {
        if (dfield.getText().isBlank() == false) {
            doctors = DoctorCRUD.ListDoctorByDeptNo(dfield.getText());
            formingTable(doctors);
        }
    }


}
