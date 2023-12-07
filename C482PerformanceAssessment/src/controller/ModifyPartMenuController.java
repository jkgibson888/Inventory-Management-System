/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.CheckFields;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;


/**
 * FXML Controller class FUTURE ENCHANCEMENT Add a timestamp to track when a change has been made to a product. 
 * 
 * @author Joshua Gibson
 */
public class ModifyPartMenuController implements Initializable {
    
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
    private TextFlow errorTextFlow;
    
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
    private TextField priceTxt;
    
    //lable to change between company and machine id label
    @FXML
    private Label machineOrCompanyLbl;
    
    //radio button id's
    @FXML
    private RadioButton outsourcedBtn;
    
    @FXML
    private RadioButton inHouseBtn;
    
    //button toggle group id
    @FXML
    private ToggleGroup partSourceTG;
    
    //Button Handler Methods

    /**
     * Returns to the main form without making changes to a part. 
     * @param event The event of the cancel button being selected. 
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

    
    //checkfields object
    private CheckFields checkFields = new CheckFields();
    boolean isMachineId = false;
    
    /**
     * Saves a part with the changes made when the save button is pressed. 
     * @param event The event of the save button being pressed. RUNTIME ERROR Originally, I accidentally imported a awt element instead of a scene one, causing an error that it they could not be converted.  Fixed it by importing the scene variant. 
     * @throws IOException
     */
    @FXML
    void onActionSaveBtn(ActionEvent event) throws IOException {
        try{
            Part part = MainFormController.GetPart();
            if(inHouseBtn.isSelected() && part instanceof InHouse){

                InHouse inHouse = (InHouse)MainFormController.GetPart();
                
                //fields to be checked
                String sname = partNameTxt.getText();
                String sprice = priceTxt.getText();
                String sstock = invTxt.getText();
                String sminInv = minInventoryTxt.getText();
                String smaxInv = maxInventoryTxt.getText();
                String smachineId = machineIdTxt.getText();
                //check if fields are valid
                errorTextFlow.getChildren().clear();
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

                //inHouse.setId(Integer.parseInt(partIdTxt.getText()));
                inHouse.setMax(Integer.parseInt(maxInventoryTxt.getText()));
                inHouse.setMin(Integer.parseInt(minInventoryTxt.getText()));
                inHouse.setName(partNameTxt.getText());
                inHouse.setPrice(Double.parseDouble(priceTxt.getText()));
                inHouse.setStock(Integer.parseInt(invTxt.getText()));
                inHouse.setMachineId(Integer.parseInt(machineIdTxt.getText()));

                Inventory.updatePart(inHouse.getId() - 1, inHouse);

            }
            else if(outsourcedBtn.isSelected() && part instanceof Outsourced){
                Outsourced outsourced = (Outsourced)MainFormController.GetPart();

                String sname = partNameTxt.getText();
                String sprice = priceTxt.getText();
                String sstock = invTxt.getText();
                String sminInv = minInventoryTxt.getText();
                String smaxInv = maxInventoryTxt.getText();
                String smachineId = machineIdTxt.getText();
                //check if fields are valid
                errorTextFlow.getChildren().clear();
                checkFields.EmptyList();

                //let checkfields know that outsourcedBtn is selected
                isMachineId = false;

                //check input fields to see if they fall in parameters
                ObservableList<Text> errorsList = FXCollections.observableArrayList();
                errorsList = checkFields.CheckFields(partNameTxt.getText(), priceTxt.getText(), invTxt.getText(), minInventoryTxt.getText(), maxInventoryTxt.getText(), machineIdTxt.getText(), isMachineId);

                //if textFlow is not empty throw exception
                if(!(errorsList.isEmpty())){

                    throw new NumberFormatException();

                }

                //inHouse.setId(Integer.parseInt(partIdTxt.getText()));
                outsourced.setMax(Integer.parseInt(maxInventoryTxt.getText()));
                outsourced.setMin(Integer.parseInt(minInventoryTxt.getText()));
                outsourced.setName(partNameTxt.getText());
                outsourced.setPrice(Double.parseDouble(priceTxt.getText()));
                outsourced.setStock(Integer.parseInt(invTxt.getText()));
                outsourced.setCompanyName(machineIdTxt.getText());

                Inventory.updatePart(outsourced.getId() - 1, outsourced);
                MainFormController.ReturnToPart((Part)outsourced);            
            }
            else if(inHouseBtn.isSelected() && part instanceof Outsourced){

                String sname = partNameTxt.getText();
                String sprice = priceTxt.getText();
                String sstock = invTxt.getText();
                String sminInv = minInventoryTxt.getText();
                String smaxInv = maxInventoryTxt.getText();
                String smachineId = machineIdTxt.getText();
                //check if fields are valid
                errorTextFlow.getChildren().clear();
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

                int id = Integer.parseInt(partIdTxt.getText());
                String name = partNameTxt.getText();
                double price = Double.parseDouble(priceTxt.getText());
                int stock = Integer.parseInt(invTxt.getText());
                int min = Integer.parseInt(minInventoryTxt.getText());
                int max = Integer.parseInt(maxInventoryTxt.getText());
                int machineId = Integer.parseInt(machineIdTxt.getText());

                InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);

                Inventory.deletePart(part);
                Inventory.addPart(inHouse);

                //sort all parts list to return to normal order
                Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
                FXCollections.sort(Inventory.getAllParts(), comparator);

                System.out.println("part updated");
            }
            else if(outsourcedBtn.isSelected() && part instanceof InHouse){

                String sname = partNameTxt.getText();
                String sprice = priceTxt.getText();
                String sstock = invTxt.getText();
                String sminInv = minInventoryTxt.getText();
                String smaxInv = maxInventoryTxt.getText();
                String smachineId = machineIdTxt.getText();
                //check if fields are valid
                errorTextFlow.getChildren().clear();
                checkFields.EmptyList();

                //let checkfields know that outsourcedBtn is selected
                isMachineId = false;

                //check input fields to see if they fall in parameters
                ObservableList<Text> errorsList = FXCollections.observableArrayList();
                errorsList = checkFields.CheckFields(partNameTxt.getText(), priceTxt.getText(), invTxt.getText(), minInventoryTxt.getText(), maxInventoryTxt.getText(), machineIdTxt.getText(), isMachineId);

                //if textFlow is not empty throw exception
                if(!(errorsList.isEmpty())){

                    throw new NumberFormatException();

                }

                int id = Integer.parseInt(partIdTxt.getText());
                String name = partNameTxt.getText();
                double price = Double.parseDouble(priceTxt.getText());
                int stock = Integer.parseInt(invTxt.getText());
                int min = Integer.parseInt(minInventoryTxt.getText());
                int max = Integer.parseInt(maxInventoryTxt.getText());
                String machineId = machineIdTxt.getText();

                Outsourced outsourced = new Outsourced(id, name, price, stock, min, max, machineId);

                Inventory.deletePart(part);
                Inventory.addPart(outsourced);

                //sort all parts list to return to normal order
                Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
                FXCollections.sort(Inventory.getAllParts(), comparator);

                System.out.println("part updated");
            }

            ChangeScene(event, "/view/MainForm.fxml");

        }
        catch(NumberFormatException e){
            ObservableList<Text> errorsList = FXCollections.observableArrayList();
            errorsList = checkFields.getTextList();
            
            Text exception = new Text("Could not save part");
            errorsList.add(exception);            

            //ConcurrentModificationException
            for(Text text: errorsList){
                    
                errorTextFlow.getChildren().add(text);
            }
            checkFields.EmptyList();
        }

    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Part part = MainFormController.GetPart();
        
        if(part instanceof InHouse){
                      
            inHouseBtn.setSelected(true);
            machineOrCompanyLbl.setText("Machine ID");
           // outsourcedBtn.setDisable(true);
            System.out.println("inhouse class");
            InHouse inHouse = (InHouse)MainFormController.GetPart();
            
            partIdTxt.setText(String.valueOf(inHouse.getId()));
            partNameTxt.setText(inHouse.getName());
            invTxt.setText(String.valueOf(inHouse.getStock()));
            maxInventoryTxt.setText(String.valueOf(inHouse.getMax()));
            minInventoryTxt.setText(String.valueOf(inHouse.getMin()));
            priceTxt.setText(String.valueOf(inHouse.getPrice()));
            machineIdTxt.setText(String.valueOf(inHouse.getMachineId())); 
            
            MainFormController.ReturnToPart(inHouse);

            
        }
        
        else/*(Part.class.isAssignableFrom(Outsourced.class))*/{
            //FIX ME
            outsourcedBtn.setSelected(true);
            machineOrCompanyLbl.setText("Company Name");
            //inHouseBtn.setDisable(true);
            
            Part outsourcedPart = (Part)MainFormController.GetPart();
            Outsourced outsourced = (Outsourced)outsourcedPart;
                        
            partIdTxt.setText(String.valueOf(outsourced.getId()));
            partNameTxt.setText(outsourced.getName());
            invTxt.setText(String.valueOf(outsourced.getStock()));
            maxInventoryTxt.setText(String.valueOf(outsourced.getMax()));
            minInventoryTxt.setText(String.valueOf(outsourced.getMin()));
            priceTxt.setText(String.valueOf(outsourced.getPrice()));
            machineIdTxt.setText(outsourced.getCompanyName());
            
            MainFormController.ReturnToPart(outsourced);
            

        }

    }    
    
}
