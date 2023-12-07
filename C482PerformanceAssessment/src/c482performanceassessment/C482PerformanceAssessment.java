/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package c482performanceassessment;


import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import model.InHouse;
import model.Outsourced;

/**
 *
 * @author Joshua Gibson
 */
public class C482PerformanceAssessment extends Application{

    //javadoc location: C482PerformanceAssessment/javadoc/index.html
    
    /**
     * Starts the program.  FUTURE ENCHANCEMENT Adding a person class and login scene to track who has made changes and when.
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        //create intial parts for testing
        InHouse part1 = new InHouse(1, "IHSeat", 5.65, 3, 1, 10, 5523);
        InHouse part2 = new InHouse(2, "IHHandle Bars", 3.58, 2, 1, 8, 5560);
        InHouse part3 = new InHouse(3, "IHChain", 1.36, 5, 2, 7, 5528);
        
        Outsourced part4 = new Outsourced(4, "OSSeat", 5.65, 3, 1, 10, "Acme");
        Outsourced part5 = new Outsourced(5, "OSHandle Bars", 3.58, 2, 1, 8, "Ball");
        Outsourced part6 = new Outsourced(6, "OSChain", 1.36, 5, 2, 7, "South");
        
        
        Product product1 = new Product(1, "Sleek Bike", 85.99, 4, 1, 7);
        Product product2 = new Product(2, "Mountain Bike", 135.66, 5, 3, 10);
        Product product3 = new Product(3, "Racing Bike", 256.54, 2, 1, 5);
        
       
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);
        
        product1.addAssociatedPart(part1);
        product1.addAssociatedPart(part2);
        product2.addAssociatedPart(part3);
        product2.addAssociatedPart(part4);
        product3.addAssociatedPart(part5);
        product3.addAssociatedPart(part6);
        
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);

        
        launch(args);

    }
    
    
    @Override
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
