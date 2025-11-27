/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Represents a basket equipment that can hold products.
 * Extends the Equipment class with a capacity of 15 items.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
public class Basket extends Equipment {
    /**
     * Constructs a Basket with default capacity of 15 items.
     */
    public Basket() {
        super(15); // Basket holds 15 items
    }
}