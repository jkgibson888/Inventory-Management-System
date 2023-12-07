/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Class to create inhouse parts.
 * @author Joshua Gibson
 */
public class InHouse extends Part{
    
    //InHouse private field
    private int machineId;
    
    //InHouse constructor

    /**
     * Creates an object for an inhouse part. 
     * @param id Id of the inhouse part. 
     * @param name Name of the inhouse part. 
     * @param price Price of the inhouse part. 
     * @param stock Current inventory level of the inhouse part.
     * @param min Minimum inventory of the inhouse part.
     * @param max Maximum inventory of the inhouse part. 
     * @param machineId Id of the machine used to make the inhouse part. 
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    //setter for InHouse private field

    /**
     * Sets the machine id for the inhouse product.
     * @param machineId Machine id to be set. 
     */

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
    //getter for InHouse private field

    /**
     * Gets the machine id for a part. 
     * @return Returns the machine id for an inhouse part. 
     */

    public int getMachineId() {
        return machineId;
    }
    
}
