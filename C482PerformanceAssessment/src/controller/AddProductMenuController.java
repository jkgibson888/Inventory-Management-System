/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import model.CheckFields;
import model.Inventory;
import model.Part;
import model.Product;


/**
 * FXML Controller class
 * Adds a product to the product list. FUTURE ENHANCEMENT Add a timestamp to document creation and edits of a part. The project demonstration had the search as typing something an then pressing enter to search.  My implementation removes this step and automatically performs a search when a key is pressed. 
 * @author Joshua Gibson
 */
public class AddProductMenuController implements Initializable {
    

        
                //Method to switch scenes
    
    Stage stage;
    Parent scene;
    
    /**
     * Changes the scene based on a button event.
     * @param event The button event that triggers the change of scene. 
     * @param scenestring The locationjav of the new scene to be displayed. 
     * @throws IOException
     */
    public void ChangeScene(ActionEvent event, String scenestring) throws IOException{
        
        //System.out.println("attempting to initialize");
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource(scenestring));
        stage.setScene(new Scene(scene));
        stage.show(); 
    }
    
    //populate a table view method

    /**
     * Populates a table with a list of parts. 
     * @param partList The observable list to be displayed. 
     * @param tableView The specific table view that will be populated. 
     * @param Column1 The first column of the table view. 
     * @param Column2 The second column of the table view. 
     * @param Column3 The third column of the table view.
     * @param Column4 The fourth column of the table view. 
     */
    
    public void PopulateTable(ObservableList<Part> partList, TableView<Part> tableView, TableColumn<Part, Integer> Column1, TableColumn<Part, String> Column2, TableColumn<Part, Integer> Column3, TableColumn<Part, Double> Column4){
        
        tableView.setItems(partList);
        
        Column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        Column2.setCellValueFactory(new PropertyValueFactory<>("name"));
        Column3.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Column4.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    //get all parts from Inventory to be used to populate the allParts table
    private ObservableList<Part> thisPartList = Inventory.getAllParts();
    
    //blank part to be added
    Product product = new Product(0, null, 0, 0, 0, 0);
    
            //fx:id labels
    
    //text field fx:id's
    @FXML
    private TextField partIdTxt;
    @FXML
    private TextField partNameTxt;
    @FXML
    private TextField inventoryLvlTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField searchBarTxt;
    
    //all parts table fx:id's
    @FXML
    private TableView<Part> allPartsTableView;
    @FXML
    private TableColumn<Part, Integer> partListIdCol;
    @FXML
    private TableColumn<Part, String> partListPartNameCol;
    @FXML
    private TableColumn<Part, Integer> partListInvCol;
    @FXML
    private TableColumn<Part, Double> partsListPriceCol;
    
    //added parts list table fx:id's
    @FXML
    private TableView<Part> partsListTableView;
    @FXML
    private TableColumn<Part, Integer> addedPartIdCol;
    @FXML
    private TableColumn<Part, String> addedPartNameCol;
    @FXML
    private TableColumn<Part, Integer> addedPartInvCol;
    @FXML
    private TableColumn<Part, Double> addPartPriceCol;
    
    //text flow id
    @FXML
    private TextFlow textFlow;

    /**
     * Adds a part to the associated parts list for a product. 
     * @param event The event of the add part button being pressed. 
     */
    @FXML
    private void onActionAddPartsToListBtn(ActionEvent event) {
       
        try{
            /*
                Get the currently selected row
                and add it to the Products associated array part list
            */

            TableViewSelectionModel<Part> selectionModel = allPartsTableView.getSelectionModel();
            selectionModel.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Part> selectedItem = selectionModel.getSelectedItems();
            Part part = selectedItem.get(0);

            product.addAssociatedPart(part);

            System.out.println(part + " was added");

            /*
                Remove added part from the all parts list
            */

            thisPartList.remove(part);

            /*
                remove selected part from all parts table
            */

            selectionModel.clearSelection();

            //sort associated parts list        
            partsListTableView.getSortOrder().add(partListIdCol);

            /*
                repopulate tables
            */

            PopulateTable(product.getAllAssociatedParts(), partsListTableView, addedPartIdCol, addedPartNameCol, addedPartInvCol, addPartPriceCol);
            PopulateTable(thisPartList, allPartsTableView, partListIdCol, partListPartNameCol, partListInvCol, partsListPriceCol);
        }
        catch(IndexOutOfBoundsException  e){
            
            //clear texflow if there is anything in there from previous errors
            textFlow.getChildren().clear();
            
            //error message if no part is selected
            Text exceptionText = new Text("No Part selected.");
            
            textFlow.getChildren().add(exceptionText);
            
        }
        

    }

    /**
     * Removes a part from the associated parts list for a product. 
     * @param event The event of the remove parts button being selected. 
     */
    @FXML
    private void onActionRemovePart(ActionEvent event) {
        
        try{
            //on remove associated button press Dialogue box presented to confirm deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete the part, do you want to continue?");

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK){
                /*
                    Get the currently selected row
                    and add it back to all parts table
                */

                TableViewSelectionModel<Part> selectionModel = partsListTableView.getSelectionModel();
                selectionModel.setSelectionMode(SelectionMode.SINGLE);
                ObservableList<Part> selectedItem = selectionModel.getSelectedItems();
                Part part = selectedItem.get(0);
                product.deleteAssociatedPart(part);

                System.out.println(part + " was deleted");

                PopulateTable(product.getAllAssociatedParts(), partsListTableView, addedPartIdCol, addedPartNameCol, addedPartInvCol, addPartPriceCol);

                /*
                    Add part back to parts list
                */

                thisPartList.add(part);

                        /*
                    Update parts list table
                */

                selectionModel.clearSelection();

                        //sort all parts table
                allPartsTableView.getSortOrder().add(partListIdCol);
            }
        }
        catch(IndexOutOfBoundsException  e){
            
            //clear texflow if there is anything in there from previous errors
            textFlow.getChildren().clear();
            
            //error message if no part is selected
            Text exceptionText = new Text("No Part selected.");
            
            textFlow.getChildren().add(exceptionText);
            
        }
    }

    //checkfield object created
    private CheckFields checkFields = new CheckFields();
    
    /**
     * Saves a product and its associated parts list. 
     * @param event The event of the save button being pressed. RUNTIME ERROR Had problems with sorting the list.  Utilized a comparator to fix this problem. 
     * @throws IOException
     */
    @FXML
    private void onActionSavePartsList(ActionEvent event) throws IOException {
        
        try{
            //add product to product list        
            int id = 0;
            if(!Inventory.getAllProducts().isEmpty()){

                id = 1;

            }
            else{
                id = Inventory.getAllProducts().size() + 1;
            }

            //get text fields to check
            String sname = partNameTxt.getText();
            String sprice = priceTxt.getText();
            String sstock = inventoryLvlTxt.getText();
            String sminInv = minTxt.getText();
            String smaxInv = maxTxt.getText();
            
            //check if fields are valid
            textFlow.getChildren().clear();
            checkFields.EmptyList();


            //check input fields to see if they fall in parameters
            ObservableList<Text> errorsList = FXCollections.observableArrayList();
            errorsList = checkFields.CheckFields(partNameTxt.getText(), priceTxt.getText(), inventoryLvlTxt.getText(), minTxt.getText(), maxTxt.getText());

            //if textFlow is not empty throw exception
            if(!(errorsList.isEmpty())){

                throw new NumberFormatException();

            }
            
            String name = partNameTxt.getText();
            double price = Double.parseDouble(priceTxt.getText());
            int stock = Integer.parseInt(inventoryLvlTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());

            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setMin(min);
            product.setMax(max);
            product.setStock(stock);


            Inventory.addProduct(product);

            //check associated parts
            System.out.println(Inventory.getAllProducts().get(3).getAllAssociatedParts().get(0));
            System.out.println(Inventory.getAllProducts().get(3).getAllAssociatedParts().get(1));

            //return added parts back to all parts list
            for(Part part: product.getAllAssociatedParts()){
                Inventory.addPart(part);
                System.out.println("part readded again");
            }

            //sort all parts list to return to normal order
            Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
            FXCollections.sort(Inventory.getAllParts(), comparator);

            //check that parts reordered correctly
            System.out.println(Inventory.getAllParts().get(0).getName());
            System.out.println(Inventory.getAllParts().get(1).getName());

            //return to MainForm
            ChangeScene(event, "/view/MainForm.fxml");
        }
        catch(NumberFormatException e){
            
            ObservableList<Text> errorsList = FXCollections.observableArrayList();
            errorsList = checkFields.getTextList();
            
            Text exception = new Text("Could not save product");
            errorsList.add(exception);            

            //ConcurrentModificationException
            for(Text text: errorsList){
                    
                        textFlow.getChildren().add(text);
                }
            checkFields.EmptyList();
            
        }
    }

    /**
     * Returns to the Main Form of the application restoring the list of all parts and reorders them. 
     * @param event
     * @throws IOException
     */
    @FXML
    private void onActionReturnToPartsMenu(ActionEvent event) throws IOException {
        
        // remove parts from product list and return them to the all parts list
        for(Part part: product.getAllAssociatedParts()){
            Inventory.addPart(part);
            System.out.println("part readded again");
        }
                
        //sort all parts list to return to normal order
        Comparator<Part> comparator = Comparator.comparingInt(Part::getId);
        FXCollections.sort(Inventory.getAllParts(), comparator);
        
        //return to MainForm
        ChangeScene(event, "/view/MainForm.fxml");
        
    }
    
    /**
     * Searches for a part when a key is pressed. 
     * @param event The event of a key being pressed. RUNTIME ERROR Problem occurred where search didn't return the correct value. Discovered it was because of the for loop in the search method.  Fixed it by deleting -1 from "the length - 1" I originally had in the for loop. 
     */
    @FXML
    void partSearchTxtField(KeyEvent event) {
        //FIX ME
        //searches for part when text field is not empty
        Part part = null;
        if(searchBarTxt.getLength()!= 0){
            
            String string = searchBarTxt.getText();
            
                //if string starts with a number use number search method otherwise use string search method
                if(Character.isDigit(string.charAt(0))){
                    //partsTableView.setItems((ObservableList<Part>)Inventory.lookupPart(Integer.parseInt(partSearchTxt.getText())));
                    part = Inventory.lookupPart(Integer.parseInt(searchBarTxt.getText()));

                    ObservableList<Part> searchParts = FXCollections.observableArrayList();
                    searchParts.add(part);


                    PopulateTable(searchParts, allPartsTableView, partListIdCol, partListPartNameCol, partListInvCol, partsListPriceCol);
            }
            else{
                
                ObservableList<Part> stringSearch = Inventory.lookupPart(string);
                
                PopulateTable(stringSearch, allPartsTableView, partListIdCol, partListPartNameCol, partListInvCol, partsListPriceCol);
                
            }
        }
        else{
            
            //returns view to full parts list when textfield is empty
            PopulateTable(Inventory.getAllParts(), allPartsTableView, partListIdCol, partListPartNameCol, partListInvCol, partsListPriceCol);
        }

    }
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initiallize tables
        PopulateTable(product.getAllAssociatedParts(), partsListTableView, addedPartIdCol, addedPartNameCol, addedPartInvCol, addPartPriceCol);
        PopulateTable(thisPartList, allPartsTableView, partListIdCol, partListPartNameCol, partListInvCol, partsListPriceCol);
    }    

}
