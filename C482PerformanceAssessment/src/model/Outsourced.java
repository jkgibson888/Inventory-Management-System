/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Class to create outsourced products. 
 * @author Joshua Gibson
 */
public class Outsourced extends Part{
    
    //Outsourced private field
    private String companyName;
    
    //constructor for Outsourced

    /**
     * Creates an object for an outsourced part. 
     * @param id Id of the outsourced part.
     * @param name Name of the outsourced part. 
     * @param price Price of the outsourced part.
     * @param stock Current inventory level of the part. 
     * @param min Minimum inventory level of the part.
     * @param max Maximum inventory level of the part.
     * @param companyName Company name that produces the part. 
     */

    public Outsourced( int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    //setter for Outsourced private field

    /**
     * Sets the company name for the outsourced part.
     * @param companyName The name to be set. 
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    //getter for Outsourced private field

    /**
     * Gets the company name that makes the outsourced part. 
     * @return The company that produces the outsourced part. 
     */

    public String getCompanyName() {
        return companyName;
    }
    
    
}
