/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * FXML Controller class
 *
 * @author Joshua Gibson
 */
public class MainFormController implements Initializable {
        

    
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
    
        //populate part table view method

    /**
     * Populates the part table with a list of parts. 
     * @param partList The observable list to be displayed. 
     * @param tableView The specific table view that will be populated. 
     * @param Column1 The first column of the table view. 
     * @param Column2 The second column of the table view. 
     * @param Column3 The third column of the table view.
     * @param Column4 The fourth column of the table view. 
     */
    
    public void PopulatePartTable(ObservableList<Part> partList, TableView<Part> tableView, TableColumn<Part, Integer> Column1, TableColumn<Part, String> Column2, TableColumn<Part, Integer> Column3, TableColumn<Part, Double> Column4){
        
        tableView.setItems(partList);
        
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Column4.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
            //populate product table view method

    /**
     * Populates the product table with a list of products. 
     * @param partList The observable list to be displayed. 
     * @param tableView The specific table view that will be populated. 
     * @param Column1 The first column of the table view. 
     * @param Column2 The second column of the table view. 
     * @param Column3 The third column of the table view.
     * @param Column4 The fourth column of the table view. 
     */
    
    public void PopulateProductTable(ObservableList<Product> partList, TableView<Product> tableView, TableColumn<Product, Integer> Column1, TableColumn<Product, String> Column2, TableColumn<Product, Integer> Column3, TableColumn<Product, Double> Column4){
        
        tableView.setItems(partList);
        
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Column4.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    private static ObservableList<Part> thePartsList = null;
    
                    //fx:id labels
    //input field id's
    @FXML
    private TextField partSearchTxt;
    
    @FXML
    private TextField productSearchTxt;
    
    //text field to output errors
    @FXML
    private TextFlow errorTextFlow;
    
    //parts table id's
    @FXML
    private TableView<Part> partsTableView;
    
    @FXML
    private TableColumn<Part, Integer> partsPartIdCol;
    
    @FXML
    private TableColumn<Part, String> partsPartNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInventorylvlCol;

    @FXML
    private TableColumn<Part, Double> partsPriceCol;


    //product table id's
    @FXML
    private TableView<Product> productsTableView;
    
    @FXML
    private TableColumn<Product, Integer> productsPartIdCol;

    @FXML
    private TableColumn<Product, String> productsPartNameCol;
    
    @FXML
    private TableColumn<Product, Integer> productsInventoryCol;

    @FXML
    private TableColumn<Product, Double> productsPriceCol;

    //Button Action Handlers
    //add part or product handlers

    /**
     * Changes to the add part scene. 
     * @param event The event of the add part button beign selected. 
     * @throws IOException
     */
    @FXML
    void onActionAddPartBtn(ActionEvent event) throws IOException {
        
      ChangeScene(event, "/view/AddPartMenu.fxml");

    }
        
    /**
     * Changes to the add product scene. 
     * @param event The event of the add product button being selected. 
     * @throws IOException
     */
    @FXML
    void onActionAddProductBtn(ActionEvent event) throws IOException {
        
        ChangeScene(event, "/view/AddProductMenu.fxml");
            
    }

    //delete part or product handlers

    /**
     * Deletes a part from the parts list. RUNTIME ERROR Error message wouldn't initially display correctly in a text area object in the scene. Changed the display area to a text flow and using internet resources fixed the problem. 
     * @param event The event of the delete part button being selected. 
     */
    @FXML
    void onActionDeletePartBtn(ActionEvent event) {
        
        //on delete button press Dialogue box presented to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the part, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        try{
            if(result.isPresent() && result.get() == ButtonType.OK){

                //delete part from parts table
                TableView.TableViewSelectionModel<Part> selectionModel = partsTableView.getSelectionModel();
                selectionModel.setSelectionMode(SelectionMode.SINGLE);
                ObservableList<Part> selectedItem = selectionModel.getSelectedItems();
                Part part = selectedItem.get(0);

                Inventory.deletePart(part);

            }
        }
        catch(IndexOutOfBoundsException  e){
            
            //clear texflow if there is anything in there from previous errors
            errorTextFlow.getChildren().clear();
            
            //error message if no part is selected
            Text exceptionText = new Text("No Part selected.");
            
            errorTextFlow.getChildren().add(exceptionText);
            
        }
    }

    /**
     * Deletes a product from the product list. 
     * @param event The event of the delete product button being selected. 
     */
    @FXML
    void onActionDeleteProductBtn(ActionEvent event) {
        
        //on delete button press Dialogue box presented to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the product, do you want to continue?");
        
        Optional<ButtonType> result = alert.showAndWait();
        
        try{
            if(result.isPresent() && result.get() == ButtonType.OK){

                //delete product from products table
                TableView.TableViewSelectionModel<Product> selectionModel = productsTableView.getSelectionModel();
                selectionModel.setSelectionMode(SelectionMode.SINGLE);
                ObservableList<Product> selectedItem = selectionModel.getSelectedItems();
                Product product = selectedItem.get(0);

                //error message if product still has parts associated with it
                if(product.getAllAssociatedParts().isEmpty()){
                    Inventory.deleteProduct(product);
                }
                else{

                    //clear texflow if there is anything in there from previous errors
                    errorTextFlow.getChildren().clear();

                    Text error = new Text("Product has associated parts, cannot be deleted");

                    errorTextFlow.getChildren().add(error);
                }
            }
        }
        catch(IndexOutOfBoundsException  e){
            
            //clear texflow if there is anything in there from previous errors
            errorTextFlow.getChildren().clear();
            
            //error message if no product is selected
            Text exceptionText = new Text("No Product selected.");
            
            errorTextFlow.getChildren().add(exceptionText);
            
        }
    }

    /**
     * Exits the program. 
     * @param event The event of the exit button being selected. 
     */
    @FXML
    void onActionExit(ActionEvent event) {
        
        System.exit(0);
    
    }

    /*private static Part passPart;
    public static Part returnPart(){
            return passPart;
        }*/
    
    
    private static Part modifyPart = null;

    /**
     * Method to pass the part to the next controller. 
     * @return Returns the selected part to the next controller and scene. 
     */
    public static Part GetPart(){
        return modifyPart;
    }

    /**
     * Sets the part in the part list to the modified part. 
     * @param part The part with the updated fields. 
     */
    public static void ReturnToPart(Part part){
        modifyPart = (Part)part;
    }
       
    /**
     * Changes to the modify part scene. 
     * @param event The event of the modify part button being selected. 
     * @throws IOException
     */
    @FXML
    void onActionModifyPartBtn(ActionEvent event) throws IOException {
        
        try{
            //get selected part and set it to modifyPart to be retrieved by modify controller
            TableView.TableViewSelectionModel<Part> selectionModel = partsTableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Part> selectedItem = selectionModel.getSelectedItems();
            modifyPart = (Part)selectedItem.get(0);
            System.out.println(modifyPart);
            ChangeScene(event, "/view/ModifyPartMenu.fxml");
        }
        catch(IndexOutOfBoundsException  e){
            
            //clear texflow if there is anything in there from previous errors
            errorTextFlow.getChildren().clear();
            
            //error message if no part is selected
            Text exceptionText = new Text("No Part selected.");
            
            errorTextFlow.getChildren().add(exceptionText);
            
        }

    }
    
    
        //field and method to pass selected product to next screen
        private static Product modifyProduct = null;

    /**
     * Method to pass the product to the next controller. 
     * @return Returns the product to be passed on to the next controller. 
     */
    public static Product GetProduct(){
            return modifyProduct;
        }
    
    /**
     * Changes to the modify product scene. 
     * @param event The event of the modify product button being selected. 
     * @throws IOException
     */
    @FXML
    void onActionModifyProductBtn(ActionEvent event) throws IOException {
        try{
            //get selected product and set it to modifyProduct to be retrieved by modify controller
            TableView.TableViewSelectionModel<Product> selectionModel = productsTableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Product> selectedItem = selectionModel.getSelectedItems();
            modifyProduct = selectedItem.get(0);
            System.out.println(modifyProduct);

            ChangeScene(event, "/view/ModifyProductMenu.fxml");
        }
        catch(IndexOutOfBoundsException  e){
                        
            //clear texflow if there is anything in there from previous errors
            errorTextFlow.getChildren().clear();
            
            //error message if no part is selected
            Text exceptionText = new Text("No Product selected.");
            
            errorTextFlow.getChildren().add(exceptionText);
            
        }

    }
    
    /**
     * Searches for a part when a key is pressed. 
     * @param event The event of a key being pressed. RUNTIME ERROR Problem occurred where search didn't return the correct value. Discovered it was because of the for loop in the search method.  Fixed it by deleting -1 from "the length - 1" I originally had in the for loop. 
     */
    @FXML
    void searchPartTxt(KeyEvent event) {
        //FIX ME add NullPointerException
        //searches for part when text field is not empty
        Part part = null;
        if(partSearchTxt.getLength()!= 0){
            
            String string = partSearchTxt.getText();
            
                //if string starts with a number use number search method otherwise use string search method
                if(Character.isDigit(string.charAt(0))){
                    //partsTableView.setItems((ObservableList<Part>)Inventory.lookupPart(Integer.parseInt(partSearchTxt.getText())));
                    part = Inventory.lookupPart(Integer.parseInt(partSearchTxt.getText()));

                TableView.TableViewSelectionModel<Part> selectionModel = partsTableView.getSelectionModel();
                //selectionModel.setSelectionMode(SelectionMode.SINGLE);
                selectionModel.select(part);
                
                System.out.println(part.getId());
            }
            else{
                
                ObservableList<Part> stringSearch = Inventory.lookupPart(string);
                
                PopulatePartTable(stringSearch, partsTableView, partsPartIdCol, partsPartNameCol, partsInventorylvlCol, partsPriceCol);
                
            }
        }
        else{
            
            //returns view to full parts list when textfield is empty
            PopulatePartTable(Inventory.getAllParts(), partsTableView, partsPartIdCol, partsPartNameCol, partsInventorylvlCol, partsPriceCol);
        }

    }

    /**
     * Searches for a product when a key is pressed. 
     * @param event The event of a key being pressed. RUNTIME ERROR Problem occurred where search didn't return the correct value. Discovered it was because of the for loop in the search method.  Fixed it by deleting -1 from "the length - 1" I originally had in the for loop. 
     */
    @FXML
    void searchProductsTxt(KeyEvent event) {
        
                //searches for product when text field is not empty
        Product product = null;
        if(productSearchTxt.getLength()!= 0){
            
            String string = productSearchTxt.getText();
            
            //if string starts with a number use number search method otherwise use string search method
            if(Character.isDigit(string.charAt(0))){
                
                //partsTableView.setItems((ObservableList<Part>)Inventory.lookupPart(Integer.parseInt(partSearchTxt.getText())));
                product = Inventory.lookupProduct(Integer.parseInt(productSearchTxt.getText()));

                //change to highlight part
                TableView.TableViewSelectionModel<Product> selectionModel = productsTableView.getSelectionModel();
                //selectionModel.setSelectionMode(SelectionMode.SINGLE);
                selectionModel.select(product);
               //productsTableView.set
            }
            else{
                
                ObservableList<Product> stringSearch = Inventory.lookupProduct(string);
                
                PopulateProductTable(stringSearch, productsTableView, productsPartIdCol, productsPartNameCol, productsInventoryCol, productsPriceCol);
            }
        }
        else{
            
            //returns view to full parts list when textfield is empty
            PopulateProductTable(Inventory.getAllProducts(), productsTableView, productsPartIdCol, productsPartNameCol, productsInventoryCol, productsPriceCol);
        }


    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        thePartsList = Inventory.getAllParts();
        
        //intialize the parts table
        PopulatePartTable(Inventory.getAllParts(), partsTableView, partsPartIdCol, partsPartNameCol, partsInventorylvlCol, partsPriceCol);
        
        //intialize the products table
        PopulateProductTable(Inventory.getAllProducts(), productsTableView, productsPartIdCol, productsPartNameCol, productsInventoryCol, productsPriceCol);

    }    
    
}
