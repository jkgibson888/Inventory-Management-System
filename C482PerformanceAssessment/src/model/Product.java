/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Creates an object instance of a Product. 
 * @author Joshua Gibson
 */
public class Product {

       
    //Product private fields
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    //constructor for Product

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    //setters for Product field

    /**
     * Set the id of the product object. 
     * @param id Id to be set within the object. 
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the name of the product object. 
     * @param name Name to be set within the object. 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the price of the product object. 
     * @param price Price to be set within the object. 
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the current inventory level of the product. 
     * @param stock Inventory level to be set within the object. 
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Sets the minimum inventory of the product. 
     * @param min Minimum inventory to be set within the object. 
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum inventory of the product.
     * @param max Maximum inventory to be set within the object. 
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    //getters for Product fields

    /**
     * Gets the id of the product.
     * @return Returns the id of the product. 
     */

    public int getId() {
        return id;
    }

    /**
     * Gets the name of the product. 
     * @return Returns the name of the product. 
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product. 
     * @return Returns the price of the product. 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the current inventory level of the product.
     * @return Returns the current inventory level of the product.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Get the minimum level of inventory for the product. 
     * @return Returns the minimum level of inventory for the product. 
     */
    public int getMin() {
        return min;
    }

    /**
     * Gets the maximum inventory of the product. 
     * @return Returns the maximum inventory of the product.
     */
    public int getMax() {
        return max;
    }
    
    //Add a part to the associated parts list

    /**
     * Adds a part to the list of parts that are associated with the product.
     * @param part The part to be added to the associated parts list.
     */
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    

    /**
     * Deletes a part from the list of associated parts for the product. 
     * @param part The part that is to be deleted from the list of associated parts for the product.
     * @return Returns true if the product was deleted. 
     */
    
    public boolean deleteAssociatedPart(Part part){
        
       associatedParts.remove(part);
       
       return true;
       
    }
    
    
    //get all associated parts from associatedParts Observable List

    /**
     * Gets the list of associated parts for the product.
     * @return Returns the list of associated parts for the product.
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }
    
}
