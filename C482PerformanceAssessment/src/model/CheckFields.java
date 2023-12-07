/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Class to check the fields of entry to see if they are valid.
 * @author Joshua Gibson
 */
public class CheckFields {
    
    private ObservableList<Text> textList = FXCollections.observableArrayList();

    /**
     * Checks the fields of a part to see if it is acceptable to be added.  RUNTIME ERROR: originally I tried to return a text flow for this method but it created
     * NullPointerExceptions and IllegalArgumentAcceptions. Tried multiple ways to fix the issue, but I deduced that it was from how text field works with its children.
     * To fix this problem I changed the return to an observable text list that would then be iterated through and added to the text flow object of the specific scene.
     * @param name Name field to be checked. 
     * @param price Price field to be checked. 
     * @param stock Inventory level to be checked. 
     * @param minInv Minimum inventory level to be checked. 
     * @param maxInv Maximum inventory level to be checked. 
     * @param machineId Machine or company name to be checked. 
     * @param isMachineId Tells if the current product is an inhouse product or an outsourced product. 
     * @return Returns a list of errors that may have occurred. 
     */
    public ObservableList<Text> CheckFields(String name, String price, String stock, String minInv, String maxInv, String machineId, boolean isMachineId){
        //check if part name is empty
        if(name == ""){
            Text exceptionText = new Text("No data in name field \n");
            textList.add(exceptionText);
            System.out.println("no data in name field");

        }
        else{
            //check if part name contains a number
            char[] characters = name.toCharArray();
            for(char c: characters){
                if(Character.isDigit(c)){
                    Text exceptionText = new Text ("Please enter a valid name \n");
                    textList.add(exceptionText);
                    break;
                }
            }
        }

        //check if price is empty
        if(price == ""){
            
            Text exceptionText = new Text("Price is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in price field");
            
        }
        else{//check if price contains a letter
            char[] priceChar = price.toCharArray();
            for(char c: priceChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Price is not a double \n");
                    textList.add(exceptionText);
                    break;
                }
            }
        }
        
        //check if stock is empty
        if(stock == ""){

            Text exceptionText = new Text("Stock is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in stock field");

        }
        else{//check if price contains a letter
            char[] stockChar = stock.toCharArray();
            for(char c: stockChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Stock is not an Integer \n");
                    textList.add(exceptionText);
                    break;
                }
            }
        }

        //check if minInv is empty
        if(minInv == ""){

            Text exceptionText = new Text("Minimum Inv is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in min field");

        }
        else{//check if minInv contains a letter
            char[] minInvChar = minInv.toCharArray();
            for(char c: minInvChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Minimum Inv is not an Integer \n");
                    textList.add(exceptionText);
                    break;
                }
            }
        }

        //check if maxInv is empty
        if(maxInv == ""){

            Text exceptionText = new Text("Maximum Inv is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in max field");
          
        }
        else{//check if minInv contains a letter
            char[] maxInvChar = maxInv.toCharArray();
            for(char c: maxInvChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Maximum Inv is not an Integer \n");
                    textList.add(exceptionText);
                    break;
                }
            }
        }
        
        if(isMachineId){
            //check if machinId is empty
            if(machineId == ""){

                Text exceptionText = new Text("Machine Id is empty \n");
                textList.add(exceptionText);
                System.out.println("no data in machineId field");

            }
            else{//check if machineId contains a letter
                char[] machineIdChar = machineId.toCharArray();
                for(char c: machineIdChar){
                    if(Character.isAlphabetic(c)){
                        Text exceptionText = new Text("Machine Id is not an Integer \n");
                        textList.add(exceptionText);
                        break;
                     }
                }
            }
        }
        else{
            if(machineId == ""){

                Text exceptionText = new Text("Company Name is empty \n");
                textList.add(exceptionText);

            }
            else{//check if machineId contains a letter
                char[] machineIdChar = machineId.toCharArray();
                for(char c: machineIdChar){
                    if(Character.isDigit(c)){
                        Text exceptionText = new Text("Please enter a valid Company Name \n");
                        textList.add(exceptionText);
                        break;
                     }
                }
            }
        }
        
        if(Integer.parseInt(minInv) >= Integer.parseInt(maxInv)){
                Text exceptionText = new Text("Min must be less than Max \n");
                textList.add(exceptionText);

        }

        if(Integer.parseInt(stock) > Integer.parseInt(maxInv) || Integer.parseInt(stock) < Integer.parseInt(minInv) || (Integer.parseInt(minInv) == 0 && Integer.parseInt(maxInv) == 0)){
                Text exceptionText = new Text("Inv must be between Min and Max \n");
                textList.add(exceptionText);
        }        

        
        return textList;
    }
    
    //method to check add product text fields

    /**
     * Overloaded method to check the fields of a product to be saved. 
     * @param name Name of the product to be saved. 
     * @param price Price of the product to be saved. 
     * @param stock Inventory level of the product to be saved. 
     * @param minInv Maximum inventory level of the product to be saved. 
     * @param maxInv Minimum inventory level of the product to be saved. 
     * @return Returns a list of errors that may have occurred. 
     */
    public ObservableList<Text> CheckFields(String name, String price, String stock, String minInv, String maxInv){
        //check if part name is empty
        if(name == ""){
            Text exceptionText = new Text("No data in name field \n");
            textList.add(exceptionText);
            System.out.println("no data in name field");

        }
        else{
            //check if part name contains a number
            char[] characters = name.toCharArray();
            for(char c: characters){
                if(Character.isDigit(c)){
                    Text exceptionText = new Text ("Please enter a valid name \n");
                    textList.add(exceptionText);

                }
            }
        }

        //check if price is empty
        if(price == ""){
            
            Text exceptionText = new Text("Price is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in price field");
            

         
        }
        else{//check if price contains a letter
            char[] priceChar = price.toCharArray();
            for(char c: priceChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Price is not a double \n");
                    textList.add(exceptionText);

                }
            }
        }
        
        //check if stock is empty
        if(stock == ""){

            Text exceptionText = new Text("Stock is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in stock field");
            

        }
        else{//check if price contains a letter
            char[] stockChar = stock.toCharArray();
            for(char c: stockChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Stock is not an Integer \n");
                    textList.add(exceptionText);

                }
            }
        }

        //check if minInv is empty
        if(minInv == ""){

            Text exceptionText = new Text("Minimum Inv is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in min field");

          
        }
        else{//check if minInv contains a letter
            char[] minInvChar = minInv.toCharArray();
            for(char c: minInvChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Minimum Inv is not an Integer \n");
                    textList.add(exceptionText);

                }
            }
        }

        //check if maxInv is empty
        if(maxInv == ""){

            Text exceptionText = new Text("Maximum Inv is empty \n");
            textList.add(exceptionText);
            System.out.println("no data in max field");
          
        }
        else{//check if minInv contains a letter
            char[] maxInvChar = maxInv.toCharArray();
            for(char c: maxInvChar){
                if(Character.isAlphabetic(c)){
                    Text exceptionText = new Text("Maximum Inv is not an Integer \n");
                    textList.add(exceptionText);
                }
            }
        }

        if(Integer.parseInt(minInv) >= Integer.parseInt(maxInv)){
                Text exceptionText = new Text("Min must be less than Max \n");
                textList.add(exceptionText);

        }

        if(Integer.parseInt(stock) > Integer.parseInt(maxInv) || Integer.parseInt(stock) < Integer.parseInt(minInv) || (Integer.parseInt(minInv) == 0 && Integer.parseInt(maxInv) == 0)){
                Text exceptionText = new Text("Inv must be between Min and Max \n");
                textList.add(exceptionText);
        }        

        
        return textList;
    }
    
    /**
     *
     */
    public void EmptyList(){
    
        textList.clear();
    
    }
    //NullPointerexception
    //fixed by initializing an new text field object
    //IllegalArgumentException

    /**
     *
     * @return
     */
    public ObservableList<Text> getTextList() {
        return textList;
    }



}
