/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.Inventory;
import model.InHouse;
import model.Outsourced;
import java.lang.NumberFormatException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import model.CheckFields;

/**
 * FXML Controller class FUTURE ENHANCEMENT Add a timestamp observable list to track when a part was made. 
 *
 * @author Joshua Gibson
 */
public class AddPartMenuController implements Initializable {
    
    //Method to switch scenes

    Stage stage;
    Parent scene;
    
    /**
     * Changes the scene based on a button event.
     * @param event The button event that triggers the change of scene. 
     * @param scenestring The location of the new scene to be displayed. 
     * @throws IOException
     */
    public void ChangeScene(ActionEvent event, String scenestring) throws IOException{
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(scenestring));
        stage.setScene(new Scene(scene));
        stage.show(); 
    }
    
         //fx:id labels
    //text flow id
    @FXML
    private TextFlow textFlow;
        
    //text field id's
    @FXML
    private TextField invTxt;

    @FXML
    private TextField machineIdTxt;

    @FXML
    private TextField maxInventoryTxt;

    @FXML
    private TextField minInventoryTxt;

    @FXML
    private TextField partIdTxt;

    @FXML
    private TextField partNameTxt;

    @FXML
    private ToggleGroup partSourceTG;

    @FXML
    private TextField priceTxt;
    
    //lable to change between company and machine id label 
    @FXML
    private Label machineOrCompanyLbl;  
    
    //radio button id's
    @FXML
    private RadioButton inHouseBtn;
    
    @FXML
    private RadioButton outsourcedBtn;
    
    
    //Button Handler Methods

    /**
     * Returns to the main form menu. 
     * @param event The button event that triggers the scene change. 
     * @throws IOException
     */

    @FXML
    void onActionCancelBtn(ActionEvent event) throws IOException {
        
        ChangeScene(event, "/view/MainForm.fxml");

    }

    /**
     * Sets the Machine Id text field if the In House button is selected.
     * @param event The button event of the In House button being selected. 
     */
    @FXML
    void onActionIHBtn(ActionEvent event) {
        
        machineOrCompanyLbl.setText("Machine ID");

    }

    /**
     * Sets the Company Name field if the Outsourced button is selected.
     * @param event The button event of the outsourced button being selected. 
     */
    @FXML
    void onActionOutsourcedBtn(ActionEvent event) {
        
        machineOrCompanyLbl.setText("Company Name");

    }

    private CheckFields checkFields = new CheckFields();
    boolean isMachineId = false;
    
    /**
     * Saves a part when the save button is pressed. 
     * @param event The event of the save button being pressed. RUNTIME ERROR Originally, I accidentally imported a awt element instead of a scene one, causing an error that it they could not be converted.  Fixed it by importing the scene variant. 
     * @throws IOException
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {
        //if InHouse button is selected an InHouse part is created otherwise an Outsourced part is created       
        try{
            if(inHouseBtn.isSelected()){
                int id;
                //check if parts inventory is empty if it is id is set as one, if not id is set to one more than the current size
                if(Inventory.getAllParts().isEmpty()){
                    id = 1;
                }
                else{
                    id =Inventory.getAllParts().size() + 1;
                }

                String sname = partNameTxt.getText();
                String sprice = priceTxt.getText();
                String sstock = invTxt.getText();
                String sminInv = minInventoryTxt.getText();
                String smaxInv = maxInventoryTxt.getText();
                String smachineId = machineIdTxt.getText();
                //check if fields are valid
                textFlow.getChildren().clear();
                checkFields.EmptyList();
                
                //let checkfields know that inSourceBtn is selected
                isMachineId = true;
                
                //check input fields to see if they fall in parameters
                ObservableList<Text> errorsList = FXCollections.observableArrayList();
                errorsList = checkFields.CheckFields(partNameTxt.getText(), priceTxt.getText(), invTxt.getText(), minInventoryTxt.getText(), maxInventoryTxt.getText(), machineIdTxt.getText(), isMachineId);

                //if textFlow is not empty throw exception
                if(!(errorsList.isEmpty())){
 
                    throw new NumberFormatException();

                }
                
                
                String name = partNameTxt.getText();
                double price = Double.parseDouble(priceTxt.getText());
                int stock = Integer.parseInt(invTxt.getText());
                int min = Integer.parseInt(minInventoryTxt.getText());
                int max = Integer.parseInt(maxInventoryTxt.getText());
                int machineId = Integer.parseInt(machineIdTxt.getText());
     
                InHouse part = new InHouse(id, name, price, stock, min, max, machineId);

                Inventory.addPart(part);
                System.out.println("insource added");
                ChangeScene(event, "/view/MainForm.fxml");
            }
        }
        catch(NumberFormatException e){
            
            ObservableList<Text> errorsList = FXCollections.observableArrayList();
            errorsList = checkFields.getTextList();
            
            Text exception = new Text("Could not save part");
            errorsList.add(exception);            

            //ConcurrentModificationException
            for(Text text: errorsList){
                    
                        textFlow.getChildren().add(text);
                }
            checkFields.EmptyList();

        }
        try{
            if(outsourcedBtn.isSelected()){
                //check if parts inventory is empty if it is id is set as one, if not id is set to one more than the current size
                int id;
                if(Inventory.getAllParts().isEmpty()){
                    id = 1;
                }
                else{
                    id =Inventory.getAllParts().size() + 1;
                }
                
                //get text from fields to check
                String sname = partNameTxt.getText();
                String sprice = priceTxt.getText();
                String sstock = invTxt.getText();
                String sminInv = minInventoryTxt.getText();
                String smaxInv = maxInventoryTxt.getText();
                String smachineId = machineIdTxt.getText();

                //check if fields are valid
                textFlow.getChildren().clear();
                checkFields.EmptyList();
                
                //let checkfields know that inSourceBtn is selected
                isMachineId = false;
                
                //check input fields to see if they fall in parameters
                ObservableList<Text> errorsList = FXCollections.observableArrayList();
                errorsList = checkFields.CheckFields(partNameTxt.getText(), priceTxt.getText(), invTxt.getText(), minInventoryTxt.getText(), maxInventoryTxt.getText(), machineIdTxt.getText(), isMachineId);

                //if textFlow is not empty throw exception
                if(!(errorsList.isEmpty())){
 
                    throw new NumberFormatException();

                }
                
                //getting user input data from the text fields
                String name = partNameTxt.getText();
                double price = Double.parseDouble(priceTxt.getText());
                int stock = Integer.parseInt(invTxt.getText());
                int min = Integer.parseInt(minInventoryTxt.getText());
                int max = Integer.parseInt(minInventoryTxt.getText());
                String machineId = machineIdTxt.getText();

                Outsourced part = new Outsourced(id, name, price, stock, min, max, machineId);

                Inventory.addPart(part);
                System.out.println("outsourced added");
                ChangeScene(event, "/view/MainForm.fxml");
            }
        }
        catch(NumberFormatException e){
            
            ObservableList<Text> errorsList = FXCollections.observableArrayList();
            errorsList = checkFields.getTextList();
            
            Text exception = new Text("Could not save part");
            errorsList.add(exception);            

            //ConcurrentModificationException
            for(Text text: errorsList){
                    
                        textFlow.getChildren().add(text);
                }
            checkFields.EmptyList();

        }

    }
    
     
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inHouseBtn.setSelected(true);
        outsourcedBtn.setSelected(false);
    }    
    
}
