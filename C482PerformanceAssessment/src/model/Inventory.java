/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Maintained list of all parts and products for the system. 
 * @author Joshua Gibson
 */
public class Inventory {
    
    //Inventory private fields
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    //add methods

    /**
     * Adds a part to the list of available parts. 
     * @param Part Part to be added to the list of available parts. 
     */
    public static void addPart(Part Part){
        allParts.add(Part);
    }
    
    /**
     * Adds a product to the list of available products.
     * @param product Product to be added to the list of available products.
     */
    public static void addProduct(Product product){
        
        if(allProducts.size() == 0){
            product.setId(1);
        }
        else{
            product.setId(allProducts.size() + 1);
        }
        
        allProducts.add(product);
    }
    
    //lookup part methods

    /**
     * Searches for a product based on it's id.
     * @param partid The id of the part being looked up. 
     * @return Returns the part if the id matches an instance in the list of available parts.
     */
    public static Part lookupPart(int partid){
        Part returnpart = null;
        for(int i = 0; i < Inventory.getAllParts().size(); ++i){
            if(allParts.get(i).getId() == partid){
                System.out.println(allParts.get(i));
                
                returnpart = (Part)allParts.get(i);
                               
            }
        }
        return returnpart;
    }
    
    /**
     * Searches for all parts that contain a portion of the search text. 
     * @param Name The text that is looked for in a part name.
     * @return Returns a part that contains the search text in it's name field. 
     */
    public static ObservableList<Part> lookupPart(String Name){
        ObservableList<Part> searchPart = FXCollections.observableArrayList();
        
        for(int i = 0; i < allParts.size(); ++i){
            if(allParts.get(i).getName().toLowerCase().contains(Name.toLowerCase())){
                
                searchPart.add(allParts.get(i));
                
            }
        }
        
        return searchPart;
    }
    
    //lookup Product methods

    /**
     * Searches for a product based on it's id. 
     * @param productid The id of the product being searched. 
     * @return Returns the product that matches the search id.
     */
    
    public static Product lookupProduct(int productid){
        
        Product returnproduct = null;
        for(int i = 0; i < Inventory.getAllProducts().size(); ++i){
            if(allProducts.get(i).getId() == productid){
                System.out.println(allProducts.get(i));
                
                returnproduct = (Product)allProducts.get(i);
                               
            }
        }
        return returnproduct;
        
    }
    
    /**
     * Searches for a product based on its name.
     * @param productName The string that is compared to a products name. 
     * @return Returns a list of products that contain the search text. 
     */
    public static ObservableList<Product> lookupProduct(String productName){
        
        ObservableList<Product> searchProduct = FXCollections.observableArrayList();
        
        for(int i = 0; i < allProducts.size(); ++i){
            if(allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())){
                
                searchProduct.add(allProducts.get(i));
                
            }
        }
        
        return searchProduct;
    }
    
    //update a part or product methods

    /**
     * Updates a product based on its index in the list of parts. 
     * @param index The index of the part to be updated. 
     * @param selectedPart The updated part whose values will replace the old part. 
     */
    public static void updatePart(int index, Part selectedPart){
        
        allParts.get(index).setMax(selectedPart.getMax());
        allParts.get(index).setMin(selectedPart.getMin());
        allParts.get(index).setMax(selectedPart.getMax());
        allParts.get(index).setName(selectedPart.getName());
        allParts.get(index).setPrice(selectedPart.getPrice());
        allParts.get(index).setStock(selectedPart.getStock());
        
   }
    
    /**
     * Updates a product in the list of products.
     * @param index The index of the product to be updated.
     * @param newProduct The updated product whose values will replace the old product.
     */
    public static void updateProduct(int index, Product newProduct){
        
        //set fields of product in all products for the chosen product with values from the new product
        allProducts.get(index).setMax(newProduct.getMax());
        allProducts.get(index).setMin(newProduct.getMin());
        allProducts.get(index).setMax(newProduct.getMax());
        allProducts.get(index).setName(newProduct.getName());
        allProducts.get(index).setPrice(newProduct.getPrice());
        allProducts.get(index).setStock(newProduct.getStock());
        

    }
    
    //delete a part or product methods

    /**
     * Removes a part from the list of parts that are available. 
     * @param selectedPart The part that is to be removed.
     * @return Returns true if the part was successfully removed.
     */
    public static boolean deletePart(Part selectedPart){
        
        for(int i = 0; i < Inventory.getAllParts().size(); ++i){
            if(selectedPart.getId() == Inventory.getAllParts().get(i).getId()){
                Part part = Inventory.lookupPart(selectedPart.getId());
        
                allParts.remove(part);
                System.out.println("part successfully deleted");
                return true;
           
            }

        }
            return false;
    }
    
    /**
     * Removes a product from the list of available products. 
     * @param selectedProduct The product that is to be removed. 
     * @return Returns true if the product was successfully removed. 
     */
    public static boolean deleteProduct(Product selectedProduct){
        
        for(int i = 0; i < Inventory.getAllProducts().size(); ++i){
            if(selectedProduct.getId() == Inventory.getAllProducts().get(i).getId()){
                Product product = Inventory.lookupProduct(selectedProduct.getId());
                
                allProducts.remove(product);
                System.out.println("product successfully deleted");
                return true;
            }
        }

        return false;
        
    }
    
    //get parts and product methods

    /**
     * Gets all of the available parts that have been added to the system.
     * @return Returns a list of all the available parts.
     */
    public static ObservableList<Part> getAllParts(){
        
        return allParts;
        
    }
    
    /**
     * Gets all of the available products that have been added to the system.
     * @return Returns the list of all available products. 
     */
    public static ObservableList<Product> getAllProducts(){
        
        return allProducts;
        
    }

}
